package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/11/30.
 */

public class GroupId {
    private String groupId;
    private String groupPortraitUri;

    public GroupId(String groupId, String groupPortraitUri) {
        this.groupId = groupId;
        this.groupPortraitUri = groupPortraitUri;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupPortraitUri() {
        return groupPortraitUri;
    }

    public void setGroupPortraitUri(String groupPortraitUri) {
        this.groupPortraitUri = groupPortraitUri;
    }
}
