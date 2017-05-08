package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.MorePersonalInfo;

/**
 * Created by Min on 2017/5/5.
 */

public interface OnFinishGetMorePersonalInfoListener {
    void showError(String errorString);
    void saveSucceed();
    void returnMorePersonalInfo(MorePersonalInfo info);
    void returnHobby(String hobbys);
}
