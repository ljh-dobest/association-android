package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/15.
 */

public class MoreUserDetailInfo {
    private String fullName;
    private String QQ;
    private String wechat;
    private String favour;
    private String finishSchool;
    private String constellation;
    private String bloodType;
    private String marriage;
    private String company;
    private String position;

    public MoreUserDetailInfo(String fullName, String QQ, String wechat, String favour, String finishSchool, String constellation, String bloodType, String marriage, String company, String position) {
        this.fullName = fullName;
        this.QQ = QQ;
        this.wechat = wechat;
        this.favour = favour;
        this.finishSchool = finishSchool;
        this.constellation = constellation;
        this.bloodType = bloodType;
        this.marriage = marriage;
        this.company = company;
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getFavour() {
        return favour;
    }

    public void setFavour(String favour) {
        this.favour = favour;
    }

    public String getFinishSchool() {
        return finishSchool;
    }

    public void setFinishSchool(String finishSchool) {
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

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
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
}
