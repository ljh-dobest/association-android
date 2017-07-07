package com.min.threeminutestoteach.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import com.zhy.m.permission.MPermissions;

/**
 * Created by Min on 2017/5/13.
 */

public class PermissionsUtil {
    private static int REQUECT_CODE=1000;
    //动态获取权限
    public static void initPermissions(Activity activity,String permisson) {
        if (ContextCompat.checkSelfPermission(activity, permisson) != PackageManager.PERMISSION_GRANTED) {
            REQUECT_CODE+=1;
            MPermissions.requestPermissions(activity, REQUECT_CODE, permisson);
        }
    }
}
