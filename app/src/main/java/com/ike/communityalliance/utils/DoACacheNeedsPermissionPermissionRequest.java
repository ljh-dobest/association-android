package com.ike.communityalliance.utils;

import android.support.v4.app.ActivityCompat;

import com.ike.communityalliance.ui.Main2Activity;

import java.lang.ref.WeakReference;

import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

/**
 * Created by Min on 2017/6/1.
 */

public class DoACacheNeedsPermissionPermissionRequest implements PermissionRequest {
    private final WeakReference<Main2Activity> weakTarget;
    //定义的权限编码,当PERMISSION_DOACACHENEEDSPERMISSION有N个权限，那么REQUEST_DOACACHENEEDSPERMISSION就会有多少值
    private static final int REQUEST_DOACACHENEEDSPERMISSION =1001;

    //需要请求的权限名称
    private static final String[] PERMISSION_DOACACHENEEDSPERMISSION = new String[] {"android.permission.READ_CONTACTS"};
    public DoACacheNeedsPermissionPermissionRequest(Main2Activity target) {
        this.weakTarget = new WeakReference<>(target);
    }

    @Override
    public void proceed() {
        Main2Activity target = weakTarget.get();
        if (target == null) return;
        ActivityCompat.requestPermissions(target, PERMISSION_DOACACHENEEDSPERMISSION, REQUEST_DOACACHENEEDSPERMISSION);
    }

    @Override
    public void cancel() {
        Main2Activity target = weakTarget.get();
        if (target == null) return;
        target.ACacheOnPermissionDenied();
    }
    /**
     * 权限请求回调
     * @param target
     * @param requestCode 权限编码
     * @param grantResults
     */
  public static void onRequestPermissionsResult(Main2Activity target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_DOACACHENEEDSPERMISSION:
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_DOACACHENEEDSPERMISSION)) {
                    target.ACacheOnPermissionDenied();
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.initContactPermission();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_DOACACHENEEDSPERMISSION)) {

                    } else {
                        target.ACacheOnPermissionDenied();
                    }
                }
                break;
            default:
                break;
        }
    }
    }
