package com.issp.association.crowdfunding.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.association.crowdfunding.bean.Code;
import com.issp.association.crowdfunding.bean.ImageUrlBean;
import com.issp.association.crowdfunding.bean.ProductCollectBean;
import com.issp.association.crowdfunding.bean.UserBean;
import com.issp.association.crowdfunding.listeners.OnProductCollectListener;
import com.issp.association.crowdfunding.network.CoreErrorConstants;
import com.issp.association.crowdfunding.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;


import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/13.
 */

public class ProductCollectModel {

    public void getProductCollectInfo(Map<String ,String> formData, final OnProductCollectListener listener){

        HttpUtils.sendGsonPostRequest("/selectProductList", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<List<ProductCollectBean>>>() {
                    }.getType();
                    Code<List<ProductCollectBean>> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            ArrayList<ProductCollectBean> data= (ArrayList<ProductCollectBean>) code.getData();
                            listener.getProductCollectInfo(data);
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

    public void getImageUrl(Map<String ,String> formData, final OnProductCollectListener listener){

        HttpUtils.sendGsonPostRequest("/selectAdv", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<List<ImageUrlBean>>>() {
                    }.getType();
                    Code<List<ImageUrlBean>> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            listener.getImageUrl(code.getData());
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
    public void selectProductIdCardView(Map<String, String> formData, final OnProductCollectListener listener) {

        HttpUtils.sendGsonPostRequest("/selectProductIdcardInfo", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<UserBean>>() {
                    }.getType();
                    Code<UserBean> code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            if (null==code.getData().getIdcard()){
                                listener.showError(code.getMsgs());
                            }else {
                                listener.selectProductIdCard("已验证");
                            }
                            break;
                        case 0:
                            listener.showError("未验证");
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

    /**
     * 点赞
     * @param formData
     * @param listener
     */
    public void postUserPraise(Map<String, String> formData, final OnProductCollectListener listener) {

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
                            listener.userPraise("点赞成功");
                            break;
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
