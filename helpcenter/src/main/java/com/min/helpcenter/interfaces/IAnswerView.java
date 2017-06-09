package com.min.helpcenter.interfaces;

import com.min.helpcenter.bean.ArticleCommentBean;

/**
 * Created by Min on 2017/3/29.
 */

public interface IAnswerView {
    void getArticleCommentData();
    void setArticleComment(ArticleCommentBean data);
    void showError(String errorString);
    void showSucceedComment(String msg);
}
