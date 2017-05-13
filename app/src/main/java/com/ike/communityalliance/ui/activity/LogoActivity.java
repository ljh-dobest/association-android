package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.ike.communityalliance.R;
import com.ike.communityalliance.UserInfoManager;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.ui.Main2Activity;
import com.ike.mylibrary.util.CommonUtils;
import com.ike.mylibrary.util.T;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 加载页
 */
public class LogoActivity extends BaseActivity {
    private SharedPreferences sharedPreferences;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        sharedPreferences=getSharedPreferences("config",MODE_PRIVATE);
        if(!CommonUtils.isNetConnect(mContext)){
            T.showShort(mContext,"网络不可用");
            toLogin();
            return;
        }
        final String cacheToken=sharedPreferences.getString("loginToken","");
        if(!TextUtils.isEmpty(cacheToken)){
            if(RongIM.getInstance().getCurrentConnectionStatus()== RongIMClient.ConnectionStatusListener.ConnectionStatus.CONNECTED){
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RongIM.getInstance().disconnect();
                        RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                            @Override
                            public void onTokenIncorrect() {

                            }

                            @Override
                            public void onSuccess(String s) {
                                toMain();
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                            }
                        });

                    }
                },0);
            }else {
                RongIM.connect(cacheToken, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toLogin();
                            }
                        },0);
                    }

                    @Override
                    public void onSuccess(String s) {
                        getSharedPreferences("config",MODE_PRIVATE).edit().putString("loginid",s).apply();
                        UserInfoManager.getInstance().setUserInfoEngineListener();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toMain();
                            }
                        },0);
                    }

                    @Override
                    public void onError(final RongIMClient.ErrorCode errorCode) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                T.showShort(mContext,"网络异常"+errorCode);
                            }
                        },0);
                    }
                });
            }
        }else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toLogin();
                }
            },0);
        }
    }

    private void toLogin() {
        startActivity(new Intent(mContext,Login2Activity.class));
        finish();
    }
    private void toMain(){
        startActivity(new Intent(mContext,Main2Activity.class));
        finish();
    }
}
