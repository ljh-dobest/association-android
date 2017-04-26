package com.ike.communityalliance.listener;

import com.ike.communityalliance.wedget.Wheel.WheelView;

/**
 * Created by Min on 2016/12/7.
 */

public interface OnWheelScrollListener {
    void onScrollingStarted(WheelView wheel);

    void onScrollingFinished(WheelView wheel);
}
