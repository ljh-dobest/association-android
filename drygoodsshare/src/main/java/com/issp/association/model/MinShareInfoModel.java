package com.issp.association.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.association.bean.Code;
import com.issp.association.bean.ShareBean;
import com.issp.association.listeners.OnMinShareListener;
import com.issp.association.listeners.OnShareListener;
import com.issp.association.network.CoreErrorConstants;
import com.issp.association.network.HttpUtils;
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

public class MinShareInfoModel {
    public void getMinShareInfo(Map<String, String> formData, final OnMinShareListener listener) {

        HttpUtils.sendGsonPostRequest("/myAddShare", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<List<ShareBean>>>() {
                    }.getType();
                    Code<List<ShareBean>> code = gson.fromJson(response, type);

                    switch (code.getCode()) {
                        case 200:
                            ArrayList<ShareBean> data = (ArrayList<ShareBean>) code.getData();
                            listener.getMinShareInfo(data);
                            break;
                        case 0:
                            listener.showError("你还未发布任何干货！");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (JsonSyntaxException e) {
                    listener.showError("系统解析服务器发生错误！");
                }
            }
        });
    }

    public void getSharePraiseInfo(Map<String, String> formData, final OnMinShareListener listener) {

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
                            listener.sharePraiseInfo("点赞成功");
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
