package com.ike.communityalliance.wedget.audio;

import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FlexibleMember;
import com.ike.communityalliance.bean.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min on 2016/12/21.
 */

public class JsonParser {

    //解析注册信息
    public static Code<Object> parserRegisterRespon(String json) {
        Code<Object> code = new Code<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            int concode = jsonObject.getInt("code");
            code.setCode(concode);
        } catch (JSONException e) {
            return code;
        }
        return code;
    }

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                JSONObject obj = items.getJSONObject(0);
                ret.append(obj.getString("w"));
//				如果需要多候选结果，解析数组其他字段
//				for(int j = 0; j < items.length(); j++)
//				{
//					JSONObject obj = items.getJSONObject(j);
//					ret.append(obj.getString("w"));
//				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }


    //解析群组活动人员信息
    public static Code<List<FlexibleMember>> parserFlexibleMember(String json) {
        Code<List<FlexibleMember>> code = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = new Code<>();
            int connCode = jsonObject.getInt("code");
            code.setCode(connCode);
            JSONArray arrays = jsonObject.getJSONArray("data");
            ArrayList<FlexibleMember> flexibleMemberList = new ArrayList<>();
            for (int i = 0; i < arrays.length(); i++) {
                JSONObject FlexibleMemberObject = arrays.getJSONObject(i);
                String tu_id = FlexibleMemberObject.getString("tu_id");
                String nickname = FlexibleMemberObject.getString("nickname");
                int sex = FlexibleMemberObject.getInt("sex");
                String avatar_image = FlexibleMemberObject.getString("avatar_image");
                FlexibleMember flexibleMember = new FlexibleMember(tu_id, nickname, sex, avatar_image);
                flexibleMemberList.add(flexibleMember);
            }
            code.setData(flexibleMemberList);
        } catch (JSONException e) {
            return code;
        }
        return code;
    }

    //解析修改个人信息返回数据
    public static Code<Image> parserPersonData(String json) {
        Code<Image> code = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = new Code<>();
            int connCode = jsonObject.getInt("code");
            code.setCode(connCode);
            JSONObject imgObject = jsonObject.getJSONObject("data");
            String avatar_image = imgObject.getString("avatar_image");
            Image image = new Image(avatar_image);
            code.setData(image);
        } catch (JSONException e) {
            return code;
        }
        return code;
    }


    public static String parseGrammarResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                for (int j = 0; j < items.length(); j++) {
                    JSONObject obj = items.getJSONObject(j);
                    if (obj.getString("w").contains("nomatch")) {
                        ret.append("没有匹配结果.");
                        return ret.toString();
                    }
                    ret.append("【结果】" + obj.getString("w"));
                    ret.append("【置信度】" + obj.getInt("sc"));
                    ret.append("\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ret.append("没有匹配结果.");
        }
        return ret.toString();
    }

    public static String parseLocalGrammarResult(String json) {
        StringBuffer ret = new StringBuffer();
        try {
            JSONTokener tokener = new JSONTokener(json);
            JSONObject joResult = new JSONObject(tokener);

            JSONArray words = joResult.getJSONArray("ws");
            for (int i = 0; i < words.length(); i++) {
                JSONArray items = words.getJSONObject(i).getJSONArray("cw");
                for (int j = 0; j < items.length(); j++) {
                    JSONObject obj = items.getJSONObject(j);
                    if (obj.getString("w").contains("nomatch")) {
                        ret.append("没有匹配结果.");
                        return ret.toString();
                    }
                    ret.append("【结果】" + obj.getString("w"));
                    ret.append("\n");
                }
            }
            ret.append("【置信度】" + joResult.optInt("sc"));

        } catch (Exception e) {
            e.printStackTrace();
            ret.append("没有匹配结果.");
        }
        return ret.toString();
    }
}
