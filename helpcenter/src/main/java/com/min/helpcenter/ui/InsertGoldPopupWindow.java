package com.min.helpcenter.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.min.helpcenter.R;
import com.min.helpcenter.ui.activitys.MineMessageActivity;

import static com.min.helpcenter.R.id.tv_helpCenter_more_mine;
import static com.min.helpcenter.R.id.tv_helpCenter_more_msg;

/**
 * Created by Min on 2016/11/24.
 * 发布提问输入贡献币
 */

public class InsertGoldPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    public InsertGoldPopupWindow(Context context) {
        this.context = context;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.popupwindow_insert_gold,null);
        //设置SelectPicPopupWindow的view
        this.setContentView(view);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);  //点击外面去取消
        this.update(); //刷新

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case tv_helpCenter_more_msg://消息
                Intent intent=new Intent(context, MineMessageActivity.class);
                context.startActivity(intent);
                break;
            case tv_helpCenter_more_mine://我的求助
                Intent intent1=new Intent(context, MineMessageActivity.class);
                context.startActivity(intent1);
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
