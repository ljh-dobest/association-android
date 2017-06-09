package com.issp.association.crowdfunding.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.issp.association.crowdfunding.R;
import com.issp.association.crowdfunding.adapter.MessageListAdapter;
import com.issp.association.crowdfunding.adapter.ShippingAddressListAdapter;
import com.issp.association.crowdfunding.base.view.BaseMvpActivity;
import com.issp.association.crowdfunding.bean.MessageBean;
import com.issp.association.crowdfunding.bean.OrderDetailBean;
import com.issp.association.crowdfunding.bean.ShippingAddressBean;
import com.issp.association.crowdfunding.interfaces.IOrderDetailListView;
import com.issp.association.crowdfunding.interfaces.IShippingAddressListView;
import com.issp.association.crowdfunding.presenters.OrderDetailPresenter;
import com.issp.association.crowdfunding.presenters.ShippingAddressPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加收货地址
 * Created by T-BayMax on 2017/3/25.
 */

public class ManagementShippingAddressActivity extends BaseMvpActivity<IShippingAddressListView, ShippingAddressPresenter> implements IShippingAddressListView {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.recycler_view_test_rv)
    RecyclerView recyclerView;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;


    List<ShippingAddressBean> personList = new ArrayList<ShippingAddressBean>(0);
    LinearLayoutManager layoutManager;
    private int mLoadCount = 0;
    ShippingAddressListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_shipping_address);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        ltMainTitle.setText(getString(R.string.str_order_detail));
        recyclerView.setHasFixedSize(true);

        initData();
        adapter = new ShippingAddressListAdapter(personList, this);
        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(adapter);
        //设置刷新完成以后，headerview固定的时间
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);

        //当需要使用数据不满一屏时不显示点击加载更多的效果时，解注释下面的三行代码
        //并注释掉第四行代码
      /*  CustomerFooter customerFooter = new CustomerFooter(this);
        customerFooter.setRecyclerView(recyclerView);
       adapter.setCustomLoadMoreView(customerFooter);*/
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }
        });
    }

    private void initData() {

    }
    @OnClick(R.id.lt_main_title_left)
    void leftClick(){
        ManagementShippingAddressActivity.this.finish();
    }


    @Override
    public ShippingAddressPresenter initPresenter() {
        return new ShippingAddressPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {

    }

    @Override
    public void setShippingAddressListData(List<ShippingAddressBean> data) {

    }
}
