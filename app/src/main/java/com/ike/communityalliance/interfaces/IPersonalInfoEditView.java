package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.UserInfo;

/**
 * Created by Min on 2017/4/24.
 */

public interface IPersonalInfoEditView {
    void postPersonalInfo(UserInfo userInfo, String type);
    void succeedToEdit(String imgPath);
    void showError(String errorString);
}
