package com.min.helpcenter.bean;

import java.util.List;

/**
 * Created by Min on 2017/4/6.
 */

public class ArticleCommentBean {
    private String title;
    private String nickname;
    private String userPortraitUrl;
    private String content;
    private String time;
    private List<CommentBean> comments;

    public ArticleCommentBean(String title, String nickname, String userPortraitUrl, String content, String time, List<CommentBean> comments) {
        this.title = title;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.content = content;
        this.time = time;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<CommentBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentBean> comments) {
        this.comments = comments;
    }
}
