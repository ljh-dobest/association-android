package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/3/18.
 */

public class OptionBean {
    private String id;
    private String content;
    private String count;

    public OptionBean(String id, String content, String count) {
        this.id = id;
        this.content = content;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
