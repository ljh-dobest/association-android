package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by Min on 2017/5/15.
 */

public interface OnfinishCheckResultListener {
    void returnCheckResult(UserInfo userInfo);
    void showError(String error);
    void returnNotUser();
}
