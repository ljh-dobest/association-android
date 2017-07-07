package com.min.threeminutestoteach.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.listener.OnFinishTeachContentListener;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/6/17.
 */

public class TeacheContentModule {
    public void getTeachContent(String userId, String articleId, final OnFinishTeachContentListener listener){
        HttpUtils.getTeachContent("/selectVideoInfo", userId, articleId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<TeacheBean>>() {
                }.getType();
                Code<TeacheBean> code = gson.fromJson(response, type);
                if(code.getCode()==200){
                    listener.returnTeachContentData(code.getData());
                }else{
                    listener.showError("获取详情失败");
                }
            }
        });
    }

    public void clickToLike(String userId, String articleId, String status, final OnFinishTeachContentListener listener){
        HttpUtils.clickToLike("/userPraise", userId, articleId,status, "9", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                if(code.getCode()==200){
                      listener.succeedToLike();
                }else{
                         listener.showError("点赞失败");
                }
            }
        });
    }
    public void clickToCollection(String userId, String articleId, final String status, final OnFinishTeachContentListener listener){
        HttpUtils.clickToCollection("/articleCollection", userId, articleId,status,"9", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                 listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        listener.succeedToCollection(status);
                        break;
                    case 0:
                        listener.showError("收藏失败");
                        break;
                    case 1031:
                        listener.showError("已收藏");
                        break;
                }
            }
        });
    }
}
