package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/11/25.
 */

public class BaseBean {
    private int code;
    private String data;

    public BaseBean(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
