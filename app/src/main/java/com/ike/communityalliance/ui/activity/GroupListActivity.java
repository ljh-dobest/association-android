package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.GroupListAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupMember;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.GroupMemberDAOImpl;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.ItemDivider;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import okhttp3.Call;

/**
 * 群列表
 */
public class  GroupListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_group_list)
    RecyclerView rvGroupList;
    @BindView(R.id.tv_no_group)
    TextView tvNoGroup;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

//    private BaseRecyclerAdapter<Groups> adapter;
    private GroupListAdapter adapter;
    private List<Groups> list = new ArrayList<>();
    private List<GroupMember> mGroupMember=new ArrayList<>();
    private String groupName;
    private String groupId;
    private String groupPortraitUri;
    private GroupsDAOImpl sqLiteDAO;
    private GroupMemberDAOImpl groupMemberDAO;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);
        ButterKnife.bind(this);

        sqLiteDAO = new GroupsDAOImpl(mContext);
        groupMemberDAO = new GroupMemberDAOImpl(mContext);

        tvTitle.setText("我的群组");
        ivTitleRight.setVisibility(View.VISIBLE);
        ivTitleRight.setImageResource(R.mipmap.add_more);
        swipeRefresh.setOnRefreshListener(this);
        LoadDialog.show(mContext);
        initAdapter();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // initGroups(userId);
       // initData();
    }

    private void initData() {
        userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        list = sqLiteDAO.findAll(userId);
        if (list.size() > 0) {
           adapter.setList(list);
            LoadDialog.dismiss(mContext);
        } else {
            rvGroupList.setVisibility(View.GONE);
            tvNoGroup.setVisibility(View.VISIBLE);
            tvNoGroup.setText("你暂时未加入任何一个群组");
            LoadDialog.dismiss(mContext);
        }
    }

    private void initAdapter() {
        adapter = new GroupListAdapter(mContext);
        rvGroupList.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvGroupList.setLayoutManager(lm);
        rvGroupList.addItemDecoration(new ItemDivider(this, ItemDivider.VERTICAL_LIST));
        initListItemClick();
    }

    private void initListItemClick() {
        adapter.setOnItemClickListener(new GroupListAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Groups groups) {
                String groupId=groups.getGroupId();
                String groupName=groups.getGroupName();
                initList(groupId,groupName);
                RongIM.getInstance().startGroupChat(mContext,groupId,groupName);
            }
        });
    }

    private void initList(String groupId,String groupName) {
        String userId=getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
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
                Code<List<GroupMember>> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    mGroupMember = code.getData();
                    for(GroupMember groupMember:mGroupMember){
                        groupMemberDAO.save(groupMember);
                    }
                }
            }
        });
    }


    @OnClick({R.id.iv_title_back, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                GroupListActivity.this.finish();
                break;
            case R.id.iv_title_right:
                Intent intent=new Intent(mContext,SelectFriendsActivity.class);
                intent.putExtra("createGroup",true);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        initGroups(userId);
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
                Code<List<Groups>> code =gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    List<Groups> groups = code.getData();
                    if(groups!=null) {
                        sqLiteDAO.delete(userId);
                        for (Groups groups1 : groups) {
                            groupId = groups1.getGroupId();
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
                    initData();
                    swipeRefresh.setRefreshing(false);
                } else {
                    swipeRefresh.setRefreshing(false);
                }
            }
        });
    }
}
