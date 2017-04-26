package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.InterestGroupBean;

import java.util.List;

/**
 * Created by Min on 2017/3/20.
 */

public interface OnGetInterestGroupDataFinishListener {
    void returnInterestGroupData(List<InterestGroupBean> gropsData);
    void showErrorString(String errorString);

}
