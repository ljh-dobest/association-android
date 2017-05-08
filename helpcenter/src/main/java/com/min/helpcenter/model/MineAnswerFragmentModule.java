package com.min.helpcenter.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.bean.MineHelp;
import com.min.helpcenter.listeners.OnGetMineAnswerFinishListener;
import com.min.helpcenter.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/4/1.
 */

public class MineAnswerFragmentModule {
    public void getMineAnswerData(String userId, String status,final OnGetMineAnswerFinishListener listener){
        HttpUtils.getMineHelpOrQuestions("/mySeekHelp", userId, status, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<List<MineHelp>>>() {
                }.getType();
                Code<List<MineHelp>> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        listener.returnMineAnswer(code.getData());
                        break;
                    case 0:
                        listener.showError("数据请求失败");
                        break;
                }
            }
        });
    }
}
