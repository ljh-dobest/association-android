package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/10.
 */

public class VerifyRecommedInfo {
    private String fullName;
    private String mobile;
    private String sex;
    private String hobby;
    private AddressBean address;
    private String relationship;
    private String birthday;
    private String homeplace;
    private String finishSchool;
    private String company;

    public VerifyRecommedInfo(String fullName, String mobile, String sex, String hobby, AddressBean address, String relationship, String birthday, String homeplace, String finishSchool, String company) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.sex = sex;
        this.hobby = hobby;
        this.address = address;
        this.relationship = relationship;
        this.birthday = birthday;
        this.homeplace = homeplace;
        this.finishSchool = finishSchool;
        this.company = company;
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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
