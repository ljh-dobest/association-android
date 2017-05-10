package com.min.helpcenter.interfaces;

import com.min.helpcenter.bean.MineHelp;

import java.util.List;

/**
 * Created by Min on 2017/5/10.
 */

public interface IMineAnswerView {
    void getMyAnswer(String userId,String status);
    void setMyAnswer(List<MineHelp> data);
    void showError(String errorString);
}
