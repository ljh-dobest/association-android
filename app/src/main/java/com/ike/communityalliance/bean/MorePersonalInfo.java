package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/4.
 */

public class MorePersonalInfo {
    private MorePersonalInfoDetail fullName;
    private MorePersonalInfoDetail birthday;
    private MorePersonalInfoDetail mobile;
    private String QQ;
    private String wechat;
    private MorePersonalInfoDetail finishSchool;
    private String constellation;
    private String bloodType;
    private MorePersonalInfoDetail marriage;
    private String company;
    private MorePersonalInfoDetail position;
    private MorePersonalInfoDetail fatherName;
    private MorePersonalInfoDetail motherName;
    private MorePersonalInfoDetail spouseName;
    private MorePersonalInfoDetail childrenName;
    private MorePersonalInfoDetail childrenSchool;
    private String industry;
    private String homeplace;


    public MorePersonalInfo(MorePersonalInfoDetail fullName, MorePersonalInfoDetail birthday, MorePersonalInfoDetail mobile, String QQ, String wechat, MorePersonalInfoDetail finishSchool, String constellation, String bloodType, MorePersonalInfoDetail marriage, String company, MorePersonalInfoDetail position, MorePersonalInfoDetail fatherName, MorePersonalInfoDetail motherName, MorePersonalInfoDetail spouseName, MorePersonalInfoDetail childrenName, MorePersonalInfoDetail childrenSchool, String industry, String homeplace) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.mobile = mobile;
        this.QQ = QQ;
        this.wechat = wechat;
        this.finishSchool = finishSchool;
        this.constellation = constellation;
        this.bloodType = bloodType;
        this.marriage = marriage;
        this.company = company;
        this.position = position;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.spouseName = spouseName;
        this.childrenName = childrenName;
        this.childrenSchool = childrenSchool;
        this.industry = industry;
        this.homeplace = homeplace;
    }

    public MorePersonalInfoDetail getFullName() {
        return fullName;
    }

    public void setFullName(MorePersonalInfoDetail fullName) {
        this.fullName = fullName;
    }

    public MorePersonalInfoDetail getBirthday() {
        return birthday;
    }

    public void setBirthday(MorePersonalInfoDetail birthday) {
        this.birthday = birthday;
    }

    public MorePersonalInfoDetail getMobile() {
        return mobile;
    }

    public void setMobile(MorePersonalInfoDetail mobile) {
        this.mobile = mobile;
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

    public MorePersonalInfoDetail getFinishSchool() {
        return finishSchool;
    }

    public void setFinishSchool(MorePersonalInfoDetail finishSchool) {
        this.finishSchool = finishSchool;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public MorePersonalInfoDetail getMarriage() {
        return marriage;
    }

    public void setMarriage(MorePersonalInfoDetail marriage) {
        this.marriage = marriage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public MorePersonalInfoDetail getPosition() {
        return position;
    }

    public void setPosition(MorePersonalInfoDetail position) {
        this.position = position;
    }

    public MorePersonalInfoDetail getFatherName() {
        return fatherName;
    }

    public void setFatherName(MorePersonalInfoDetail fatherName) {
        this.fatherName = fatherName;
    }

    public MorePersonalInfoDetail getMotherName() {
        return motherName;
    }

    public void setMotherName(MorePersonalInfoDetail motherName) {
        this.motherName = motherName;
    }

    public MorePersonalInfoDetail getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(MorePersonalInfoDetail spouseName) {
        this.spouseName = spouseName;
    }

    public MorePersonalInfoDetail getChildrenName() {
        return childrenName;
    }

    public void setChildrenName(MorePersonalInfoDetail childrenName) {
        this.childrenName = childrenName;
    }

    public MorePersonalInfoDetail getChildrenSchool() {
        return childrenSchool;
    }

    public void setChildrenSchool(MorePersonalInfoDetail childrenSchool) {
        this.childrenSchool = childrenSchool;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHomeplace() {
        return homeplace;
    }

    public void setHomeplace(String homeplace) {
        this.homeplace = homeplace;
    }
}
