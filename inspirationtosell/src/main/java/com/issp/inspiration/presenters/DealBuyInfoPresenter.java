package com.issp.inspiration.presenters;


import com.issp.inspiration.base.presenter.BasePersenter;
import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.bean.ImageUrlBean;
import com.issp.inspiration.interfaces.IDealBuyListView;
import com.issp.inspiration.listeners.OnDealBuyListener;
import com.issp.inspiration.model.DealBuyInfoModel;

import java.util.ArrayList;
import java.util.Map;

/**
 *Created by T-BayMax on 2017/3/13.
 */

public class DealBuyInfoPresenter extends BasePersenter<IDealBuyListView> implements OnDealBuyListener {
    private DealBuyInfoModel model;

    public DealBuyInfoPresenter() {
        model = new DealBuyInfoModel();
    }

    public void ShareInfoPresenter(Map<String, String> formData) {
        model.getDealBuyInfo(formData, this);
    }

    public void getImage(Map<String ,String> formData){
        model.getImageUrl(formData,this);
    }
    public void sharePraiseInfoPresenter(Map<String, String> formData) {
        model.getDealBuyPraiseInfo(formData, this);
    }

    @Override
    public void getDealBuyInfo(ArrayList<DealBuyBean> data) {
        mView.setDealBuyListData(data);
    }

    @Override
    public void getImageUrl(ImageUrlBean bean) {
        mView.getImageUrlView(bean);
    }

    @Override
    public void DealBuyPraiseInfo(String data) {
        mView.dealBuyPraise(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
