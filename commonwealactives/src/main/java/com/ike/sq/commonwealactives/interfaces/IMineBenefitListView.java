package com.ike.sq.commonwealactives.interfaces;

import com.ike.sq.commonwealactives.base.view.BaseView;
import com.ike.sq.commonwealactives.bean.BenefitBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/5/5.
 */

public interface IMineBenefitListView extends BaseView {
    void setBenefitListData(List<BenefitBean> data);

    void likeBenefitView(String data);
}
