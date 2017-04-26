package com.ike.communityalliance.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpFragment;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsView;
import com.ike.communityalliance.presenter.InterpersonalConnectionsPresenter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 人脉关系
 * Created by T-BayMax on 2017/3/14.
 */

public class InterpersonalConnectionsFragment extends BaseMvpFragment<IInterpersonalConnectionsView,InterpersonalConnectionsPresenter> implements IInterpersonalConnectionsView{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_connections, container, false);
        ButterKnife.bind(this,containerView);
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
    public void onConnectionsSucceed(List data) {

    }
}
