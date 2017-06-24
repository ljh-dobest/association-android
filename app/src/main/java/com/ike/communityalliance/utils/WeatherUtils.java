package com.ike.communityalliance.utils;

import com.ike.communityalliance.R;

/**
 * Created by Min on 2017/6/22.
 */

public class WeatherUtils {
    public static String[] hotCitys={"北京市","深圳市","广州市","天津市","上海市","重庆市","南京市","沈阳市","长春市","哈尔滨","武汉市","澳门","香港","台北市","高雄市","台中市"};
    public static int getWeatherIcon(String weather){
            int imgResour = R.mipmap.weather_sunny;
            switch (weather) {
                case "晴":
                    imgResour = R.mipmap.weather_sunny;
                    break;
                case "多云":
                    imgResour = R.mipmap.weather_dy;
                    break;
                case "阴":
                    imgResour = R.mipmap.weather_yin;
                    break;
                case "暴雨":
                    imgResour = R.mipmap.weather_by;
                    break;
                case "雷雨":
                case "雷阵雨":
                    imgResour = R.mipmap.weather_lzy;
                    break;
                case "大雨":
                    imgResour = R.mipmap.weather_big_rain;
                    break;
                case "中雨":
                case "小到中雨":
                    imgResour = R.mipmap.weather_zhongyu;
                    break;
                case "阵雨":
                    imgResour = R.mipmap.weather_zy;
                    break;
                case "小雨":
                    imgResour = R.mipmap.weather_xy;
                    break;
            }
            return imgResour;
    }
    public static int getWeatherBg(String weather,String time){
        int imgResour = R.mipmap.weather_sunny_mor_bg;
        switch (weather) {
            case "晴":
                imgResour =time.equals("上午")?R.mipmap.weather_sunny_mor_bg:R.mipmap.weather_sunny_after;
                break;
            case "多云":
                imgResour = R.mipmap.weather_dy_bg;
                break;
            case "阴":
                imgResour = R.mipmap.weather_yt_bg;
                break;
            case "雷雨":
            case "雷阵雨":
                imgResour = R.mipmap.weather_lzy_bg;
                break;
            case "暴雨":
            case "大雨":
            case "中雨":
            case "阵雨":
            case "小到中雨":
            case "小雨":
                imgResour =time.equals("上午")?R.mipmap.weather_rain_mor:R.mipmap.weather_rain_after;
                break;
        }
        return imgResour;
    }
}
