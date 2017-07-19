package com.issp.association.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by T-BayMax on 2017/7/14.
 */
@Entity
public class DownloadBean {
    @Id
    private Long  id;
    private String name;//文件名
    private String path;//保存路径
    private Long size;//文件总大小
    private Long sofar;//下载量
    private String time;//下载时间
    private String url;//下载路径
    @Generated(hash = 306197085)
    public DownloadBean(Long id, String name, String path, Long size, Long sofar,
            String time, String url) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.size = size;
        this.sofar = sofar;
        this.time = time;
        this.url = url;
    }
    @Generated(hash = 2040406903)
    public DownloadBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public Long getSize() {
        return this.size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public Long getSofar() {
        return this.sofar;
    }
    public void setSofar(Long sofar) {
        this.sofar = sofar;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
