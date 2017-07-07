package com.min.threeminutestoteach.presenter;

import com.min.threeminutestoteach.base.BasePersenter;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.interfaces.ITeacheContentView;
import com.min.threeminutestoteach.listener.OnFinishTeachContentListener;
import com.min.threeminutestoteach.module.TeacheContentModule;

/**
 * Created by Min on 2017/6/12.
 */

public class TeacheContentPresenter extends BasePersenter<ITeacheContentView> implements OnFinishTeachContentListener {
    TeacheContentModule teacheContentModule;
    public TeacheContentPresenter(){
        teacheContentModule=new TeacheContentModule();
    }

    public void getTeacheContent(String userId,String activesId){
            teacheContentModule.getTeachContent(userId,activesId,this);
    }
    public  void clickToLike(String useId,String articleId,String status){
        teacheContentModule.clickToLike(useId,articleId,status,this);
    }
   public void clickToCollection(String userId,String articleId,String status){
       teacheContentModule.clickToCollection(userId,articleId,status,this);
   }

    @Override
    public void showError(String errorString) {
        if(mView!=null){
             mView.showError(errorString);
        }
    }

    @Override
    public void returnTeachContentData(TeacheBean data) {
        if(mView!=null){
             mView.setTeachContent(data);
        }
    }

    @Override
    public void succeedToLike() {
        if(mView!=null){
             mView.succeedToLike();
        }
    }

    @Override
    public void succeedToCollection(String status) {
        if(mView!=null){
            mView.succeedToCollection(status);
        }
    }
}
