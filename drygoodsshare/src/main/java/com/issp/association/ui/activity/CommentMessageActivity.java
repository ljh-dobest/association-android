package com.issp.association.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.issp.association.R;
import com.issp.association.adapter.IndexPageAdapter;
import com.issp.association.adapter.ShareCommentListAdapter;
import com.issp.association.adapter.SimpleAdapter;
import com.issp.association.base.view.BaseMvpActivity;
import com.issp.association.bean.CommentsBean;
import com.issp.association.bean.ShareBean;
import com.issp.association.bean.ShareCommentBean;
import com.issp.association.interfaces.ICommentMessageListView;
import com.issp.association.presenters.CommentMessagePresenter;
import com.issp.association.utils.T;
import com.issp.association.view.BannerViewPager;
import com.issp.association.view.CustomGifHeader;
import com.issp.association.view.CustomerFooter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消息列表
 * Created by T-BayMax on 2017/3/18.
 */

public class CommentMessageActivity extends BaseMvpActivity<ICommentMessageListView, CommentMessagePresenter> implements ICommentMessageListView {


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
    private String userId;
    private int page=1;
    List<CommentsBean> personList = new ArrayList<CommentsBean>();
    LinearLayoutManager layoutManager;
    private int mLoadCount = 0;
    ShareCommentListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_message);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ltMainTitle.setText("消息");
        recyclerView.setHasFixedSize(true);
        userId = getIntent().getStringExtra("userId");
        initData();
        adapter = new ShareCommentListAdapter(personList, this);
        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 静默加载模式不能设置footerview
        recyclerView.setAdapter(adapter);
        //设置刷新完成以后，headerview固定的时间

        xRefreshView.setPinnedTime(1500);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setAutoLoadMore(false);

        //当需要使用数据不满一屏时不显示点击加载更多的效果时，解注释下面的三行代码
        //并注释掉第四行代码
      /*  CustomerFooter customerFooter = new CustomerFooter(this);
        customerFooter.setRecyclerView(recyclerView);
       adapter.setCustomLoadMoreView(customerFooter);*/
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xRefreshView.enableReleaseToLoadMore(false);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
               initData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }
        });
    }

    private void initData() {
        // isRefresh = true;
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("type", "3");
        //  formData.put("limit", limit + "");
        // formData.put("page", page + "");
        presenter.ShareInfoPresenter(formData);
    }

    @OnClick(R.id.lt_main_title_left)
    void leftClick() {
        CommentMessageActivity.this.finish();
    }


    @Override
    public CommentMessagePresenter initPresenter() {
        return new CommentMessagePresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {
        if (page == 1) {
            xRefreshView.stopRefresh(false);
        } else {
            xRefreshView.stopLoadMore(false);
        }
        T.showLong(CommentMessageActivity.this,errorString);
    }

    @Override
    public void setCommentMessageListData(List<CommentsBean> data) {
        adapter.setData(data);
        if (page == 1) {
            xRefreshView.stopRefresh(true);
        } else {
            xRefreshView.stopLoadMore(true);
        }
    }
}
