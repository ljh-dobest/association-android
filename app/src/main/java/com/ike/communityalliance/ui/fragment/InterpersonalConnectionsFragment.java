package com.ike.communityalliance.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpFragment;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsView;
import com.ike.communityalliance.presenter.InterpersonalConnectionsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 人脉关系
 * Created by T-BayMax on 2017/3/14.
 */

public class InterpersonalConnectionsFragment extends BaseMvpFragment<IInterpersonalConnectionsView, InterpersonalConnectionsPresenter> implements IInterpersonalConnectionsView {

    @BindView(R.id.tv_relation_colleague)
    TextView tvRelationColleague;
    @BindView(R.id.tv_relation_schoolfellow)
    TextView tvRelationSchoolfellow;
    @BindView(R.id.tv_relation_countrymen)
    TextView tvRelationCountrymen;
    @BindView(R.id.tv_relation_relative)
    TextView tvRelationRelative;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_connections, container, false);
        ButterKnife.bind(this, containerView);
        return containerView;
    }

    @Override
    public InterpersonalConnectionsPresenter initPresenter() {
        return new InterpersonalConnectionsPresenter();
    }

    @Override
    public void onConnectionsError(String string) {

    }

    @Override
    public void onConnectionsSucceed(List<RelationshipBean> data) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.tv_relation_colleague, R.id.tv_relation_schoolfellow, R.id.tv_relation_countrymen, R.id.tv_relation_relative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_relation_colleague:
                break;
            case R.id.tv_relation_schoolfellow:
                break;
            case R.id.tv_relation_countrymen:
                break;
            case R.id.tv_relation_relative:
                break;
        }
    }
}
