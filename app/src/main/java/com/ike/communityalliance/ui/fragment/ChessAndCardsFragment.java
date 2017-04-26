package com.ike.communityalliance.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.InterestingRecyclerAdapter;
import com.ike.communityalliance.base.BaseMvpFragment;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.bean.InterestGroupBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IChessAndCardsView;
import com.ike.communityalliance.presenter.ChessAndCardsPresnter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Min on 2017/3/20.
 */

public class ChessAndCardsFragment extends BaseMvpFragment<IChessAndCardsView,ChessAndCardsPresnter> implements IChessAndCardsView {
@BindView(R.id.rv_interesGroup)
    RecyclerView rv_interesGroup;
    private List<Groups> groups=new ArrayList<>();
    private InterestingRecyclerAdapter adapter;
    private SharedPreferences sp;
    private String hobbyId;
    private String userId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View containerView=inflater.inflate(R.layout.fragment_chessandcards, container, false);
        ButterKnife.bind(this,containerView);
        initData();
        getListData();
        return containerView;
    }
    private void initData() {
          adapter=new InterestingRecyclerAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        rv_interesGroup.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        //设置Adapter
        rv_interesGroup.setAdapter(adapter);
        //设置增加或删除条目的动画
        rv_interesGroup.setItemAnimator(new DefaultItemAnimator());
        rv_interesGroup.addItemDecoration(new DividerItemDecoration(getContext(), OrientationHelper.VERTICAL));
    }
public void setHobbyId(String hobbyId){
    this.hobbyId=hobbyId;
}
    @Override
    public ChessAndCardsPresnter initPresenter() {
        return new ChessAndCardsPresnter();
    }

    @Override
    public void getListData() {
        sp = getContext().getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
            presenter.getInterestGroupData(userId,hobbyId);
    }

    @Override
    public void setListData(List<InterestGroupBean> groupsData) {
              adapter.setmDatas(groupsData);
    }

    @Override
    public void showErrorString(String errorString) {
        T.showShort(getContext(),errorString);
    }
}
