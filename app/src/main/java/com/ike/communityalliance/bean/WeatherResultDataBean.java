package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/6/21.
 */

public class WeatherResultDataBean {
    private List<WeatherFuture> weather;
    private WeatherRealtime realtime;

    public WeatherResultDataBean(List<WeatherFuture> weather, WeatherRealtime realtime) {
        this.weather = weather;
        this.realtime = realtime;
    }

    public List<WeatherFuture> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherFuture> weather) {
        this.weather = weather;
    }

    public WeatherRealtime getRealtime() {
        return realtime;
    }

    public void setRealtime(WeatherRealtime realtime) {
        this.realtime = realtime;
    }
}
