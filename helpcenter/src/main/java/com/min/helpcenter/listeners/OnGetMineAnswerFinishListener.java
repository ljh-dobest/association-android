package com.min.helpcenter.listeners;

import com.min.helpcenter.bean.MineHelp;

import java.util.List;

/**
 * Created by Min on 2017/4/1.
 */

public interface OnGetMineAnswerFinishListener {
    void showError(String errorString);
    void returnMineAnswer(List<MineHelp> data);
}
