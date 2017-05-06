package com.ike.sq.commonwealactives.interfaces;


import com.ike.sq.commonwealactives.base.view.BaseView;
import com.ike.sq.commonwealactives.bean.BenefitBean;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface IBenefitParticularsView extends BaseView {
    void setBenefitParticularsData(BenefitBean data);
    void BenefitActivesJoinSucceed(String data);

    void userPraise(String data);
   // void dealBuyPraise(String data);
}
