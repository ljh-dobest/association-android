package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.bean.UnreadMessgaeBean;
import com.ike.communityalliance.listener.OnGetTalkTalkDataFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.base.cache.disk.DiskLruCacheHelper;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/23.
 */

public class ShareFriendsModule {
    public void getTalkTalkData(String useId, final int page, final OnGetTalkTalkDataFinishListener listener){
        HttpUtils.getTalkTalkData("/selectFriendsCircle", useId, "1",page, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
               listener.showError(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<TalkTalkBean>>>() {
                }.getType();
                Code<List<TalkTalkBean>> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        if (page==1){
                            listener.returnTalkTalkData(code.getData());
                        }else{
                            listener.returnLoadMoreData(code.getData());
                        }

                        break;
                    case 0:
                        listener.showError("无数据");
                        break;
                }
            }
        });
    }
    public void getLocalData(DiskLruCacheHelper helper,OnGetTalkTalkDataFinishListener listener){
        List<TalkTalkBean> data=helper.getAsSerializable("talkTalkBean");
        listener.returnLocalData(data);
    }
    public  void getUnreadMessageData(String useId, final OnGetTalkTalkDataFinishListener listener){
            HttpUtils.getUnreadMessageData("/commentNewsList", useId, "2", new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    listener.showError(e.toString());
                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<List<UnreadMessgaeBean>>>() {
                    }.getType();
                    Code<List<UnreadMessgaeBean>> code = gson.fromJson(response,type);
                if(code.getCode()==200){
                    listener.returnUnreadMessageData(code.getData());
                }else{
                    listener.showError("获取未读消息失败");
                }
                }
            });
    }
}
