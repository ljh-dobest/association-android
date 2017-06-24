package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/21.
 */

public class WeatherResult {
 private WeatherResultDataBean data;

    public WeatherResult(WeatherResultDataBean data) {
        this.data = data;
    }

    public WeatherResultDataBean getData() {
        return data;
    }

    public void setData(WeatherResultDataBean data) {
        this.data = data;
    }
}
