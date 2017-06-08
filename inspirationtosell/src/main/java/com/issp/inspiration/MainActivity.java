package com.issp.inspiration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.issp.inspiration.adapter.BannerImageLoader;
import com.issp.inspiration.adapter.IndexPageAdapter;
import com.issp.inspiration.adapter.SimpleAdapter;
import com.issp.inspiration.base.view.BaseMvpActivity;
import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.bean.ImageUrlBean;
import com.issp.inspiration.interfaces.IDealBuyListView;
import com.issp.inspiration.network.HttpUtils;
import com.issp.inspiration.presenters.DealBuyInfoPresenter;
import com.issp.inspiration.ui.activity.AddArticleActivity;
import com.issp.inspiration.ui.activity.CommentMessageActivity;
import com.issp.inspiration.ui.activity.FeedForCommentActivity;
import com.issp.inspiration.ui.activity.ReadDealBuyActivity;
import com.issp.inspiration.utils.DisplayUtils;
import com.issp.inspiration.utils.PreferenceService;
import com.issp.inspiration.utils.T;
import com.issp.inspiration.view.BannerViewPager;
import com.issp.inspiration.view.CustomGifHeader;
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
 * 灵感贩卖
 */
public class MainActivity extends BaseMvpActivity<IDealBuyListView, DealBuyInfoPresenter> implements IDealBuyListView {

    @BindView(R.id.tv_add_article)
    TextView btnAddArticle;
    private PopupWindow mPopupWindow;

    @BindView(R.id.lt_main_title_left)
    TextView lt_main_title_left;
    @BindView(R.id.lt_main_title)
    TextView lt_main_title;
    @BindView(R.id.lt_main_title_right)
    TextView lt_main_title_right;
    @BindView(R.id.recycler_view_test_rv)
    RecyclerView recyclerView;
    @BindView(R.id.xrefreshview)
    XRefreshView xRefreshView;

    private View headerView;

    private boolean isRefresh;

    SimpleAdapter adapter;
    List<DealBuyBean> personList = new ArrayList<DealBuyBean>();
    List<ImageUrlBean> imageUrlBeanList;
    GridLayoutManager layoutManager;
    private int mLoadCount = 0;


    private int limit = 20;
    private int page = 1;

    private String userId;
    Banner homepage_banner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //PreferenceService ps=new PreferenceService(MainActivity.this);
        userId = getIntent().getStringExtra("loginid");
        lt_main_title.setText("灵感贩卖");
        xRefreshView.setPullLoadEnable(true);
        recyclerView.setHasFixedSize(true);

        initData();
        adapter = new SimpleAdapter(personList, this);
        // 设置静默加载模式
//		xRefreshView1.setSilenceLoadMore();
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        headerView = adapter.setHeaderView(R.layout.view_banner, recyclerView);

        homepage_banner = (Banner) headerView.findViewById(R.id.homepage_banner);
        initImageData();

        CustomGifHeader header = new CustomGifHeader(this);
        xRefreshView.setCustomHeaderView(header);
        recyclerView.setAdapter(adapter);
        xRefreshView.setAutoLoadMore(false);
        xRefreshView.setPinnedTime(1500);
        xRefreshView.setMoveForHorizontal(true);
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

        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

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
            public void onItemClick(View view, DealBuyBean bean) {
                Intent intent = new Intent(MainActivity.this, ReadDealBuyActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("activesId", bean.getId());
                startActivity(intent);
            }

            @Override
            public void onLikeClick(View view, DealBuyBean bean) {
                isRefresh = false;
                Map<String, String> formData = new HashMap<String, String>(0);
                formData.put("userId", userId);
                formData.put("articleId", bean.getId());
                formData.put("type", "5");
                formData.put("status", "1");
                tv_like_btn = (TextView) view.findViewById(R.id.tv_like_btn);
                iv_like_btn = (ImageView) view.findViewById(R.id.iv_like_btn);

                presenter.sharePraiseInfoPresenter(formData);
            }

            @Override
            public void onCommentClick(View view, DealBuyBean bean) {
                Intent intent = new Intent(MainActivity.this, FeedForCommentActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("bean", bean);
                startActivity(intent);
            }
        });
    }

    TextView tv_like_btn;
    ImageView iv_like_btn;

    private void initData() {
        isRefresh = true;
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("page", page + "");
        presenter.ShareInfoPresenter(formData);

    }

    private void initImageData() {

        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("type", "5");
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
                Intent intent = new Intent(MainActivity.this, ReadDealBuyActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("activesId", imageUrlBeanList.get(position).getArticleId());
                startActivity(intent);
            }
        });
    }

    @Override
    public DealBuyInfoPresenter initPresenter() {
        return new DealBuyInfoPresenter();
    }


    @OnClick(R.id.lt_main_title_right)
    void initPopupWindow() {
        int width = lt_main_title_right.getWidth();
        int WidthPixels = DisplayUtils.getScreenWidthPixels(MainActivity.this);
        if (null == mPopupWindow || !mPopupWindow.isShowing()) {
            LayoutInflater mLayoutInflater = (LayoutInflater) this
                    .getSystemService(LAYOUT_INFLATER_SERVICE);
            View popwindow_more = mLayoutInflater.inflate(
                    R.layout.popwindow_more, null);
            // AutoUtils.autoSize(popwindow_more, AutoAttr.BASE_HEIGHT);
            mPopupWindow = new PopupWindow(popwindow_more, WidthPixels / 3, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            mPopupWindow.setTouchable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
            mPopupWindow.showAsDropDown(lt_main_title_right, -width, 0);
            TextView tv_information = (TextView) popwindow_more.findViewById(R.id.tv_information);
            TextView tv_my_share = (TextView) popwindow_more.findViewById(R.id.tv_my_share);

            tv_information.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CommentMessageActivity.class);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    mPopupWindow.dismiss();
                }
            });
            tv_my_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* Intent intent = new Intent(MainActivity.this, MinShareActivity.class);
                    startActivity(intent);
                    mPopupWindow.dismiss();*/
                }
            });
        }
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
                xRefreshView.stopRefresh(false);
            } else {
                xRefreshView.stopLoadMore(false);
            }
        T.showShort(MainActivity.this, errorString);
    }

    @Override
    public void setDealBuyListData(ArrayList<DealBuyBean> data) {
        if (page == 1) {
            xRefreshView.stopRefresh(true);
        } else {
            xRefreshView.stopLoadMore(true);
        }
        if (null != data && data.size() > 0) {
            adapter.setData(data, page);
        }
    }

    @Override
    public void getImageUrlView(List<ImageUrlBean> bean) {

        imageUrlBeanList = bean;
        if (null != imageUrlBeanList && imageUrlBeanList.size() > 0)

            initBanner();
    }

    @Override
    public void dealBuyPraise(String data) {
        int likes = Integer.parseInt(tv_like_btn.getText().toString().trim());
        tv_like_btn.setText((likes + 1) + "");
        iv_like_btn.setImageResource(R.mipmap.img_have_thumb_up_btn);
        T.showShort(MainActivity.this, data);
    }

    @OnClick(R.id.tv_add_article)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, AddArticleActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }
}
