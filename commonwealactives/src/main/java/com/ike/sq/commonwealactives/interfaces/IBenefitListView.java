package com.ike.sq.commonwealactives.interfaces;

import com.ike.sq.commonwealactives.base.view.BaseView;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.ImageUrlBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/5/5.
 */

public interface IBenefitListView extends BaseView {
    void setBenefitListData(List<BenefitBean> data);

    void getImageUrlView(ImageUrlBean bean);
    void likeBenefitView(String data);
}
