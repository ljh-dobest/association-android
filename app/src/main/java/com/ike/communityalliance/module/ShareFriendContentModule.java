package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.Comment;
import com.ike.communityalliance.listener.OnGetCommentDataFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/23.
 */

public class ShareFriendContentModule {
    public void getCommentData(String useId, final String id, final OnGetCommentDataFinishListener listener){
        HttpUtils.getCommentData("/selectArticleComment", id,useId,"2",new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
               listener.showError(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Comment>>() {
                }.getType();
                Code<Comment> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        listener.returnCommentData(code.getData().getComments());
                        break;
                    case 0:
                        listener.showError("无数据");
                        break;
                }
            }
        });
    }
    public void postComment(String id, String useId, String content, final OnGetCommentDataFinishListener listener){
        if(content.equals("")){
            listener.showError("");
            return;
        }
        HttpUtils.postCommentData("/articleComment", id, useId, content,"2", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                if(code.getCode()==200){
                    listener.succeedToComment("评论成功");
                }else{
                    listener.showError("评论失败");
                }
            }
        });
    }
}
