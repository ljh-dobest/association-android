package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.MorePersonalInfo;

/**
 * Created by Min on 2017/5/5.
 */

public interface IMorePersonalInfoView {
    void getMorePersonalInfoData(String userId);
    void setMorePersoalInfoData(MorePersonalInfo info);
    void showError(String errorString);
    void setHobby(String hobbys);
    void saveSucceed();
}
