package com.ike.communityalliance.bean;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by T-BayMax on 2017/5/9.
 */

public class RelationshipBean implements Serializable {
    private String userId;            //用户id
    private String nickname;          //昵称
    private int sex;           //1男2 女
    private String userPortraitUrl;      //头像
    private String recommendUser;     //推荐人
    private int status;            //0非好友  1好友

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(String recommendUser) {
        this.recommendUser = recommendUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
