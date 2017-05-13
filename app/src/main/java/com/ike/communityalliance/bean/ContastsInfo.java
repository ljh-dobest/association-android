package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/12.
 */

public class ContastsInfo {
    private String name;
    private String number;


    public ContastsInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
