package com.ike.communityalliance.ui.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
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
import com.ike.communityalliance.wedget.DemoGridView;
import com.ike.mylibrary.util.T;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by just on 2017/3/1.
 */

public class HomeFragment extends BaseMvpFragment<IHomePageView,HomePageFragmentPresenter> implements IHomePageView,AbsListView.OnScrollListener, AdapterView.OnItemClickListener, OnBannerClickListener {


    LinearLayout homepage_lv_header;
    RelativeLayout home_lv_header2;
    Banner homepage_banner;
    DemoGridView homepage_gv;
    @BindView(R.id.homepage_lv)
    ListView homepage_lv;
    @BindView(R.id.homepage_iv_top)
    ImageView homepage_iv_top;
    private ArrayList<String> imgList;
    private SharedPreferences sp;
    private String useId;
    private String checkVip;
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
        homepage_gv = (DemoGridView) home_lv_header2.findViewById(R.id.homepage_gv);
        ButterKnife.bind(this, containerView);
        sp = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        useId = sp.getString(Const.LOGIN_ID, "");
        checkVip=sp.getString(Const.LOGIN_VIP,"0");
        initView();
        getHomePageData(useId);
        return containerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initGridView();
    }

    @Override
    public HomePageFragmentPresenter initPresenter() {
        return new HomePageFragmentPresenter();
    }

    private void initView() {
        imgList = new ArrayList<>();
        homepage_banner.setOnBannerClickListener(this);
        initListView();
    }

    private void initListView() {
        homepage_lv.addHeaderView(homepage_lv_header);
        homepage_lv.addHeaderView(home_lv_header2);
        adapter = new HomePageLVAdapter(getContext(),checkVip);
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
        for (int i = 0; i < advsBeanList.size(); i++) {
            imgList.add(HttpUtils.IMAGE_RUL + advsBeanList.get(i).getArticleImage());
        }
        //设置图片加载器
        homepage_banner.setImages(imgList)
                .setImageLoader(new BannerImageLoader())
                .start();
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
    public void onStart() {
        super.onStart();
        homepage_banner.startAutoPlay();
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

      if(totalItemCount<6){
          homepage_iv_top.setVisibility(View.GONE);
          return;
      }

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
                    if(checkVip.equals("0")){
                        T.showShort(getContext(),"只有VIP才能认领用户");
                        return;
                    }
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
        advsBeanList=homePageData.getAdvs();
        initBanner(advsBeanList);
        adapter.setData(homePageData);
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError(String errorString) {
          T.showShort(getContext(),errorString);
    }

    @Override
    public void OnBannerClick(int position) {
      AdvsBean advsBean=advsBeanList.get(position-1);
        switch (advsBean.getType()) {
            case "1":
                openAPK("众筹",advsBean.getArticleId());
                break;
        }
    }

    private void openAPK(String type,String articleId) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).title.equals(type)){
               // apkOperator.openApk(list.get(i));
                ComponentName componentName = new ComponentName("com.issp.association.crowdfunding",
                        "com.issp.association.crowdfunding.ui.activity.ProductParticularsActivity");
                Intent intent = new Intent("com.issp.association.crowdfunding.ui.activity.ProductParticularsActivity");
                Bundle bundle = new Bundle();

                SharedPreferences sp=getActivity().getSharedPreferences("config", Context.MODE_APPEND);
                intent.putExtra("userId",sp.getString(Const.LOGIN_ID,""));
                intent.putExtra("articleId",articleId);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);//设置category
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//设置singleTask启动模式
               // intent.putExtras(bundle);
                intent.setComponent(componentName);
                startActivity(intent);
                return;
            }
        }
        T.showShort(getContext(),"您还没安装"+type+"喔!赶紧去应用中心下载吧!!");
    }
}
