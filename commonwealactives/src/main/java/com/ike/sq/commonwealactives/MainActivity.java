package com.ike.sq.commonwealactives;

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
import com.ike.sq.commonwealactives.adapter.SimpleAdapter;
import com.ike.sq.commonwealactives.base.adpater.BannerImageLoader;
import com.ike.sq.commonwealactives.base.view.BaseMvpActivity;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.ImageUrlBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.ike.sq.commonwealactives.presenters.BenefitPresenter;
import com.ike.sq.commonwealactives.ui.activity.BenefitParticularsActivity;
import com.ike.sq.commonwealactives.ui.activity.FeedForCommentActivity;
import com.ike.sq.commonwealactives.ui.activity.MessageActivity;
import com.ike.sq.commonwealactives.ui.activity.MineBenefitActivity;
import com.ike.sq.commonwealactives.utils.DisplayUtils;
import com.ike.sq.commonwealactives.utils.PreferenceService;
import com.ike.sq.commonwealactives.utils.T;
import com.ike.sq.commonwealactives.view.BannerViewPager;
import com.ike.sq.commonwealactives.view.CustomGifHeader;
import com.ike.sq.commonwealactives.view.CustomerFooter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
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
 * 公益活动
 * Created by T-BayMax on 2017/5/5.
 */
public class MainActivity extends BaseMvpActivity<IBenefitListView, BenefitPresenter> implements IBenefitListView {

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

    private View headerView;

    SimpleAdapter adapter;
    List<BenefitBean> personList = new ArrayList<BenefitBean>(0);
    List<ImageUrlBean> imageUrlBeanList;
    GridLayoutManager layoutManager;
    private int mLoadCount = 0;

    private int limit = 20;
    private int page = 1;

    private boolean isRefresh;
    Banner homepage_banner;

    private PopupWindow mPopupWindow;

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
        userId = getIntent().getStringExtra("loginid");
        App.checkVip= Integer.parseInt(getIntent().getStringExtra("checkVip"));
        //userId = "110";
        xrefreshview.setPullLoadEnable(true);
        recyclerViewTestRv.setHasFixedSize(true);

        initData();
        adapter = new SimpleAdapter(personList, this);
        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();
        layoutManager = new GridLayoutManager(this, 1);
        recyclerViewTestRv.setLayoutManager(layoutManager);

        headerView = adapter.setHeaderView(R.layout.view_banner, recyclerViewTestRv);

        homepage_banner = (Banner) headerView.findViewById(R.id.homepage_banner);
        initImageData();

        CustomGifHeader header = new CustomGifHeader(this);
        xrefreshview.setCustomHeaderView(header);
        recyclerViewTestRv.setAdapter(adapter);
        xrefreshview.setAutoLoadMore(false);
        xrefreshview.setPinnedTime(1000);
        xrefreshview.setMoveForHorizontal(true);
//        recyclerviewAdapter.setHeaderView(headerView, recyclerView);
        //当需要使用数据不满一屏时不显示点击加载更多的效果时，解注释下面的三行代码
        //并注释掉第四行代码
        CustomerFooter customerFooter = new CustomerFooter(this);
        customerFooter.setRecyclerView(recyclerViewTestRv);
        adapter.setCustomLoadMoreView(customerFooter);
        //adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
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
                Intent intent = new Intent(MainActivity.this, BenefitParticularsActivity.class);
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
                formData.put("status", "1");
                tv_like_btn = (TextView) view.findViewById(R.id.tv_likes);
                iv_like_btn = (ImageView) view.findViewById(R.id.iv_likes);

                presenter.likeBenefitPresenter(formData);
            }

            @Override
            public void onCommentClick(View view, BenefitBean bean) {
                Intent intent = new Intent(MainActivity.this, FeedForCommentActivity.class);
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
        // formData.put("type", "1");
        //formData.put("limit", limit + "");
        formData.put("page", page + "");
        presenter.getBenefitPresenter(formData);

    }

    private void initImageData() {

        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("type", "7");
        presenter.getImage(formData);
    }

    private void initBanner() {
        List<String> imgList = new ArrayList<String>(0);

        for (ImageUrlBean url : imageUrlBeanList) {
            imgList.add(HttpUtils.IMAGE_RUL + url.getImages());
        }
        //设置图片加载器
        homepage_banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        homepage_banner.setImages(imgList);
        //设置滚动时间
        homepage_banner.setDelayTime(5000);

        //banner设置方法全部调用完毕时最后调用
        homepage_banner.start();
        homepage_banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(MainActivity.this, BenefitParticularsActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("activesId", imageUrlBeanList.get(position).getArticleId());
                startActivity(intent);
            }
        });
    }


    @Override
    public BenefitPresenter initPresenter() {
        return new BenefitPresenter();
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
        T.showShort(MainActivity.this, errorString);
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
    public void getImageUrlView(List<ImageUrlBean> bean) {
        imageUrlBeanList = bean;
        if (null != imageUrlBeanList && imageUrlBeanList.size() > 0)
            initBanner();
    }

    @Override
    public void likeBenefitView(String data) {
        int likes = Integer.parseInt(tv_like_btn.getText().toString().trim());
        tv_like_btn.setText((likes + 1) + "");
        iv_like_btn.setImageResource(R.mipmap.img_have_thumb_up_btn);
        T.showShort(MainActivity.this, data);
    }

    @OnClick({R.id.lt_main_title_left, R.id.lt_main_title_right, R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                MainActivity.this.finish();
                break;
            case R.id.lt_main_title_right:
                initPopupWindow();
                break;
            case R.id.iv_top:
                recyclerViewTestRv.smoothScrollToPosition(0);
                break;
        }
    }

    /**
     * 右上角弹出框
     */
    private void initPopupWindow() {
        int width = ltMainTitleLeft.getWidth();
        int WidthPixels = DisplayUtils.getScreenWidthPixels(MainActivity.this);
        if (null == mPopupWindow || !mPopupWindow.isShowing()) {
            LayoutInflater mLayoutInflater = (LayoutInflater) this
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popwindow_more = mLayoutInflater.inflate(
                    R.layout.popwindow_more, null);

            AutoUtils.autoSize(popwindow_more, AutoAttr.BASE_HEIGHT);

            mPopupWindow = new PopupWindow(popwindow_more, WidthPixels / 3, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            mPopupWindow.showAsDropDown(ltMainTitleRight, -width, 0);
            TextView tv_information = (TextView) popwindow_more.findViewById(R.id.tv_information);
            TextView tv_my_share = (TextView) popwindow_more.findViewById(R.id.tv_my_share);

            tv_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    mPopupWindow.dismiss();
                }
            });
            tv_my_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MainActivity.this, MineBenefitActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    mPopupWindow.dismiss();
                }
            });
        }
    }

}
