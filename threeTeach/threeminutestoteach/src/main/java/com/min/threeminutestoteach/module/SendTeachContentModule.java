package com.min.threeminutestoteach.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.listener.OnFinishSendTeachContentListener;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Min on 2017/6/12.
 */

public class SendTeachContentModule {
    public void sendTeachContent(String userId, String title, String content,String playTime, String status, File imgFile, File videoFile, final OnFinishSendTeachContentListener listener){
        Map<String,String> map=new HashMap<>();
        map.put("userId",userId);
        map.put("title",title);
        map.put("content",content);
        map.put("playTime",playTime);
        map.put("status",status);
        HttpUtils.postTeachContent("/releaseVideo",map, imgFile, videoFile, new StringCallback() {
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
                if (code.getCode()==200){
                    listener.succeedToPublic();
                }else{
                    listener.showError("发布失败");
                }
            }

        });
    }
}
