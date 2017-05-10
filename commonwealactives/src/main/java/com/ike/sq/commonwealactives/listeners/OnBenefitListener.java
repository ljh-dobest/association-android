package com.ike.sq.commonwealactives.listeners;

import com.ike.sq.commonwealactives.bean.BenefitBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/5/5.
 */

public interface OnBenefitListener {

    void getBenefitList(List<BenefitBean> data);

    void likeBenefit(String data);

    void showError(String errorString);
}
