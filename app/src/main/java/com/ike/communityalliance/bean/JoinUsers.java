package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/17.
 */

public class JoinUsers  {
    private String userId;
    private String userPortraitUrl;

    public JoinUsers(String userId, String userPortraitUrl) {
        this.userId = userId;
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }
}
