package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by Min on 2016/11/26.
 */
public class FriendInfo implements Parcelable {
    private String myId;
    private String userId;
    private String nickname;
    private String displayName;
    private String status;
    private Long timestamp;
    private String letters;
    private String email;
    private String userPortraitUrl;
    private String mobile;

    public FriendInfo(String userId, String userName, String userPort, String userPhone, String userEmail) {
        this.userId=userId;
        this.nickname=userName;
        this.userPortraitUrl=userPort;
        this.mobile=userPhone;
        this.email=userEmail;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public FriendInfo(String myId, String userId, String nickname, String displayName, String status, Long timestamp, String letters, String email, String userPortraitUrl, String mobile) {
        this.myId = myId;
        this.userId = userId;
        this.nickname = nickname;
        this.displayName = displayName;
        this.status = status;
        this.timestamp = timestamp;
        this.letters = letters;
        this.email = email;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public FriendInfo(String userId, String nickname, String displayName, String userPortraitUrl, String mobile, String email, String letters) {
        this.userId = userId;
        this.nickname = nickname;
        this.displayName = displayName;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
        this.email = email;
        this.letters = letters;
    }

    public FriendInfo(String userId, String nickname, String displayName, String userPortraitUrl, String mobile, String email) {
        this.userId = userId;
        this.nickname = nickname;
        this.displayName = displayName;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
        this.email = email;
    }

    public FriendInfo() {
    }

    public FriendInfo(String userId, String nickname, String userPortraitUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
    }

    public FriendInfo(String userId, String nickname, String userPortraitUrl, String displayName) {
        this.userId=userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.displayName = displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.myId);
        dest.writeString(this.userId);
        dest.writeString(this.nickname);
        dest.writeString(this.displayName);
        dest.writeString(this.status);
        dest.writeValue(this.timestamp);
        dest.writeString(this.letters);
        dest.writeString(this.email);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.mobile);
    }

    protected FriendInfo(Parcel in) {
        this.myId = in.readString();
        this.userId = in.readString();
        this.nickname = in.readString();
        this.displayName = in.readString();
        this.status = in.readString();
        this.timestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.letters = in.readString();
        this.email = in.readString();
        this.userPortraitUrl = in.readString();
        this.mobile = in.readString();
    }

    public static final Parcelable.Creator<FriendInfo> CREATOR = new Parcelable.Creator<FriendInfo>() {
        @Override
        public FriendInfo createFromParcel(Parcel source) {
            return new FriendInfo(source);
        }

        @Override
        public FriendInfo[] newArray(int size) {
            return new FriendInfo[size];
        }
    };
    public boolean isExitsDisplayName() {
        if (TextUtils.isEmpty(getDisplayName())) {
            return false;
        }
        return true;
    }
}
