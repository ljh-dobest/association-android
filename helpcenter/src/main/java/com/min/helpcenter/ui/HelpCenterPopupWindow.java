package com.min.helpcenter.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.min.helpcenter.R;
import com.min.helpcenter.ui.activitys.MineMessageActivity;

/**
 * Created by Min on 2016/11/24.
 *  求助中心下拉
 */

public class HelpCenterPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
  private TextView tv_helpCenter_more_msg,tv_helpCenter_more_mine;
    private String userId;
    public HelpCenterPopupWindow(Context context,String userId) {
        this.context = context;
        this.userId=userId;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.popupwindow_help_center,null);
        //设置SelectPicPopupWindow的view
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);  //点击外面去取消
        this.update(); //刷新
        tv_helpCenter_more_msg= (TextView) view.findViewById(R.id.tv_helpCenter_more_msg);
        tv_helpCenter_more_mine= (TextView) view.findViewById(R.id.tv_helpCenter_more_mine);
        tv_helpCenter_more_msg.setOnClickListener(this);
        tv_helpCenter_more_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_helpCenter_more_msg://消息
                Intent intent=new Intent(context, MineMessageActivity.class);
                 intent.putExtra("userId",userId);
                context.startActivity(intent);
                this.dismiss();
                break;
            case R.id.tv_helpCenter_more_mine://我的求助
                Intent intent1=new Intent(context, MineMessageActivity.class);
                intent1.putExtra("userId",userId);
                context.startActivity(intent1);
                this.dismiss();
                break;
            default:
                break;
        }
    }
    public void showPopupWindow(View view){
        if(!this.isShowing()){
            //以下拉的方式显示
            this.showAsDropDown(view,0,0);
        }else {
            this.dismiss();
        }
    }
}
