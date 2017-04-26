package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by just on 2017/3/5.
 */

public interface OnLoginFinishListener {
    void userNameOrPassWordError();
    void succeedToLogin(UserInfo userInfo);
    void failedToLogin(String string);
}
