package com.ike.communityalliance.module;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.RecommendCode;
import com.ike.communityalliance.listener.OnRecommedFinishListener;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.mylibrary.util.AMUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by Min on 2017/3/6.
 */

public class RecommedMoudle {
    public void recommed(PersonalVipBean recommendBean, final OnRecommedFinishListener listener) {
      if(recommendBean.getUserId().equals("")||recommendBean.getFullName().equals("")||recommendBean.getMobile().equals("")||recommendBean.getSex().equals("")
              ||recommendBean.getHobby().equals("")||recommendBean.getAddress().size()==0||recommendBean.getRelationship().equals("")
            ){
          listener.showTextEmpty();
          return;
      }
      if(!AMUtils.isMobile(recommendBean.getMobile())){
                 listener.showRecommedError("请输入正确的手机号码");
          return;
      }
        if(recommendBean.getAddress().get(0).equals("请选择")){
            listener.showRecommedError("请输入具体地址信息");
            return;
        }
        if(recommendBean.getHomeplace().equals("请选择,请选择,请选择")){
            recommendBean.setHomeplace("");
        }
          HttpUtils.postRecommend("/friendsRecommend",recommendBean, new StringCallback() {
                      @Override
                      public void onError(Call call, Exception e, int id) {
                          listener.showRecommedError(e.toString());
                      }

                      @Override
                      public void onResponse(String response, int id) {
                          Gson gson = new Gson();
                          Type type = new TypeToken<Code<RecommendCode>>() {
                          }.getType();
                          Code<RecommendCode> code = gson.fromJson(response, type);
                          switch (code.getCode()) {
                              case 200:
                                  RecommendCode recommendCode = code.getData();
                                  String recommendId = recommendCode.getRecommendId();
                                  listener.succeedToRecommed(recommendId);
                                  break;
                              case 0:
                                  listener.showRecommedError("推荐失败");
                                  break;
                              case 100:
                                  listener.showRecommedError("用户已注册");
                                  break;
                              case 101:
                                  listener.showRecommedError("数据插入失败");
                                  break;
                              case 300:
                                  listener.showRecommedError("账户已推荐");
                                  break;
                          }
                      }
                  });

     }

    public void getParserData(final Context mComtext, final String fileName, final OnRecommedFinishListener listener){
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
    public void getHobby(ViewGroup group, OnRecommedFinishListener listener) {
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
        listener.returnHobbys(hobbys);
    }
    private boolean isFirstCharacter=true;
    //获取选择的性格
    public void getCharacters(ViewGroup group, OnRecommedFinishListener listener) {
        String characters="";
        for (int i = 0; i < group.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) group.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb= (CheckBox) ll.getChildAt(j);
                if (rb.isChecked()){
                    if (isFirstCharacter){
                        characters=rb.getText().toString();
                        isFirstCharacter=false;
                    }else{
                        characters=characters+","+rb.getText().toString();
                    }
                }
            }
        }
        isFirstCharacter=true;
        listener.returnCharacters(characters);
    }
    private HashMap<String,Object> getMap(ArrayList<ProvinceBean> result) {
        HashMap<String,Object> map=new HashMap<>();
        ArrayList<String> options1Items=new ArrayList<>();
        ArrayList<ArrayList<String>> options2Items=new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> options3Items=new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            options1Items.add(result.get(i).getName());
           ArrayList<String> options2Items_item=new ArrayList<>();
            ArrayList<ArrayList<String>> options3Items_item=new ArrayList<>();
            ArrayList<CityBean> citys= (ArrayList<CityBean>) result.get(i).getSub();
            if(citys==null){
                options2Items_item.add("");
                continue;
            }else {
                for (int j = 0; j < citys.size(); j++) {
                    options2Items_item.add(citys.get(j).getName());
                    ArrayList<String> options3Items_item_item = new ArrayList<>();
                    ArrayList<CountyBean> countys = (ArrayList<CountyBean>) citys.get(j).getSub();
                    if (countys == null) {
                        options3Items_item_item.add("");
                        continue;
                    } else {
                        for (int k = 0; k < countys.size(); k++) {
                            options3Items_item_item.add(countys.get(k).getName());
                        }
                    }
                    options3Items_item.add(options3Items_item_item);
                }
                options2Items.add(options2Items_item);
                options3Items.add(options3Items_item);
            }
        }
        options1Items.remove(0);
           map.put("options1Items",options1Items);
           map.put("options2Items",options2Items);
           map.put("options3Items",options3Items);
        return map;
    }
}
