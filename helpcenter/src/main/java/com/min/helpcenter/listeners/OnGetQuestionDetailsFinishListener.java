package com.min.helpcenter.listeners;

import com.min.helpcenter.bean.HelpBean;

/**
 * Created by Min on 2017/4/1.
 */

public interface OnGetQuestionDetailsFinishListener {
    void showError(String errorString);
    void returnQuestion(HelpBean data);
}
