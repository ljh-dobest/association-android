package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.HomePageBean;

/**
 * Created by Min on 2017/4/26.
 */

public interface OnGetHomePageDataFinishListener {
    void returnHomePageData(HomePageBean homePageBean);
    void showErrorString(String errorString);
}
