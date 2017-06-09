package com.min.helpcenter.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.listeners.OnGetQuestionDetailsFinishListener;
import com.min.helpcenter.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/4/1.
 */

public class QuestionModule {
    public void getQuestionDetail(String userId, String seekId, final OnGetQuestionDetailsFinishListener listener){
        HttpUtils.getQuestionDetails("/selectSeekHelpInfo", userId, seekId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<HelpBean>>() {
                }.getType();
                Code<HelpBean> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        listener.returnQuestion(code.getData());
                        break;
                    case 0:
                        listener.showError("数据请求失败");
                        break;
                }
            }
        });
    }
}
