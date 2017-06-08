package com.issp.association.crowdfunding.listeners;



import com.issp.association.crowdfunding.bean.CommentsBean;
import com.issp.association.crowdfunding.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public interface OnMessageListListener {
    void getCommentMessageInfo(List<CommentsBean> data);
    void showError(String errorString);
}
