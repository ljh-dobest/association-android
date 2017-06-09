package com.min.helpcenter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/4/1.
 */

public class AnswersBean implements Parcelable {
    private String id;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String time;
    private String content;
    private String commentNumber;
    private String likes;
    private String likesStatus;

    public AnswersBean(String id, String userId, String nickname, String userPortraitUrl, String time, String content, String commentNumber, String likes, String likesStatus) {
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.time = time;
        this.content = content;
        this.commentNumber = commentNumber;
        this.likes = likes;
        this.likesStatus = likesStatus;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.nickname);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.time);
        dest.writeString(this.content);
        dest.writeString(this.commentNumber);
        dest.writeString(this.likes);
        dest.writeString(this.likesStatus);
    }

    protected AnswersBean(Parcel in) {
        this.id = in.readString();
        this.userId = in.readString();
        this.nickname = in.readString();
        this.userPortraitUrl = in.readString();
        this.time = in.readString();
        this.content = in.readString();
        this.commentNumber = in.readString();
        this.likes = in.readString();
        this.likesStatus = in.readString();
    }

    public static final Creator<AnswersBean> CREATOR = new Creator<AnswersBean>() {
        @Override
        public AnswersBean createFromParcel(Parcel source) {
            return new AnswersBean(source);
        }

        @Override
        public AnswersBean[] newArray(int size) {
            return new AnswersBean[size];
        }
    };
}
