package com.ike.communityalliance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.RelationShipAdapter;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsView;
import com.ike.communityalliance.presenter.InterpersonalConnectionsPresenter;
import com.ike.mylibrary.util.T;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 人脉关系
 * Created by T-BayMax on 2017/5/9.
 */

public class RelationShipActivity extends BaseMvpActivity<IInterpersonalConnectionsView, InterpersonalConnectionsPresenter> implements IInterpersonalConnectionsView {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_relationship)
    RecyclerView rlRelationship;

    private RelationShipAdapter adapter;
    private String userId;               //当前用户id
    private int type;    //1亲人2同事3校友4同乡
    private String title;
    private List<RelationshipBean> data;
    SharedPreferences sp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);
        ButterKnife.bind(this);
        initData();
        click();
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        title = intent.getStringExtra("title");
        tvTitle.setText(title);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        data = new ArrayList<>(0);

        adapter = new RelationShipAdapter(this, data);
        rlRelationship.setAdapter(adapter);
        rlRelationship.setLayoutManager(new LinearLayoutManager(this));
        rlRelationship.setHasFixedSize(true);
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("type", type + "");
        presenter.getConnectionsData(formData);
    }

    private void click() {
        adapter.setOnItemClickListener(new RelationShipAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RelationShipAdapter.ViewHolder viewHolder, RelationshipBean bean) {

            }

            @Override
            public void onAddFriendsClick(RelationShipAdapter.ViewHolder viewHolder, RelationshipBean bean) {
                if (bean.getStatus() == 0) {
                    Map<String, String> formData = new HashMap<String, String>(0);
                    formData.put("userId", userId);
                    formData.put("status", "0");
                    formData.put("nickname", sp.getString(Const.LOGIN_NICKNAME,""));
                    formData.put("friendUserid",bean.getUserId());
                    formData.put("addFriendMessage","");
                    presenter.addFriendsPresenter(formData);
                }
            }
        });
    }

    @Override
    public void onConnectionsError(String string) {
        T.showLong(RelationShipActivity.this, string);
    }

    @Override
    public void onConnectionsSucceed(List<RelationshipBean> mData) {
        if (null != mData) {
            this.data = mData;
            adapter.setData(data);
        }
    }

    @Override
    public void addFriends(String data) {
        T.showLong(RelationShipActivity.this, data);
    }

    @Override
    public InterpersonalConnectionsPresenter initPresenter() {
        return new InterpersonalConnectionsPresenter();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        RelationShipActivity.this.finish();
    }
}
