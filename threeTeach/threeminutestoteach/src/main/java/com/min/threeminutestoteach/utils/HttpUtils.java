package com.min.threeminutestoteach.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

/**
 *Created by T-BayMax on 2017/3/13.
 */
public class HttpUtils {
//          public static final String IMAGE_URL ="http://192.168.0.104:90";
//      public static final String URL ="http://192.168.0.104:90/appapi/app";
     public static final String URL = "https://sq.bjike.com/appapi/app";
    public static final String IMAGE_URL = "https://sq.bjike.com";
    private static final String TAG = "HttpClient-Request";




    /**
     * Patch请求
     *
     * @param url
     * @param callback
     */
    public static void sendPatchRequest(String url, StringCallback callback) {
        OkHttpUtils
                .patch()
                .url(URL + url)
                .build()
                .execute(callback);
    }


    /**
     * post提交文件
     *
     * @param url
     * @param file
     * @param callback
     */
    public static void sendFilePostRequest(String url, File file, Callback callback) {
        OkHttpUtils
                .postFile()
                .url(URL + url)
                .file(file)
                .build()
                .execute(callback);
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param params
     * @param headers
     * @param file
     * @param fileName
     * @param callback
     */
    public static void sendFormatPostRequest(String url, Map<String, String> params,
                                             Map<String, String> headers, File file, String fileName, Callback callback) {

        OkHttpUtils.post()
                .addFile(fileName, file.getName(), file)
                .url(URL + url)
                .params(params)
                .headers(headers)
                .build()
                .execute(callback);
    }

    /**
     * 上传多个文件
     *
     * @param url
     * @param params
     * @param headers
     * @param files
     * @param fileName
     * @param callback
     */
    public static void sendFormatPostRequest(String url, Map<String, String> params,
                                             Map<String, String> headers, Map<String, File> files, String fileName, Callback callback) {
        OkHttpUtils.post()
                .headers(headers)
                .url(URL + url)
                .params(params)
                .files(fileName, files)
                .build()
                .execute(callback);
    }

  public  static void postTeachContent(String url,Map<String,String> map, File imgFile,File videoFile,StringCallback callback ){
      OkHttpUtils.post()
              .addHeader("Connection", "close")
              .addFile("fileImage",imgFile.getName(), imgFile)
              .addFile("fileVideo",videoFile.getName(), videoFile)
              .url(URL+url)
              .params(map)
              .build()
              .execute(callback);
  }
  //获取三分钟列表
  public  static void getThreeTeachData(String url,String userId, String page,StringCallback callback ){
      OkHttpUtils.post()
              .addHeader("Connection", "close")
              .url(URL+url)
              .addParams("userId",userId)
              .addParams("page",page)
              .build()
              .execute(callback);
  }
    //获取我发布的三分钟列表
    public  static void getMineThreeTeachData(String url,String userId,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .build()
                .execute(callback);
    }
    //获取我发布的三分钟列表
    public  static void getMineCollectTeachData(String url,String userId,String type,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .addParams("type",type)
                .build()
                .execute(callback);
    }
    //点赞
    public  static void clickToLike(String url,String userId,String articleId,String status,String type,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .addParams("articleId",articleId)
                .addParams("status",status)
                .addParams("type",type)
                .build()
                .execute(callback);
    }
    //评论点赞
    public  static void clickToComentLike(String url,String userId,String commentId,String status,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .addParams("commentId",commentId)
                .addParams("status",status)
                .build()
                .execute(callback);
    }
    //文章收藏
    public  static void clickToCollection(String url,String userId,String articleId,String status,String type,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .addParams("articleId",articleId)
                .addParams("status",status)
                .addParams("type",type)
                .build()
                .execute(callback);
    }
    //获取三分钟详情
    public  static void getTeachContent(String url,String userId,String articleId,StringCallback callback ){
        OkHttpUtils.post()
                .addHeader("Connection", "close")
                .url(URL+url)
                .addParams("userId",userId)
                .addParams("activesId",articleId)
                .build()
                .execute(callback);
    }

    //查看评论
    public static void getArticleComment(String url,String articleId,String userId,String page,String type,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("page",page)
                .addParams("type",type)
                .build().execute(callback);
    }
    //评论问题回答
    public static void commentAnswer(String url,String articleId,String userId,String content,String type,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("content",content)
                .addParams("type",type)
                .build().execute(callback);
    }
    //记录分享或者下载次数
    public static void postDownloadOrShareTimes(String url,String articleId,String type,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("type",type)
                .build().execute(callback);
    }
    //获取消息
    public static void getMessageData(String url,String userId,String type,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("type",type)
                .build().execute(callback);
    }
}
