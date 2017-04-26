package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/20.
 */

public class InterestGroupBean {
    private String groupId;
    private String groupName;
    private String groupPortraitUrl;
    private String groupUserNumber;
    private int status;

    public InterestGroupBean(String groupId, String groupName, String groupPortraitUrl, String groupUserNumber, int status) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupPortraitUrl = groupPortraitUrl;
        this.groupUserNumber = groupUserNumber;
        this.status = status;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
