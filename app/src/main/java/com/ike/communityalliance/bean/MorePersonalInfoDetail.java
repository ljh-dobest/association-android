package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/5/4.
 */

public class MorePersonalInfoDetail {
    private int status;
    private String name;


    public MorePersonalInfoDetail(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
