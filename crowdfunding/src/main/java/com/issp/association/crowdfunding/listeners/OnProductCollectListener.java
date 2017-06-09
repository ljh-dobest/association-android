package com.issp.association.crowdfunding.listeners;


import com.issp.association.crowdfunding.bean.ImageUrlBean;
import com.issp.association.crowdfunding.bean.ProductCollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface OnProductCollectListener {
    void getProductCollectInfo(ArrayList<ProductCollectBean> data);

    void selectProductIdCard(String data);
    void getImageUrl(List<ImageUrlBean> bean);
    void userPraise(String data);

    void showError(String errorString);
}
