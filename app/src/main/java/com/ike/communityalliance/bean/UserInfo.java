package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/4.
 */

public class UserInfo  {
    private  String status;
    private String userId;
    private String numberId;
    private String nickname;
    private String friendNickname;
    private String token;
    private String userPortraitUrl;
    private String sex;
    private String mobile;
    private String address;
    private String birthday;
    private String email;
    private String age;
    private String experience;
    private String creditScore;
    private String contributionScore;
    private String recommendUserId;
    private String claimUserId;
    private String intimacy;
    private String checkVip;

    public UserInfo(String status, String userId, String numberId, String nickname, String friendNickname, String token, String userPortraitUrl, String sex, String mobile, String address, String birthday, String email, String age, String experience, String creditScore, String contributionScore, String recommendUserId, String claimUserId, String intimacy, String checkVip) {
        this.status = status;
        this.userId = userId;
        this.numberId = numberId;
        this.nickname = nickname;
        this.friendNickname = friendNickname;
        this.token = token;
        this.userPortraitUrl = userPortraitUrl;
        this.sex = sex;
        this.mobile = mobile;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.age = age;
        this.experience = experience;
        this.creditScore = creditScore;
        this.contributionScore = contributionScore;
        this.recommendUserId = recommendUserId;
        this.claimUserId = claimUserId;
        this.intimacy = intimacy;
        this.checkVip = checkVip;
    }

    public UserInfo(String status, String nickname, String userPortraitUrl, String mobile) {
        this.status = status;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.mobile = mobile;
    }

    public UserInfo(String status, String userId, String numberId, String nickname, String token, String userPortraitUrl, String sex, String mobile, String address, String birthday, String email, String age, String experience, String creditScore, String contributionScore, String recommendUserId, String claimUserId) {
        this.status = status;
        this.userId = userId;
        this.numberId = numberId;
        this.nickname = nickname;
        this.token = token;
        this.userPortraitUrl = userPortraitUrl;
        this.sex = sex;
        this.mobile = mobile;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.age = age;
        this.experience = experience;
        this.creditScore = creditScore;
        this.contributionScore = contributionScore;
        this.recommendUserId = recommendUserId;
        this.claimUserId = claimUserId;
    }

    public UserInfo(String userId, String nickname, String userPortraitUrl, String sex, String mobile, String address, String birthday, String email, String age) {
        this.userId = userId;
        this.nickname = nickname;
        this.userPortraitUrl = userPortraitUrl;
        this.sex = sex;
        this.mobile = mobile;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.age = age;
    }

    public UserInfo(String userId, String nickname, String sex, String mobile, String birthday, String address, String email, String age) {
        this.userId = userId;
        this.nickname = nickname;
        this.sex = sex;
        this.mobile = mobile;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public String getCheckVip() {
        return checkVip;
    }

    public void setCheckVip(String checkVip) {
        this.checkVip = checkVip;
    }

    public String getIntimacy() {
        return intimacy;
    }

    public void setIntimacy(String intimacy) {
        this.intimacy = intimacy;
    }

    public String getFriendNickname() {
        return friendNickname;
    }

    public void setFriendNickname(String friendNickname) {
        this.friendNickname = friendNickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserPortraitUrl() {
        return userPortraitUrl;
    }

    public void setUserPortraitUrl(String userPortraitUrl) {
        this.userPortraitUrl = userPortraitUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getContributionScore() {
        return contributionScore;
    }

    public void setContributionScore(String contributionScore) {
        this.contributionScore = contributionScore;
    }

    public String getRecommendUserId() {
        return recommendUserId;
    }

    public void setRecommendUserId(String recommendUserId) {
        this.recommendUserId = recommendUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimUserId() {
        return claimUserId;
    }

    public void setClaimUserId(String claimUserId) {
        this.claimUserId = claimUserId;
    }
}
