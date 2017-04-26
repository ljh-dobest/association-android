package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.DateBean;
import com.ike.communityalliance.interfaces.ISignPickerView;
import com.ike.communityalliance.listener.OnGetSignPickerDataFinishListener;
import com.ike.communityalliance.module.SignPickerMoudle;

import java.util.List;

/**
 * Created by Min on 2017/3/10.
 */

public class SignPickerPresenter extends BasePersenter<ISignPickerView> implements OnGetSignPickerDataFinishListener {
    private SignPickerMoudle signPickerMoudle;
    public SignPickerPresenter(){
        signPickerMoudle=new SignPickerMoudle();
    }

  public void getSignPickerData(String userId){
               signPickerMoudle.getSignPickerData(userId,this);
  }
    public void sign(String useId){
          signPickerMoudle.sign(useId,this);
    }
    @Override
    public void showError(String errorString) {
      if(mView!=null){
        mView.showErrorString(errorString);
      }
    }

    @Override
    public void returnSignPickerData(List<DateBean> data) {
        if(mView!=null){
            mView.setSingPickerData(data);
        }
    }

    @Override
    public void succeedToSign(String msg) {
        if(mView!=null){
            mView.succeedToSign(msg);
        }
    }

}
