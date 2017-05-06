package com.ike.sq.commonwealactives.bean;

import java.io.Serializable;

/**
 * Created by T-BayMax on 2017/5/5.
 */

public class BenefitBean implements Serializable {
    private String id;              //活动id
    private String userId;           //发起人id
    private String nickname;         //发起人昵称
    private String userPortraitUrl;     //发起人头像
    private String status; //1活动报名进行中  0报名已结束  2还未开始

    private String title;            //活动标题
    private String address;          //活动地点
    private String activesImage;        //活动图片
    private String startTime;         //活动开始时间
    private String endTime;           //活动结束时间
    private double costMoney;       //活动费用
    private String content;         //活动详情
    private int joinStatus;     //1当前用户已报名  0未报名
    private int joinUsersNumber;  //报名人数
    private UserBean joinUsers;               //报名人员
    private int commentNumber;     //评论数量
    private int likes;             //点赞数量
    private int likesStatus;     //0当前用户未点赞  1已点赞

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivesImage() {
        return activesImage;
    }

    public void setActivesImage(String activesImage) {
        this.activesImage = activesImage;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(double costMoney) {
        this.costMoney = costMoney;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(int joinStatus) {
        this.joinStatus = joinStatus;
    }

    public int getJoinUsersNumber() {
        return joinUsersNumber;
    }

    public void setJoinUsersNumber(int joinUsersNumber) {
        this.joinUsersNumber = joinUsersNumber;
    }

    public UserBean getJoinUsers() {
        return joinUsers;
    }

    public void setJoinUsers(UserBean joinUsers) {
        this.joinUsers = joinUsers;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikesStatus() {
        return likesStatus;
    }

    public void setLikesStatus(int likesStatus) {
        this.likesStatus = likesStatus;
    }
}
