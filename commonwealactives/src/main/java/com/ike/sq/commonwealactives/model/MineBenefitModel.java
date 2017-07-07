package com.ike.sq.commonwealactives.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.bean.Code;
import com.ike.sq.commonwealactives.listeners.OnBenefitListener;
import com.ike.sq.commonwealactives.listeners.OnMineBenefitListener;
import com.ike.sq.commonwealactives.network.CoreErrorConstants;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public class MineBenefitModel {

    public void getBenefitList(Map<String, String> formData, final OnMineBenefitListener listener) {

        HttpUtils.sendGsonPostRequest("/selectJoinCommonwealActives", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<List<BenefitBean>>>() {
                    }.getType();
                    Code<List<BenefitBean>> code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            List<BenefitBean> data = code.getData();
                            listener.getBenefitList(data);
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

    public void getBenefitPraise(Map<String, String> formData, final OnMineBenefitListener listener) {

        HttpUtils.sendGsonPostRequest("/userPraise", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code>() {
                    }.getType();
                    Code code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            listener.likeBenefit("点赞成功");
                            break;
                       /* case 100:
                            listener.showError("已点赞");
                            break;*/
                        case 0:
                            listener.showError("点赞失败");
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
