package com.ike.communityalliance.listener;

import com.ike.communityalliance.wedget.Wheel.WheelView;

/**
 * Created by Min on 2016/12/7.
 */

public interface OnWheelChangedListener {
    void onChanged(WheelView wheel, int oldValue, int newValue);
}
