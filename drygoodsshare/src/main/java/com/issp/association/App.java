package com.issp.association;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.issp.association.utils.GreenDaoManager;
import com.liulishuo.filedownloader.FileDownloader;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T-BayMax on 2017/3/21.
 */

public class App extends Application {
    public static int checkVip;
    private static Context mContext;
    public static Map<String, Activity> activityMap = new HashMap<String, Activity>(0);

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
        mContext = getApplicationContext();
        GreenDaoManager.getInstance();
        FileDownloader.init(getApplicationContext());

    }

    public static Context getContext() {
        return mContext;
    }
}
