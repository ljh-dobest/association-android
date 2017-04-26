package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/4/10.
 */

public class JoinUserBean {
    private String userId;
    private String nickname;
    private String sex;
    private String avatarImage;

    public JoinUserBean(String userId, String nickname, String sex, String avatarImage) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
    }
}
