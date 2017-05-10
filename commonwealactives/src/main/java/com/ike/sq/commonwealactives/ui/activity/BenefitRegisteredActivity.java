package com.ike.sq.commonwealactives.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.ike.sq.commonwealactives.R;
import com.ike.sq.commonwealactives.adapter.BenefitRegisteredAdapter;
import com.ike.sq.commonwealactives.base.view.BaseMvpActivity;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.UserBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitRegisteredListView;
import com.ike.sq.commonwealactives.presenters.BenefitRegisteredPresenter;
import com.ike.sq.commonwealactives.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 已报名
 * Created by T-BayMax on 2017/3/18.
 */
public class BenefitRegisteredActivity extends BaseMvpActivity<IBenefitRegisteredListView, BenefitRegisteredPresenter> implements IBenefitRegisteredListView {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.gv_apply)
    GridView gvApply;
    private int mLoadCount = 0;

    private int limit = 20;
    private int page = 1;

    private boolean isRefresh;
    BenefitBean bean;
    BenefitRegisteredAdapter adapter;
    List<UserBean> personList;
    private String userId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public BenefitRegisteredPresenter initPresenter() {
        return new BenefitRegisteredPresenter();
    }

    private void initView() {
        Intent intent=getIntent();
        userId=intent.getStringExtra("userId");
        bean = (BenefitBean) getIntent().getSerializableExtra("bean");
        ltMainTitleLeft.setText("返回");
        ltMainTitle.setText("已报名（" + bean.getJoinUsersNumber() + "）");
        personList = new ArrayList<UserBean>(0);
        adapter = new BenefitRegisteredAdapter(personList, this);
        gvApply.setAdapter(adapter);

    }

    private void initData() {
        isRefresh = true;
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("activesId", bean.getId());
        presenter.PlatformRegisteredPresenter(formData);

    }

    @OnClick(R.id.lt_main_title_left)
    void leftClick() {
        BenefitRegisteredActivity.this.finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {

        T.showShort(BenefitRegisteredActivity.this, errorString);
    }

    @Override
    public void setPlatformRegisteredListData(List<UserBean> data) {
        adapter.setData(data, page);
    }
}

