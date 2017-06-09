package com.min.helpcenter.interfaces;

import com.min.helpcenter.bean.HelpBean;

/**
 * Created by Min on 2017/3/28.
 */

public interface IQuestionView {
      void getQuestionDetails(String userId,String seekId);
      void setQuestionDetails(HelpBean helpBean);
    void showError(String errorString);
}
