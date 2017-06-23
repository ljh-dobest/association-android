package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/6/23.
 */

public class WeatherRealtimeInfo {
    private String temperature;
    private String humidity;
    private String info;

    public WeatherRealtimeInfo(String temperature, String humidity, String info) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.info = info;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
