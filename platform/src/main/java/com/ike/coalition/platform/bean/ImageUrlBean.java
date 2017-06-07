package com.ike.coalition.platform.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by T-BayMax on 2017/6/6.
 */

public class ImageUrlBean implements Serializable {
    private String articleId;//文字id
    private String images;//广告图片

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}

