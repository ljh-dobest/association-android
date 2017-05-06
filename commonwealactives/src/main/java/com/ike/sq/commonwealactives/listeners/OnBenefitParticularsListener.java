package com.ike.sq.commonwealactives.listeners;


import com.ike.sq.commonwealactives.bean.BenefitBean;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface OnBenefitParticularsListener {
    void getBenefitParticulars(BenefitBean data);

    void benefitActivesJoinSucceed(String data);

    void userPraise(String data);

    void showError(String errorString);
}
