package com.ike.communityalliance.module;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.VerifyRecommedInfo;
import com.ike.communityalliance.bean.VerifyRecommedInfoBean;
import com.ike.communityalliance.listener.OnVerifyRecommedInfoFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.mylibrary.util.AMUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/6.
 */

public class VerifyRecommedInfoMoudle {
    public  void getVerifyRecommedInfo(String useId, final OnVerifyRecommedInfoFinishListener listener){
            HttpUtils.getRecommedInfo("/selectRecommendInfo", useId, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    listener.failedToVerifyInfo(e.toString());
                }

                @Override
                public void onResponse(String response, int id) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<VerifyRecommedInfo>>() {
                    }.getType();
                    Code<VerifyRecommedInfo> code = gson.fromJson(response, type);
                    if (code.getCode()==200){
                             listener.returnVerifyInfo(code.getData());
                    }else{
                        listener.failedToVerifyInfo("无推荐信息");
                    }
                }
            });
    }


    public void verifyRecommedInfo(VerifyRecommedInfoBean verifyRecommedInfoBean, final OnVerifyRecommedInfoFinishListener listener) {
      if(verifyRecommedInfoBean.getUserId().equals("")||verifyRecommedInfoBean.getFullName().equals("")||verifyRecommedInfoBean.getMobile().equals("")||verifyRecommedInfoBean.getSex().equals("")
              ||verifyRecommedInfoBean.getHobby().equals("")||verifyRecommedInfoBean.getAddress().size()==0){
          listener.showTextEmpty();
          return;
      }

      if(!AMUtils.isMobile(verifyRecommedInfoBean.getMobile())){
                 listener.failedToVerifyInfo("请输入正确的手机号码");
          return;
      }
        if(verifyRecommedInfoBean.getAddress().get(0).equals("请选择")){
            listener.failedToVerifyInfo("请输入具体地址信息");
            return;
        }
        if(verifyRecommedInfoBean.getHomeplace().equals("请选择,请选择,请选择")){
            verifyRecommedInfoBean.setHomeplace("");
        }
          HttpUtils.postVerifyRecommedInfo("/editRecommendInfo",verifyRecommedInfoBean, new StringCallback() {
                      @Override
                      public void onError(Call call, Exception e, int id) {
                          listener.failedToVerifyInfo(e.toString());
                      }

                      @Override
                      public void onResponse(String response, int id) {
                          Gson gson = new Gson();
                          Type type = new TypeToken<Code<Object>>() {
                          }.getType();
                          Code<Object> code = gson.fromJson(response, type);
                          switch (code.getCode()) {
                              case 200:
                                  listener.succeedToVerifyInfo();
                                  break;
                              case 0:
                                  listener.failedToVerifyInfo("信息提交失败");
                                  break;
                          }
                      }
                  });

     }

    public void getParserData(final Context mComtext, final String fileName, final OnVerifyRecommedInfoFinishListener listener){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                       String jsonData= DateUtils.getJson(mComtext,fileName);
                        //解析数据
                        Gson gson=new Gson();
                        Type type = new TypeToken<ArrayList<ProvinceBean>>() {
                        }.getType();
                        ArrayList<ProvinceBean> result=gson.fromJson(jsonData,type);
                       // HashMap<String,Object> map =getMap(result);
                        listener.returnParserData(result);
                    }
                }).start();
     }


    private boolean isFirstHobby=true;
    //获取选择的爱好
    public void getHobby(ViewGroup group, OnVerifyRecommedInfoFinishListener listener) {
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
        listener.returnHobby(hobbys);
    }

}
