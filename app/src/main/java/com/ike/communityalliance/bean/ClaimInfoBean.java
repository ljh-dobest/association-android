package com.ike.communityalliance.bean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimInfoBean {
    private String userId;
    private String claimUserId;
    private String fullName;
    private String mobile;
    private String sex;
    private String hobby;
    private ArrayList<String> address;
    private String creditScore;
    private String relationship;
    private String birthday;
    private String homeplace;
    private String finishSchool;
    private String degree;
    private String company;
    private String position;
    private String email;
    private String QQ;
    private String wechat;
    private String industry;
    private String character;
    private String fatherName;
    private String motherName;
    private String marriage;
    private String spouseName;
    private String childrenName;
    private String childrenSchool;

    public ClaimInfoBean(String userId, String claimUserId, String fullName, String mobile, String sex, String hobby, ArrayList<String> address, String creditScore, String relationship, String birthday, String homeplace, String finishSchool, String degree, String company, String position, String email, String QQ, String wechat, String industry, String character, String fatherName, String motherName, String marriage, String spouseName, String childrenName, String childrenSchool) {
        this.userId = userId;
        this.claimUserId = claimUserId;
        this.fullName = fullName;
        this.mobile = mobile;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
        this.creditScore = creditScore;
        this.relationship = relationship;
        this.birthday = birthday;
        this.homeplace = homeplace;
        this.finishSchool = finishSchool;
        this.degree = degree;
        this.company = company;
        this.position = position;
        this.email = email;
        this.QQ = QQ;
        this.wechat = wechat;
        this.industry = industry;
        this.character = character;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.marriage = marriage;
        this.spouseName = spouseName;
        this.childrenName = childrenName;
        this.childrenSchool = childrenSchool;
    }

    public ClaimInfoBean(String userId, String claimUserId, String fullName, String mobile, String sex, String hobby, ArrayList<String> address, String creditScore, String relationship, String birthday, String homeplace, String finishSchool, String degree, String company, String position, String email, String QQ, String wechat) {
        this.userId = userId;
        this.claimUserId = claimUserId;
        this.fullName = fullName;
        this.mobile = mobile;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
        this.creditScore = creditScore;
        this.relationship = relationship;
        this.birthday = birthday;
        this.homeplace = homeplace;
        this.finishSchool = finishSchool;
        this.degree = degree;
        this.company = company;
        this.position = position;
        this.email = email;
        this.QQ = QQ;
        this.wechat = wechat;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(String childrenName) {
        this.childrenName = childrenName;
    }

    public String getChildrenSchool() {
        return childrenSchool;
    }

    public void setChildrenSchool(String childrenSchool) {
        this.childrenSchool = childrenSchool;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClaimUserId() {
        return claimUserId;
    }

    public void setClaimUserId(String claimUserId) {
        this.claimUserId = claimUserId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    public String getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(String creditScore) {
        this.creditScore = creditScore;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHomeplace() {
        return homeplace;
    }

    public void setHomeplace(String homeplace) {
        this.homeplace = homeplace;
    }

    public String getFinishSchool() {
        return finishSchool;
    }

    public void setFinishSchool(String finishSchool) {
        this.finishSchool = finishSchool;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}
