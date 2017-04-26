package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.interfaces.ILoginPresenter;
import com.ike.communityalliance.interfaces.ILoginView;
import com.ike.communityalliance.listener.OnLoginFinishListener;
import com.ike.communityalliance.module.LoginMoudle;

/**
 * Created by just on 2017/3/5.
 */

public class LoginPresenterImpl extends BasePersenter<ILoginView> implements ILoginPresenter,OnLoginFinishListener {
    private LoginMoudle loginMoudle;
    public LoginPresenterImpl() {
        this.loginMoudle = new LoginMoudle();
    }

    @Override
    public void verifyLoginInfo(String userName, String password) {
               loginMoudle.login(userName,password,this);
    }
    @Override
    public void userNameOrPassWordError() {
        if(mView!=null){
            mView.showUserNameOrPassWordEmpty("用户名或密码不能为空");
        }
    }

    @Override
    public void succeedToLogin(UserInfo userInfo) {
        if(mView!=null){
            mView.succeedToLogin(userInfo);
        }
    }

    @Override
    public void failedToLogin(String string) {
        if(mView!=null){
            mView.showFailedLogin(string);
        }
    }
}
