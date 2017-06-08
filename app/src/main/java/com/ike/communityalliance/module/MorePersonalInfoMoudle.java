package com.ike.communityalliance.module;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.EditMorePersonalInfo;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.listener.OnFinishGetMorePersonalInfoListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * Created by Min on 2017/5/5.
 */

public class MorePersonalInfoMoudle {
 public  void getMorePersonalInfoData(String userId, final OnFinishGetMorePersonalInfoListener listener){
     HttpUtils.getMorePersonalInfo("/selectUserInfo", userId,userId,"0",new StringCallback() {
         @Override
         public void onError(Call call, Exception e, int id) {
            listener.showError(e.toString());
         }

         @Override
         public void onResponse(String response, int id) {
             Gson gson = new Gson();
             Type type = new TypeToken<Code<MorePersonalInfo>>() {
             }.getType();
             Code<MorePersonalInfo> result = gson.fromJson(response, type);
             if (result.getCode() == 200) {
                 listener.returnMorePersonalInfo(result.getData());
             } else {
                listener.showError("请求失败");
             }
         }
     });
  }

    public void postMorePersonalInfo(EditMorePersonalInfo info, final OnFinishGetMorePersonalInfoListener listener){
         HttpUtils.postMorePersonalInfo("/editMoreUserInfo", info, new StringCallback() {
             @Override
             public void onError(Call call, Exception e, int id) {
                 listener.showError(e.toString());
             }

             @Override
             public void onResponse(String response, int id) {
                 Gson gson = new Gson();
                 Type type = new TypeToken<Code<Object>>() {
                 }.getType();
                 Code<Object> code=gson.fromJson(response, type);
                 if(code.getCode()==200){
                     listener.saveSucceed();
                 }else{
                     listener.showError("保存失败");
                 }
             }
         });


    }


    private boolean isFirstHobby=true;
    //获取选择的爱好
    public void getHobby(ViewGroup group,OnFinishGetMorePersonalInfoListener listener) {
        String hobbys="";
        for (int i = 0; i < group.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) group.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
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
        listener.returnHobby(hobbys);
    }
}
