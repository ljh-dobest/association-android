package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.HomePageBean;
import com.ike.communityalliance.listener.OnGetHomePageDataFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/4/26.
 */

public class HomePageFragmentModule {
    public void getHomePageFragmentData(String userId, final OnGetHomePageDataFinishListener listener){
        HttpUtils.getHomePageData("/indexData", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showErrorString(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<HomePageBean>>() {
                }.getType();
                Code<HomePageBean> code=gson.fromJson(response,type);
                if(code.getCode()==200){
                     listener.returnHomePageData(code.getData());
                }else{
                    listener.showErrorString("数据获取失败");
                }

            }
        });
    }
}
