package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.bean.UnreadMessgaeBean;
import com.ike.communityalliance.interfaces.IShareFriendsView;
import com.ike.communityalliance.listener.OnGetTalkTalkDataFinishListener;
import com.ike.communityalliance.module.ShareFriendsModule;
import com.zhy.base.cache.disk.DiskLruCacheHelper;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public class ShareFriendsPresenter extends BasePersenter<IShareFriendsView> implements OnGetTalkTalkDataFinishListener {
private ShareFriendsModule shareFriendsModule;
    public ShareFriendsPresenter(){
        shareFriendsModule=new ShareFriendsModule();
    }
    public void getTalkTalkData(String userId,int page){
        shareFriendsModule.getTalkTalkData(userId,page,this);
    }
    public void getUnreadMessageData(String userId){
        shareFriendsModule.getUnreadMessageData(userId,this);
    }
    @Override
    public void showError(String errorString) {
        if (mView!=null){
            mView.showError(errorString);
        }
    }

    public void getLocalData(DiskLruCacheHelper helper){
        shareFriendsModule.getLocalData(helper,this);
    }
    @Override
    public void returnTalkTalkData(List<TalkTalkBean> data) {
       if(mView!=null){
           mView.setTalkTalkData(data);
       }
    }

    @Override
    public void returnLocalData(List<TalkTalkBean> data) {
              if(mView!=null){
                  mView.setLocalData(data);
              }
    }

    @Override
    public void returnLoadMoreData(List<TalkTalkBean> data) {
        if(mView!=null){
            mView.setLoadMoreData(data);
        }
    }

    @Override
    public void returnUnreadMessageData(List<UnreadMessgaeBean> data) {
        if(mView!=null){
            mView.setUnreadMessageData(data);
        }
    }
}
