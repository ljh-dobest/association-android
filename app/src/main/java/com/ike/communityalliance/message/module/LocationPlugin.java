package com.ike.communityalliance.message.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.LocationPopupWindow;
import com.ike.communityalliance.utils.DisplayUtils;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by Min on 2016/12/21.
 */

public class LocationPlugin implements IPluginModule {
    private Context context;
    /**
     * 初始化语音识别实例
     *
     * @param context 上下文
     */
    public void init(Context context) {
        this.context=context;
    }

    @Override
    public Drawable obtainDrawable(Context context) {
//        return context.getResources().getDrawable(R.mipmap.ic_launcher);
        return ContextCompat.getDrawable(context,R.mipmap.location);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.location);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {

        int WidthPixels = DisplayUtils.getScreenWidthPixels((Activity) context);
        int height=DisplayUtils.getScreenHeightPixels((Activity) context);
        LocationPopupWindow locationPopupWindow =new LocationPopupWindow((Activity) context,WidthPixels,height);
       locationPopupWindow.setTargetIdAndConversationType(extension.getTargetId(),extension.getConversationType());
        locationPopupWindow.showPopupWindow(R.id.fr_converstaion);

//        if(RongContext.getInstance() != null && RongContext.getInstance().getLocationProvider() != null) {
//            RongContext.getInstance().getLocationProvider().onStartLocation(context, new RongIM.LocationProvider.LocationCallback() {
//                public void onSuccess(LocationMessage locationMessage) {
//                    Message message = Message.obtain(extension.getTargetId(), extension.getConversationType(), locationMessage);
//                    RongIM.getInstance().sendLocationMessage(message, null, null, null);
//                }
//
//                public void onFailure(String msg) {
//                }
//            });
//        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
