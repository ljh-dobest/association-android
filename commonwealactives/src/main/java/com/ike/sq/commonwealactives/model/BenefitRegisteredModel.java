package com.ike.sq.commonwealactives.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.sq.commonwealactives.bean.Code;
import com.ike.sq.commonwealactives.bean.UserBean;
import com.ike.sq.commonwealactives.listeners.OnBenefitParticularsListener;
import com.ike.sq.commonwealactives.listeners.OnBenefitRegisteredListener;
import com.ike.sq.commonwealactives.network.CoreErrorConstants;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/13.
 */

public class BenefitRegisteredModel {

    public void getPlatformRegisteredInfo(Map<String ,String> formData, final OnBenefitRegisteredListener listener){

        HttpUtils.sendGsonPostRequest("/commonwealActivesJoinUser", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<List<UserBean>>>() {
                }.getType();
                Code<List<UserBean>> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        List<UserBean> data=  code.getData();
                        listener.getPlatformRegisteredList(data);
                        break;
                    case 0:
                        listener.showError("查询失败");
                        break;
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }
}
