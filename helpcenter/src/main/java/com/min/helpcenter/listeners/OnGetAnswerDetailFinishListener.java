package com.min.helpcenter.listeners;

import com.min.helpcenter.bean.ArticleCommentBean;

/**
 * Created by Min on 2017/4/6.
 */

public interface OnGetAnswerDetailFinishListener {
    void showError( String errorString);
    void returnAnswerDetail(ArticleCommentBean articleComment);
    void showSucceedComment(String msg);
}
