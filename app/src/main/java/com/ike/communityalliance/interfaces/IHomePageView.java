package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.HomePageBean;

/**
 * Created by Min on 2017/3/10.
 */

public interface IHomePageView extends BaseView {
    void getHomePageData(String userId);
    void setHomePageData(HomePageBean homePageData);
    void showError(String errorString);
}
