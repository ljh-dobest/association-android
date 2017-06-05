package com.ike.communityalliance.module;

import android.text.TextUtils;

import com.ike.mylibrary.util.AMUtils;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.listener.OnRegisterFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.audio.JsonParser;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/6.
 */

public class RegisterMoudle {
    public void register(String userName, String mobile, String pwd, String recommendId, final OnRegisterFinishListener listener){
        if (TextUtils.isEmpty(mobile)|| TextUtils.isEmpty(pwd)|| TextUtils.isEmpty(recommendId)){
                    listener.showTextEmpty();
            return;
        }
        if(!AMUtils.isMobile(mobile)) {
            listener.failedToRegister("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(pwd) || pwd.length() < 4) {
            listener.showPwdError();
            return;
        }

            //信息提交服务器
            HttpUtils.postRegisterRequest1("/register", userName, mobile, pwd, recommendId, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                   listener.failedToRegister(e.toString());
                }
                @Override
                public void onResponse(String response, int id) {
                    Code<Object> code = JsonParser.parserRegisterRespon(response);
                    switch (code.getCode()) {
                        case 200:
                            listener.succeedToRegiset();
                            break;
                        case 100:
                            listener.failedToRegister("账号已注册");
                            break;
                        case 1000:
                            listener.failedToRegister("推荐码不正确");
                            break;
                          default:
                              listener.failedToRegister("未知错误");
                              break;
                    }
                }
            });
        }
}
