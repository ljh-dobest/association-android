package com.min.threeminutestoteach.interfaces;

import com.min.threeminutestoteach.bean.TeacheBean;

import java.util.List;

/**
 * Created by Min on 2017/6/12.
 */

public interface IThreeTeacheView {
    void getThreeTeachData(String userId,String page);
    void showError(String errorString);
    void setThreeTeacheData(List<TeacheBean> data);
    void loadMoreData(String userId,String page);
    void setMoreData(List<TeacheBean> data);
}
