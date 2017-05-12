package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/11.
 */

public class MineCliamMsgBean {
    private String userId;
    private String nickname;
    private String claimTime;
    private String userPortraitUrl;
    private String status;

    public MineCliamMsgBean(String userId, String nickname, String claimTime, String userPortraitUrl, String status) {
        this.userId = userId;
        this.nickname = nickname;
        this.claimTime = claimTime;
        this.userPortraitUrl = userPortraitUrl;
        this.status = status;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(String claimTime) {
        this.claimTime = claimTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
