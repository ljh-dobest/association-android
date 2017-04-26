package com.ike.communityalliance.interfaces;

/**
 * Created by Min on 2017/3/6.
 */

public interface IRegisterView {
    void showTextEmpty();
    void showRegisterError(String string);
    void showPwdError();
    void succeedToRegister();
}
