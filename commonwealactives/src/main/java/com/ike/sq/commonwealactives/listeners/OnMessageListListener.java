package com.ike.sq.commonwealactives.listeners;



import com.ike.sq.commonwealactives.bean.MessageBean;

import java.util.ArrayList;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public interface OnMessageListListener {
    void getCommentMessageInfo(ArrayList<MessageBean> data);
    void showError(String errorString);
}
