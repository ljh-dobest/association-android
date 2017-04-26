package com.ike.communityalliance.bean;

/**
 * Created by Min on 2016/11/28.
 */

public class Code<T> {
    private int code;
    private T data;
    private String msgs;

    public Code() {
    }

    public Code(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public Code(int code, T data, String msgs) {
        this.code = code;
        this.data = data;
        this.msgs = msgs;
    }

    public String getMsgs() {
        return msgs;
    }

    public void setMsgs(String msgs) {
        this.msgs = msgs;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
