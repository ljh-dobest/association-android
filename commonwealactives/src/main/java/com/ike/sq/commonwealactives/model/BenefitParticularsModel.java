package com.ike.sq.commonwealactives.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.Code;
import com.ike.sq.commonwealactives.listeners.OnBenefitParticularsListener;
import com.ike.sq.commonwealactives.network.CoreErrorConstants;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public class BenefitParticularsModel {

    public void getProductCollectInfo(Map<String, String> formData, final OnBenefitParticularsListener listener) {

        HttpUtils.sendGsonPostRequest("/commonwealActivesInfo", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BenefitBean>>() {
                }.getType();
                Code<BenefitBean> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        BenefitBean data = code.getData();
                        listener.getBenefitParticulars(data);
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

    public void platformActivesJoin(Map<String, String> formData, final OnBenefitParticularsListener listener) {
        HttpUtils.sendGsonPostRequest("/commonwealActivesJoin", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code>() {
                }.getType();
                Code code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        listener.benefitActivesJoinSucceed("报名成功！！！");
                        break;
                    case 0:
                        listener.showError("报名失败！！！");
                        break;
                  /*  case 100:
                        listener.benefitActivesJoinSucceed("亲，你已经报过名了！");
                        break;
                    case 101:
                        listener.showError("报名失败！！！");
                        break;*/
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }
    public void userPraise(Map<String, String> formData, final OnBenefitParticularsListener listener) {

        HttpUtils.sendGsonPostRequest("/userPraise", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code>() {
                }.getType();
                Code code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        listener.userPraise("点赞成功");
                        break;
                    /*case 100:
                        listener.showError(code.getMsgs());
                        break;*/
                    case 0:
                        listener.showError("点赞失败");
                        break;
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }
}
