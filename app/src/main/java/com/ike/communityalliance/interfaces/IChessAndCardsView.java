package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.InterestGroupBean;

import java.util.List;

/**
 * Created by Min on 2017/3/20.
 */

public interface IChessAndCardsView {

     void getListData();
    void setListData(List<InterestGroupBean> groupsData);
    void showErrorString(String errorString);
}
