package com.min.threeminutestoteach.listener;

import com.min.threeminutestoteach.bean.TeacheBean;

/**
 * Created by Min on 2017/6/12.
 */

public interface OnFinishTeachContentListener {
    void showError(String errorString);
    void returnTeachContentData(TeacheBean data);
    void succeedToLike();
    void succeedToCollection(String status);

}
