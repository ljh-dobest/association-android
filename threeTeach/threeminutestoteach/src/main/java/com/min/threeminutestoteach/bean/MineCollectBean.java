package com.min.threeminutestoteach.bean;

/**
 * Created by Min on 2017/6/15.
 */

public class MineCollectBean {
    private String id;
    private String nickname;
    private String title;
    private String image;
    private String time;

    public MineCollectBean(String id, String nickname, String title, String image, String time) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.image = image;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
