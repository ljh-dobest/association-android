package com.min.helpcenter.presenters;

import com.min.helpcenter.base.presenter.BasePersenter;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.interfaces.IQuestionView;
import com.min.helpcenter.listeners.OnGetQuestionDetailsFinishListener;
import com.min.helpcenter.model.QuestionModule;

/**
 * Created by Min on 2017/3/28.
 */

public class QuestionPresenter extends BasePersenter<IQuestionView> implements OnGetQuestionDetailsFinishListener {
private QuestionModule questionModule;

    public QuestionPresenter() {
       questionModule=new QuestionModule();
    }
    public void getQuestionDetails(String userId,String seekId){
        questionModule.getQuestionDetail(userId,seekId,this);
    }

    @Override
    public void showError(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnQuestion(HelpBean data) {
        if(mView!=null){
          mView.setQuestionDetails(data);
        }
    }
}
