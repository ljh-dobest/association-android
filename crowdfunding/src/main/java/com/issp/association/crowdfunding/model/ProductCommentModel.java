package com.issp.association.crowdfunding.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.issp.association.crowdfunding.bean.Code;
import com.issp.association.crowdfunding.bean.CommentsBean;
import com.issp.association.crowdfunding.bean.ProductCommentBean;
import com.issp.association.crowdfunding.listeners.OnProductCommentListListener;
import com.issp.association.crowdfunding.network.CoreErrorConstants;
import com.issp.association.crowdfunding.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class ProductCommentModel {
    public void getProductCommentInfo(Map<String, String> formData, final OnProductCommentListListener listener){

        HttpUtils.sendGsonPostRequest("/selectArticleComment", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<ProductCommentBean>>() {
                    }.getType();
                    Code<ProductCommentBean> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            if (null!=code.getData()) {
                                ProductCommentBean comment = code.getData();
                                List<CommentsBean> data=comment.getComments();
                                listener.getProductCommentInfo(data);
                            }
                            break;
                        case 0:
                            listener.showError("还没有任何评论");
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
