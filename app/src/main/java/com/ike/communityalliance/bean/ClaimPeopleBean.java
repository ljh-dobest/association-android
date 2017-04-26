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
    private String userId;

    public ClaimPeopleBean(String recommendId, String fullName, String nickname, String numberId, String userPortraitUrl, String userId) {
        this.recommendId = recommendId;
        this.fullName = fullName;
        this.nickname = nickname;
        this.numberId = numberId;
        this.userPortraitUrl = userPortraitUrl;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        dest.writeString(this.userId);
    }

    protected ClaimPeopleBean(Parcel in) {
        this.recommendId = in.readString();
        this.fullName = in.readString();
        this.nickname = in.readString();
        this.numberId = in.readString();
        this.userPortraitUrl = in.readString();
        this.userId = in.readString();
    }

    public static final Parcelable.Creator<ClaimPeopleBean> CREATOR = new Parcelable.Creator<ClaimPeopleBean>() {
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
