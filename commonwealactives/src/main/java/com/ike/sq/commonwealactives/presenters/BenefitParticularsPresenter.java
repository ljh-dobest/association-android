package com.ike.sq.commonwealactives.presenters;

import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitListView;
import com.ike.sq.commonwealactives.interfaces.IBenefitParticularsView;
import com.ike.sq.commonwealactives.listeners.OnBenefitParticularsListener;
import com.ike.sq.commonwealactives.model.BenefitModel;
import com.ike.sq.commonwealactives.model.BenefitParticularsModel;

import java.util.Map;


/**
 * Created by T-BayMax on 2017/3/13.
 */

public class BenefitParticularsPresenter extends BasePersenter<IBenefitParticularsView> implements OnBenefitParticularsListener {
    private BenefitParticularsModel recommendInfoMoudle;

    public BenefitParticularsPresenter() {
        recommendInfoMoudle = new BenefitParticularsModel();
    }


    public void ParticularsPresenter(Map<String, String> formData) {
        recommendInfoMoudle.getProductCollectInfo(formData, this);

    }

    public void platformActivesJoin(Map<String, String> formData) {
        recommendInfoMoudle.platformActivesJoin(formData, this);
    }

    public void addUserPraise(Map<String, String> formData) {
        recommendInfoMoudle.userPraise(formData, this);
    }


    @Override
    public void getBenefitParticulars(BenefitBean data) {
        mView.setBenefitParticularsData(data);
    }

    @Override
    public void benefitActivesJoinSucceed(String data) {
        mView.BenefitActivesJoinSucceed(data);
    }

    @Override
    public void userPraise(String data) {
        mView.userPraise(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
