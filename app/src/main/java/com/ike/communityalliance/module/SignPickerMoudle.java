package com.ike.communityalliance.module;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.DateBean;
import com.ike.communityalliance.bean.SignBean;
import com.ike.communityalliance.listener.OnGetSignPickerDataFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/10.
 */

public class SignPickerMoudle {
 public void getSignPickerData(String userId, final OnGetSignPickerDataFinishListener listener){
             if(userId==null){
                 return;
             }
     HttpUtils.getSignPicker("/signDate", userId, new StringCallback() {
         @Override
         public void onError(Call call, Exception e, int id) {
               listener.showError(e.toString());
         }

         @Override
         public void onResponse(String response, int id) {
             Gson gson=new Gson();
             Type type = new TypeToken<Code<List<DateBean>>>() {
             }.getType();
             Code<List<DateBean>> code = gson.fromJson(response,type);
             switch (code.getCode()) {
                 case 200:
                     List<DateBean> dateBeanList=code.getData();
                     listener.returnSignPickerData(dateBeanList);
                     break;
                 case 0:
                     List<DateBean> dateBeanList1=new ArrayList<DateBean>();
                     listener.returnSignPickerData(dateBeanList1);
                     break;
             }

         }
     });
 }
     public void sign(String userId, final OnGetSignPickerDataFinishListener listener){
          HttpUtils.sign("/sign", userId, new StringCallback() {
              @Override
              public void onError(Call call, Exception e, int id) {
                   listener.showError(e.toString());
              }

              @Override
              public void onResponse(String response, int id) {
                  Gson gson=new Gson();
                  Type type = new TypeToken<Code<SignBean>>() {
                  }.getType();
                  Code<SignBean> code = gson.fromJson(response,type);
                  switch (code.getCode()) {
                      case 200:
                          String msg="连续签到"+code.getData().getDays()+"天,奖励"+code.getData().getExperience()+"经验值";
                         listener.succeedToSign(msg);
                          break;
                      case 100:
                          listener.showError("今天已签到");
                          break;
                      case 101:
                          listener.showError("签到失败");
                          break;
              }
              }
          });
     }
}
