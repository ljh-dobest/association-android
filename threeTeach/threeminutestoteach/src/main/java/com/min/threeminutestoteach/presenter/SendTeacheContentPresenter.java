package com.min.threeminutestoteach.presenter;

import com.min.threeminutestoteach.base.BasePersenter;
import com.min.threeminutestoteach.interfaces.ISendTeacheContentView;
import com.min.threeminutestoteach.listener.OnFinishSendTeachContentListener;
import com.min.threeminutestoteach.module.SendTeachContentModule;

import java.io.File;

/**
 * Created by Min on 2017/6/12.
 */

public class SendTeacheContentPresenter extends BasePersenter<ISendTeacheContentView> implements OnFinishSendTeachContentListener {
    SendTeachContentModule sendTeachContentModule;
    public  SendTeacheContentPresenter(){
        sendTeachContentModule=new SendTeachContentModule();
    }
    public void sendTeachContent(String userId, String title, String content,String playTime, String status, File imgFile, File videoFile){
        sendTeachContentModule.sendTeachContent(userId,title,content,playTime,status,imgFile,videoFile,this);
    }

    @Override
    public void showError(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void succeedToPublic() {
        if(mView!=null){
            mView.succeedToPublic();
        }
    }

    @Override
    public void currentProgress(float progress) {
        if(mView!=null){
            mView.currentProgress(progress);
        }
    }


}
