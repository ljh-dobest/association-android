package com.min.threeminutestoteach.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.min.threeminutestoteach.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

/**
 * Created by Min on 2017/5/24.
 */

public class MyGSYVideoPlayer extends StandardGSYVideoPlayer {
    public MyGSYVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MyGSYVideoPlayer(Context context) {
        super(context);
    }

    public MyGSYVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.simple_control;
    }
    @Override
    protected void updateStartImage() {
        View enPlayView =mStartButton;
        if (mCurrentState == CURRENT_STATE_PLAYING) {
            enPlayView.setBackgroundResource(R.mipmap.vedio_stop);
        } else if (mCurrentState == CURRENT_STATE_ERROR) {
            enPlayView.setBackgroundResource(R.mipmap.vedio_start);
        } else {
            enPlayView.setBackgroundResource(R.mipmap.vedio_start);
        }
    }
}
