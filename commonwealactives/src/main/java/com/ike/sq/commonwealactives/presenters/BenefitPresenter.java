package com.ike.sq.commonwealactives.presenters;


import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.ImageUrlBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.listeners.OnBenefitListener;
import com.ike.sq.commonwealactives.model.BenefitModel;

import java.util.List;
import java.util.Map;


/**
 *Created by T-BayMax on 2017/3/13.
 */

public class BenefitPresenter extends BasePersenter<IBenefitListView> implements OnBenefitListener {
    private BenefitModel model;

    public BenefitPresenter() {
        model = new BenefitModel();
    }
    public void getBenefitPresenter(Map<String,String> formData){
        model.getBenefitList(formData,this);
    }
    public void likeBenefitPresenter(Map<String,String> formData){
        model.getBenefitPraise(formData,this);
    }

    public void getImage(Map<String ,String> formData){
        model.getImageUrl(formData,this);
    }


    @Override
    public void getBenefitList(List<BenefitBean> data) {
        mView.setBenefitListData(data);
    }

    @Override
    public void likeBenefit(String data) {
        mView.likeBenefitView(data);
    }

    @Override
    public void getImageUrl(List<ImageUrlBean> bean) {
        mView.getImageUrlView(bean);
    }
    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
