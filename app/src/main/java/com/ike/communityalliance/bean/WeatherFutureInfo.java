package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/6/21.
 */

public class WeatherFutureInfo {
    private List<String> day;
    private List<String> night;

    public WeatherFutureInfo(List<String> day, List<String> night) {
        this.day = day;
        this.night = night;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }

    public List<String> getNight() {
        return night;
    }

    public void setNight(List<String> night) {
        this.night = night;
    }
}
