package com.ike.communityalliance.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.listener.OnFinishCommentListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import okhttp3.Call;

import static com.ike.communityalliance.R.id.et_shareFriendContent_commentContent;

/**
 * Created by Min on 2016/11/24.
 *  添加好友，创建群组下拉框
 */

public class CommentPopuWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private EditText et_comment_content;
    private TextView tv_send_comment;
    private SharedPreferences sp;
    private String useId;
    private String articleId;
    private boolean isCommentArticle;
    private String commentId;
    private View view;
    public void setUseId(String useId) {
        this.useId = useId;
    }
    private OnFinishCommentListener listener;

    public void setListener(OnFinishCommentListener listener) {
        this.listener = listener;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
    public void setCommentArticle(boolean commentArticle) {
        isCommentArticle = commentArticle;
    }

    public CommentPopuWindow(Context context,View showView,OnFinishCommentListener listener) {
        this.context = context;
        this.view=showView;
        this.listener=listener;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.comment_popuwindow,null);
        //设置SelectPicPopupWindow的view
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);  //点击外面去取消
        this.update(); //刷新
      et_comment_content= (EditText) view.findViewById(et_shareFriendContent_commentContent);
        tv_send_comment= (TextView) view.findViewById(R.id.tv_shareFriendContent_sendComment);
    tv_send_comment.setOnClickListener(this);
    }


    public void showPopupWindow(){
        if(!this.isShowing()){
            //以下拉的方式显示
            this.showAtLocation(view, Gravity.BOTTOM,0,0);
        }else {
            this.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shareFriendContent_sendComment:
                String content=et_comment_content.getText().toString();
                if(content.equals("")){
                    T.showShort(context,"评论内容不为空");
                    return;
                }
                if(isCommentArticle){
                    postComment(articleId,useId,content);
                }else {
                    //回复评论
          postReplayComment(commentId,articleId,useId,content);
                }
                break;
        }
    }
    public void postComment(String id, String useId, String content){
        HttpUtils.postCommentData("/articleComment", id, useId, content,"2", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(context,e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                if(code.getCode()==200){
                    listener.finishComment();
                    et_comment_content.setText("");
                    CommentPopuWindow.this.dismiss();
                    T.showShort(context,"评论成功");
                }else{
                    T.showShort(context,"评论失败");
                }
            }
        });
    }
    public void postReplayComment(String commentId,String articleId,String userId,String content){
        HttpUtils.postReplayCommentData("/replyArticleComment", commentId,articleId, useId, content,"2", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(context,e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                if(code.getCode()==200){
                    listener.finishComment();
                    et_comment_content.setText("");
                    CommentPopuWindow.this.dismiss();
                    T.showShort(context,"评论成功");
                }else{
                    T.showShort(context,"评论失败");
                }
            }
        });
    }
    public void setHint(String userName){
        et_comment_content.setHint("回复"+userName+":");
    }
}
