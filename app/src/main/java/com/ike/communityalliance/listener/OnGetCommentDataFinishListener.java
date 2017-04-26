package com.ike.communityalliance.listener;

import com.ike.communityalliance.bean.CommentBean;

import java.util.List;

/**
 * Created by Min on 2017/3/23.
 */

public interface OnGetCommentDataFinishListener {
    void showError(String errorString);
    void returnCommentData(List<CommentBean> data);
    void succeedToComment(String string);
}
