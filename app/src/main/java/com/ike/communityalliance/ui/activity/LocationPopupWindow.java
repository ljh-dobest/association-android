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

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.LocationMessage;

/**
 * Created by Min on 2017/5/18.
 */

public class LocationPopupWindow extends BasePopup {
    private TextView tv_location_pop_sendAddress,tv_location_pop_sendMyAddress,tv_location_pop_cancel;
    private String targetId;
    private Conversation.ConversationType conversationType;
    public LocationPopupWindow(Activity activity, int Width, int Height) {
        super(activity, Width, Height);
    }


    public void setTargetIdAndConversationType(String targetId,Conversation.ConversationType conversationType){
        this.targetId=targetId;
        this.conversationType=conversationType;
    }
    @Override
    public void setTitleText() {
    }

    @Override
    public View getView() {
            LayoutInflater inflater= (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.location_pop,null);
            tv_location_pop_sendAddress= (TextView) view.findViewById(R.id.tv_location_pop_sendAddress);
            tv_location_pop_sendMyAddress= (TextView) view.findViewById(R.id.tv_location_pop_sendMyAddress);
        tv_location_pop_cancel= (TextView) view.findViewById(R.id.tv_location_pop_cancel);
        tv_location_pop_sendAddress.setOnClickListener(this);
        tv_location_pop_sendMyAddress.setOnClickListener(this);
            return view;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_location_pop_sendAddress://发送指定位置
                this.dismiss();
                toLocationActivity(false);
                break;
            case R.id.tv_location_pop_sendMyAddress://发送实时位置
                if (targetId == null || conversationType == null) {
                    return;
                }
                this.dismiss();
                toLocationActivity(true);
                break;
        }
    }

    private void toLocationActivity(boolean isMyAddress) {
        if(RongContext.getInstance() != null && RongContext.getInstance().getLocationProvider() != null) {
            Intent intent = new Intent(mActivity, AMAPLocationActivity.class);
            intent.putExtra("myAddress",isMyAddress);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent);
            RongContext.getInstance().getLocationProvider().onStartLocation(mActivity, new RongIM.LocationProvider.LocationCallback() {
                public void onSuccess(LocationMessage locationMessage) {
                    Message message = Message.obtain(targetId,conversationType, locationMessage);
                    RongIM.getInstance().sendLocationMessage(message, null, null, null);
                }
                public void onFailure(String msg) {
                }
            });
        }
    }

    @Override
    public Animation setAnima() {
        return null;
    }

    @Override
    public View getCancelButton() {
        return tv_location_pop_cancel;
    }

    @Override
    public View getCompleteButton() {
        return null;
    }
}
