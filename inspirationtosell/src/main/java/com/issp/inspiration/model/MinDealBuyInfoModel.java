package com.issp.inspiration.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.inspiration.bean.Code;
import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.listeners.OnMinDealBuyListener;
import com.issp.inspiration.network.CoreErrorConstants;
import com.issp.inspiration.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
*
 * Created by T-BayMax on 2017/3/13.
 */

public class MinDealBuyInfoModel {
    public void getMinDealBuyInfo(Map<String, String> formData, final OnMinDealBuyListener listener) {

        HttpUtils.sendGsonPostRequest("/myAddShare", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<List<DealBuyBean>>>() {
                    }.getType();
                    Code<List<DealBuyBean>> code = gson.fromJson(response, type);

                    switch (code.getCode()) {
                        case 200:
                            ArrayList<DealBuyBean> data = (ArrayList<DealBuyBean>) code.getData();
                            listener.getMinDealBuyInfo(data);
                            break;
                        case 0:
                            listener.showError("你还未发布任何干货！");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器错误！");
                }
            }
        });
    }

    public void getDealBuyPraiseInfo(Map<String, String> formData, final OnMinDealBuyListener listener) {

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
                            listener.dealBuyPraiseInfo("点赞成功");
                            break;
                        case 0:
                            listener.showError("点赞失败");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器错误！");
                }
            }
        });
    }
}
