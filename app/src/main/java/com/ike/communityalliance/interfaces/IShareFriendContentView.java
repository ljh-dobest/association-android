package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.CommentBean;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public interface IShareFriendContentView {
    void getCommentData(String userId, String talkTalkId);
    void setCommentData(List<CommentBean> data);
    void showError(String errorString);
    void succeedToComment(String string);
}
