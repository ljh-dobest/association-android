package com.ike.sq.commonwealactives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.ike.sq.commonwealactives.adapter.SimpleAdapter;
import com.ike.sq.commonwealactives.base.adpater.BannerImageLoader;
import com.ike.sq.commonwealactives.base.view.BaseMvpActivity;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.presenters.BenefitPresenter;
import com.ike.sq.commonwealactives.utils.PreferenceService;
import com.ike.sq.commonwealactives.utils.T;
import com.ike.sq.commonwealactives.view.BannerViewPager;
import com.ike.sq.commonwealactives.view.CustomGifHeader;
import com.youth.banner.Banner;

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
    List<BenefitBean> personList = new ArrayList<BenefitBean>();
    GridLayoutManager layoutManager;
    private int mLoadCount = 0;

    private int limit = 20;
    private int page = 1;

    private boolean isRefresh;
    Banner homepage_banner;
    private boolean isIDCard;


    private String userId;

    private ArrayList<String> imgList;
    String[] images = {"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=699105693,866957547&fm=21&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=787324823,4149955059&fm=21&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2152422253,1846971893&fm=21&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3258213409,1470632782&fm=21&gp=0.jpg"};


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
        PreferenceService ps = new PreferenceService(MainActivity.this);
        userId = ps.getPreferences("loginid");

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
        initBanner();

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
                /*Intent intent = new Intent(MainActivity.this, ProductParticularsActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("bean", bean);
                startActivity(intent);*/
            }

            @Override
            public void onLikeClick(View view, BenefitBean bean) {
                isRefresh = false;
              /*  Map<String, String> formData = new HashMap<String, String>(0);
                formData.put("userId", userId);
                formData.put("articleId", bean.getId());
                formData.put("type", "1");
                formData.put("status", "1");
                tv_like_btn = (TextView) view.findViewById(R.id.tv_like_btn);
                iv_like_btn = (ImageView) view.findViewById(R.id.iv_like_btn);

                presenter.postUserPraise(formData);*/
            }

            @Override
            public void onCommentClick(View view, BenefitBean bean) {
             /*   Intent intent = new Intent(MainActivity.this, FeedForCommentActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("bean", bean);
                startActivity(intent);*/

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

    private void initBanner() {
        imgList = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            imgList.add(images[i]);
        }
        //设置图片加载器
        homepage_banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        homepage_banner.setImages(imgList);
        //设置滚动时间
        homepage_banner.setDelayTime(5000);
        //banner设置方法全部调用完毕时最后调用
        homepage_banner.start();
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

    @OnClick({R.id.lt_main_title_left, R.id.lt_main_title_right, R.id.iv_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                break;
            case R.id.lt_main_title_right:
                break;
            case R.id.iv_top:
                break;
        }
    }
}
