package com.ike.sq.commonwealactives.listeners;



import com.ike.sq.commonwealactives.bean.UserBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface OnBenefitRegisteredListener {
    void getPlatformRegisteredList(List<UserBean> data);

    void showError(String errorString);
}
