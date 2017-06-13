package com.min.helpcenter.presenters;

import com.min.helpcenter.base.presenter.BasePersenter;
import com.min.helpcenter.bean.MineHelp;
import com.min.helpcenter.interfaces.IMineMessageView;
import com.min.helpcenter.listeners.OnGetMineHelpFinishListener;
import com.min.helpcenter.model.MineMessageFragmentModule;

import java.util.List;

/**
 * Created by Min on 2017/3/31.
 */

public class MineMessageFragmentPresenter extends BasePersenter<IMineMessageView> implements OnGetMineHelpFinishListener {
    private MineMessageFragmentModule mineMessageFragmentModule;

    public MineMessageFragmentPresenter() {
        mineMessageFragmentModule=new MineMessageFragmentModule();
    }
    public void getMineHelpData(String userId,String status){
        mineMessageFragmentModule.getMineHelpData(userId,status,this);
    }

    @Override
    public void showError(String errorString) {
      if(mView!=null){
          mView.showError(errorString);
      }
    }

    @Override
    public void returnMineHelp(List<MineHelp> data) {
        if(mView!=null){
            mView.setMyHelpData(data);
        }
    }
}
