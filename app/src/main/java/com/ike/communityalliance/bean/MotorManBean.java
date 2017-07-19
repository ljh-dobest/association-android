package com.ike.communityalliance.bean;

/**
 * Created by T-BayMax on 2017/7/19.
 */

public class MotorManBean {

    private int status; //司机状态
    private String time;
    private String endTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
