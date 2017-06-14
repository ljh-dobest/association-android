package com.min.helpcenter.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.bean.ArticleCommentBean;
import com.min.helpcenter.bean.BaseBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.listeners.OnGetAnswerDetailFinishListener;
import com.min.helpcenter.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/4/1.
 */

public class AnswerModule {
    public void getAnswerDetail(String articleId,String userId, String type, final OnGetAnswerDetailFinishListener listener){
        HttpUtils.getArticleComment("/selectArticleComment",articleId, userId, type, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<ArticleCommentBean>>() {
                }.getType();
                Code<ArticleCommentBean> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        listener.returnAnswerDetail(code.getData());
                        break;
                    case 0:

                        break;
                }
            }
        });
    }
    public void commentAnswer(String id, String userId, String content, String type, final OnGetAnswerDetailFinishListener listener){
        if (content.equals("")){
            return;
        }
        HttpUtils.commentAnswer("/articleComment",id,userId, content, type, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                    listener.showSucceedComment("评价成功");
                        break;
                    case 0:
<<<<<<< HEAD
                        listener.showError("评价失败");
=======
                        listener.showSucceedComment("评价失败");
>>>>>>> ljh
                        break;
                }
            }
        });
    }
}
