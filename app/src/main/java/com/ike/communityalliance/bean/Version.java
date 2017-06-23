package com.ike.communityalliance.bean;

import java.io.Serializable;

/**
 * 版本管理
 * Created by T-BayMax on 2016/11/16.
 */

public class Version implements Serializable {

    private String title;          //app名称
    private String  content;        //内容
    private String url;           //地址
    private String version;        //版本号
    private String time;           //时间

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
