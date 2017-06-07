package com.issp.association.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.issp.association.bean.Code;
import com.issp.association.bean.ImageUrlBean;
import com.issp.association.bean.ShareBean;
import com.issp.association.listeners.OnShareListener;
import com.issp.association.network.CoreErrorConstants;
import com.issp.association.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
*
 * Created by T-BayMax on 2017/3/13.
 */

public class ShareInfoModel {
    public void getShareInfo(Map<String, String> formData, final OnShareListener listener) {

        HttpUtils.sendGsonPostRequest("/listShare", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<ShareBean>>>() {
                }.getType();
                Code<List<ShareBean>> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        ArrayList<ShareBean> data = (ArrayList<ShareBean>) code.getData();
                        listener.getShareInfo(data);
                        break;
                    case 0:
                        listener.showError("查询失败");
                        break;
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }

    public void getImageUrl(Map<String ,String> formData, final OnShareListener listener){

        HttpUtils.sendGsonPostRequest("/selectAdv", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<List<ImageUrlBean>>>() {
                }.getType();
                Code<List<ImageUrlBean>> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        listener.getImageUrl(code.getData());
                        break;
                    case 0:
                        listener.showError("查询失败");
                        break;
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }
    public void getSharePraiseInfo(Map<String, String> formData, final OnShareListener listener) {

        HttpUtils.sendGsonPostRequest("/userPraise", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code>() {
                }.getType();
                Code code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        listener.sharePraiseInfo("点赞成功");
                        break;
                    case 0:
                        listener.showError("点赞失败");
                        break;
                    default:
                        listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                        break;
                }
            }
        });
    }
}
