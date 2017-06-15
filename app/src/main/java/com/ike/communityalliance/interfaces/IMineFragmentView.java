package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by Min on 2017/3/9.
 */

public interface IMineFragmentView extends BaseView {
    void showError(String errorString);
    void getMineUserInfo(String userId);
    void setData(UserInfo data);
}
