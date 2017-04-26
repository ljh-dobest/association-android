package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.bean.DateBean;

import java.util.List;

/**
 * Created by Min on 2017/3/9.
 */

public interface ISignPickerView  {
    void setSingPickerData(List<DateBean> data);
    void showErrorString(String errorString);
    void succeedToSign(String msg);
}
