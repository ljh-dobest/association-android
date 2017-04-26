package com.ike.communityalliance.message.module;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.ike.communityalliance.R;

import io.rong.imkit.widget.provider.FilePlugin;

/**
 * Created by Min on 2017/4/10.
 */
//自定义+号里的文件插件
public class MyFilePluginPlugin extends FilePlugin {
    @Override
    public Drawable obtainDrawable(Context context) {
//        return context.getResources().getDrawable(R.mipmap.ic_launcher);
        return ContextCompat.getDrawable(context, R.mipmap.file);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.file);
    }
}
