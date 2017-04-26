package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.CommentBean;
import com.ike.communityalliance.interfaces.IShareFriendContentView;
import com.ike.communityalliance.listener.OnGetCommentDataFinishListener;
import com.ike.communityalliance.module.ShareFriendContentModule;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public class ShareFriendContentPresenter extends BasePersenter<IShareFriendContentView> implements OnGetCommentDataFinishListener {
private ShareFriendContentModule shareFriendContentModule;
    public ShareFriendContentPresenter(){
        shareFriendContentModule=new ShareFriendContentModule();
    }
    public void getCommentData(String userId,String talkTalkId){
        shareFriendContentModule.getCommentData(userId,talkTalkId,this);
    }
  public void postComment(String id,String userId,String content){
    shareFriendContentModule.postComment(id,userId,content,this);
  }
    @Override
    public void showError(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnCommentData(List<CommentBean> data) {
        if(mView!=null){
            mView.setCommentData(data);
        }
    }

    @Override
    public void succeedToComment(String string) {
        if (mView!=null){
                mView.succeedToComment(string);
        }
    }
}
