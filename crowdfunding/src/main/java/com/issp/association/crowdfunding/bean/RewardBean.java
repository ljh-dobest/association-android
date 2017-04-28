package com.issp.association.crowdfunding.bean;

import java.io.Serializable;

/**
 * 提交商品
 * Created by T-BayMax on 2017/4/28.
 */

public class RewardBean implements Serializable {
    private double supportMoney;        //支持金额
    private String productTitle;         //商品标题
    private String productContent;     //商品内容
    private int productLimitNumber;  //人数限制（0不限制）
    private double productDelivery;  //发货时间天数（0结束立即发货）

    public double getSupportMoney() {
        return supportMoney;
    }

    public void setSupportMoney(double supportMoney) {
        this.supportMoney = supportMoney;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    public int getProductLimitNumber() {
        return productLimitNumber;
    }

    public void setProductLimitNumber(int productLimitNumber) {
        this.productLimitNumber = productLimitNumber;
    }

    public double getProductDelivery() {
        return productDelivery;
    }

    public void setProductDelivery(double productDelivery) {
        this.productDelivery = productDelivery;
    }
}
