package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/22.
 */

public class UserOrGroupBean {
    private String groupId;
    private String groupName;
    private String groupPortraitUrl;
    private String userId;
    private String nickname;
    private String userPortraitUrl;
    private String status;

    public UserOrGroupBean(String groupId, String groupName, String groupPortraitUrl, String userId, String nickname, String userPortraitUrl, String status) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupPortraitUrl = groupPortraitUrl;
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.status = status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupPortraitUrl() {
        return groupPortraitUrl;
    }

    public void setGroupPortraitUrl(String groupPortraitUrl) {
        this.groupPortraitUrl = groupPortraitUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
