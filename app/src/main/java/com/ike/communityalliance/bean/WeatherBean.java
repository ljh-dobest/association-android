package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/21.
 */

public class WeatherBean {
    private WeatherResult result;

    public WeatherBean(WeatherResult result) {
        this.result = result;
    }

    public WeatherResult getResult() {
        return result;
    }

    public void setResult(WeatherResult result) {
        this.result = result;
    }
}
