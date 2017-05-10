package com.ike.sq.commonwealactives.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.ike.sq.commonwealactives.R;
import com.ike.sq.commonwealactives.adapter.SimpleAdapter;
import com.ike.sq.commonwealactives.base.adpater.BannerImageLoader;
import com.ike.sq.commonwealactives.base.view.BaseMvpActivity;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.interfaces.IMineBenefitListView;
import com.ike.sq.commonwealactives.presenters.BenefitPresenter;
import com.ike.sq.commonwealactives.presenters.MineBenefitPresenter;
import com.ike.sq.commonwealactives.utils.DisplayUtils;
import com.ike.sq.commonwealactives.utils.T;
import com.ike.sq.commonwealactives.view.CustomGifHeader;
import com.youth.banner.Banner;
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
 * 我参与的公益活动
 * Created by T-BayMax on 2017/5/5.
 */
public class MineBenefitActivity extends BaseMvpActivity<IMineBenefitListView, MineBenefitPresenter> implements IMineBenefitListView {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.recycler_view_test_rv)
    RecyclerView recyclerViewTestRv;
    @BindView(R.id.xrefreshview)
    XRefreshView xrefreshview;
    @BindView(R.id.iv_top)
    ImageView ivTop;


    SimpleAdapter adapter;
    List<BenefitBean> personList = new ArrayList<BenefitBean>();
    GridLayoutManager layoutManager;
    private int mLoadCount = 0;

    private int limit = 20;
    private int page = 1;

    private boolean isRefresh;


    private String userId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    TextView tv_like_btn;
    ImageView iv_like_btn;

    private void initView() {
        //  PreferenceService ps = new PreferenceService(MainActivity.this);
        userId = getIntent().getStringExtra("userId");
        ltMainTitle.setText("我参与的公益");

        xrefreshview.setPullLoadEnable(true);
        recyclerViewTestRv.setHasFixedSize(true);

        initData();
        adapter = new SimpleAdapter(personList, this);
        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();
        layoutManager = new GridLayoutManager(this, 1);
        recyclerViewTestRv.setLayoutManager(layoutManager);

        CustomGifHeader header = new CustomGifHeader(this);
        xrefreshview.setCustomHeaderView(header);
        recyclerViewTestRv.setAdapter(adapter);
        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);
//        recyclerviewAdapter.setHeaderView(headerView, recyclerView);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
//        xRefreshView1.setPullRefreshEnable(false);
        //设置在下拉刷新被禁用的情况下，是否允许界面被下拉,默认是true
//        xRefreshView1.setMoveHeadWhenDisablePullRefresh(false);
//        xRefreshView1.enablePullUpWhenLoadCompleted(false);
//		xRefreshView1.setPullLoadEnable(false);
//        xRefreshView1.enableRecyclerViewPullUp(false);
        //设置静默加载时提前加载的item个数
//		xRefreshView1.setPreLoadCount(2);

        xrefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {

                page = 1;
                initData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {

                page++;
                initData();

            }
        });
        adapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BenefitBean bean) {
                Intent intent = new Intent(MineBenefitActivity.this, BenefitParticularsActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("activesId", bean.getId());
                startActivity(intent);
            }

            @Override
            public void onLikeClick(View view, BenefitBean bean) {
                isRefresh = false;
                Map<String, String> formData = new HashMap<String, String>(0);
                formData.put("userId", userId);
                formData.put("articleId", bean.getId());
                formData.put("type", "7");
                formData.put("status", "7");
                tv_like_btn = (TextView) view.findViewById(R.id.tv_like_btn);
                iv_like_btn = (ImageView) view.findViewById(R.id.iv_like_btn);

                presenter.likeBenefitPresenter(formData);
            }

            @Override
            public void onCommentClick(View view, BenefitBean bean) {
                Intent intent = new Intent(MineBenefitActivity.this, FeedForCommentActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("bean", bean);
                startActivity(intent);

            }
        });
    }


    private void initData() {
        isRefresh = true;
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        presenter.getBenefitPresenter(formData);

    }


    @Override
    public MineBenefitPresenter initPresenter() {
        return new MineBenefitPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {
        if (isRefresh)
            if (page == 1) {
                xrefreshview.stopRefresh(false);
            } else {
                xrefreshview.stopLoadMore(false);
            }
        T.showShort(MineBenefitActivity.this, errorString);
    }

    @Override
    public void setBenefitListData(List<BenefitBean> data) {

        if (page == 1) {
            xrefreshview.stopRefresh(true);
        } else {
            xrefreshview.stopLoadMore(true);
        }
        adapter.setData(data, page);
    }

    @Override
    public void likeBenefitView(String data) {
        int likes = Integer.parseInt(tv_like_btn.getText().toString().trim());
        tv_like_btn.setText((likes + 1) + "");
        iv_like_btn.setImageResource(R.mipmap.img_have_thumb_up_btn);
        T.showShort(MineBenefitActivity.this, data);
    }

    @OnClick({R.id.lt_main_title_left, R.id.lt_main_title_right, R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                MineBenefitActivity.this.finish();
                break;
            case R.id.lt_main_title_right:

                break;
            case R.id.iv_top:
                recyclerViewTestRv.smoothScrollToPosition(0);
                break;
        }
    }


}
