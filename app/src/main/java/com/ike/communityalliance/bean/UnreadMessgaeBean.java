package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/4/20.
 */

public class UnreadMessgaeBean implements Parcelable {
     private String userId;
     private String nickname;
     private String userPortraitUrl;
     private String id;
     private String content;
     private String articleId;
     private String articleImages;
     private String commentTime;

    public UnreadMessgaeBean(String userId, String nickname, String userPortraitUrl, String id, String content, String articleId, String articleImages, String commentTime) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.articleImages = articleImages;
        this.commentTime = commentTime;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleImages() {
        return articleImages;
    }

    public void setArticleImages(String articleImages) {
        this.articleImages = articleImages;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.nickname);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.id);
        dest.writeString(this.content);
        dest.writeString(this.articleId);
        dest.writeString(this.articleImages);
        dest.writeString(this.commentTime);
    }

    protected UnreadMessgaeBean(Parcel in) {
        this.userId = in.readString();
        this.nickname = in.readString();
        this.userPortraitUrl = in.readString();
        this.id = in.readString();
        this.content = in.readString();
        this.articleId = in.readString();
        this.articleImages = in.readString();
        this.commentTime = in.readString();
    }

    public static final Creator<UnreadMessgaeBean> CREATOR = new Creator<UnreadMessgaeBean>() {
        @Override
        public UnreadMessgaeBean createFromParcel(Parcel source) {
            return new UnreadMessgaeBean(source);
        }

        @Override
        public UnreadMessgaeBean[] newArray(int size) {
            return new UnreadMessgaeBean[size];
        }
    };
}
