package com.min.threeminutestoteach.presenter;


import com.min.threeminutestoteach.base.BasePersenter;
import com.min.threeminutestoteach.bean.ArticleCommentBean;
import com.min.threeminutestoteach.interfaces.IAnswerView;
import com.min.threeminutestoteach.listener.OnGetAnswerDetailFinishListener;
import com.min.threeminutestoteach.module.AnswerModule;

/**
 * Created by Min on 2017/3/28.
 */

public class AnswerPresenter extends BasePersenter<IAnswerView> implements OnGetAnswerDetailFinishListener {
    private AnswerModule answerModule;
    public AnswerPresenter() {
       answerModule=new AnswerModule();
    }

    public void getAnswersDetail(String articleId, String userId, String type){
        answerModule.getAnswerDetail(articleId,userId,type,this);
    }
    public void commentAnswer(String id, String userId, String content, String type){
        answerModule.commentAnswer(id,userId,content,type,this);
    }
    @Override
    public void showError(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnAnswerDetail(ArticleCommentBean articleComment) {
         if (mView!=null){
             mView.setArticleComment(articleComment);
         }
    }

    @Override
    public void showSucceedComment(String msg) {
        if (mView!=null){
            mView.showSucceedComment(msg);
        }
    }
}
