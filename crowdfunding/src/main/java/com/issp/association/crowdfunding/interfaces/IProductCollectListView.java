package com.issp.association.crowdfunding.interfaces;


import com.issp.association.crowdfunding.base.view.BaseView;
import com.issp.association.crowdfunding.bean.ImageUrlBean;
import com.issp.association.crowdfunding.bean.ProductCollectBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品众筹列表
 * Created by T-BayMax on 2017/3/13.
 */

public interface IProductCollectListView extends BaseView {
    void setProductCollectData(ArrayList<ProductCollectBean> data);

    void getImageUrlView(List<ImageUrlBean> bean);
    void selectProductIdCardView(String data);

    void userPraise(String data);
}
