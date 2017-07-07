package com.min.threeminutestoteach.interfaces;


import com.min.threeminutestoteach.bean.ArticleCommentBean;

/**
 * Created by Min on 2017/3/29.
 */

public interface IAnswerView {
    void getArticleCommentData();
    void setArticleComment(ArticleCommentBean data);
    void showError(String errorString);
    void showSucceedComment(String msg);
}
