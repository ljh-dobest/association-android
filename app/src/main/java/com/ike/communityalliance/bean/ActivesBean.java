package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/4/26.
 */

public class ActivesBean {
    private  String activesImage;
    private  String title;
    private  String address;

    public ActivesBean(String activesImage, String title, String address) {
        this.activesImage = activesImage;
        this.title = title;
        this.address = address;
    }

    public String getActivesImage() {
        return activesImage;
    }

    public void setActivesImage(String activesImage) {
        this.activesImage = activesImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
