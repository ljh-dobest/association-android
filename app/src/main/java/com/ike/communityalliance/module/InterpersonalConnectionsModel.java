package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.RelationBean;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.listener.InterpersonalConnectionsListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by T-BayMax on 2017/3/14.
 */

public class InterpersonalConnectionsModel {
    public void getConnectionData(Map<String, String> formData, final InterpersonalConnectionsListener listener) {
        HttpUtils.sendFormBodyPostRequest("/relationship", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                listener.onConnectionsError("系统异常");
            }

            @Override
            public void onResponse(String response, int id) {
                Type jsonType = new TypeToken<Code<List<RelationshipBean>>>() {
                }.getType();
                Code<List<RelationshipBean>> code = new Gson().fromJson(response, jsonType);
                switch (code.getCode()) {
                    case 200:
                        listener.onConnectionsSucceed(code.getData());
                        break;
                    case 0:
                        listener.onConnectionsError("没有查找到关系");
                        break;
                }
            }
        });
    }

    public void sendAddFriendRequest(Map<String, String> formData, final InterpersonalConnectionsListener listener) {
        HttpUtils.sendFormBodyPostRequest("/relationship", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                listener.onConnectionsError("系统异常");
            }

            @Override
            public void onResponse(String response, int id) {
                Type jsonType = new TypeToken<Code>() {
                }.getType();
                Code code = new Gson().fromJson(response, jsonType);
                switch (code.getCode()) {
                    case 200:
                        listener.addFriends("好友添加成功");
                        break;
                    case 0:
                        listener.onConnectionsError("没有查找到关系");
                        break;
                }
            }
        });
    }
}
