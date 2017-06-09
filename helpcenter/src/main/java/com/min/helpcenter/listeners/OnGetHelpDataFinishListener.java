package com.min.helpcenter.listeners;

import com.min.helpcenter.bean.HelpBean;

import java.util.List;

/**
 * Created by Min on 2017/4/1.
 */

public interface OnGetHelpDataFinishListener {
    void showError(String errorString);
    void returnHelpData(List<HelpBean> data);
    void returnMoreHelpData(List<HelpBean> data);
}
