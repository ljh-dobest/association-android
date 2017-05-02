package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/2.
 */

public class AddressBean {
    private String firstStage;
    private String secondStage;
    private String thirdStage;
    private String address;

    public AddressBean(String firstStage, String secondStage, String thirdStage, String address) {
        this.firstStage = firstStage;
        this.secondStage = secondStage;
        this.thirdStage = thirdStage;
        this.address = address;
    }

    public String getFirstStage() {
        return firstStage;
    }

    public void setFirstStage(String firstStage) {
        this.firstStage = firstStage;
    }

    public String getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(String secondStage) {
        this.secondStage = secondStage;
    }

    public String getThirdStage() {
        return thirdStage;
    }

    public void setThirdStage(String thirdStage) {
        this.thirdStage = thirdStage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
