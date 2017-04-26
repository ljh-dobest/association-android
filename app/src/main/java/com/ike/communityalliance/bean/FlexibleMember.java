package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/12/15.
 */

public class FlexibleMember{
    private String userId;
    private String nickname;
    private int sex;
    private String avatarImage;

    public FlexibleMember(String userId, String nickname, int sex, String avatarImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
        this.avatarImage = avatarImage;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
