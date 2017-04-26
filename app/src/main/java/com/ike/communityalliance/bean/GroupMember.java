package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/11/25.
 */
public class GroupMember {
    /** Not-null value. */
    private String groupId;
    /** Not-null value. */
    private String userId;
    private String userName;
    private String groupPortraitUrl;
    private String displayName;
    private String nameSpelling;
    private String displayNameSpelling;
    private String groupName;
    private String groupNameSpelling;
    private String userPortraitUrl;
    private String mobile;
    private String email;
    private String role;

    public GroupMember(String groupId, String userId, String userName, String groupPortraitUrl, String displayName, String nameSpelling, String displayNameSpelling, String groupName, String groupNameSpelling, String userPortraitUrl, String mobile, String email, String role) {
        this.groupId = groupId;
        this.userId = userId;
        this.userName = userName;
        this.groupPortraitUrl = groupPortraitUrl;
        this.displayName = displayName;
        this.nameSpelling = nameSpelling;
        this.displayNameSpelling = displayNameSpelling;
        this.groupName = groupName;
        this.groupNameSpelling = groupNameSpelling;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
        this.email = email;
        this.role = role;
    }

    public GroupMember() {
    }

    public GroupMember(String userId, String userName, String userPortraitUrl, String mobile, String role) {
        this.userId = userId;
        this.userName = userName;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
        this.role = role;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupPortraitUrl() {
        return groupPortraitUrl;
    }

    public void setGroupPortraitUrl(String groupPortraitUrl) {
        this.groupPortraitUrl = groupPortraitUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNameSpelling() {
        return nameSpelling;
    }

    public void setNameSpelling(String nameSpelling) {
        this.nameSpelling = nameSpelling;
    }

    public String getDisplayNameSpelling() {
        return displayNameSpelling;
    }

    public void setDisplayNameSpelling(String displayNameSpelling) {
        this.displayNameSpelling = displayNameSpelling;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNameSpelling() {
        return groupNameSpelling;
    }

    public void setGroupNameSpelling(String groupNameSpelling) {
        this.groupNameSpelling = groupNameSpelling;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
