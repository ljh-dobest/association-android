package com.ike.communityalliance.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BasePopup;


/**
 * Created by Min on 2016/11/24.
 *  认领中心下拉
 */

public class ClaimPopupWindow extends BasePopup implements View.OnClickListener {
    private TextView tv_claim_mineClaim,tv_claim_msg;

    public ClaimPopupWindow(Activity activity, int Width) {
        super(activity, Width);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_claim_msg:    //查看认领消息
                Intent intent1=new Intent(mActivity,MineClaimMsgActivity.class);
                mActivity.startActivity(intent1);
                this.dismiss();
                break;
            case R.id.tv_claim_mineClaim:   //我的认领
                Intent intent=new Intent(mActivity,MineClaimActivity.class);
                mActivity.startActivity(intent);
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
        View view=inflater.inflate(R.layout.popupwindow_claim,null);
        tv_claim_mineClaim= (TextView) view.findViewById(R.id.tv_claim_mineClaim);
        tv_claim_msg= (TextView) view.findViewById(R.id.tv_claim_msg);
        tv_claim_mineClaim.setOnClickListener(this);
        tv_claim_msg.setOnClickListener(this);
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
