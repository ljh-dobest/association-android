package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/4/19.
 */

public class SignBean {
    private String experience;
    private String days;

    public SignBean(String experience, String days) {
        this.experience = experience;
        this.days = days;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
