package com.ike.communityalliance.message.module;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by Min on 2016/12/21.
 */

public class VedioPlugin implements IPluginModule {
    private Context context;

    /**
     * 初始化实例
     *
     * @param context 上下文
     */
    public void init(Context context) {
        this.context=context;
    }

    @Override
    public Drawable obtainDrawable(Context context) {
        return ContextCompat.getDrawable(context,R.mipmap.vedio);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.vedio);
    }

    @Override
    public void onClick(Fragment currentFragment, final RongExtension extension) {
        T.showShort(context,"很抱歉！视频聊天功能暂不支持...");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
