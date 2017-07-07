package com.min.threeminutestoteach.listener;

import com.min.threeminutestoteach.bean.TeacheBean;

import java.util.List;

/**
 * Created by Min on 2017/6/12.
 */

public interface OnFinishGetThreeTeachListener {
    void showError(String errorString);
    void returnThreeTeachData(List<TeacheBean> data);
    void returnMoreTeachData(List<TeacheBean> data);
}
