package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.interfaces.IUserDetailView;
import com.ike.communityalliance.listener.OnfinishCheckResultListener;
import com.ike.communityalliance.module.UserDetailMoudle;

/**
 * Created by Min on 2017/3/20.
 */

public class UserDetailPresnter extends BasePersenter<IUserDetailView> implements OnfinishCheckResultListener {
    private UserDetailMoudle userDetailMoudle;
    public UserDetailPresnter() {
        userDetailMoudle=new UserDetailMoudle();
    }
    public void getCheckPhoneResult(String useId,String mobile){
      userDetailMoudle.checkPhone(useId,mobile,this);
    }


    @Override
    public void returnCheckResult(UserInfo userInfo) {
       if (mView!=null){
           mView.checkResult(userInfo);
       }
    }

    @Override
    public void showError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }

    @Override
    public void returnNotUser() {
        if (mView!=null){
            mView.mobileUnRegister();
        }
    }
}
