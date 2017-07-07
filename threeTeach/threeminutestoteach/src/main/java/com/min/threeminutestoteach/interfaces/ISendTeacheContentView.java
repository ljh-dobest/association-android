package com.min.threeminutestoteach.interfaces;

/**
 * Created by Min on 2017/6/12.
 */

public interface ISendTeacheContentView {
    void postTeachContent();
    void showError(String errorString);
    void succeedToPublic();
    void currentProgress(float progress);
}
