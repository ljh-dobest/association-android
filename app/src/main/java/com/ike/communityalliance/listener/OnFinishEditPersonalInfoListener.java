package com.ike.communityalliance.listener;

/**
 * Created by Min on 2017/4/24.
 */

public interface OnFinishEditPersonalInfoListener {
    void succeedToEdit(String imgPath);
    void showError(String errorString);
}
