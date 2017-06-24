package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/23.
 */

public class WeatherRealtime {
    private String city_name;
    private WeatherRealtimeInfo weather;

    public WeatherRealtime(String city_name, WeatherRealtimeInfo weather) {
        this.city_name = city_name;
        this.weather = weather;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public WeatherRealtimeInfo getWeather() {
        return weather;
    }

    public void setWeather(WeatherRealtimeInfo weather) {
        this.weather = weather;
    }
}
