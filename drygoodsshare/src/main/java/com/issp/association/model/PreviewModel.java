package com.issp.association.model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.issp.association.bean.Code;
import com.issp.association.listeners.OnPreviewListener;
import com.issp.association.network.CoreErrorConstants;
import com.issp.association.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;

/**
 * 添加
 * Created by T-BayMax on 2017/4/19.
 */

public class PreviewModel {

    /**
     * 添加
     *
     * @param params
     * @param file
     * @param fileName
     * @param listener
     */
    public void publishAnArticle(Map<String, String> params, File file, String fileName, final OnPreviewListener listener) {

        HttpUtils.sendFormatPostRequest("/addShare", params, file, fileName, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常");
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
                            listener.publishAnArticleListener("干货分享发表成功");
                            break;
                        case 0:
                            listener.showError("干货分享发表失败");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("未知错误");
                }

            }
        });
    }
}
