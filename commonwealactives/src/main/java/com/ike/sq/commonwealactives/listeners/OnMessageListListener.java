package com.ike.sq.commonwealactives.listeners;



import com.ike.sq.commonwealactives.bean.CommentsBean;
import com.ike.sq.commonwealactives.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public interface OnMessageListListener {
    void getCommentMessageInfo(List<CommentsBean> data);
    void showError(String errorString);
}
