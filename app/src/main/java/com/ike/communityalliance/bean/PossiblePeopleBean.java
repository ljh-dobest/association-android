package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/2.
 */

public class PossiblePeopleBean {
    private String nickname;
    private String userId;
    private String numberId;
    private String userPortraitUrl;
    private String count;
    private String sex;
    private String relationship;

    public PossiblePeopleBean(String nickname, String userId, String numberId, String userPortraitUrl, String count, String sex, String relationship) {
        this.nickname = nickname;
        this.userId = userId;
        this.numberId = numberId;
        this.userPortraitUrl = userPortraitUrl;
        this.count = count;
        this.sex = sex;
        this.relationship = relationship;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
