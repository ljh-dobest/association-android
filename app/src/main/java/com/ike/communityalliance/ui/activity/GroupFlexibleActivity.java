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
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.GroupFlexibleRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupFlexible;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.ItemDivider;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 群活动
 */
public class GroupFlexibleActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, GroupFlexibleRvAdapter.OnItemClickLitener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_group_activity)
    RecyclerView rvGroupActivity;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private GroupFlexibleRvAdapter adapter;
    private List<GroupFlexible> list = new ArrayList<>();

    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_flexible);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        groupId = intent.getStringExtra("groupId");
        initView();
        initListView();
    }

    private void initListView() {
        String userid = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        HttpUtils.postGroupActiivity("/listActives", userid, groupId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/group/groupAcitivity------onError");
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<GroupFlexible>>>() {
                }.getType();
                Code<List<GroupFlexible>> code =gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    list=code.getData();
                    initAdapter();
                } else {
                    T.showShort(mContext, "没有群活动");
                }
            }
        });
    }

    private void initAdapter() {
        adapter=new GroupFlexibleRvAdapter(this);
        adapter.setmDatas(list);
        adapter.setOnItemClickLitener(this);
        rvGroupActivity.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvGroupActivity.setLayoutManager(lm);
        rvGroupActivity.addItemDecoration(new ItemDivider(mContext, ItemDivider.VERTICAL_LIST));
    }

    private void initView() {
        tvTitle.setText("群活动");
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText("添加");
        swipeRefresh.setOnRefreshListener(this);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                Intent intent = new Intent(mContext, GroupAddFlexibleActivity.class);
                intent.putExtra("group_id", groupId);
//                startActivity(intent);
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            list.clear();
            initListView();
            if(list.size()!=0) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRefresh() {
        list.clear();
        initListView();
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(GroupFlexibleActivity.this, FlexibleDetailActivity.class);
        intent.putExtra("flexible", list.get(position));
        startActivity(intent);
    }
}
