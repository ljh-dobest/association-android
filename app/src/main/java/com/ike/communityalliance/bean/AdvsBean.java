package com.ike.communityalliance.bean;

/**
 * Created by Min on 2017/4/26.
 */

public class AdvsBean {
    private String articleId;
    private String articleImage;
    private String type;

    public AdvsBean(String articleId, String articleImage, String type) {
        this.articleId = articleId;
        this.articleImage = articleImage;
        this.type = type;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
