package com.ike.communityalliance.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.ChatPopupWindow;
import com.ike.communityalliance.ui.activity.LoginActivity;
import com.ike.communityalliance.ui.activity.NewFriendListActivity;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.server.broadcast.BroadcastManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.message.ContactNotificationMessage;

import static com.ike.communityalliance.R.id.chat_more;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private List<Fragment> mFragment = new ArrayList<>();
    private ImageView chatMore;

    /**
     * 会话列表的fragment
     */
    private Fragment mConversationListFragment = null;
    private boolean isDebug;
    private Context mContext;
    public static final String EXIT = "EXIT";
    private Conversation.ConversationType[] mConversationsTypes = null;
    private ChatPopupWindow chatPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        isDebug = getSharedPreferences("config", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE).getBoolean("isDebug", false);
        if (RongIM.getInstance() != null &&
                RongIM.getInstance().getCurrentConnectionStatus()
                        .equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    initViews();
                    initMainViewPager();
                    if (RongIM.getInstance() != null && RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                        reconnect();
                    }
                }
            }, 300);
        } else {
            initViews();
            initMainViewPager();
        }
        EventBus.getDefault().register(this);
    }

    private void initViews() {
        chatPopupWindow = new ChatPopupWindow(mContext);
        mViewPager= (ViewPager) findViewById(R.id.main_viewpager);
        chatMore= (ImageView) findViewById(chat_more);
        chatMore.setOnClickListener(this);
    }
    private void initMainViewPager() {
        Fragment conversationList = initConversationList();
        mFragment.add(conversationList);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
        };
        mViewPager.setAdapter(fragmentPagerAdapter);
        initData();
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
                SharedPreferences.Editor editor = getSharedPreferences("config", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE).edit();
                editor.putBoolean("exit", true);
                editor.putString("loginToken", "");
                editor.putString("loginid", "");
                editor.apply();
                RongIM.getInstance().logout();
                context.unregisterReceiver(this);
                MainActivity.this.finish();
                try {
                    Thread.sleep(500);
                    android.os.Process.killProcess(android.os.Process.myPid());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment=new ConversationListFragment();
            Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
            //        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build();
            mConversationsTypes = new Conversation.ConversationType[] {Conversation.ConversationType.PRIVATE,
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
                SharedPreferences sharedPreferences = getSharedPreferences("config", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
                String cacheToken = sharedPreferences.getString("loginToken", "");
                if (TextUtils.isEmpty(cacheToken)) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
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

    private void reconnect() {
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        String token = sp.getString(Const.LOGIN_TOKEN, "");
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                initViews();
                initMainViewPager();
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case chat_more:  //
                chatPopupWindow.showPopupWindow(chatMore);
                break;
            default:
                break;
        }
    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra("systemconversation", false)) {
            mViewPager.setCurrentItem(0, false);
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
                            startActivity(new Intent(MainActivity.this, NewFriendListActivity.class));
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
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEventDicoverMessage(TalkTalkBean event){
        chatPopupWindow.showDicoverRedPoint();
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEventAllMessage(FriendInfo event){
        chatPopupWindow.showMessageRedPoint();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
