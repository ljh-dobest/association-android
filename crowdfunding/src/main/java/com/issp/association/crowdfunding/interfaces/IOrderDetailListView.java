package com.issp.association.crowdfunding.interfaces;

import com.issp.association.crowdfunding.base.view.BaseView;
import com.issp.association.crowdfunding.bean.OrderDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情
 * Created by T-BayMax on 2017/3/25.
 */

public interface IOrderDetailListView  extends BaseView {
    void setOrderDetailListData(List<OrderDetailBean> data);
}
