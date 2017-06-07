package com.issp.inspiration.interfaces;


import com.issp.inspiration.base.view.BaseView;
import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.bean.ImageUrlBean;

import java.util.ArrayList;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface IDealBuyListView extends BaseView {
    void setDealBuyListData(ArrayList<DealBuyBean> data);

    void getImageUrlView(ImageUrlBean bean);
    void dealBuyPraise(String data);
}
