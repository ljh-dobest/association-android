package com.issp.association.crowdfunding.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.association.crowdfunding.bean.Code;
import com.issp.association.crowdfunding.bean.OrderDetailBean;
import com.issp.association.crowdfunding.listeners.OnAddShippingAddressListListener;
import com.issp.association.crowdfunding.listeners.OnShippingAddressListListener;
import com.issp.association.crowdfunding.network.CoreErrorConstants;
import com.issp.association.crowdfunding.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 收货地址管理
 *Created by T-BayMax on 2017/3/25.
 */

public class AddShippingAddressModel {
    public void getShippingAddressInfo(Map<String,String> fromData, final OnAddShippingAddressListListener listener){

        HttpUtils.sendGsonPostRequest("/allRecommendsUsers", fromData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<List<OrderDetailBean>>>() {
                    }.getType();
                    Code<List<OrderDetailBean>> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            //ArrayList<OrderDetailBean> data= (ArrayList<OrderDetailBean>) code.getData();
                            listener.getShippingAddressInfo("");
                            break;
                        case 0:
                            listener.showError("查询失败");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器发生错误！");
                }
            }
        });
    }
}
