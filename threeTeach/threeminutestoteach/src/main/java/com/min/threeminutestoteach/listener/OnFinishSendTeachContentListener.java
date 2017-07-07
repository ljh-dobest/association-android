package com.min.threeminutestoteach.listener;

/**
 * Created by Min on 2017/6/12.
 */

public interface OnFinishSendTeachContentListener {
    void showError(String errorString);
    void succeedToPublic();
    void currentProgress(float progress);
}
