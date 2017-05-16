package com.ike.communityalliance.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.PhotoUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.BottomMenuDialog;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.MyGridView;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupMember;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.DBOpenHelper;
import com.ike.communityalliance.db.GroupMemberDAOImpl;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.file.image.MyBitmapUtils;
import com.ike.communityalliance.wedget.DemoGridView;
import com.ike.communityalliance.wedget.Operation;
import com.ike.communityalliance.wedget.SwitchButton;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import okhttp3.Call;

/**
 * 群组详情
 */
public class GroupDetailActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.gridview)
    DemoGridView mGridView;
    @BindView(R.id.tv_group_member_size)
    TextView tvGroupMemberSize;
    @BindView(R.id.rl_group_member_size_item)    //全体成员
            RelativeLayout rlGroupMemberSizeItem;
    @BindView(R.id.tv_group_name)
    TextView tvGroupName;
    @BindView(R.id.ll_group_name)               //群名
            LinearLayout llGroupName;
    @BindView(R.id.ll_group_announcement)       //公告
            LinearLayout llGroupAnnouncement;
    @BindView(R.id.sw_group_notfaction)         //消息免打扰
            SwitchButton swGroupNotfaction;
    @BindView(R.id.sw_group_top)                //消息置顶
            SwitchButton swGroupTop;
    @BindView(R.id.btn_group_quit)
    Button btnGroupQuit;
    @BindView(R.id.btn_group_dismiss)
    Button btnGroupDismiss;
    @BindView(R.id.ll_my_nickname)
    LinearLayout llMyNickname;
    @BindView(R.id.tv_my_name)
    TextView tvMyName;
    @BindView(R.id.tv_group_number)       //群号码
            TextView tv_group_number;
     @BindView(R.id.ll_group_setvicePrincipal)
     LinearLayout ll_group_setvicePrincipal;

    private List<GroupMember> mGroupMember;
    private String groupId;    //群ID
    private Conversation.ConversationType mConversationType;
    private boolean isFromConversation;
    private Groups mGroup;
    private boolean isCreated = false;   //群主
    private PhotoUtils photoUtils;
    private String imageUrl;
    private File imageFile;              //群头像
    private BottomMenuDialog dialog;
    private Bitmap bitmap;
    private String userId;
    private String nickname;
    private String groupName;
    private EditText editText;

    private SharedPreferences sp;

    private DBOpenHelper dbOpenHelper;  //SQLite
    private GroupsDAOImpl sqLiteDAO;
    private GroupMemberDAOImpl groupMemberDAO;

    private MyGridView adapter;
    private MyBitmapUtils myBitmapUtils;

    private static final String CACHE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/SmallTalk/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        ButterKnife.bind(this);
        myBitmapUtils = new MyBitmapUtils();
        //群组会话界面点进群组详情---groupId
        groupId = getIntent().getStringExtra("TargetId");
        initSQLite();
        initView();
        //----GROUP
        mConversationType = (Conversation.ConversationType) getIntent().getSerializableExtra("conversationType");

        if (!TextUtils.isEmpty(groupId)) {
            isFromConversation = true;
        }

        if (isFromConversation) {  //群主会话页进入
            LoadDialog.show(mContext);
//            getGroups();  //群组信息
            initGroups(userId);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add(Menu.NONE, Menu.NONE, Menu.NONE, "Exit");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                System.exit(0);
                return true;
            }
        });
        return true;
    }

    //数据库
    private void initSQLite() {
        sqLiteDAO = new GroupsDAOImpl(mContext);
        groupMemberDAO = new GroupMemberDAOImpl(mContext);
    }


    private void initView() {
        tvTitle.setText("群组信息");
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");   //个人id
        nickname = sp.getString(Const.LOGIN_NICKNAME, "");  //个人昵称
        tvMyName.setText(nickname);
        tv_group_number.setText(groupId);
        swGroupTop.setOnCheckedChangeListener(this);
        swGroupNotfaction.setOnCheckedChangeListener(this);
    }

    /**
     * 群组信息
     */
    private void getGroupsSqlite() {
        Groups groups = sqLiteDAO.find(groupId);
        groupName = groups.getGroupName();
        tvGroupName.setText(groupName);   //群名
        mGroup = new Groups(groups.getGroupId(), groupName,
                groups.getGroupPortraitUrl(), groups.getRole());
        L.e("--------------====", mGroup + "");
    }



    //群组信息
    private void initGroupData() {
        /**
         * 会话置顶
         */
        if (RongIM.getInstance() != null) {
            RongIM.getInstance().getConversation(Conversation.ConversationType.GROUP,
                    mGroup.getGroupId(), new RongIMClient.ResultCallback<Conversation>() {
                        @Override
                        public void onSuccess(Conversation conversation) {
                            if (conversation == null) {
                                return;
                            }
                            if (conversation.isTop()) {
                                swGroupTop.setChecked(true);
                            } else {
                                swGroupTop.setChecked(false);
                            }

                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
            /**
             * 消息免打扰
             */
            RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP,
                    mGroup.getGroupId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                        @Override
                        public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {

                            if (conversationNotificationStatus == Conversation.ConversationNotificationStatus.DO_NOT_DISTURB) {
                                swGroupNotfaction.setChecked(true);
                            } else {
                                swGroupNotfaction.setChecked(false);
                            }
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });
        }
        //成员角色---1：群主

        if (mGroup.getRole().equals("2")) {
            isCreated = true;
        }
        if (!isCreated) {
            ll_group_setvicePrincipal.setVisibility(View.GONE);
            llGroupAnnouncement.setVisibility(View.GONE);
        } else {
            llGroupAnnouncement.setVisibility(View.VISIBLE);
            btnGroupDismiss.setVisibility(View.VISIBLE);
            btnGroupQuit.setVisibility(View.GONE);
        }
        getGroupMembers(isCreated); //成员信息
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(groupId)) {
            isFromConversation = true;
        }

        if (isFromConversation) {  //群主会话页进入
          //  LoadDialog.show(mContext);
//            getGroups();  //群组信息
        initGroups(userId);
//            getGroupsSqlite();
          //  LoadDialog.dismiss(mContext);
        }
    }

    /**
     * 群组成员
     */
    private void getGroupMembers(final boolean isCreator) {
        HttpUtils.postGroupsRequest("/groupMember", groupId, userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "groupMember------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<GroupMember>>>() {
                }.getType();
                Code<List<GroupMember>> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    mGroupMember = code.getData();
                    if (mGroupMember != null && mGroupMember.size() > 0) {
                        tvGroupMemberSize.setText("全部成员" + "(" + mGroupMember.size() + ")");
                        adapter = new MyGridView(mContext, mGroupMember, isCreator, mGroup);
                        mGridView.setAdapter(adapter);
                    } else {
                        return;
                    }
                }
            }
        });
    }

    //点击事件
    @OnClick({R.id.iv_title_back, R.id.rl_group_member_size_item,R.id.ll_group_name,
            R.id.sw_group_top, R.id.sw_group_notfaction, R.id.btn_group_quit,
            R.id.btn_group_dismiss, R.id.ll_group_announcement,R.id.ll_my_nickname,R.id.ll_group_setvicePrincipal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:  //返回
                GroupDetailActivity.this.finish();
                break;
            case R.id.rl_group_member_size_item:  //群成员
                Intent intent = new Intent(mContext, GroupMemberActivity.class);
                intent.putExtra("groupId", groupId);
                intent.putExtra("isCreate",isCreated);
                startActivity(intent);
                break;
            case R.id.ll_group_setvicePrincipal:   //设置副群主
                if (isCreated) {
                    Intent intent2= new Intent(this, SelectFriendsActivity.class);
                    intent2.putExtra("isSetvicePrincipal", true);
                    intent2.putExtra("GroupId",groupId);
                    startActivity(intent2);
                } else {
                    T.showShort(mContext, "只有群主才能设置副群主");
                }
                break;
            case R.id.ll_group_name:   //群名称
                if (isCreated) {
                    final EditText editText = new EditText(mContext);
                    new AlertDialog.Builder(mContext)
                            .setTitle("修改群名称")
                            .setView(editText)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String et = editText.getText().toString();
                                    changGroupName(et);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                } else {
                    T.showShort(mContext, "只有群主才能修改群名称");
                    break;
                }
                break;
            case R.id.btn_group_quit:   //退出群
                AlertDialog quit = new AlertDialog.Builder(mContext)
                        .setTitle("退出群")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                quitGroup();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                break;
            case R.id.btn_group_dismiss:  //解散群
                AlertDialog dismiss = new AlertDialog.Builder(mContext)
                        .setTitle("退出群")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dismissGroup();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                break;
            case R.id.ll_group_announcement:  //群公告
                Intent intent2 = new Intent(mContext, GroupNoticeActivity.class);
                intent2.putExtra("conversationType", Conversation.ConversationType.GROUP.getValue());
                intent2.putExtra("targetId", groupId);
                startActivity(intent2);
                break;

            case R.id.ll_my_nickname:  //修改群个人昵称
                editText = new EditText(mContext);
                new AlertDialog.Builder(mContext)
                        .setTitle("修改个人昵称")
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String et = editText.getText().toString();
                                changeMyName(et);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    //修改群个人昵称
    private void changeMyName(String string) {
        HttpUtils.postChangeNameGroup("/changeUserName", groupId, userId, string, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/changeUserName------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<Integer> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    tvMyName.setText(editText.getText());
                    T.showShort(mContext, "修改成功");
                } else {
                    T.showShort(mContext, "修改失败");
                }
            }
        });
    }

    //退出群
    private void quitGroup() {
        HttpUtils.postQuitGroup("/dissolutionGroup", groupId, userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/quit_group------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                if (code.getCode() == 100) {
                    RongIM.getInstance().getConversation(Conversation.ConversationType.GROUP, groupId,
                            new RongIMClient.ResultCallback<Conversation>() {

                                @Override
                                public void onSuccess(Conversation conversation) {
                                    RongIM.getInstance().clearMessages(Conversation.ConversationType.GROUP,
                                            groupId, new RongIMClient.ResultCallback<Boolean>() {
                                                @Override
                                                public void onSuccess(Boolean aBoolean) {
                                                    RongIM.getInstance().removeConversation(Conversation.ConversationType.GROUP, groupId, null);
                                                }

                                                @Override
                                                public void onError(RongIMClient.ErrorCode errorCode) {

                                                }
                                            });
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
//                    BroadcastManager.getInstance(mContext).sendBroadcast(Const.GROUP_LIST_UPDATE);
                    sqLiteDAO.deleteOne(groupId);
                    T.showShort(mContext, "退出成功");
                    startActivity(new Intent(mContext,GroupListActivity.class));
                    finish();
                }
            }
        });

    }

    //解散群
    private void dismissGroup() {
        HttpUtils.postDismissGroup("/dissolutionGroup", groupId, userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/dismiss_group------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    RongIM.getInstance().getConversation(Conversation.ConversationType.GROUP, groupId,
                            new RongIMClient.ResultCallback<Conversation>() {

                                @Override
                                public void onSuccess(Conversation conversation) {
                                    RongIM.getInstance().clearMessages(Conversation.ConversationType.GROUP,
                                            groupId, new RongIMClient.ResultCallback<Boolean>() {
                                                @Override
                                                public void onSuccess(Boolean aBoolean) {
                                                    RongIM.getInstance().removeConversation(Conversation.ConversationType.GROUP, groupId, null);
                                                }

                                                @Override
                                                public void onError(RongIMClient.ErrorCode errorCode) {

                                                }
                                            });
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
                    sqLiteDAO.deleteOne(groupId);
                    T.showShort(mContext, "解散群成功");
//                    BroadcastManager.getInstance(mContext).sendBroadcast(Const.GROUP_LIST_UPDATE);
                    startActivity(new Intent(mContext,GroupListActivity.class));
                    finish();
                } else {
                    T.showShort(mContext, "解散群失败");
                }
            }
        });

    }

    //修改群名称
    private void changGroupName(final String groupName) {
        File ff = new File(CACHE_PATH+groupId+".jpg");
        HttpUtils.postChangeGroupName("/changeGroupName",userId,groupId, groupName, ff, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/changeGroupName---" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Groups>>() {
                }.getType();
                Code<Groups> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    tvGroupName.setText(groupName);
                    sqLiteDAO.update(groupName,groupId);
                    T.showShort(mContext, "修改群名称成功");
                } else {
                    T.showShort(mContext, "连接错误");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PhotoUtils.INTENT_CROP:
            case PhotoUtils.INTENT_TAKE:
            case PhotoUtils.INTENT_SELECT:
                photoUtils.onActivityResult(GroupDetailActivity.this, requestCode, resultCode, data);
        }
    }

    //消息设置
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        switch (compoundButton.getId()) {
            case R.id.sw_group_top:  //消息置顶
                if (isChecked) {
                    if (mGroup != null) {
                        Operation.setConversationTop(mContext, Conversation.ConversationType.GROUP, mGroup.getGroupId(), true);
                    }
                } else {
                    if (mGroup != null) {
                        Operation.setConversationTop(mContext, Conversation.ConversationType.GROUP, mGroup.getGroupId(), false);
                    }
                }
                break;
            case R.id.sw_group_notfaction:  //消息免打扰
                if (isChecked) {
                    if (mGroup != null) {
                        Operation.setConverstionNotif(mContext, Conversation.ConversationType.GROUP, mGroup.getGroupId(), true);
                    }
                } else {
                    if (mGroup != null) {
                        Operation.setConverstionNotif(mContext, Conversation.ConversationType.GROUP, mGroup.getGroupId(), false);
                    }
                }
                break;
            default:
                break;
        }
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
                L.e("gounps","群组列表"+response);
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<Groups>>>() {
                }.getType();
                Code<List<Groups>> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    List<Groups> groups = code.getData();
                    if(groups!=null) {
                        sqLiteDAO.delete(userId);
                        for (Groups groups1 : groups) {
                          String  curGroupId = groups1.getGroupId();
                            String groupName = groups1.getGroupName();
                            String groupPort = HttpUtils.IMAGE_RUL + groups1.getGroupPortraitUrl();
                            String role = groups1.getRole();
//                        list.add(new Groups(groupid, groupName, groupPort));
                            Groups groups2 = new Groups();
                            groups2.setUserId(userId);
                            groups2.setGroupId(curGroupId);  //groupId
                            groups2.setGroupName(groupName);  //groupName
                            groups2.setGroupPortraitUrl(groupPort);
                            groups2.setRole(role);
                            sqLiteDAO.save(groups2);
                            L.e("-------------==-=-", "群组列表插入成功");// 用日志记录一个我们自定义的输出。可以在LogCat窗口中查看，
                        }
                    }
                    getGroupsSqlite();
                      initGroupData();
                    LoadDialog.dismiss(mContext);
                } else {
                    LoadDialog.dismiss(mContext);
                }
            }
        });
    }
}
