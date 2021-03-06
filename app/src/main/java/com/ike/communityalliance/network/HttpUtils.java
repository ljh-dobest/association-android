package com.ike.communityalliance.network;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;
import com.ike.communityalliance.bean.ClaimInfoBean;
import com.ike.communityalliance.bean.EditMorePersonalInfo;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.CookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.io.File;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by Min on 2016/9/10.
 */
public class HttpUtils {

    public static final String CACHE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath();
/*      public static final String IMAGE_RUL ="https://sq.bjike.com";
    public static final String BASE_RUL ="https://sq.bjike.com/appapi/app";*/
      public static final String IMAGE_RUL ="http://192.168.0.104:90";
      public static final String BASE_RUL ="http://192.168.0.104:90/appapi/app";

    /**
     * post请求
     * @param url 请求路径
     * @param formBody  请求参数
     * @param callback  请求回调
     */
    public static void sendFormBodyPostRequest(String url, Map<String, String>  formBody, Callback callback) {
        OkHttpUtils
                .post()
                .url(BASE_RUL + url)
                .params(formBody)
                .build()
                .execute(callback);
    }

    /**
     * 设置cookie
     * @param context
     */
    public static void setCookie(Context context){
        CookieStore cookieStore=new PersistentCookieStore(context);
        CookieJarImpl cookieJar=new CookieJarImpl(cookieStore);
        OkHttpClient client=new OkHttpClient.Builder().cookieJar(cookieJar).build();
        OkHttpUtils.initClient(client);
    }

    //普通的GET请求，根据泛型Bean返回值也是Bean
    public static void getRequest(String url, StringCallback callback) {
        OkHttpUtils.get().url(BASE_RUL +url).build().execute(callback);
    }

    //好友列表
    public static void postRequest(String url, String userid, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close").addParams("userId",userid).build().execute(callback);
    }

    public static void postListRequest(String url, String text, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addParams("text",text)
//                .addParams("list",list)
                .build().execute(callback);
    }

    //登录
    public static void postLoginRequest(String url,String username,String password,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("mobile",username)
                .addParams("password",password)
                .build().execute(callback);
    }

    //好友请求列表
    public static void postAddFriendsRequest(String url, String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close").addParams("userId",userId).build().execute(callback);
    }
    //确认好友添加请求
    public static void postEnterFriendRequest(String url,String userid,String friendid, int status, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("userId",userid)
                .addParams("friendUserId",friendid)
                .addParams("status", String.valueOf(status)).build().execute(callback);
    }

    //普通Post上传----用户登录
    public static void sendPostRequest(String url,String username,String password,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("username",username)
                .addParams("password",password)
                .build().execute(callback);
    }


    //直接上传String类型的文本
    public static void sendPostStringRequest(String url,Object object,StringCallback callback){
        OkHttpUtils.postString().url(BASE_RUL +url).content(object.toString()).build().execute(callback);
    }
    //提交通讯录
    public static void PostMobile(String url, String string,String mobile, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL + url)
                .addHeader("Connection", "close")
                .addParams("userId",string)
                .addParams("mobile",mobile)
                .build().execute(callback);
    }

    //搜索好友或群
    public static void PostSearchFriendRequest(String url,String userId, String string, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL + url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("number",string)
                .build().execute(callback);
    }
//请求加好友
    public static void sendPostRequest(String url,String status,String userid, String nickname, String f_userid,String message,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("status",status)
                .addParams("userId",userid)
                .addParams("nickname",nickname)
                .addParams("friendUserid",f_userid)
                .addParams("addFriendMessage",message)
                .build()
                .execute(callback);
    }
    //请求加群
    public static void sendPostRequest(String url,String status,String userid, String groupId,String message,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("status",status)
                .addParams("userId",userid)
                .addParams("groupId",groupId)
                .addParams("addFriendMessage",message)
                .build()
                .execute(callback);
    }

    //判断该手机是否已近注册
    public static void postPhoneRequest(String url,String phone,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addParams("phone",phone)
                .build().execute(callback);
    }

    //注册
    public static void postRegisterRequest(String url, String nickname, String phone,String password,
                                           StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("nickname",nickname)
                .addParams("mobile",phone)
                .addParams("password",password)
                .build().execute(callback);
    }
    //注册
    public static void postRegisterRequest1(String url, String nickname, String mobile, String password,String recommendId,
                                           StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("nickname",nickname)
                .addParams("mobile",mobile)
                .addParams("password",password)
                .addParams("recommendId",recommendId)
                .build().execute(callback);
    }

    //好友信息
    public static void postFriendsRequest(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }

    //创建群组
    public static void sendPostListRequest(String url,String userid, String text, String aaa,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("groupName",text)
                .addParams("userId",userid)
                .addParams("groupUser", aaa)
                .build()
                .execute(callback);
    }

    //创建兴趣联盟
    public static void sendPostListRequest(String url,String userid, String text,String aaa, String hobbyId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("groupName",text)
                .addParams("userId",userid)
                .addParams("groupUser", aaa)
                .addParams("hobbyId",hobbyId)
                .build()
                .execute(callback);
    }

    //群组列表
    public static void postGroupListRequest(String url, String userId, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close").addParams("userId",userId).build().execute(callback);
    }
    //群组信息---群组成员
    public static void postGroupsRequest(String url,String groupId, String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId).addParams("userId",userId).build().execute(callback);
    }
    //修改群头像
    public static void postChangeGroupHead(String url,String groupId,File file,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addFile("file","",file)
                .build().execute(callback);
    }
    //修改群信息
    public static void postChangeGroupName(String url,String groupId,String userId,String groupName, File file,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("userId",userId)
                .addParams("groupName",groupName)
                .addFile("file","crop_file.jpg",file)
                .build().execute(callback);
    }
    //修改群图片
    public static void postChangeGroupHead(String url,String groupId,String groupName, File file,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("groupName",groupName)
                .addFile("file","crop_file.jpg",file)
                .build().execute(callback);
    }
    //退出群
    public static void postQuitGroup(String url,String groupId,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("groupUser",userId)
                .build().execute(callback);
    }
    //解散群
    public static void postDismissGroup(String url,String groupId,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("groupUser",userId)
                .build().execute(callback);
    }
    //群活动
    public static void postGroupActiivity(String url, String userid,String groupId ,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userid)
                .addParams("groupId",groupId)
                .build().execute(callback);
    }
    //删除好友
    public static void postDelFriendRequest(String url, String userId, String friendId, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL +url)
                .addHeader("Connection", "close")
                .addParams("userId",userId).addParams("friendUserid",friendId).build().execute(callback);
    }

    //删除群成员
    public static void postDelGroupMember(String url,String userIds,String userId, String groupId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("Users",userIds)
                .addParams("groupUserId",userId)   //群主
                .addParams("groupId ",groupId).build().execute(callback);
    }
    //添加群成员
    public static void postAddGroupMember(String url,String groupId,String userIds,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("groupUser",userIds)
                .addParams("userId",userId)
                .build().execute(callback);
    }

    //修改群个人昵称
    public static void postChangeNameGroup(String url,String groupId,String userId,String groupName,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("userId",userId)
                .addParams("groupName",groupName)
                .build().execute(callback);
    }

    //修改好友备注名
    public static void postChangeFriendName(String url,String userId,String friendUserId, String nickname,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("friendUserid",friendUserId)
                .addParams("displayname",nickname)
                .build().execute(callback);
    }
    //查询对方信息
    public static void checkUserInfo(String url, String userId, String otherUserId,String status,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("otherUserId",otherUserId)
                .addParams("status",status)
                .build().execute(callback);
    }

    //修改个人资料
    public static void postChangePerson1(String url, UserInfo userInfo, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userInfo.getUserId())
                .addParams("nickname",userInfo.getNickname())
                .addParams("sex",userInfo.getSex())
                .addParams("email",userInfo.getEmail())
                .addParams("address",userInfo.getAddress())
                .addParams("favour",userInfo.getFavour())
                .build().execute(callback);
    }
    //修改个人资料
    public static void postChangePerson(String url,UserInfo userInfo,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addParams("userId",userInfo.getUserId())
                .addParams("nickname",userInfo.getNickname())
                .addParams("sex",userInfo.getSex())
                .addParams("email",userInfo.getEmail())
                .addParams("address",userInfo.getAddress())
                .addParams("favour",userInfo.getFavour())
                 .addFile("file","crop_file.jpg",new File(userInfo.getUserPortraitUrl()))
                .build().execute(callback);
    }
    //修改个人资料
    public static void postChangePerson(String url,String string, File file,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("person",string)
                .addFile("file","crop_file.jpg",file)
                .build().execute(callback);

    }


    //添加群活动
    public static void postAddGroupFlexible(String url,String userId, String group_id, String actives_title,
                                            String actives_content, int actives_limit, String actives_start,
                                            String actives_end, String actives_address,String activesClosing,
                                            File file,Map<String,File> files, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("groupId",group_id)
                .addParams("activesTitle",actives_title)
                .addParams("activesContent",actives_content)
                .addParams("activesLimit",String.valueOf(actives_limit))
                .addParams("activesStart",actives_start)
                .addParams("activesEnd",actives_end)
                .addParams("activesAddress",actives_address)
                .addParams("activesClosing",activesClosing)
                .addFile("file","crop_file.jpg",file)
                .files("files[]",files)
                .build().execute(callback);
    }
    //获取群活动详细信息
    public static void getGroupFlexibleDetail(String url,String activesId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("activesId",activesId)
                .build().execute(callback);
    }
    //
    public static void postChangePerson(String url,String string,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("person",string)
                .build().execute(callback);
    }

    //参加群活动
    public static void postAddFlexible(String url, String userId,String actives_id,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("activesId",actives_id)
                .build().execute(callback);
    }
    //添加群成员
    public static void postAddGroupMember(String url,String actives_id,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("activesId",actives_id).build().execute(callback);
    }

    //判断是否是添加好友
    public static void postAddFriender(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId).build().execute(callback);
    }

    //投票列表
    public static void postVoteList(String url, String userId,String groupId ,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",groupId)
                .addParams("userId",userId)
                .build().execute(callback);
    }

    //创建投票
    public static void postAddVote(String url,String userId,String group_id,String vote_title,
                                   String vote_option, int mode, String end_time,File file,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("groupId",group_id)
                .addParams("voteTitle",vote_title)
                .addParams("voteOption",vote_option)
                .addParams("mode", String.valueOf(mode))
                .addParams("endTime",end_time)
                .addFile("file","crop_file.jpg",file)
                .build().execute(callback);
    }


    //投票详情
    public static void postVoteDetails(String url,String group_id, String vote_id,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("groupId",group_id)
                .addParams("voteId",vote_id)
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //投票详情
    public static void postVote(String url,String userId, String group_id, String vote_id, String vote_option, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("groupId",group_id)
                .addParams("voteId",vote_id)
                .addParams("voteOption",vote_option)
                .build().execute(callback);
    }

    //好友认领列表
    public static void postClaimFriendsList(String url, String userId, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close").addParams("userId",userId).build().execute(callback);
    }

    //认领好友
    public static void postClaimFriends(String url, String userId, String friends_userId, String answer,
                                        StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("friends_userId",friends_userId)
                .addParams("answer",answer)
                .build().execute(callback);
    }

    //设置副群主
    public static void postVicePrincipal(String url, String userId, String groupUserId, String groupId,
                                        StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("groupUserId",groupUserId)
                .addParams("groupId",groupId)
                .build().execute(callback);
    }
    //群主踢人
    public static void postKickGroupUser(String url, String users, String groupUserId, String groupId,
                                         StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("users",users)
                .addParams("groupUserId",groupUserId)
                .addParams("groupId",groupId)
                .build().execute(callback);
    }
    //获取兴趣联盟
    public static void getInterestGroupsData(String url, String userId, String hobbyId,
                                         StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("hobbyId",hobbyId)
                .build().execute(callback);
    }
    //发布朋友圈
    public static void publishShareFriends(String url, String userId, String content,String whoSee,Map<String,File> files,
                                           StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("content",content)
                .addParams("status",whoSee)
                .files("files[]",files)
                .build().execute(callback);
    }
    //查看朋友圈未读消息
    public static void getUnreadMessageData(String url, String userId, String type,
                                            StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("type",type)
                .build().execute(callback);
    }
    //查看朋友圈未读消息
    public static void clearUnreadMsg(String url, String userId,
                                            StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //查看朋友圈说说
    public static void getTalkTalkData(String url, String userId, String status,int page,
                                           StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("status",status)
                .addParams("page",page+"")
                .build().execute(callback);
    }
    //朋友圈点赞
    public static void postTalkTalkLike(String url,String articleId, String userId,String type, String status,
                                       StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("type",type)
                .addParams("status",status)
                .build().execute(callback);
    }
    //获取评论
    public static void getCommentData(String url,String articleId, String userId,String type,
                                        StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("type",type)
                .build().execute(callback);
    }
    //发布评论
    public static void postCommentData(String url,String articleId, String userId,String content,String type,
                                      StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("content",content)
                .addParams("type",type)
                .build().execute(callback);
    }
    //发布回复评论
    public static void postReplayCommentData(String url,String commentId,String articleId, String userId,String content,String type,
                                      StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("commentId",commentId)
                .addParams("articleId",articleId)
                .addParams("userId",userId)
                .addParams("content",content)
                .addParams("type",type)
                .build().execute(callback);
    }
    //提交推荐信息
    public static void postRecommend(String url, PersonalVipBean recommendBean, StringCallback callback){
        Gson gson = new Gson();
        String addressList = gson.toJson(recommendBean.getAddress());
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",recommendBean.getUserId())
                .addParams("fullName",recommendBean.getFullName())
                .addParams("mobile",recommendBean.getMobile())
                .addParams("sex",recommendBean.getSex())
                .addParams("hobby",recommendBean.getHobby())
                .addParams("address",addressList)
                .addParams("relationship",recommendBean.getRelationship())
                .addParams("character",recommendBean.getCharacter())
                .addParams("birthday",recommendBean.getBirthday())
                .addParams("homeplace",recommendBean.getHomeplace())
                .addParams("finishSchool",recommendBean.getFinishSchool())
                .addParams("company",recommendBean.getCompany())
                .addParams("fatherName",recommendBean.getFatherName())
                .addParams("motherName",recommendBean.getMotherName())
                .addParams("marriage",recommendBean.getMarriage())
                .addParams("spouseName",recommendBean.getSpouseName())
                .addParams("childrenName",recommendBean.getChildrenName())
                .addParams("childrenSchool",recommendBean.getChildrenSchool())
                .addParams("degree",recommendBean.getDegree())
                .addParams("position",recommendBean.getPosition())
                .addParams("industry",recommendBean.getIndustry())
                .addParams("email",recommendBean.getEmail())
                .addParams("wechat",recommendBean.getEmail())
                .addParams("QQ",recommendBean.getEmail())
                .build().execute(callback);
    }
    //提交申请VIP信息
    public static void postPersonalVip(String url, PersonalVipBean personalVipBean, StringCallback callback){
        Gson gson = new Gson();
        String addressList = gson.toJson(personalVipBean.getAddress());
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",personalVipBean.getUserId())
                .addParams("fullName",personalVipBean.getFullName())
                .addParams("mobile",personalVipBean.getMobile())
                .addParams("sex",personalVipBean.getSex())
                .addParams("hobby",personalVipBean.getHobby())
                .addParams("address",addressList)
                .addParams("character",personalVipBean.getCharacter())
                .addParams("birthday",personalVipBean.getBirthday())
                .addParams("homeplace",personalVipBean.getHomeplace())
                .addParams("finishSchool",personalVipBean.getFinishSchool())
                .addParams("company",personalVipBean.getCompany())
                .addParams("fatherName",personalVipBean.getFatherName())
                .addParams("motherName",personalVipBean.getMotherName())
                .addParams("marriage",personalVipBean.getMarriage())
                .addParams("spouseName",personalVipBean.getSpouseName())
                .addParams("childrenName",personalVipBean.getChildrenName())
                .addParams("childrenSchool",personalVipBean.getChildrenSchool())
                .addParams("degree",personalVipBean.getDegree())
                .addParams("position",personalVipBean.getPosition())
                .addParams("industry",personalVipBean.getIndustry())
                .addParams("email",personalVipBean.getEmail())
                .addParams("wechat",personalVipBean.getEmail())
                .addParams("QQ",personalVipBean.getEmail ())
                .build().execute(callback);
    }


    //获取已推荐的信息
    public static void getRecommedInfo(String url,String userId,String recommendId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("recommendId",recommendId)
                .build().execute(callback);
    }
    //获取已推荐的信息
    public static void getWasRecommedInfo(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //获取未认领的人
    public static void getClaimPeopleInfo(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("status","0")
                .build().execute(callback);
    }
    //获取已认领的人
    public static void getWasClaimPeopleInfo(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("status","1")
                .build().execute(callback);
    }
    //获取认领消息
    public static void getMineClaimMsgInfo(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("type","1")
                .build().execute(callback);
    }
    //提交认领确认消息
    public static void postClaimMsg(String url,String userId,String claimUserId,String status,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("claimUserId",claimUserId)
                .addParams("status",status)
                .build().execute(callback);
    }

    //提交认领信息
    public static void postClaimInfo(String url, ClaimInfoBean claimInfo, StringCallback callback){
        Gson gson=new Gson();
        String address = gson.toJson(claimInfo.getAddress());
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",claimInfo.getUserId())
                .addParams("claimUserId",claimInfo.getClaimUserId())
                .addParams("fullName",claimInfo.getFullName())
                .addParams("mobile",claimInfo.getMobile())
                .addParams("sex",claimInfo.getSex())
                .addParams("hobby",claimInfo.getHobby())
                .addParams("address",address)
                .addParams("relationship",claimInfo.getRelationship())
                .addParams("creditScore",claimInfo.getCreditScore())
                .addParams("birthday",claimInfo.getBirthday())
                .addParams("homeplace",claimInfo.getHomeplace())
                .addParams("finishSchool",claimInfo.getFinishSchool())
                .addParams("degree",claimInfo.getDegree())
                .addParams("company",claimInfo.getCompany())
                .addParams("position",claimInfo.getPosition())
                .addParams("email",claimInfo.getEmail())
                .addParams("QQ",claimInfo.getQQ())
                .addParams("wechat",claimInfo.getWechat())
                .addParams("character",claimInfo.getCharacter())
                .addParams("industry",claimInfo.getIndustry())
                .addParams("fatherName",claimInfo.getFatherName())
                .addParams("motherName",claimInfo.getMotherName())
                .addParams("marriage",claimInfo.getMarriage())
                .addParams("spouseName",claimInfo.getSpouseName())
                .addParams("childrenName",claimInfo.getChildrenName())
                .addParams("childrenSchool",claimInfo.getChildrenSchool())
                .build().execute(callback);
    }
    //提交确认个人信息
    public static void postVerifyRecommedInfo(String url, PersonalVipBean verifyRecommedInfo, StringCallback callback){
        Gson gson=new Gson();
        String address = gson.toJson(verifyRecommedInfo.getAddress());
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("recommendId",verifyRecommedInfo.getRecommendId())
                .addParams("userId",verifyRecommedInfo.getUserId())
                .addParams("fullName",verifyRecommedInfo.getFullName())
                .addParams("mobile",verifyRecommedInfo.getMobile())
                .addParams("sex",verifyRecommedInfo.getSex())
                .addParams("hobby",verifyRecommedInfo.getHobby())
                .addParams("address",address)
                .addParams("birthday",verifyRecommedInfo.getBirthday())
                .addParams("homeplace",verifyRecommedInfo.getHomeplace())
                .addParams("character",verifyRecommedInfo.getCharacter())
                .addParams("finishSchool",verifyRecommedInfo.getFinishSchool())
                .addParams("degree",verifyRecommedInfo.getDegree())
                .addParams("industry",verifyRecommedInfo.getIndustry())
                .addParams("company",verifyRecommedInfo.getCompany())
                .addParams("position",verifyRecommedInfo.getPosition())
                .addParams("email",verifyRecommedInfo.getEmail())
                .addParams("QQ",verifyRecommedInfo.getQQ())
                .addParams("wechat",verifyRecommedInfo.getWechat())
                .addParams("fatherName",verifyRecommedInfo.getFatherName())
                .addParams("motherName",verifyRecommedInfo.getMotherName())
                .addParams("marriage",verifyRecommedInfo.getMarriage())
                .addParams("spouseName",verifyRecommedInfo.getSpouseName())
                .addParams("childrenName",verifyRecommedInfo.getChildrenName())
                .addParams("childrenSchool",verifyRecommedInfo.getChildrenSchool())
                .build().execute(callback);
    }
    //已签到日期
    public static void getSignPicker(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //签到
    public static void sign(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //修改个人信息
    public static void postPersonalInfo(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //获取首页数据
    public static void getHomePageData(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //获取个人更多资料
    public static void getMorePersonalInfo(String url,String userId,String otherUserId,String status,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("otherUserId",otherUserId)
                .addParams("status",status)
                .build().execute(callback);
    }
    //提交个人更多资料
    public static void postMorePersonalInfo(String url, EditMorePersonalInfo info, StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",info.getUserId())
                .addParams("fullName",info.getFullName())
                .addParams("SfullName",info.getSfullName())
                .addParams("mobile",info.getMobile())
                .addParams("Smobile",info.getSmobile())
                .addParams("birthday",info.getBirthday())
                .addParams("Sbirthday",info.getSbirthday())
                .addParams("QQ",info.getQQ())
                .addParams("wechat", info.getWechat())
                .addParams("fatherName", info.getFatherName())
                .addParams("SfatherName", info.getSfatherName())
                .addParams("motherName", info.getMotherName())
                .addParams("SmotherName", info.getSmotherName())
                .addParams("finishSchool",info.getFinishSchool())
                .addParams("SfinishSchool",info.getSfinishSchool())
                .addParams("constellation",info.getConstellation())
                .addParams("bloodType",info.getBloodType())
                .addParams("marriage",info.getMarriage())
                .addParams("Smarriage",info.getSmarriage())
                .addParams("spouseName",info.getSpouseName())
                .addParams("SspouseName",info.getSspouseName())
                .addParams("childrenName",info.getChildrenName())
                .addParams("SchildrenName",info.getSchildrenName())
                .addParams("childrenSchool",info.getChildrenSchool())
                .addParams("SchildrenSchool",info.getChildrenSchool())
                .addParams("company",info.getCompany())
                .addParams("position",info.getPosition())
                .addParams("Sposition",info.getSposition())
                .addParams("industry",info.getIndustry())
                .addParams("homeplace",info.getHomeplace())
                .build().execute(callback);
    }
    //提交平台反馈
    public static void postFeedBack(String url,String userId,String content,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .addParams("content",content)
                .build().execute(callback);
    }
    //获取可能认识的人
    public static void getPossibleUnderstandPeople(String url,String userId,StringCallback callback){
        OkHttpUtils.post().url(BASE_RUL+url)
                .addHeader("Connection", "close")
                .addParams("userId",userId)
                .build().execute(callback);
    }
    //获取天气信息
    public static void getWeatherData(String url,String appkey,String city,StringCallback callback) {
        OkHttpUtils.get().url(url)
                .addParams("call_key",appkey)
                .addParams("cityname",city)
                .build().execute(callback);
    }
}
