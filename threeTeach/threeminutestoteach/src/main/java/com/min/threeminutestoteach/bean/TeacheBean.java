package com.min.threeminutestoteach.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/5/22.
 */

public class TeacheBean implements Parcelable {
   private String userId;
   private String nickname;
   private String userPortraitUrl;
   private String id;
   private String title;
   private String imageUrl;
   private String image;
   private String content;
   private String videoUrl;
   private String likes;
   private String likesStatus;
   private String commentNumber;
    private String playTime;
   private String time;
   private String collectionNumber;
   private String downloadNumber;
   private String shareNumber;
   private String checkCollect;

    public TeacheBean(String userId, String nickname, String userPortraitUrl, String id, String title, String imageUrl, String image, String content, String videoUrl, String likes, String likesStatus, String commentNumber, String playTime, String time, String collectionNumber, String downloadNumber, String shareNumber, String checkCollect) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.image = image;
        this.content = content;
        this.videoUrl = videoUrl;
        this.likes = likes;
        this.likesStatus = likesStatus;
        this.commentNumber = commentNumber;
        this.playTime = playTime;
        this.time = time;
        this.collectionNumber = collectionNumber;
        this.downloadNumber = downloadNumber;
        this.shareNumber = shareNumber;
        this.checkCollect = checkCollect;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public static Creator<TeacheBean> getCREATOR() {
        return CREATOR;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(String collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    public String getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(String downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getShareNumber() {
        return shareNumber;
    }

    public void setShareNumber(String shareNumber) {
        this.shareNumber = shareNumber;
    }

    public String getCheckCollect() {
        return checkCollect;
    }

    public void setCheckCollect(String checkCollect) {
        this.checkCollect = checkCollect;
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
        dest.writeString(this.title);
        dest.writeString(this.imageUrl);
        dest.writeString(this.image);
        dest.writeString(this.content);
        dest.writeString(this.videoUrl);
        dest.writeString(this.likes);
        dest.writeString(this.likesStatus);
        dest.writeString(this.commentNumber);
        dest.writeString(this.playTime);
        dest.writeString(this.time);
        dest.writeString(this.collectionNumber);
        dest.writeString(this.downloadNumber);
        dest.writeString(this.shareNumber);
        dest.writeString(this.checkCollect);
    }

    protected TeacheBean(Parcel in) {
        this.userId = in.readString();
        this.nickname = in.readString();
        this.userPortraitUrl = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.image = in.readString();
        this.content = in.readString();
        this.videoUrl = in.readString();
        this.likes = in.readString();
        this.likesStatus = in.readString();
        this.commentNumber = in.readString();
        this.playTime = in.readString();
        this.time = in.readString();
        this.collectionNumber = in.readString();
        this.downloadNumber = in.readString();
        this.shareNumber = in.readString();
        this.checkCollect = in.readString();
    }

    public static final Creator<TeacheBean> CREATOR = new Creator<TeacheBean>() {
        @Override
        public TeacheBean createFromParcel(Parcel source) {
            return new TeacheBean(source);
        }

        @Override
        public TeacheBean[] newArray(int size) {
            return new TeacheBean[size];
        }
    };
}
