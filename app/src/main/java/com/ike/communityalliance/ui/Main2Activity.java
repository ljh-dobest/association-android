package com.ike.communityalliance.ui;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.MainPageAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.server.broadcast.BroadcastManager;
import com.ike.communityalliance.ui.activity.ChatPopupWindow;
import com.ike.communityalliance.ui.activity.LoginActivity;
import com.ike.communityalliance.ui.activity.NewFriendListActivity;
import com.ike.communityalliance.ui.activity.RecommendActivity;
import com.ike.communityalliance.ui.fragment.HomeFragment;
import com.ike.communityalliance.ui.fragment.MineFragment;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.communityalliance.utils.file.PermissionsUtil;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;

import static com.ike.mylibrary.util.L.isDebug;


public class Main2Activity extends BaseActivity implements  ViewPager.OnPageChangeListener {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.main_vp)
    ViewPager main_vp;
    @BindView(R.id.rl_main_header)
    RelativeLayout rl_main_header;
    private int checkedcolor = 0xffff3300;
    private RadioButton radioButtons[];
    private List<Fragment> fragments;
    @BindView(R.id.iv_main_recommed)
    ImageView iv_main_recomend;
    @BindView(R.id.iv_main_chat_more)
    ImageView iv_main_chat_more;
    @BindView(R.id.tv_main_title)
    TextView tv_main_title;
    private ChatPopupWindow chatPopupWindow;
    private MainPageAdapter adapter;
    /**
     * 会话列表的fragment
     */
    private Fragment mConversationListFragment = null;
    private Context mContext;
    public static final String EXIT = "EXIT";
    private Conversation.ConversationType[] mConversationsTypes = null;
    private Integer[] images={R.drawable.main_home_bg,R.drawable.main_chat_bg,R.drawable.main_mine_bg};
    private String[] names={"首页","聊天","我的"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        ButterKnife.bind(this);
        PermissionsUtil.initPermissions(this, Manifest.permission.CAMERA);
        mContext = this;
        isDebug = getSharedPreferences("config", MODE_PRIVATE).getBoolean("isDebug", false);
       getLocationParserData(this,"data.txt");
        if (RongIM.getInstance() != null &&
                RongIM.getInstance().getCurrentConnectionStatus()
                        .equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    init();
                    if (RongIM.getInstance() != null && RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                        reconnect();
                    }
                }
            }, 100);
        } else {
            init();
        }
        EventBus.getDefault().register(this);
    }

    private void init() {
        chatPopupWindow = new ChatPopupWindow(mContext);
        Fragment conversationList = initConversationList();
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(conversationList);
        fragments.add(new MineFragment());
        adapter=new MainPageAdapter(getSupportFragmentManager(),fragments);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        main_vp.setAdapter(adapter);
        main_vp.addOnPageChangeListener(this);
        main_vp.setCurrentItem(0);
        tabLayout.setupWithViewPager(main_vp,true );
        initTab();
        initData();
    }
    private void initTab() {
        int tabCount = tabLayout.getTabCount();//获取TabLayout的个数
        for (int i=0; i<tabCount; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.tab_item,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
            imageView.setImageDrawable(getResources().getDrawable(images[i]));
            TextView textView = (TextView) view.findViewById(R.id.tv_msg);
            textView.setText(names[i]);
            TabLayout.Tab tab = tabLayout.getTabAt(i);////获取TabLayout的子元素Tab
            tab.setCustomView(view);//设置TabLayout的子元素Tab的布局View
        }
    }
    protected void initData() {

        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

//        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
        getConversationPush();// 获取 push 的 id 和 target
        getPushMessage();
        BroadcastManager.getInstance(mContext).addAction(EXIT, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences.Editor editor = getSharedPreferences("config", MODE_PRIVATE).edit();
                editor.putBoolean("exit", true);
                editor.putString("loginToken", "");
                editor.putString("loginid", "");
                editor.apply();
                RongIM.getInstance().logout();
                context.unregisterReceiver(this);
                Main2Activity.this.finish();
                try {
                    Thread.sleep(500);
                    Process.killProcess(Process.myPid());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //获取省、市、区（县）地理数据
    public void getLocationParserData(final Context mComtext, final String fileName){
        new Thread(new Runnable() {
            @Override
            public  void run() {
                String jsonData= DateUtils.getJson(mComtext,fileName);
                //解析数据
                Gson gson=new Gson();
                Type type = new TypeToken<ArrayList<ProvinceBean>>() {
                }.getType();
                DateUtils.provinceData=gson.fromJson(jsonData,type);
            }
        }).start();
    }
    //初始化会话fragment
    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                    //        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                    Conversation.ConversationType.GROUP,
                    Conversation.ConversationType.PUBLIC_SERVICE,
                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
                    Conversation.ConversationType.SYSTEM};
            listFragment.setUri(uri);
            return listFragment;
        } else {
            return mConversationListFragment;
        }
    }

    /**
     * 得到不落地 push 消息
     */
    private void getPushMessage() {
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong")) {
            String path = intent.getData().getPath();
            if (path.contains("push_message")) {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                } else {
                    if (!RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED)) {
//                        LoadingDialog.show(mContext);
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {
                            }

                            @Override
                            public void onSuccess(String s) {
//                                LoadingDialog.dismiss(mContext);
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode e) {

                            }
                        });
                    }
                }
            }
        }
    }

    //好友消息验证
    private void getConversationPush() {
        if (getIntent() != null && getIntent().hasExtra("PUSH_CONVERSATIONTYPE") && getIntent().hasExtra("PUSH_TARGETID")) {
            final String conversationType = getIntent().getStringExtra("PUSH_CONVERSATIONTYPE");
            final String targetId = getIntent().getStringExtra("PUSH_TARGETID");
            RongIM.getInstance().getConversation(Conversation.ConversationType.valueOf(conversationType), targetId, new RongIMClient.ResultCallback<Conversation>() {
                @Override
                public void onSuccess(Conversation conversation) {

                    if (conversation != null) {

                        if (conversation.getLatestMessage() instanceof ContactNotificationMessage) { //好友消息的push
                            startActivity(new Intent(Main2Activity.this, NewFriendListActivity.class));
                        } else {
                            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon().appendPath("conversation")
                                    .appendPath(conversationType).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {

                }
            });
        }
    }

    private void reconnect() {
        SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
        String token = sp.getString(Const.LOGIN_TOKEN, "");
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }
            @Override
            public void onSuccess(String s) {
                init();
            }
            @Override
            public void onError(RongIMClient.ErrorCode e) {
            }
        });
    }




    private void reset() {
        for (RadioButton rb : radioButtons) {
            rb.setTextColor(Color.BLACK);
        }

    }

    private long clickTime = 0; //记录第一次点击的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "你忍心退出吗~~",
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            finish();

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                iv_main_chat_more.setVisibility(View.GONE);
                iv_main_recomend.setVisibility(View.VISIBLE);
                tv_main_title.setText("社群联盟");
                rl_main_header.setVisibility(View.VISIBLE);
                break;
            case 1:
                iv_main_chat_more.setVisibility(View.VISIBLE);
                iv_main_recomend.setVisibility(View.GONE);
                tv_main_title.setText("聊天");
                rl_main_header.setVisibility(View.VISIBLE);
                break;
            case 2:
                rl_main_header.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventDicoverMessage(TalkTalkBean event) {
        chatPopupWindow.showDicoverRedPoint();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventAllMessage(FriendInfo event) {
        chatPopupWindow.showMessageRedPoint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.iv_main_recommed, R.id.iv_main_chat_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_main_recommed:
               startActivity(new Intent(this, RecommendActivity.class));
                break;
            case R.id.iv_main_chat_more:
                chatPopupWindow.showPopupWindow(iv_main_chat_more);
                break;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(1001)
    public void requestCameraSuccess()
    {
        //Toast.makeText(this, "成功获取相机权限", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(1001)
    public void requestCameraFailed()
    {
        Toast.makeText(this, "获取相机权限失败", Toast.LENGTH_SHORT).show();
    }
}
