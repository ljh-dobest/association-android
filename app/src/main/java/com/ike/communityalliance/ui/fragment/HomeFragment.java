package com.ike.communityalliance.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.BannerImageLoader;
import com.ike.communityalliance.adapter.HomePageGVAdapter;
import com.ike.communityalliance.adapter.HomePageLVAdapter;
import com.ike.communityalliance.base.BaseMvpFragment;
import com.ike.communityalliance.bean.AdvsBean;
import com.ike.communityalliance.bean.ApkItem;
import com.ike.communityalliance.bean.HomePageBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IHomePageView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.HomePageFragmentPresenter;
import com.ike.communityalliance.ui.activity.AddApplicationActivity;
import com.ike.communityalliance.ui.activity.ClaimActiviy;
import com.ike.communityalliance.utils.ApkOperator;
import com.ike.mylibrary.util.T;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by just on 2017/3/1.
 */

public class HomeFragment extends BaseMvpFragment<IHomePageView, HomePageFragmentPresenter> implements IHomePageView, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    LinearLayout homepage_lv_header;
    RelativeLayout home_lv_header2;
    Banner homepage_banner;
    GridView homepage_gv;
    @BindView(R.id.homepage_lv)
    ListView homepage_lv;
    @BindView(R.id.homepage_iv_top)
    ImageView homepage_iv_top;
    private ArrayList<String> imgList;
    private SharedPreferences sp;
    private String useId;
    private ArrayList<String> data;
    private List<AdvsBean> advsBeanList;
    private HomePageLVAdapter adapter;
    ArrayList<ApkItem> list;
    private ImageView iv_home_appcenter;

    ApkOperator apkOperator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View containerView = inflater.inflate(R.layout.homepage_fragment, container, false);
        homepage_lv_header = (LinearLayout) inflater.inflate(R.layout.homepage_lv_header, null);
        iv_home_appcenter = (ImageView) homepage_lv_header.findViewById(R.id.iv_home_appcenter);
        home_lv_header2 = (RelativeLayout) inflater.inflate(R.layout.home_lv_header2, null);
        homepage_banner = (Banner) homepage_lv_header.findViewById(R.id.homepage_banner);
        homepage_gv = (GridView) home_lv_header2.findViewById(R.id.homepage_gv);
        ButterKnife.bind(this, containerView);
        sp = getContext().getSharedPreferences("config", Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
        useId = sp.getString(Const.LOGIN_ID, "");
        initView();
        getHomePageData(useId);
        return containerView;
    }

    @Override
    public HomePageFragmentPresenter initPresenter() {
        return new HomePageFragmentPresenter();
    }

    private void initView() {
        initGridView();
        initListView();
    }

    private void initListView() {
        homepage_lv.addHeaderView(homepage_lv_header);
        homepage_lv.addHeaderView(home_lv_header2);
        adapter = new HomePageLVAdapter(getContext());
        homepage_lv.setAdapter(adapter);
        homepage_lv.setOnScrollListener(this);
        homepage_lv.setOnItemClickListener(this);
        iv_home_appcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddApplicationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initGridView() {
        apkOperator = new ApkOperator(getActivity());
        list = apkOperator.getApkFromInstall();
        /*data = new ArrayList<>();
        data.add("认领中心");*/
        list.add(0, new ApkItem("认领中心"));
        homepage_gv.setAdapter(new HomePageGVAdapter(getContext(), list));
        homepage_gv.setOnItemClickListener(this);
    }

    private void initBanner(List<AdvsBean> advsBeanList) {
        imgList = new ArrayList<>();
        for (int i = 0; i < advsBeanList.size(); i++) {
            imgList.add(HttpUtils.IMAGE_RUL + advsBeanList.get(i).getArticleImage());
        }
        //设置图片加载器
        homepage_banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        homepage_banner.setImages(imgList);
        //banner设置方法全部调用完毕时最后调用
        homepage_banner.start();
    }

    @OnClick(R.id.homepage_iv_top)
    public void toTopOnClick(View view) {
        switch (view.getId()) {
            case R.id.homepage_iv_top:
                homepage_lv.smoothScrollToPosition(0);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        homepage_banner.stopAutoPlay();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount < 4) {
            homepage_iv_top.setVisibility(View.GONE);
            return;
        }
        if (visibleItemCount + firstVisibleItem == totalItemCount) {
            homepage_iv_top.setVisibility(View.VISIBLE);
        } else {
            homepage_iv_top.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.homepage_gv) {
            switch (position) {
                case 0:
                    startActivity(new Intent(getActivity(), ClaimActiviy.class));
                    break;
                default:
                    apkOperator.openApk(list.get(position));
                    break;

            }
        } else {
            //跳到认领信息页面
        }

    }

    @Override
    public void getHomePageData(String userId) {
        presenter.getHomePageFragmentData(userId);
    }

    @Override
    public void setHomePageData(HomePageBean homePageData) {
        initBanner(homePageData.getAdvs());
        adapter.setData(homePageData);
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
}
