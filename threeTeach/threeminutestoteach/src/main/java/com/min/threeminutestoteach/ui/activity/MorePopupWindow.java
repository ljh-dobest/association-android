package com.min.threeminutestoteach.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BasePopup;


/**
 * Created by Min on 2016/11/24.
 *  认领中心下拉
 */

public class MorePopupWindow extends BasePopup implements View.OnClickListener {
    private TextView tv_more_msg,tv_more_mineTeache,tv_more_mineDownLoad;
    private String userId;
    public MorePopupWindow(Activity activity,String userId,int Width) {
        super(activity, Width);
        this.userId=userId;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_more_msg:    //消息
                Intent intent2=new Intent(mActivity,MessageActivity.class);
                intent2.putExtra("userId",userId);
                mActivity.startActivity(intent2);
                this.dismiss();
                break;
            case R.id.tv_more_mineTeache:   //我的教学
                Intent intent=new Intent(mActivity,MineMessageActivity.class);
                intent.putExtra("userId",userId);
                mActivity.startActivity(intent);
                this.dismiss();
                break;
            case R.id.tv_more_mineDownload:   //我的下载
                Intent intent1=new Intent(mActivity,MineDownLoadActivity.class);
                mActivity.startActivity(intent1);
               this.dismiss();
                break;
        }
    }
    @Override
    public void setTitleText() {
    }
    @Override
    public View getView() {
        LayoutInflater inflater= (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.popupwindow_more,null);
        tv_more_msg= (TextView) view.findViewById(R.id.tv_more_msg);
        tv_more_mineTeache= (TextView) view.findViewById(R.id.tv_more_mineTeache);
        tv_more_mineDownLoad= (TextView) view.findViewById(R.id.tv_more_mineDownload);
        tv_more_msg.setOnClickListener(this);
        tv_more_mineTeache.setOnClickListener(this);
        tv_more_mineDownLoad.setOnClickListener(this);
        return view;
    }

    @Override
    public Animation setAnima() {
        return null;
    }

    @Override
    public View getCancelButton() {
        return null;
    }

    @Override
    public View getCompleteButton() {
        return null;
    }
}
