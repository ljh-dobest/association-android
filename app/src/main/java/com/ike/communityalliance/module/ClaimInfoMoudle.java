package com.ike.communityalliance.module;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.ClaimInfoBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.listener.OnClaimFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.mylibrary.util.AMUtils;
import com.ike.mylibrary.util.CommonUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/10.
 */

public class ClaimInfoMoudle {
 public void postClaimInfo(ClaimInfoBean claimInfo, final OnClaimFinishListener listener){
       if (claimInfo.getFullName().equals("")||claimInfo.getMobile().equals("")||claimInfo.getSex().equals("")
           ||claimInfo.getHobby().equals("")||claimInfo.getAddress().size()==0||claimInfo.getRelationship().equals("")
           ||claimInfo.getCreditScore().equals("")){
       listener.showTextEmpty();
        return;
       }
  if(!AMUtils.isMobile(claimInfo.getMobile())) {
   listener.failedToClaim("请输入正确的手机号码");
   return;
  }
  if(claimInfo.getAddress().get(0).equals("请选择")){
   listener.failedToClaim("请输入具体地址信息");
   return;
  }
if(!claimInfo.getEmail().equals("")&&!CommonUtils.isEmail(claimInfo.getEmail())){
 listener.failedToClaim("请输入正确的邮箱");
 return;
}
  if(claimInfo.getHomeplace().equals("请选择,请选择,请选择")){
   claimInfo.setHomeplace("");
  }
  HttpUtils.postClaimInfo("/claimUser", claimInfo, new StringCallback() {
   @Override
   public void onError(Call call, Exception e, int id) {
    listener.failedToClaim(e.toString());
   }

   @Override
   public void onResponse(String response, int id) {
         Gson gson=new Gson();
    Type type = new TypeToken<Code<Object>>() {
    }.getType();
    Code<Object> code=gson.fromJson(response,type);
    switch (code.getCode()) {
     case 200:
      listener.succeedToClaim();
      break;
     case 0:
      listener.failedToClaim("认领失败");
      break;
     case 100:
      listener.failedToClaim("已认领");
      break;
     case 300:
      listener.waitClaim();
      break;
     case 101:
      listener.waitClaim();
      break;
    }
   }
  });
  }
 public void getParserData(final Context mComtext, final String fileName, final OnClaimFinishListener listener){
  new Thread(new Runnable() {
   @Override
   public  void run() {
    String jsonData= DateUtils.getJson(mComtext,fileName);
    //解析数据
    Gson gson=new Gson();
    Type type = new TypeToken<ArrayList<ProvinceBean>>() {
    }.getType();
    ArrayList<ProvinceBean> result=gson.fromJson(jsonData,type);
    listener.returnParserData(result);
   }
  }).start();
 }

 private boolean isFirstHobby=true;
 //获取选择的爱好
 public void getHobby(ViewGroup group, OnClaimFinishListener listener) {
  String hobbys="";
  for (int i = 0; i < group.getChildCount(); i++) {
   LinearLayout ll= (LinearLayout) group.getChildAt(i);
   for (int j= 2; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
    CheckBox rb= (CheckBox) ll.getChildAt(j);
    if (rb.isChecked()) {
     if (isFirstHobby) {
      hobbys = rb.getText().toString();
      isFirstHobby = false;
     } else {
      hobbys = hobbys + "," + rb.getText().toString();
     }
    }
   }
  }
     isFirstHobby=true;
     listener.retturnHobbys(hobbys);
 }
}
