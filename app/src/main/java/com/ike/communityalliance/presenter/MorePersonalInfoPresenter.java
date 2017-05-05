package com.ike.communityalliance.presenter;

import android.view.ViewGroup;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.EditMorePersonalInfo;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.interfaces.IMorePersonalInfoView;
import com.ike.communityalliance.listener.OnFinishGetMorePersonalInfoListener;
import com.ike.communityalliance.module.MorePersonalInfoMoudle;

/**
 * Created by Min on 2017/5/5.
 */

public class MorePersonalInfoPresenter extends BasePersenter<IMorePersonalInfoView> implements OnFinishGetMorePersonalInfoListener {
private MorePersonalInfoMoudle morePersonalInfoMoudle;

    public MorePersonalInfoPresenter() {
        morePersonalInfoMoudle=new MorePersonalInfoMoudle();
    }
    public void getMorePersonalInfoData(String userId){
        morePersonalInfoMoudle.getMorePersonalInfoData(userId,this);
    }
    public void postMorePersonalInfo(EditMorePersonalInfo editMorePersonalInfo){
          morePersonalInfoMoudle.postMorePersonalInfo(editMorePersonalInfo,this);
    }
     public void getHobby(ViewGroup group){
         morePersonalInfoMoudle.getHobby(group,this);
     }
    @Override
    public void showError(String errorString) {
          if(mView!=null){
              mView.showError(errorString);
          }
    }

    @Override
    public void saveSucceed() {
        if(mView!=null){
            mView.saveSucceed();
        }
    }

    @Override
    public void returnMorePersonalInfo(MorePersonalInfo info) {
        if(mView!=null){
            mView.setMorePersoalInfoData(info);
        }
    }

    @Override
    public void returnHobby(String hobbys) {
        if(mView!=null){
            mView.setHobby(hobbys);
        }
    }
}
