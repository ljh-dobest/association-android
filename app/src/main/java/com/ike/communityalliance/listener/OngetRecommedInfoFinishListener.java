package com.ike.communityalliance.listener;


import com.ike.communityalliance.bean.RecommendInfoBean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/9.
 */

public interface OngetRecommedInfoFinishListener {
    void getRecommedInfo(ArrayList<RecommendInfoBean> data);
    void showError(String errorString);
}
