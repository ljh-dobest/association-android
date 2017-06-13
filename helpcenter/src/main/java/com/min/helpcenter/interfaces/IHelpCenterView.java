package com.min.helpcenter.interfaces;

import com.min.helpcenter.base.view.BaseView;
import com.min.helpcenter.bean.HelpBean;

import java.util.List;

/**
 * Created by Min on 2017/3/28.
 */

public interface IHelpCenterView extends BaseView {
    void getHelpData(String userId,int Page);
    void setHelpData(List<HelpBean> data);
    void setMoreHelpData(List<HelpBean> data);
}
