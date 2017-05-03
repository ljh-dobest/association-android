package com.ike.communityalliance.module;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.InterestGroupBean;
import com.ike.communityalliance.listener.OnGetInterestGroupDataFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/20.
 */

public class ChessAndCardsModule {
    /**
     * 根据类型id获取相应的兴趣联盟
     * @param hobbyId
     */
    public void getInterestGroupData(String useId, String hobbyId, final OnGetInterestGroupDataFinishListener listener){
        HttpUtils.getInterestGroupsData("/hobbyGroupList", useId, hobbyId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                   listener.showErrorString(e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<List<InterestGroupBean>>>() {
                }.getType();
                Code<List<InterestGroupBean>> code=gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                       List<InterestGroupBean> groupData=code.getData();
                        List<InterestGroupBean> unJoinGroupData=getUnJoinGroupData(groupData);
                        listener.returnInterestGroupData(unJoinGroupData);
                        break;
                    case 0:
                        listener.showErrorString("服务器繁忙");
                        break;
                }
            }
        });
    }
/**
 * 返回未加入的兴趣联盟
 * */
    private List<InterestGroupBean> getUnJoinGroupData(List<InterestGroupBean> groupData) {
        List<InterestGroupBean> unJoinGroupData=new ArrayList<>();
        for (InterestGroupBean interestGroupBean : groupData) {
            if(interestGroupBean.getStatus()==0){
                unJoinGroupData.add(interestGroupBean);
            }
        }
            return unJoinGroupData;
    }
}
