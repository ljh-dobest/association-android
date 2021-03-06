package com.ike.coalition.platform.listeners;


import com.ike.coalition.platform.bean.ImageUrlBean;
import com.ike.coalition.platform.bean.PlatformBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface OnPlatformListener {
    void getPlatformList(List<PlatformBean> data);

    void getImageUrl(List<ImageUrlBean> bean);
    void showError(String errorString);
}
