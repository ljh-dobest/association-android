package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/11/30.
 */

public class GroupId {
    private String groupId;
    private String groupPortraitUrl;

    public GroupId(String groupId, String groupPortraitUrl) {
        this.groupId = groupId;
        this.groupPortraitUrl = groupPortraitUrl;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupPortraitUrl() {
        return groupPortraitUrl;
    }

    public void setGroupPortraitUrl(String groupPortraitUrl) {
        this.groupPortraitUrl = groupPortraitUrl;
    }
}
