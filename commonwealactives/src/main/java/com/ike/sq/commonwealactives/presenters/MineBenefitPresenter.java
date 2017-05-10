package com.ike.sq.commonwealactives.presenters;


import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.interfaces.IMineBenefitListView;
import com.ike.sq.commonwealactives.listeners.OnBenefitListener;
import com.ike.sq.commonwealactives.listeners.OnMineBenefitListener;
import com.ike.sq.commonwealactives.model.BenefitModel;
import com.ike.sq.commonwealactives.model.MineBenefitModel;

import java.util.List;
import java.util.Map;


/**
 *Created by T-BayMax on 2017/3/13.
 */

public class MineBenefitPresenter extends BasePersenter<IMineBenefitListView> implements OnMineBenefitListener {
    private MineBenefitModel moudle;

    public MineBenefitPresenter() {
        moudle = new MineBenefitModel();
    }
    public void getBenefitPresenter(Map<String,String> formData){
        moudle.getBenefitList(formData,this);
    }
    public void likeBenefitPresenter(Map<String,String> formData){
        moudle.getBenefitPraise(formData,this);
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
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
