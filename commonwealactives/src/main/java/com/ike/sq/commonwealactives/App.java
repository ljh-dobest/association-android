package com.ike.sq.commonwealactives;


import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by T-BayMax on 2017/3/21.
 */

public class App extends Application {
    public static int checkVip;
    @Override
    public void onCreate()
    {
        super.onCreate();

        AutoLayoutConifg.getInstance().useDeviceSize().init(this);

    }
}
