package com.min.helpcenter.presenters;

import com.min.helpcenter.base.presenter.BasePersenter;
import com.min.helpcenter.bean.MineHelp;
import com.min.helpcenter.interfaces.IMineAnswerView;
import com.min.helpcenter.listeners.OnGetMineAnswerFinishListener;
import com.min.helpcenter.model.MineAnswersFragmentModule;

import java.util.List;

/**
 * Created by Min on 2017/3/31.
 */

public class MineAnswerFragmentPresenter extends BasePersenter<IMineAnswerView> implements OnGetMineAnswerFinishListener {
        private MineAnswersFragmentModule mineAnswerFragmentModule;

    public MineAnswerFragmentPresenter() {
        mineAnswerFragmentModule=new MineAnswersFragmentModule();
    }
    public void getMineHelpData(String userId,String status){
        mineAnswerFragmentModule.getMineAnswerData(userId,status,this);
    }

    @Override
    public void showError(String errorString) {
          if(mView!=null){
              mView.showError(errorString);
          }
    }

    @Override
    public void returnMineAnswer(List<MineHelp> data) {
        if(mView!=null){
            mView.setMyAnswer(data);
        }
    }

}
