// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiscussionActivity_ViewBinding<T extends DiscussionActivity> implements Unbinder {
  protected T target;

  private View view2131493023;

  private View view2131493030;

  @UiThread
  public DiscussionActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_answer_back, "field 'llAnswerBack' and method 'onAnswerViewClick'");
    target.llAnswerBack = Utils.castView(view, R.id.ll_answer_back, "field 'llAnswerBack'", LinearLayout.class);
    view2131493023 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswerViewClick(p0);
      }
    });
    target.ivAnswerUserHeader = Utils.findRequiredViewAsType(source, R.id.iv_answer_userHeader, "field 'ivAnswerUserHeader'", XCRoundRectImageView.class);
    target.tvAnswerTitle = Utils.findRequiredViewAsType(source, R.id.tv_answer_title, "field 'tvAnswerTitle'", TextView.class);
    target.rvAnswer = Utils.findRequiredViewAsType(source, R.id.rv_Answer, "field 'rvAnswer'", RecyclerView.class);
    target.etAnswerEvaluate = Utils.findRequiredViewAsType(source, R.id.et_answer_evaluate, "field 'etAnswerEvaluate'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_answer_sendEvaluate, "field 'btnAnswerSendEvaluate' and method 'onAnswerViewClick'");
    target.btnAnswerSendEvaluate = Utils.castView(view, R.id.btn_answer_sendEvaluate, "field 'btnAnswerSendEvaluate'", Button.class);
    view2131493030 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswerViewClick(p0);
      }
    });
    target.activityAnswer = Utils.findRequiredViewAsType(source, R.id.activity_answer, "field 'activityAnswer'", RelativeLayout.class);
    target.llAnswerNull = Utils.findRequiredViewAsType(source, R.id.ll_answer_null, "field 'llAnswerNull'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llAnswerBack = null;
    target.ivAnswerUserHeader = null;
    target.tvAnswerTitle = null;
    target.rvAnswer = null;
    target.etAnswerEvaluate = null;
    target.btnAnswerSendEvaluate = null;
    target.activityAnswer = null;
    target.llAnswerNull = null;

    view2131493023.setOnClickListener(null);
    view2131493023 = null;
    view2131493030.setOnClickListener(null);
    view2131493030 = null;

    this.target = null;
  }
}
