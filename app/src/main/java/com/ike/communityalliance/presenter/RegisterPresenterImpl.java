package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.interfaces.IRegisterPresenter;
import com.ike.communityalliance.interfaces.IRegisterView;
import com.ike.communityalliance.listener.OnRegisterFinishListener;
import com.ike.communityalliance.module.RegisterMoudle;

/**
 * Created by just on 2017/3/5.
 */

public class RegisterPresenterImpl extends BasePersenter<IRegisterView> implements IRegisterPresenter,OnRegisterFinishListener {
    private RegisterMoudle registerMoudle;
    public RegisterPresenterImpl() {
        this.registerMoudle = new RegisterMoudle();
    }

    @Override
    public void verifyRegisterInfo(String userName, String mobile, String password, String recommendId) {
             registerMoudle.register(userName,mobile,password,recommendId,this);
    }

    @Override
    public void showTextEmpty() {
              mView.showTextEmpty();
    }

    @Override
    public void succeedToRegiset() {
        mView.succeedToRegister();
    }

    @Override
    public void showPwdError() {
        mView.showPwdError();
    }

    @Override
    public void failedToRegister(String string) {
        mView.showRegisterError(string );
    }
}
