package com.min.threeminutestoteach.interfaces;

import com.min.threeminutestoteach.bean.TeacheBean;

/**
 * Created by Min on 2017/6/12.
 */

public interface ITeacheContentView {
    void postClikeToLike();
    void showError(String errorString);
    void setTeachContent(TeacheBean teacheBean);
    void succeedToLike();
    void succeedToCollection(String status);
}
