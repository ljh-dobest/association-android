package com.min.helpcenter.listeners;

import com.min.helpcenter.bean.MineHelp;

import java.util.List;

/**
 * Created by Min on 2017/5/10.
 */

public interface OnGetMineAnswerFinishListener {
    void showError(String s);
    void returnMineAnswer(List<MineHelp> data);
}
