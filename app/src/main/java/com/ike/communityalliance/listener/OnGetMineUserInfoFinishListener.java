package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by T-BayMax on 2017/3/14.
 */

public interface OnGetMineUserInfoFinishListener {

    void showError(String errorString);
    void returnMineUserInfo(UserInfo data);
}
