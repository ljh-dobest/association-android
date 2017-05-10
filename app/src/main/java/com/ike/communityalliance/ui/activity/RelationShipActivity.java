package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsView;
import com.ike.communityalliance.presenter.InterpersonalConnectionsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by T-BayMax on 2017/5/9.
 */

public class RelationShipActivity extends BaseMvpActivity<IInterpersonalConnectionsView, InterpersonalConnectionsPresenter> implements IInterpersonalConnectionsView {


    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_relationship)
    RelativeLayout rlRelationship;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationship);
        ButterKnife.bind(this);
    }

    @Override
    public void onConnectionsError(String string) {

    }

    @Override
    public void onConnectionsSucceed(List<RelationshipBean> data) {

    }

    @Override
    public InterpersonalConnectionsPresenter initPresenter() {
        return new InterpersonalConnectionsPresenter();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
    }
}
