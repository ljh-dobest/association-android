package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.GroupMember;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 群成员
 */
public class GroupMemberActivity extends BaseActivity{
    @BindView(R.id.ll_group_member_back)
    LinearLayout ll_group_member_back;
    @BindView(R.id.rv_groupmenber)
    RecyclerView rv_groupmenber;
    @BindView(R.id.tv_group_member_title)
    TextView tv_group_member_title;
    private String groupId;
    private String userId,userName,userPort,mobile,userEmail,userDisplayName,role;
    private List<GroupMember> list=new ArrayList<>();
    private List<FriendInfo> friendInfoList=new ArrayList<>();
    private String fromConversationId;
    private GroupMember groupMember;
    private boolean isCreate;//群主
    private List<String> kickMemberId=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member);
        ButterKnife.bind(this);
        groupId = getIntent().getStringExtra("groupId");
           isCreate=getIntent().getBooleanExtra("isCreate",false);
        initList();
    }


    @OnClick(R.id.ll_group_member_back)
    public void onClick() {
        finish();
    }

    private void initList() {
        fromConversationId = getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
        HttpUtils.postGroupsRequest("/groupMember", groupId, fromConversationId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/groupMember----------连接失败");
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type=new TypeToken<Code<List<GroupMember>>>(){}.getType();
                Code<List<GroupMember>> code =gson.fromJson(response,type);
                if(code.getCode()==200){
                    List<GroupMember> groupMember=code.getData();
                    for(GroupMember member:groupMember) {
                        userId = member.getUserId();
                        userName = member.getUserName();
                        userPort = HttpUtils.IMAGE_RUL+member.getUserPortraitUrl();
                        userDisplayName=member.getDisplayName();
                        mobile=member.getMobile();
                        userEmail=member.getEmail();
                        role=member.getRole();
                        list.add(new GroupMember(userId, userName, userPort,mobile,role));
                        friendInfoList.add(new FriendInfo(userId,userName,userPort,mobile,userEmail));
                    }
                    initAdapter();
                }else {
                    T.showShort(mContext,"/group/group_member---------获取数据失败");
                }
            }
        });
    }

    private void initAdapter() {
        tv_group_member_title.setText("群成员("+list.size()+")");
        rv_groupmenber.setLayoutManager(new GridLayoutManager(this,3));
        rv_groupmenber.setAdapter(new CommonAdapter<GroupMember>(this,R.layout.item_group_member,list) {
            @Override
            protected void convert(ViewHolder holder, GroupMember groupMember, final int position) {
                ImageView iv= holder.getView(R.id.siv_group_member_head);
                Picasso.with(GroupMemberActivity.this).load(groupMember.getUserPortraitUrl()).into(iv);
               iv.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FriendInfo friend=friendInfoList.get(position);
                       String mId=getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
                       if(friend.getUserId().equals(mId)){
                           T.showShort(mContext,"这是自己");
                           return;
                       }
                       Intent intent=new Intent(mContext,UserDetailActivity.class);
                       intent.putExtra("friends",friend);
                       startActivity(intent);
                   }
               });
                if(TextUtils.isEmpty(groupMember.getDisplayName())){
                   holder.setText(R.id.tv_group_member_name,groupMember.getUserName());
               }else{
                   holder.setText(R.id.tv_group_member_name,groupMember.getDisplayName());
               }
            }
        });

    }
}
