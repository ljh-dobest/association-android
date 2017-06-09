package com.min.helpcenter.presenters;

import com.min.helpcenter.base.presenter.BasePersenter;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.interfaces.IHelpCenterView;
import com.min.helpcenter.listeners.OnGetHelpDataFinishListener;
import com.min.helpcenter.model.HelpCenterModule;

import java.util.List;

/**
 * Created by Min on 2017/3/28.
 */

public class HelpCenterPresenter extends BasePersenter<IHelpCenterView> implements OnGetHelpDataFinishListener {
    private HelpCenterModule helpCenterModule;
    public  HelpCenterPresenter(){
        helpCenterModule=new HelpCenterModule();
    }
     public void getHelpData(String userId, int page){
         helpCenterModule.getHelpData(userId,page,this);
     }

    @Override
    public void showError(String errorString) {
        if (mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnHelpData(List<HelpBean> data) {
        if (mView!=null){
            mView.setHelpData(data);
        }
    }

    @Override
    public void returnMoreHelpData(List<HelpBean> data) {
        if (mView!=null){
            mView.setMoreHelpData(data);
        }
    }
}
