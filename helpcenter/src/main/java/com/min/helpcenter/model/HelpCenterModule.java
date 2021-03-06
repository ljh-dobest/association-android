package com.min.helpcenter.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.listeners.OnGetHelpDataFinishListener;
import com.min.helpcenter.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/28.
 */

public class HelpCenterModule {
    public void getHelpData(String userId, final int page, final OnGetHelpDataFinishListener listener){
        HttpUtils.getHelpData("/selectSeekHelpList", userId, page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
        listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<List<HelpBean>>>() {
                }.getType();
                Code<List<HelpBean>> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        if(page==1){
                            listener.returnHelpData(code.getData());
                        }else {
                            listener.returnMoreHelpData(code.getData());
                        }
                        break;
                    case 0:
                        listener.showError("数据请求失败");
                        break;
                }
            }
        });
    }
}
