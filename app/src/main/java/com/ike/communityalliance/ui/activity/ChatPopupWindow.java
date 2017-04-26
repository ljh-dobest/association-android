package com.ike.communityalliance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by Min on 2016/11/24.
 *  添加好友，创建群组下拉框
 */

public class ChatPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private LinearLayout ll_pop_talkworld,ll_pop_addressbook,ll_pop_grout,ll_pop_addfriend;
    private RelativeLayout rl_pop_discover,rl_pop_message;
    private ImageView iv_pop_disc_item,iv_pop_message_item;
    public ChatPopupWindow(Context context) {
        this.context = context;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.popupwindow_chat,null);
        //设置SelectPicPopupWindow的view
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);  //点击外面去取消
        this.update(); //刷新
        ll_pop_grout= (LinearLayout) view.findViewById(R.id.ll_pop_group);
        ll_pop_addfriend= (LinearLayout) view.findViewById(R.id.ll_pop_add);
        ll_pop_talkworld= (LinearLayout) view.findViewById(R.id.ll_pop_talkworld);
        ll_pop_addressbook= (LinearLayout) view.findViewById(R.id.ll_pop_addressbook);
        rl_pop_discover= (RelativeLayout) view.findViewById(R.id.rl_pop_discover);
        rl_pop_message= (RelativeLayout) view.findViewById(R.id.rl_pop_message);
        iv_pop_disc_item= (ImageView) view.findViewById(R.id.iv_pop_disc_item);
        iv_pop_message_item= (ImageView) view.findViewById(R.id.iv_pop_message_item);

        ll_pop_grout.setOnClickListener(this);
        ll_pop_addfriend.setOnClickListener(this);
        ll_pop_talkworld.setOnClickListener(this);
        ll_pop_addressbook.setOnClickListener(this);
        rl_pop_discover.setOnClickListener(this);
        rl_pop_message.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_pop_talkworld://聊天大厅
                RongIMClient.getInstance().joinChatRoom("888888", 10, new RongIMClient.OperationCallback() {
                    @Override
                    public void onSuccess() {
                        T.showShort(context,"加入聊天大厅");
                        RongIM.getInstance().startChatRoomChat(context,"888888",true);
                    }
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                       T.showShort(context,"加入聊天大厅失败");
                    }
                });
                ChatPopupWindow.this.dismiss();
                break;
            case R.id.ll_pop_addressbook://通讯录
                context.startActivity( new Intent(context,FriendActivity.class));
                ChatPopupWindow.this.dismiss();
                break;
            case R.id.rl_pop_discover: //发现
                context.startActivity( new Intent(context,ShareFriendsActivity.class));
                iv_pop_disc_item.setVisibility(View.GONE);
                ChatPopupWindow.this.dismiss();
                break;
            case R.id.ll_pop_group:    //创建群组
                Intent intent=new Intent(context,SelectFriendsActivity.class);
                intent.putExtra("createGroup",true);
                context.startActivity(intent);
                ChatPopupWindow.this.dismiss();
                break;
            case R.id.ll_pop_add:   //添加好友
                context.startActivity(new Intent(context,SearchFriendActivity.class));
                ChatPopupWindow.this.dismiss();
                break;
            case R.id.rl_pop_message:   //消息
                context.startActivity(new Intent(context,NewFriendListActivity.class));
                iv_pop_message_item.setVisibility(View.GONE);
                ChatPopupWindow.this.dismiss();
                break;
            default:
                break;
        }
    }
    public void showPopupWindow(View view){
        if(!this.isShowing()){
            //以下拉的方式显示
            this.showAsDropDown(view,-200,-30);
        }else {
            this.dismiss();
        }
    }
    public  void showDicoverRedPoint(){
              iv_pop_disc_item.setVisibility(View.VISIBLE);
    }
    public  void showMessageRedPoint(){
        iv_pop_message_item.setVisibility(View.VISIBLE);
    }
}
