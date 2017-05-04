package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/4.
 */

public class MorePersonalInfo {
    private MorePersonalInfoDetail fullName;
    private MorePersonalInfoDetail QQ;
    private MorePersonalInfoDetail wechat;
    private MorePersonalInfoDetail favour;
    private MorePersonalInfoDetail finishSchool;
    private MorePersonalInfoDetail constellation;
    private MorePersonalInfoDetail bloodType;
    private MorePersonalInfoDetail marriage;
    private MorePersonalInfoDetail company;
    private MorePersonalInfoDetail position;

    public MorePersonalInfo(MorePersonalInfoDetail fullName, MorePersonalInfoDetail QQ, MorePersonalInfoDetail wechat, MorePersonalInfoDetail favour, MorePersonalInfoDetail finishSchool, MorePersonalInfoDetail constellation, MorePersonalInfoDetail bloodType, MorePersonalInfoDetail marriage, MorePersonalInfoDetail company, MorePersonalInfoDetail position) {
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

    public MorePersonalInfoDetail getFullName() {
        return fullName;
    }

    public void setFullName(MorePersonalInfoDetail fullName) {
        this.fullName = fullName;
    }

    public MorePersonalInfoDetail getQQ() {
        return QQ;
    }

    public void setQQ(MorePersonalInfoDetail QQ) {
        this.QQ = QQ;
    }

    public MorePersonalInfoDetail getWechat() {
        return wechat;
    }

    public void setWechat(MorePersonalInfoDetail wechat) {
        this.wechat = wechat;
    }

    public MorePersonalInfoDetail getFavour() {
        return favour;
    }

    public void setFavour(MorePersonalInfoDetail favour) {
        this.favour = favour;
    }

    public MorePersonalInfoDetail getFinishSchool() {
        return finishSchool;
    }

    public void setFinishSchool(MorePersonalInfoDetail finishSchool) {
        this.finishSchool = finishSchool;
    }

    public MorePersonalInfoDetail getConstellation() {
        return constellation;
    }

    public void setConstellation(MorePersonalInfoDetail constellation) {
        this.constellation = constellation;
    }

    public MorePersonalInfoDetail getBloodType() {
        return bloodType;
    }

    public void setBloodType(MorePersonalInfoDetail bloodType) {
        this.bloodType = bloodType;
    }

    public MorePersonalInfoDetail getMarriage() {
        return marriage;
    }

    public void setMarriage(MorePersonalInfoDetail marriage) {
        this.marriage = marriage;
    }

    public MorePersonalInfoDetail getCompany() {
        return company;
    }

    public void setCompany(MorePersonalInfoDetail company) {
        this.company = company;
    }

    public MorePersonalInfoDetail getPosition() {
        return position;
    }

    public void setPosition(MorePersonalInfoDetail position) {
        this.position = position;
    }
}
