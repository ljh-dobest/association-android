package com.ike.sq.commonwealactives.presenters;


import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.UserBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitRegisteredListView;
import com.ike.sq.commonwealactives.listeners.OnBenefitRegisteredListener;
import com.ike.sq.commonwealactives.model.BenefitRegisteredModel;

import java.util.List;
import java.util.Map;


/**
 *Created by T-BayMax on 2017/3/13.
 */

public class BenefitRegisteredPresenter extends BasePersenter<IBenefitRegisteredListView> implements OnBenefitRegisteredListener {
    private BenefitRegisteredModel recommendInfoMoudle;

    public BenefitRegisteredPresenter() {
        recommendInfoMoudle = new BenefitRegisteredModel();
    }


    public void PlatformRegisteredPresenter(Map<String ,String> formData) {
        recommendInfoMoudle.getPlatformRegisteredInfo(formData, this);

    }


    @Override
    public void getPlatformRegisteredList(List<UserBean> data) {
        mView.setPlatformRegisteredListData(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
