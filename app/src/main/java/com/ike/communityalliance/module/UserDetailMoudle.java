package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.listener.OnfinishCheckResultListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/5/15.
 */

public class UserDetailMoudle {
    public void checkPhone(String userId, String mobile, final OnfinishCheckResultListener listener){
        HttpUtils.checkUserInfo("/selectUserInfo", userId, mobile,"1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<UserInfo>>() {
                }.getType();
                Code<UserInfo> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                   listener.returnCheckResult(code.getData());
                } else {
                   listener.returnNotUser();
                }
            }
        });
    }
}
