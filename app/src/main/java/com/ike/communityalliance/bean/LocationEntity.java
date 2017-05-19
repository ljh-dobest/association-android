package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/12/22.
 */

public class LocationEntity {
    public double latitue;
    public double longitude;
    public String address;
    public String city;

    public LocationEntity() {
    }

    public LocationEntity(double latitue, double longitude, String address, String city) {
        this.latitue = latitue;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitue() {
        return latitue;
    }

    public void setLatitue(double latitue) {
        this.latitue = latitue;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
