package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2016/12/9.
 */

public class AllAddFriends implements Parcelable {
    private String userId;
    private String nickname;
    private String addFriendMessage;
    private String addtime;
    private String userPortraitUrl;
    private String mobile;
    private String email;
    private int status;

    public AllAddFriends(String userId, String nickname, String addFriendMessage, String addtime, String userPortraitUrl, String mobile, String email, int status) {
        this.userId = userId;
        this.nickname = nickname;
        this.addFriendMessage = addFriendMessage;
        this.addtime = addtime;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
        this.email = email;
        this.status = status;
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

    public String getAddFriendMessage() {
        return addFriendMessage;
    }

    public void setAddFriendMessage(String addFriendMessage) {
        this.addFriendMessage = addFriendMessage;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.nickname);
        dest.writeString(this.addFriendMessage);
        dest.writeString(this.addtime);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.mobile);
        dest.writeString(this.email);
        dest.writeInt(this.status);
    }

    protected AllAddFriends(Parcel in) {
        this.userId = in.readString();
        this.nickname = in.readString();
        this.addFriendMessage = in.readString();
        this.addtime = in.readString();
        this.userPortraitUrl = in.readString();
        this.mobile = in.readString();
        this.email = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<AllAddFriends> CREATOR = new Creator<AllAddFriends>() {
        @Override
        public AllAddFriends createFromParcel(Parcel source) {
            return new AllAddFriends(source);
        }

        @Override
        public AllAddFriends[] newArray(int size) {
            return new AllAddFriends[size];
        }
    };
}
