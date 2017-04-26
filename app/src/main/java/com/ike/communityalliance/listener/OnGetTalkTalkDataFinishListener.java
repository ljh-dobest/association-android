package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.bean.UnreadMessgaeBean;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public interface OnGetTalkTalkDataFinishListener {
    void showError(String errorString);
    void returnTalkTalkData(List<TalkTalkBean> data);
    void returnLocalData(List<TalkTalkBean> data);
    void returnLoadMoreData(List<TalkTalkBean> data);
    void returnUnreadMessageData(List<UnreadMessgaeBean> data);
}
