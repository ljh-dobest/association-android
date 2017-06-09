package com.min.helpcenter.network;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.CookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.io.File;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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
     * 设置Cookie
     *
     * @param context
     */
    public static void setCookie(Context context) {
        CookieStore cookieStore = new PersistentCookieStore(context);
        CookieJarImpl cookieJar = new CookieJarImpl(cookieStore);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 设置Cookie
     *
     * @param context
     * @param cookieStore
     */
    public static void setCookie(Context context, CookieStore cookieStore) {
        // = new PersistentCookieStore(context);
        CookieJarImpl cookieJar = new CookieJarImpl(cookieStore);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    /**
     * 登录
     *
     * @param url
     * @param name
     * @param password
     * @param callback
     */
    public static void sendPostRequest(String url, String name, String password, Callback callback) {
        OkHttpUtils
                .post()
                .url(URL + url)
                .addParams("employeeNumber", name)
                .addParams("password", password)
                .addParams("rememberMe", "on")
                .build()
                .execute(callback);
    }

    /**
     * post 提交Gson数据
     *
     * @param url
     * @param param
     * @param callback
     */
    public static void sendGsonPostRequest(String url, String param, Callback callback) {
        OkHttpUtils
                .postString()
                .url(URL + url)
                .content(param)
                .build()
                .execute(callback);
    }

    public static void sendGsonPostRequest(String url, Map<String, String> formBody, Callback callback) {
        OkHttpUtils
                .post()
                .url(URL + url)
                .params(formBody)
                .build()
                .execute(callback);
    }

    public static void sendGsonPostRequest(String url, Map<String, String> formBody, int id, Callback callback) {
        OkHttpUtils
                .post()
                .url(URL + url)
                .id(id)
                .params(formBody)
                .build()
                .execute(callback);
    }

    /**
     * post 提交Gson数据
     *
     * @param url
     * @param com.t.baymax.navi.entity
     * @param com.t.baymax.navi.callback
     */
 /*   public static void sendGsonPostRequest(String url, Object com.t.baymax.navi.entity, Callback com.t.baymax.navi.callback) {
        OkHttpUtils
                .postString()
                .url(URL + url)
                .content(new Gson().toJson(com.t.baymax.navi.entity))
                .build()
                .execute(com.t.baymax.navi.callback);
    }*/

    /**
     * get 请求
     *
     * @param url
     * @param callback
     */
    public static void sendGetRequest(String url, StringCallback callback) {
        OkHttpUtils
                .get()
                .url(URL + url)
                .build()
                .execute(callback);
    }

    public static void sendGetRequest(String url, int id, StringCallback callback) {
        OkHttpUtils
                .get()
                .id(id)
                .url(URL + url)
                .build()
                .execute(callback);
    }

    /**
     * 执行删除请求
     *
     * @param url
     * @param callback
     */
    public static void sendDeleteRequest(String url, StringCallback callback) {
        OkHttpUtils
                .delete()
                .url(URL + url)
                .build()
                .execute(callback);
    }

    public static void sendPutRequest(String url, RequestBody requestBody, StringCallback callback) {
        OkHttpUtils
                .put()
                .url(URL + url)
                .requestBody(requestBody)
                .build()
                .execute(callback);
    }

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

    /**
     * 文件下载
     *
     * @param url
     * @param fileCallBack
     */
    public static void sendDownloadRequest(String url, FileCallBack fileCallBack) {
        OkHttpUtils
                .get()
                .url(URL + url)
                .build()
                .execute(fileCallBack);
    }


    /**
     * 加载图片
     *
     * @param url
     * @param bitmapCallback
     */
    public static void sendImageRequest(String url, BitmapCallback bitmapCallback) {
        OkHttpUtils
                .get()
                .url(URL + url)
                .build()
                .execute(bitmapCallback);
    }
    //获取问题列表
    public static void getHelpData(String url,String userId,int page,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("page",page+"")
                .build().execute(callback);
    }
  //获取问题详情
    public static void getQuestionDetails(String url,String userId,String seekId,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("seekId",seekId)
                .build().execute(callback);
    }
    //发起求助
    public static void postSeekHelp(String url,String userId,String title,String content,String contributionCoin,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("title",title)
                .addParams("content",content)
                .addParams("contributionCoin",contributionCoin)
                .build().execute(callback);
    }
    //查看我的求助或回答
    public static void getMineHelpOrQuestions(String url,String userId,String status,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("status",status)
                .build().execute(callback);
    }
    //查看评论
    public static void getArticleComment(String url,String articleId,String userId,String type,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("type",type)
                .build().execute(callback);
    }
    //回答求助问题
    public static void answerSeekHelp(String url,String seekId,String userId,String content,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("seekId",seekId)
                .addParams("userId",userId)
                .addParams("content",content)
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
    //采纳问题
    public static void answerSeekAdopt(String url,String userId,String seekId,String answerId,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("seekId",seekId)
                .addParams("answerId",answerId)
                .build().execute(callback);
    }
    //上传图片
    public static void postImage(String url,String userId,File file,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addFile("file","img.jpg",file)
                .build().execute(callback);
    }
    //点赞文章
    public static void postUserPraise(String url,String userId,String articleId,String type,String status,StringCallback callback){
        OkHttpUtils.post().url(URL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("articleId",articleId)
                .addParams("type",type)
                .addParams("status",status)
                .build().execute(callback);
    }
}
