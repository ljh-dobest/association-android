package com.issp.association.crowdfunding.interfaces;

import com.issp.association.crowdfunding.base.view.BaseView;
import com.issp.association.crowdfunding.bean.OrderDetailBean;
import com.issp.association.crowdfunding.bean.ShippingAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址
 * Created by T-BayMax on 2017/3/25.
 */

public interface IShippingAddressListView extends BaseView {
    void setShippingAddressListData(List<ShippingAddressBean> data);
}
