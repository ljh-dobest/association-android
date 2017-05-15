package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by Min on 2017/5/15.
 */

public interface IUserDetailView {
    void checkPhoneConact(String userId,String mobile);
    void checkResult(UserInfo userInfo);
    void showError(String error);
    void mobileUnRegister();
}
