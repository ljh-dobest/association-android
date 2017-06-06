package com.ike.communityalliance.interfaces;

import android.view.ViewGroup;

import com.ike.communityalliance.bean.ProvinceBean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/6.
 */

public interface IPersonalApplayView {
    void showTextEmpty();
    void showRecommedError(String string);
    void succeedToRecommed(String RecommendId);
    void initView();
    void getViewData();
    void getparserData(ArrayList<ProvinceBean> provinces);
    void getHobbys(ViewGroup group);
    void setHobbys(String hobbys);
    void getCharacters(ViewGroup group);
    void setCharacters(String characters);
}
