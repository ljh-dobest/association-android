package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.interfaces.IClaimCenterView;
import com.ike.communityalliance.listener.OnClaimPeopleFinishListener;
import com.ike.communityalliance.module.ClaimCenterMoudle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimCenterPresenter extends BasePersenter<IClaimCenterView> implements OnClaimPeopleFinishListener {
    private ClaimCenterMoudle claimCenterMoudle;
    public ClaimCenterPresenter(){
          claimCenterMoudle=new ClaimCenterMoudle();
    }
     public void getClaimPeopleData(String userId){
         claimCenterMoudle.getClaimPeopleData(userId,this);
     }

    @Override
    public void succeedToGetClaimPeople(List<ClaimPeopleBean> peoples) {
        if(mView!=null){
            mView.setPeoplesData((ArrayList<ClaimPeopleBean>) peoples);
        }
    }

    @Override
    public void failedToGetClaimPeople(String string) {
        if(mView!=null){
            mView.showError(string);
        }
    }

    @Override
    public void showErrorString(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }
}
