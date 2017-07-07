package com.issp.inspiration.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.inspiration.bean.ArticleCommentBean;
import com.issp.inspiration.bean.Code;
import com.issp.inspiration.bean.CommentsBean;
import com.issp.inspiration.listeners.OnCommentMessageListListener;
import com.issp.inspiration.network.CoreErrorConstants;
import com.issp.inspiration.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class CommentMessageModel {
    public void getCommentMessageInfo(Map<String,String> formData, final OnCommentMessageListListener listener){

        HttpUtils.sendGsonPostRequest("/allNews", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<List<CommentsBean>>>() {
                    }.getType();
                    Code<List<CommentsBean>> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            listener.getCommentMessageInfo(code.getData());
                            break;
                        case 0:
                            listener.showError("查询失败");
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
