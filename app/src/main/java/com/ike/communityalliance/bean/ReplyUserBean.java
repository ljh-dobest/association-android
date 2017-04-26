package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/4/19.
 */

public class ReplyUserBean {
    private String id;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String content;
    private String fromId;
    private String fromUserId;
    private String fromNickname;
    private String fromPortraitUrl;
    private String fromContent;
    private String commentTime;
    private String likes;
    private String likesStatus;

    public ReplyUserBean(String id, String userId, String nickname, String userPortraitUrl, String content, String fromId, String fromUserId, String fromNickname, String fromPortraitUrl, String fromContent, String commentTime, String likes, String likesStatus) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.content = content;
        this.fromId = fromId;
        this.fromUserId = fromUserId;
        this.fromNickname = fromNickname;
        this.fromPortraitUrl = fromPortraitUrl;
        this.fromContent = fromContent;
        this.commentTime = commentTime;
        this.likes = likes;
        this.likesStatus = likesStatus;
    }

    public ReplyUserBean(String content, String nickname, String fromNickname) {
        this.content = content;
        this.nickname = nickname;
        this.fromNickname = fromNickname;
    }

    public ReplyUserBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public String getFromPortraitUrl() {
        return fromPortraitUrl;
    }

    public void setFromPortraitUrl(String fromPortraitUrl) {
        this.fromPortraitUrl = fromPortraitUrl;
    }

    public String getFromContent() {
        return fromContent;
    }

    public void setFromContent(String fromContent) {
        this.fromContent = fromContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikesStatus() {
        return likesStatus;
    }

    public void setLikesStatus(String likesStatus) {
        this.likesStatus = likesStatus;
    }
}
