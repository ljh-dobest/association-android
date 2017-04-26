package com.ike.communityalliance.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Min on 2017/3/22.
 */

public class TalkTalkBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String content;
    private String releaseTime;
    private int likedNumber;
    private int commentNumber;
    private int likeStatus;
    private List<String> images;

    public TalkTalkBean() {
    }

    public TalkTalkBean(String id, String userId, String nickname, String userPortraitUrl, String content, String releaseTime, int likedNumber, int commentNumber, int likeStatus, List<String> images) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.content = content;
        this.releaseTime = releaseTime;
        this.likedNumber = likedNumber;
        this.commentNumber = commentNumber;
        this.likeStatus = likeStatus;
        this.images = images;
    }

    public TalkTalkBean(String userId, String nickname, String userPortraitUrl, String content, String releaseTime, List<String> images) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.content = content;
        this.releaseTime = releaseTime;
        this.images = images;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getLikedNumber() {
        return likedNumber;
    }

    public void setLikedNumber(int likedNumber) {
        this.likedNumber = likedNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
