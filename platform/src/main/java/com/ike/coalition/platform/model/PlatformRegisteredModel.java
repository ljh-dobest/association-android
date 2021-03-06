package com.ike.coalition.platform.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ike.coalition.platform.bean.Code;
import com.ike.coalition.platform.bean.PlatformBean;
import com.ike.coalition.platform.bean.UserBean;
import com.ike.coalition.platform.listeners.OnPlatformListener;
import com.ike.coalition.platform.listeners.OnPlatformRegisteredListener;
import com.ike.coalition.platform.network.CoreErrorConstants;
import com.ike.coalition.platform.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 *Created by T-BayMax on 2017/3/13.
 */

public class PlatformRegisteredModel {

    public void getPlatformRegisteredInfo(Map<String ,String> formData, final OnPlatformRegisteredListener listener){

        HttpUtils.sendGsonPostRequest("/platformActivesJoinUser", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
              listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson=new Gson();
                    Type type = new TypeToken<Code<List<UserBean>>>() {
                    }.getType();
                    Code<List<UserBean>> code = gson.fromJson(response,type);
                    switch (code.getCode()) {
                        case 200:
                            List<UserBean> data=  code.getData();
                            listener.getPlatformRegisteredList(data);
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
