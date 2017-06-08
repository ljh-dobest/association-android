package com.issp.association.crowdfunding.listeners;


import com.issp.association.crowdfunding.bean.OrderDetailBean;
import com.issp.association.crowdfunding.bean.ShippingAddressBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 收货地址管理
 * Created by T-BayMax on 2017/3/25.
 */

public interface OnShippingAddressListListener {
    void getShippingAddressInfo(List<ShippingAddressBean> data);

    void showError(String errorString);
}
