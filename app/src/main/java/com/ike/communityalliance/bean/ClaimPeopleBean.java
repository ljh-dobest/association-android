package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimPeopleBean implements Parcelable {
    private String userId;
    private String recommendId;
    private String fullName;
    private String nickname;
    private String numberId;
    private String userPortraitUrl;
    private String claimNumberId;
    private String claimNickName;
    private String claimFullName;
    private String claimTime;

    public ClaimPeopleBean(String userId, String recommendId, String fullName, String nickname, String numberId, String userPortraitUrl, String claimNumberId, String claimNickName, String claimFullName, String claimTime) {
        this.userId = userId;
        this.recommendId = recommendId;
        this.fullName = fullName;
        this.nickname = nickname;
        this.numberId = numberId;
        this.userPortraitUrl = userPortraitUrl;
        this.claimNumberId = claimNumberId;
        this.claimNickName = claimNickName;
        this.claimFullName = claimFullName;
        this.claimTime = claimTime;
    }

    public ClaimPeopleBean(String userId, String recommendId, String nickname, String userPortraitUrl) {
        this.userId = userId;
        this.recommendId = recommendId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(String claimTime) {
        this.claimTime = claimTime;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getClaimNumberId() {
        return claimNumberId;
    }

    public void setClaimNumberId(String claimNumberId) {
        this.claimNumberId = claimNumberId;
    }

    public String getClaimNickName() {
        return claimNickName;
    }

    public void setClaimNickName(String claimNickName) {
        this.claimNickName = claimNickName;
    }

    public String getClaimFullName() {
        return claimFullName;
    }

    public void setClaimFullName(String claimFullName) {
        this.claimFullName = claimFullName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.recommendId);
        dest.writeString(this.fullName);
        dest.writeString(this.nickname);
        dest.writeString(this.numberId);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.claimNumberId);
        dest.writeString(this.claimNickName);
        dest.writeString(this.claimFullName);
        dest.writeString(this.claimTime);
    }

    protected ClaimPeopleBean(Parcel in) {
        this.userId = in.readString();
        this.recommendId = in.readString();
        this.fullName = in.readString();
        this.nickname = in.readString();
        this.numberId = in.readString();
        this.userPortraitUrl = in.readString();
        this.claimNumberId = in.readString();
        this.claimNickName = in.readString();
        this.claimFullName = in.readString();
        this.claimTime = in.readString();
    }

    public static final Creator<ClaimPeopleBean> CREATOR = new Creator<ClaimPeopleBean>() {
        @Override
        public ClaimPeopleBean createFromParcel(Parcel source) {
            return new ClaimPeopleBean(source);
        }

        @Override
        public ClaimPeopleBean[] newArray(int size) {
            return new ClaimPeopleBean[size];
        }
    };
}
