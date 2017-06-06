package com.ike.communityalliance.interfaces;

import android.view.ViewGroup;

import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.VerifyRecommedInfo;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/6.
 */

public interface IVerifyRecommedInfoView {
    void showTextEmpty();
    void showVerifyInfoError(String string);
    void succeedVerifyInfo();
    void showComfirmDialog();
    void setHobby(String hobbys);
    void setProvinceData(ArrayList<ProvinceBean> provinceData);
    void getHobby(ViewGroup group);
    void getVerifyInfo(String userId,String recommendId);
    void setVerifyInfo(VerifyRecommedInfo verifyInfo);
    void succeedToVip();
}
