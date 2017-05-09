package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.AppContext;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.GroupMember;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.bean.UserId;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.db.GroupMemberDAOImpl;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.ike.communityalliance.message.provider.MyGroupInfoProvider;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.ui.MainActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import io.rong.eventbus.EventBus;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import okhttp3.Call;

/**
 * 会话页面
 */
public class ConversationActivity extends BaseActivity implements View.OnClickListener, RongIM.UserInfoProvider, RongIMClient.TypingStatusListener {

    private static final int SET_TEXT_TYPING_TITLE =101;
    private static final int SET_VOICE_TYPING_TITLE =102 ;
    private static final int SET_TARGETID_TITLE =100 ;
    /*@BindView(R.id.iv_title_back)
        ImageView ivTitleBack;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_title_right)
        ImageView ivTitleRight;*/
    private ImageView ivTitleBack, ivTitleRight;
    private TextView tvTitle;
    private List<FriendInfo> list;
    private String TAG = ConversationActivity.class.getSimpleName();
    //对方id
    public static String mTargetId;
    //我方id
    private String userId;
    //会话类型
    private Conversation.ConversationType mConversationType;
    //是否在讨论组内，如果不在讨论组内，则进入不到讨论组设置页面
    private boolean isFromPush = false;
    private String title;
    private SharedPreferences sp;
    private FriendInfoDAOImpl friendInfoDAO;
    private GroupMemberDAOImpl groupMemberDAO;
    private GroupsDAOImpl sqLiteDAO;
    private RongIM.IGroupMemberCallback mMentionMemberCallback;
    private Groups groups;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SET_TEXT_TYPING_TITLE:
                    tvTitle.setText("对方正在输入...");
                    break;
                case SET_VOICE_TYPING_TITLE:
                    tvTitle.setText("对方正在说话...");
                    break;
                case SET_TARGETID_TITLE:
                    tvTitle.setText(title);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        friendInfoDAO = new FriendInfoDAOImpl(mContext);
        groupMemberDAO = new GroupMemberDAOImpl(mContext);
        sqLiteDAO=new GroupsDAOImpl(mContext);
        ivTitleBack = (ImageView) findViewById(R.id.iv_title_back);
        ivTitleRight = (ImageView) findViewById(R.id.iv_title_right);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        ivTitleBack.setOnClickListener(this);
        ivTitleRight.setOnClickListener(this);

        sp = getSharedPreferences("config", MODE_PRIVATE);

        Intent intent = getIntent();
        initPortrait();   //头像
        if (intent == null || intent.getData() == null)
            return;
        userId=sp.getString(Const.LOGIN_ID, "");
        mTargetId = intent.getData().getQueryParameter("targetId");
        //10000 为 Demo Server 加好友的 id，若 targetId 为 10000，则为加好友消息，默认跳转到 NewFriendListActivity
        // Demo 逻辑
        newFriend();  //好友请求

        setActionBarTitle(mConversationType, mTargetId);
        //展示如何从 Intent 中得到 融云会话页面传递的 Uri
//        intent.getData().getLastPathSegment();//获得当前会话类型
        mConversationType = Conversation.ConversationType.valueOf(intent.getData()
                .getLastPathSegment().toUpperCase(Locale.getDefault()));

        RongIMClient.setTypingStatusListener(this);//注册输入状态监听
        title = intent.getData().getQueryParameter("title");
        if(mConversationType== Conversation.ConversationType.CHATROOM){
           title="聊天大厅";
        }
        //私聊---群聊
        if(mConversationType== Conversation.ConversationType.PRIVATE){

          AppContext.setPersonInputProvider();//设置个人聊天时的输入拓展框
        }
        //群聊
        if (mConversationType == Conversation.ConversationType.GROUP) {
            AppContext.setGroupInputProvider();//设置群组聊天时的输入拓展框
            ivTitleRight.setVisibility(View.VISIBLE);
            ivTitleRight.setImageResource(R.mipmap.icon2_menu);
            groups = sqLiteDAO.find(mTargetId);
            if(groups ==null){
                initGroups(userId);
            }else{
                title= groups.getGroupName();
                RongIM.getInstance().refreshGroupInfoCache(new Group(mTargetId,title,Uri.parse(groups.getGroupPortraitUrl())));
            }
            MyGroupInfoProvider.init(this);        //初始化群组信息提供者
        }
        tvTitle.setText(title);

        isPushMessage(intent);
//        if("ConversationActivity".equals(this.getClass().getSimpleName())){
//            EventBus.getDefault().register(this);
//        }

        // android 6.0 以上版本，监听SDK权限请求，弹出对应请求框。
        initPermission();

        AppContext.getInstance().pushActivity(this);

        RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
            @Override
            public void getGroupMembers(String groupId, RongIM.IGroupMemberCallback callback) {
                mMentionMemberCallback = callback;
                getGroupMembersForMention();
            }
        });

    }

    private void newFriend() {
        final String userid = sp.getString(Const.LOGIN_ID, "");
        HttpUtils.postAddFriender("/allUnreadFriends", userid, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/all_unread_friends--------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<UserId>>>() {
                }.getType();
                Code<List<UserId>> code =gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    List<UserId> userIds = code.getData();
                    for (int i = 0; i < userIds.size(); i++) {
                        if (mTargetId != null && mTargetId.equals(userIds.get(i).getUserId())) {
                            startActivity(new Intent(ConversationActivity.this, NewFriendListActivity.class));
                            return;
                        }
                    }
                }else{
                    T.showShort(ConversationActivity.this,"网络异常```");
                }
            }
        });
        /*if (mTargetId != null && mTargetId.equals("10000")) {
            startActivity(new Intent(ConversationActivity.this, NewFriendListActivity.class));
            return;
        }*/
    }

    /**
     * 用户头像
     */
    private void initPortrait() {
        String uid = sp.getString(Const.LOGIN_ID, "");
        String nickName = sp.getString(Const.LOGIN_NICKNAME, "");
        String portraitUri = sp.getString(Const.LOGIN_PORTRAIT, "");
        list = new ArrayList<>();
        list.add(new FriendInfo(uid,nickName,portraitUri));
        list.clear();
        list=friendInfoDAO.findAll(uid);

        RongIM.getInstance().refreshUserInfoCache(new UserInfo(uid, nickName, Uri.parse(portraitUri)));
        L.e("-------sss----",list.toString());
        RongIM.setUserInfoProvider(this, true);
    }

    /**
     * 群头像
     */
    private void getGroupMembersForMention() {
        List<GroupMember> groupMembers = groupMemberDAO.findAll(mTargetId);
                List < UserInfo > userInfos = new ArrayList<>();
        if (groupMembers != null) {
            for (GroupMember groupMember : groupMembers) {
                if (groupMember != null) {
                    UserInfo userInfo = new UserInfo(groupMember.getUserId(), groupMember.getUserName(),
                            Uri.parse(groupMember.getGroupPortraitUrl()));
                    userInfos.add(userInfo);
                }
            }
        }
        mMentionMemberCallback.onGetGroupMembersResult(userInfos);
    }

    /**
     * 判断是否是 Push 消息，判断是否需要做 connect 操作
     */
    private void isPushMessage(Intent intent) {

        if (intent == null || intent.getData() == null)
            return;

        //push
        if (intent.getData().getScheme().equals("rong") && intent.getData().getQueryParameter("isFromPush") != null) {

            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (intent.getData().getQueryParameter("isFromPush").equals("true")) {
                //只有收到系统消息和不落地 push 消息的时候，pushId 不为 null。而且这两种消息只能通过 server 来发送，客户端发送不了。
//                RongIM.getInstance().getRongIMClient().recordNotificationEvent(id);
                isFromPush = true;
            } else if (RongIM.getInstance().getCurrentConnectionStatus().equals(
                    RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                if (intent.getData().getPath().contains("conversation/system")) {
                    Intent intent1 = new Intent(mContext, MainActivity.class);
                    intent1.putExtra("systemconversation", true);
                    startActivity(intent1);
                    finish();
                    return;
                }
                enterActivity();
            } else {
                if (intent.getData().getPath().contains("conversation/system")) {
                    Intent intent1 = new Intent(mContext, MainActivity.class);
                    intent1.putExtra("systemconversation", true);
                    startActivity(intent1);
                    finish();
                    return;
                }
                enterFragment(mConversationType, mTargetId);
            }

        } else {
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(
                    RongIMClient.ConnectionStatusListener.ConnectionStatus.DISCONNECTED)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enterActivity();
                    }
                }, 300);
            } else {
                enterFragment(mConversationType, mTargetId);
            }
        }
    }


    /**
     * 收到 push 消息后，选择进入哪个 Activity
     * 如果程序缓存未被清理，进入 MainActivity
     * 程序缓存被清理，进入 LoginActivity，重新获取token
     * <p/>
     * 作用：由于在 manifest 中 intent-filter 是配置在 ConversationActivity 下面，所以收到消息后点击notifacition 会跳转到 DemoActivity。
     * 以跳到 MainActivity 为例：
     * 在 ConversationActivity 收到消息后，选择进入 MainActivity，这样就把 MainActivity 激活了，当你读完收到的消息点击 返回键 时，程序会退到
     * MainActivity 页面，而不是直接退回到 桌面。
     */
    private void enterActivity() {

        String token = sp.getString(Const.LOGIN_TOKEN, "");//loginToken

        if (token.equals("default")) {
            L.e("ConversationActivity push", "push2");
            startActivity(new Intent(ConversationActivity.this, LoginActivity.class));
            finish();
        } else {
            L.e("ConversationActivity push", "push3");
            reconnect(token);
        }
    }

    private void reconnect(String token) {

        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "---onTokenIncorrect--");
            }

            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "---onSuccess--" + s);
                L.e("ConversationActivity push", "push4");

                Intent intent = new Intent();
                intent.setClass(ConversationActivity.this, MainActivity.class);
                intent.putExtra("PUSH_CONVERSATIONTYPE", mConversationType.toString());
                intent.putExtra("PUSH_TAR   GETID", mTargetId);
                startActivity(intent);
                finish();
                enterFragment(mConversationType, mTargetId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {
                Log.e(TAG, "---onError--" + e);
                enterFragment(mConversationType, mTargetId);
            }
        });

    }

    private ConversationFragment fragment;

    /**
     * 加载会话页面 ConversationFragment
     *
     * @param mConversationType 会话类型
     * @param mTargetId         会话 Id
     */


    private void enterFragment(Conversation.ConversationType mConversationType, String mTargetId) {

        fragment = new ConversationFragment();

        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
                .appendQueryParameter("targetId", mTargetId).build();

        fragment.setUri(uri);
        /*fragment.setInputBoardListener(new InputView.IInputBoardListener() {
            @Override
            public void onBoardExpanded(int height) {
                L.e(TAG, "onBoardExpanded h : " + height);
            }

            @Override
            public void onBoardCollapsed() {
                L.e(TAG, "onBoardCollapsed");
            }
        });*/

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //xxx 为你要加载的 id
        transaction.add(R.id.rong_content, fragment);
        transaction.commitAllowingStateLoss();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 501) {
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (isFromPush) {
            isFromPush = false;
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        if ("ConversationActivity".equals(this.getClass().getSimpleName()))
            EventBus.getDefault().unregister(this);
        RongIM.getInstance().setGroupMembersProvider(null);
        RongIM.getInstance().setRequestPermissionListener(null);
        RongIMClient.setTypingStatusListener(null);
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            if (fragment != null && !fragment.onBackPressed()) {
                if (isFromPush) {
                    isFromPush = false;
                    startActivity(new Intent(this, MainActivity.class));
                }
                AppContext.getInstance().popAllActivity();
            }
        }
        return false;
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            RongIM.getInstance().setRequestPermissionListener(new RongIM.RequestPermissionsListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onPermissionRequest(String[] permissions, final int requestCode) {
                    for (final String permission : permissions) {
                        if (shouldShowRequestPermissionRationale(permission)) {
                            requestPermissions(new String[]{permission}, requestCode);
                        } else {
                            int isPermissionGranted = checkSelfPermission(permission);
                            if (isPermissionGranted != PackageManager.PERMISSION_GRANTED) {
                                new AlertDialog.Builder(ConversationActivity.this)
                                        .setMessage("你需要在设置里打开以下权限:" + permission)
                                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                requestPermissions(new String[]{permission}, requestCode);
                                            }
                                        })
                                        .setNegativeButton("取消", null)
                                        .create().show();
                            }
                            return;
                        }
                    }
                }
            });
        }
    }

    /**
     * 设置会话页面 Title
     *
     * @param conversationType 会话类型
     * @param targetId         目标 Id
     */
    private void setActionBarTitle(Conversation.ConversationType conversationType, String targetId) {

        if (conversationType == null)
            return;

        if (conversationType.equals(Conversation.ConversationType.PRIVATE)) {
            setPrivateActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.GROUP)) {
            setGroupActionBar(targetId);
        } else if (conversationType.equals(Conversation.ConversationType.CHATROOM)) {
            setTitle(title);
        } else if (conversationType.equals(Conversation.ConversationType.SYSTEM)) {
            setTitle(R.string.de_actionbar_system);
        } else if (conversationType.equals(Conversation.ConversationType.CUSTOMER_SERVICE)) {
            setTitle(R.string.main_customer);
        } else {
            setTitle(R.string.de_actionbar_sub_defult);
        }
    }

    /**
     * 设置私聊界面 ActionBar
     */
    private void setPrivateActionBar(String targetId) {
        if (!TextUtils.isEmpty(title)) {
            if (title.equals("null")) {
                if (!TextUtils.isEmpty(targetId)) {
                    UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(targetId);
                    if (userInfo != null) {
                        setTitle(userInfo.getName());
                    }
                }
            } else {
                setTitle(title);
            }

        } else {
            setTitle(targetId);
        }
    }

    /**
     * 设置群聊界面 ActionBar
     *
     * @param targetId 会话 Id
     */
    private void setGroupActionBar(String targetId) {
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        } else {
            setTitle(targetId);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                ConversationActivity.this.finish();
                break;
            case R.id.iv_title_right:
                enterSettingActivity();
                break;
            default:
                break;

        }
    }

    private void enterSettingActivity() {
        if (mConversationType == Conversation.ConversationType.PUBLIC_SERVICE
                || mConversationType == Conversation.ConversationType.APP_PUBLIC_SERVICE) {

            RongIM.getInstance().startPublicServiceProfile(this, mConversationType, mTargetId);
        } else {
            UriFragment fragment = (UriFragment) getSupportFragmentManager().getFragments().get(0);
            //得到讨论组的 targetId
            mTargetId = fragment.getUri().getQueryParameter("targetId");

            if (TextUtils.isEmpty(mTargetId)) {
                T.showShort(mContext, "讨论组尚未创建成功");
            }

            Intent intent = null;
            if (mConversationType == Conversation.ConversationType.GROUP) {
                intent = new Intent(this, GroupDetailActivity.class);
                intent.putExtra("conversationType", Conversation.ConversationType.GROUP);
            } else if (mConversationType == Conversation.ConversationType.PRIVATE) {
                intent = new Intent(this, PrivateChatDetailActivity.class);
                intent.putExtra("conversationType", Conversation.ConversationType.PRIVATE);
            }
            intent.putExtra("TargetId", mTargetId);
            if (intent != null) {
                startActivityForResult(intent, 500);
            }

        }
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (FriendInfo i : list) {
            if (i.getUserId().equals(s)) {
                UserInfo userInfo = new UserInfo(i.getUserId(), i.getNickname(), Uri.parse(i.getUserPortraitUrl()));
                return userInfo;
            }
        }
//        UserInfoManager.getInstance().getUserInfo(s);
        return null;
    }
    /**
     * 获取群组列表
     */
    private void initGroups(final String userId) {
        HttpUtils.postGroupListRequest("/groupData", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/groupData-----" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
//                L.e("gounps","群组列表"+response);
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<Groups>>>() {
                }.getType();
                Code<List<Groups>> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    List<Groups> groups = code.getData();
                    if(groups!=null) {
                        sqLiteDAO.delete(userId);
                        for (Groups groups1 : groups) {
                          String  groupId = groups1.getGroupId();
                            String groupName = groups1.getGroupName();
                            String groupPort = HttpUtils.IMAGE_RUL + groups1.getGroupPortraitUrl();
                            String role = groups1.getRole();
//                        list.add(new Groups(groupid, groupName, groupPort));
                            Groups groups2 = new Groups();
                            groups2.setUserId(userId);
                            groups2.setGroupId(groupId);  //groupId
                            groups2.setGroupName(groupName);  //groupName
                            groups2.setGroupPortraitUrl(groupPort);
                            groups2.setRole(role);
                            sqLiteDAO.save(groups2);
                            L.e("-------------==-=-", "群组列表插入成功");// 用日志记录一个我们自定义的输出。可以在LogCat窗口中查看，
                        }
                    }
                     Groups mGroup= sqLiteDAO.find(mTargetId);
                    title=mGroup.getGroupName();
                    tvTitle.setText(title);
                    RongIM.getInstance().refreshGroupInfoCache(new Group(mTargetId,title,Uri.parse(mGroup.getGroupPortraitUrl())));
                    LoadDialog.dismiss(mContext);
                } else {
                    LoadDialog.dismiss(mContext);
                }
            }
        });
    }

    @Override
    public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {

            //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
            if (type.equals(mConversationType) && targetId.equals(mTargetId)) {
                //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
                int count = typingStatusSet.size();
                if (count > 0) {
                    Iterator iterator = typingStatusSet.iterator();
                    TypingStatus status = (TypingStatus) iterator.next();
                    String objectName = status.getTypingContentType();

                    MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
                    MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
                    //匹配对方正在输入的是文本消息还是语音消息
                    if (objectName.equals(textTag.value())) {
                        //显示“对方正在输入”
                        mHandler.sendEmptyMessage(SET_TEXT_TYPING_TITLE);
                    } else if (objectName.equals(voiceTag.value())) {
                        //显示"对方正在讲话"
                        mHandler.sendEmptyMessage(SET_VOICE_TYPING_TITLE);
                    }
                } else {
                    //当前会话没有用户正在输入，标题栏仍显示原来标题
                    mHandler.sendEmptyMessage(SET_TARGETID_TITLE);
                }
            }
        }

}
