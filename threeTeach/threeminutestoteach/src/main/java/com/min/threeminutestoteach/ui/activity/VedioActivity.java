package com.min.threeminutestoteach.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.MyGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VedioActivity extends AppCompatActivity {
    public final static String TRANSITION = "TRANSITION";
    @BindView(R.id.gsy_video_player)
    MyGSYVideoPlayer gsyVideoPlayer;
    private String filePath;
    private OrientationUtils orientationUtils;
    private boolean isTransition;

//    private Transition transition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);
        ButterKnife.bind(this);
        filePath = getIntent().getStringExtra("filePath");
        isTransition = getIntent().getBooleanExtra(TRANSITION, false);
        initPlayer();
    }

    private void initPlayer() {
        gsyVideoPlayer.setUp(filePath, true, null, "");
         gsyVideoPlayer.getBackButton().setImageResource(R.mipmap.btn_back);
        gsyVideoPlayer.getBackButton().setVisibility(View.VISIBLE);
        gsyVideoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBack();
            }
        });
        //设置旋转
        orientationUtils = new OrientationUtils(this, gsyVideoPlayer);
        gsyVideoPlayer.startPlayLogic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gsyVideoPlayer.onVideoPause();
    }

    @Override
    public void onBackPressed() {
        toBack();
    }

    private void toBack() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            gsyVideoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        gsyVideoPlayer.setStandardVideoAllCallBack(null);
        GSYVideoPlayer.releaseAllVideos();
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onBackPressed();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                }
            }, 500);
        }
    }
}
