package com.min.threeminutestoteach.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.listener.OnFinishGetThreeTeachListener;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/6/12.
 */

public class ThreeTeachModule {
public void getThreeTeachData(String userId, final String page, final OnFinishGetThreeTeachListener listener){
    HttpUtils.getThreeTeachData("/selectVideoList", userId, page, new StringCallback() {
        @Override
        public void onError(Call call, Exception e, int id) {
            listener.showError(e.toString());
        }

        @Override
        public void onResponse(String response, int id) {
            Gson gson = new Gson();
            Type type = new TypeToken<Code<List<TeacheBean>>>() {
            }.getType();
            Code<List<TeacheBean>> code = gson.fromJson(response, type);
            if (code.getCode()==200){
                if(page.equals("1")){
                    listener.returnThreeTeachData(code.getData());
                }else{
                    listener.returnMoreTeachData(code.getData());
                }
            }else{
                listener.showError("获取失败");
            }
        }
    });
}
}
