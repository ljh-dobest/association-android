package com.issp.inspiration.listeners;




import com.issp.inspiration.bean.CommentsBean;

import java.util.List;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public interface OnFeedForCommentListListener {
    void getFeedCommentInfo(List<CommentsBean> data);

    void getAddCommentInfo(String data);
    void commentLikes(String data);

    void showError(String errorString);
}
