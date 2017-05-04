package com.ike.communityalliance.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimPeopleBean implements Parcelable {
    private String recommendId;
    private String fullName;
    private String nickname;
    private String numberId;
    private String userPortraitUrl;
    private String claimNumberId;
    private String claimNickName;
    private String claimFullName;

    public ClaimPeopleBean(String recommendId, String fullName, String nickname, String numberId, String userPortraitUrl, String claimNumberId, String claimNickName, String claimFullName) {
        this.recommendId = recommendId;
        this.fullName = fullName;
        this.nickname = nickname;
        this.numberId = numberId;
        this.userPortraitUrl = userPortraitUrl;
        this.claimNumberId = claimNumberId;
        this.claimNickName = claimNickName;
        this.claimFullName = claimFullName;
    }

    public ClaimPeopleBean(String recommendId, String fullName, String userPortraitUrl, String nickname) {
        this.recommendId = recommendId;
        this.fullName = fullName;
        this.userPortraitUrl = userPortraitUrl;
        this.nickname = nickname;
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
        dest.writeString(this.recommendId);
        dest.writeString(this.fullName);
        dest.writeString(this.nickname);
        dest.writeString(this.numberId);
        dest.writeString(this.userPortraitUrl);
        dest.writeString(this.claimNumberId);
        dest.writeString(this.claimNickName);
        dest.writeString(this.claimFullName);
    }

    protected ClaimPeopleBean(Parcel in) {
        this.recommendId = in.readString();
        this.fullName = in.readString();
        this.nickname = in.readString();
        this.numberId = in.readString();
        this.userPortraitUrl = in.readString();
        this.claimNumberId = in.readString();
        this.claimNickName = in.readString();
        this.claimFullName = in.readString();
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
