package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.VerifyRecommedInfo;

import java.util.ArrayList;

/**
 * Created by just on 2017/3/5.
 */

public interface OnVerifyRecommedInfoFinishListener {
    void showTextEmpty();
    void succeedToVerifyInfo();
    void failedToVerifyInfo(String string);
    void returnParserData(ArrayList<ProvinceBean> data);
    void returnHobby(String hobbys);
    void returnVerifyInfo(VerifyRecommedInfo verifyRecommedInfoBean);
    void returnCharacters(String characters);
}
