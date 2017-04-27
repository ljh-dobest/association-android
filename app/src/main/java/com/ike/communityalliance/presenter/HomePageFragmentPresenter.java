package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.HomePageBean;
import com.ike.communityalliance.interfaces.IHomePageView;
import com.ike.communityalliance.listener.OnGetHomePageDataFinishListener;
import com.ike.communityalliance.module.HomePageFragmentModule;

/**
 * Created by Min on 2017/4/26.
 */

public class HomePageFragmentPresenter extends BasePersenter<IHomePageView> implements OnGetHomePageDataFinishListener {
    private HomePageFragmentModule homePageFragmentModule;

    public HomePageFragmentPresenter() {
        homePageFragmentModule=new HomePageFragmentModule();
    }
   public void getHomePageFragmentData(String userId){
       homePageFragmentModule.getHomePageFragmentData(userId,this);
   }

    @Override
    public void returnHomePageData(HomePageBean homePageBean) {
          if(mView!=null){
              mView.setHomePageData(homePageBean);
          }
    }

    @Override
    public void showErrorString(String errorString) {
      if(mView!=null){
          mView.showError(errorString);
      }
    }
}
