package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.Image;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.listener.OnFinishEditPersonalInfoListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/4/24.
 */

public class PersonalInfoEditMoudle {
    public void postPersonalInfo(UserInfo userInfo, String type, final OnFinishEditPersonalInfoListener listener){
        if(type.equals("1")){
            HttpUtils.postChangePerson1("/editUserInfo",userInfo ,new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    listener.showError(e.toString());
                }
                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<Image>>() {
                    }.getType();
                    Code<Image> code = gson.fromJson(response,type);
                    if (code.getCode() == 200) {
                        listener.succeedToEdit(code.getData().getUserPortraitUrl());
                    } else {
                        listener.showError("修改失败");
                    }
                }
            });
        } else {
            HttpUtils.postChangePerson("/editUserInfo",userInfo, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    listener.showError(e.toString());
                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<Image>>() {
                    }.getType();
                    Code<Image> code = gson.fromJson(response,type);
                    if (code.getCode() == 200) {
                        listener.succeedToEdit(code.getData().getUserPortraitUrl());
                    } else {
                       listener.showError("修改失败");
                    }
                }
            });
        }

    }

}
