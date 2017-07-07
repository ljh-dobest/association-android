package com.ike.coalition.platform.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.coalition.platform.bean.ArticleCommentBean;
import com.ike.coalition.platform.bean.Code;
import com.ike.coalition.platform.bean.CommentsBean;
import com.ike.coalition.platform.listeners.OnCommentMessageListListener;
import com.ike.coalition.platform.network.CoreErrorConstants;
import com.ike.coalition.platform.network.HttpUtils;
import com.ike.coalition.platform.utils.L;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class CommentMessageModel {
    public void getCommentMessageInfo(Map<String,String>formData, final OnCommentMessageListListener listener){

        HttpUtils.sendGsonPostRequest("/allNews", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<List<CommentsBean>>>() {
                    }.getType();
                    Code<List<CommentsBean>> code = gson.fromJson(response, type);
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
                }catch (Exception e){
                    L.e("MessageCallback",e.toString());
                    listener.showError("系统解析服务器错误！");
                }
            }
        });
    }
}
