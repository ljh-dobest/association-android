package com.ike.communityalliance.listener;


import com.ike.communityalliance.bean.DateBean;

import java.util.List;

/**
 * Created by just on 2017/3/5.
 */

public interface OnGetSignPickerDataFinishListener {
    void showError(String errorString);
    void returnSignPickerData(List<DateBean> data);
    void succeedToSign(String msg);
}
