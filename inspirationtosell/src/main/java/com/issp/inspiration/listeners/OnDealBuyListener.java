package com.issp.inspiration.listeners;


import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.bean.ImageUrlBean;

import java.util.ArrayList;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface OnDealBuyListener {
    void getDealBuyInfo(ArrayList<DealBuyBean> data);

    void getImageUrl(ImageUrlBean bean);

    void DealBuyPraiseInfo(String data);

    void showError(String errorString);
}
