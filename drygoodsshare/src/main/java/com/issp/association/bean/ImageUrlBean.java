package com.issp.association.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by T-BayMax on 2017/6/6.
 */

public class ImageUrlBean implements Serializable {
    private String id;//文字id
    private List<String> images;//广告图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

