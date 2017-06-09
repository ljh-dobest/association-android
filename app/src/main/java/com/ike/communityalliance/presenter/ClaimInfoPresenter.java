package com.ike.communityalliance.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.ClaimInfoBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.interfaces.IClaimInfoView;
import com.ike.communityalliance.listener.OnClaimFinishListener;
import com.ike.communityalliance.module.ClaimInfoMoudle;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimInfoPresenter extends BasePersenter<IClaimInfoView> implements OnClaimFinishListener {
    private ClaimInfoMoudle claimInfoMoudle;
    public ClaimInfoPresenter(){
        claimInfoMoudle=new ClaimInfoMoudle();
    }
     public void postClaimPeopleInfo(ClaimInfoBean claimInfo ){
         mView.showLoading();
            claimInfoMoudle.postClaimInfo(claimInfo,this);
     }

      public void getParserData(Context context, String fileName){
          claimInfoMoudle.getParserData(context,fileName,this);
      }
     public void getHobbys(ViewGroup group){
                claimInfoMoudle.getHobby(group,this);
     }
    public void getCharacters(ViewGroup group) {
        claimInfoMoudle.getCharacters(group,this);
    }
    @Override
    public void showTextEmpty() {
        if(mView!=null){
            mView.showTextEmpty();
            mView.hideLoading();
        }
    }

    @Override
    public void succeedToClaim() {
        if(mView!=null){
            mView.hideLoading();
            mView.showSucceedClaim();
        }
    }

    @Override
    public void failedToClaim(String string) {
        if(mView!=null){
            mView.hideLoading();
            mView.showFailClaim();
        }
    }

    @Override
    public void waitClaim() {
        if(mView!=null){
            mView.hideLoading();
            mView.showWaitClaim();
        }
    }

    @Override
    public void returnParserData(ArrayList<ProvinceBean> provinces) {
        if(mView!=null){
            mView.setprovinceData(provinces);
        }
    }

    @Override
    public void retturnHobbys(String hobbys) {
        if(mView!=null){
            mView.setHobbys(hobbys);
        }
    }

    @Override
    public void returnCharacters(String characters) {
        if(mView!=null){
            mView.setCharacters(characters);
        }
    }
}
