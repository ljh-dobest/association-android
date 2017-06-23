package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/21.
 */

public class WeatherFuture {
    private String date;
    private String nongli;
    private String week;
    private WeatherFutureInfo info;

    public WeatherFuture(String date, String nongli, String week, WeatherFutureInfo info) {
        this.date = date;
        this.nongli = nongli;
        this.week = week;
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNongli() {
        return nongli;
    }

    public void setNongli(String nongli) {
        this.nongli = nongli;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public WeatherFutureInfo getInfo() {
        return info;
    }

    public void setInfo(WeatherFutureInfo info) {
        this.info = info;
    }
}
