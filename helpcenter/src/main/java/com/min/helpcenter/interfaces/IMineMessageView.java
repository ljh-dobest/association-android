package com.min.helpcenter.interfaces;

import com.min.helpcenter.bean.MineHelp;

import java.util.List;

/**
 * Created by Min on 2017/3/29.
 */

public interface IMineMessageView {
    void getMyHelpData(String userId,String status);
    void setMyHelpData(List<MineHelp> data);
    void showError(String errorString);
}
