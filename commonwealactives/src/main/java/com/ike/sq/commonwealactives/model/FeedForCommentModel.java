package com.ike.sq.commonwealactives.model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ike.sq.commonwealactives.bean.ArticleCommentBean;
import com.ike.sq.commonwealactives.bean.Code;
import com.ike.sq.commonwealactives.bean.CommentsBean;
import com.ike.sq.commonwealactives.listeners.OnFeedForCommentListListener;
import com.ike.sq.commonwealactives.network.CoreErrorConstants;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * 评论
 * Created by T-BayMax on 2017/3/20.
 */

public class FeedForCommentModel {
    /**
     * 查看评论
     * @param formData
     * @param listener
     */
    public void getFeedCommentInfo(Map<String, String> formData, final OnFeedForCommentListListener listener) {

        HttpUtils.sendGsonPostRequest("/selectArticleComment", formData, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code<ArticleCommentBean>>() {
                    }.getType();
                    Code<ArticleCommentBean> code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            if (null != code.getData()) {
                                ArticleCommentBean bean = code.getData();
                                List<CommentsBean> data = bean.getComments();
                                listener.getFeedCommentInfo(data);
                            }
                            break;
                        case 0:
                            listener.showError("暂无评论");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器发生错误！");
                }
            }
        });
    }

    /**
     * 添加评论
     * @param formData
     * @param listener
     */
    public void addCommentInfo(Map<String, String> formData, final OnFeedForCommentListListener listener) {

        HttpUtils.sendGsonPostRequest("/articleComment", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code>() {
                    }.getType();
                    Code code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            listener.getAddCommentInfo("评论成功");
                            break;
                        case 0:
                            listener.showError("评论失败");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器发生错误！");
                }
            }
        });
    }

    /**
     * 评论点赞
     * @param formData
     * @param listener
     */
    public void commentLikes(Map<String, String> formData, final OnFeedForCommentListListener listener) {

        HttpUtils.sendGsonPostRequest("/commentLikes", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                listener.showError("系统异常！");
            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Code>() {
                    }.getType();
                    Code code = gson.fromJson(response, type);
                    switch (code.getCode()) {
                        case 200:
                            listener.commentLikes("点赞成功");
                            break;
                        case 0:
                            listener.showError("点赞失败");
                            break;
                        default:
                            listener.showError(CoreErrorConstants.errors.get(code.getCode()));
                            break;
                    }
                } catch (Exception e) {
                    listener.showError("系统解析服务器发生错误！");
                }
            }
        });
    }
}
