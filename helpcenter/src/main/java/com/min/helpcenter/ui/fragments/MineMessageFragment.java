package com.min.helpcenter.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.min.helpcenter.R;
import com.min.helpcenter.adapters.MineHelpOrQuestionRvAdapter;
import com.min.helpcenter.base.view.BaseMvpFragment;
import com.min.helpcenter.bean.MineHelp;
import com.min.helpcenter.interfaces.IMineMessageView;
import com.min.helpcenter.presenters.MineMessageFragmentPresenter;
import com.min.helpcenter.utils.PreferenceService;
import com.min.helpcenter.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/31.
 */

public class MineMessageFragment extends BaseMvpFragment<IMineMessageView,MineMessageFragmentPresenter>implements  IMineMessageView{
    @BindView(R.id.rv_mineHelp)
    RecyclerView rv_mineHelp;
    private MineHelpOrQuestionRvAdapter mAdapter;
    private List<MineHelp> data=new ArrayList<>();
    private PreferenceService preferenceService;
    private  String userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View containerView=inflater.inflate(R.layout.fragment_mine_help, container, false);
        ButterKnife.bind(this,containerView);
        initRV();
        getMyHelpData(userId,"0");
        return containerView;
    }

    private void initRV() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_mineHelp.setLayoutManager(layoutManager);
        rv_mineHelp.setItemAnimator(new DefaultItemAnimator());
        rv_mineHelp.addItemDecoration(new DividerItemDecoration(getContext(), OrientationHelper.VERTICAL));
        mAdapter =new MineHelpOrQuestionRvAdapter(getContext());
        mAdapter.setmDatas(data);
        rv_mineHelp.setAdapter(mAdapter);
    }

    @Override
    public MineMessageFragmentPresenter initPresenter() {
        return new MineMessageFragmentPresenter();
    }
public  void setUserId(String userId){
    this.userId=userId;
}
    @Override
    public void getMyHelpData(String userId, String status) {
        presenter.getMineHelpData(userId,status);
    }

    @Override
    public void setMyHelpData(List<MineHelp> data) {
        mAdapter.setmDatas(data);
    }

    @Override
    public void showError(String errorString) {
        T.showShort(getContext(),errorString);
    }
}
