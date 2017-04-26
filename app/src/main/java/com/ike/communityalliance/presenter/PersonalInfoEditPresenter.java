package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.interfaces.IPersonalInfoEditView;
import com.ike.communityalliance.listener.OnFinishEditPersonalInfoListener;
import com.ike.communityalliance.module.PersonalInfoEditMoudle;

/**
 * Created by Min on 2017/3/20.
 */

public class PersonalInfoEditPresenter extends BasePersenter<IPersonalInfoEditView> implements OnFinishEditPersonalInfoListener {
   private PersonalInfoEditMoudle personalInfoEditMoudle;

    public PersonalInfoEditPresenter() {
        personalInfoEditMoudle=new PersonalInfoEditMoudle();
    }
    public void postPersonalData(UserInfo userInfo,String type){
     personalInfoEditMoudle.postPersonalInfo(userInfo,type,this);
    }

    @Override
    public void succeedToEdit(String imgPath) {
        if (mView!=null){
         mView.succeedToEdit(imgPath);
        }
    }

    @Override
    public void showError(String errorString) {
     if (mView!=null){
      mView.showError(errorString);
     }
    }
}
