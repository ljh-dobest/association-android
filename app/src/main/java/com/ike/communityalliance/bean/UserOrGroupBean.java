package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/22.
 */

public class UserOrGroupBean {
    private String groupId;
    private String groupName;
    private String groupPortraitUrl;
    private String groupUserNumber;
    private String userId;
    private String numberId;
    private String nickname;
    private String userPortraitUrl;
    private String status;
    private String count;
    private String relationship;
    private String sex;
    private String checkFriends;

    public UserOrGroupBean(String groupId, String groupName, String groupPortraitUrl, String groupUserNumber, String userId, String numberId, String nickname, String userPortraitUrl, String status, String count, String relationship, String sex, String checkFriends) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupPortraitUrl = groupPortraitUrl;
        this.groupUserNumber = groupUserNumber;
        this.userId = userId;
        this.numberId = numberId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.status = status;
        this.count = count;
        this.relationship = relationship;
        this.sex = sex;
        this.checkFriends = checkFriends;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
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

    public String getGroupUserNumber() {
        return groupUserNumber;
    }

    public void setGroupUserNumber(String groupUserNumber) {
        this.groupUserNumber = groupUserNumber;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCheckFriends() {
        return checkFriends;
    }

    public void setCheckFriends(String checkFriends) {
        this.checkFriends = checkFriends;
    }
}
