package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.bean.UnreadMessgaeBean;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public interface IShareFriendsView{
    void getUnreadMessageData(String userId);
    void setUnreadMessageData(List<UnreadMessgaeBean> data);
    void getTalkTalkData(String userId,int page);
    void setTalkTalkData(List<TalkTalkBean> data);
    void showError(String errorString);
    void setLocalData(List<TalkTalkBean> data);
    void setLoadMoreData(List<TalkTalkBean> data);
}
