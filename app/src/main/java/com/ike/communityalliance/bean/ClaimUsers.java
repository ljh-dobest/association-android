package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimUsers {
    private String nickname;
    private String userPortraitUrl;
    private String fullName;
    private String recommendId;
    private String claimUsersId;
    private String claimUsersName;

    public ClaimUsers(String nickname, String userPortraitUrl, String fullName, String recommendId, String claimUsersId, String claimUsersName) {
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.fullName = fullName;
        this.recommendId = recommendId;
        this.claimUsersId = claimUsersId;
        this.claimUsersName = claimUsersName;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public String getClaimUsersId() {
        return claimUsersId;
    }

    public void setClaimUsersId(String claimUsersId) {
        this.claimUsersId = claimUsersId;
    }

    public String getClaimUsersName() {
        return claimUsersName;
    }

    public void setClaimUsersName(String claimUsersName) {
        this.claimUsersName = claimUsersName;
    }
}
