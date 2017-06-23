package com.issp.inspiration;


import android.app.Activity;
import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by T-BayMax on 2017/3/21.
 */

public class App extends Application {
    public static int checkVip;

    public static Map<String, Activity> activityMap = new HashMap<String, Activity>(0);
    @Override
    public void onCreate()
    {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize().init(this);
    }
}
