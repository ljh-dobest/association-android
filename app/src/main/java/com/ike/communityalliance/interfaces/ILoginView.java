package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by just on 2017/3/5.
 */

public interface ILoginView {
    void showUserNameOrPassWordEmpty(String errorString);
    void showFailedLogin(String errorString);
    void succeedToLogin(UserInfo userInfo);
}
