package com.issp.association.listeners;


import com.issp.association.bean.CommentsBean;
import com.issp.association.bean.ShareBean;
import com.issp.association.bean.ShareCommentBean;

import java.util.ArrayList;
import java.util.List;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public interface OnCommentMessageListListener {
    void getCommentMessageInfo(List<CommentsBean> data);
    void showError(String errorString);
}
