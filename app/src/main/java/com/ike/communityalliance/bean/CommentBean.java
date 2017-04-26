package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/3/27.
 */

public class CommentBean {
    private int id;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String content;
    private String commentTime;
    private List<ReplyUserBean> replyUsers;

    public CommentBean(int id, String userId, String nickname, String userPortraitUrl, String content, String commentTime, List<ReplyUserBean> replyUsers) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.content = content;
        this.commentTime = commentTime;
        this.replyUsers = replyUsers;
    }

    public CommentBean(int id, String commentTime, String content, String userPortraitUrl, String nickname, String userId) {
        this.id = id;
        this.commentTime = commentTime;
        this.content = content;
        this.userPortraitUrl = userPortraitUrl;
        this.nickname = nickname;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public List<ReplyUserBean> getReplyUsers() {
        return replyUsers;
    }

    public void setReplyUsers(List<ReplyUserBean> replyUsers) {
        this.replyUsers = replyUsers;
    }
}
