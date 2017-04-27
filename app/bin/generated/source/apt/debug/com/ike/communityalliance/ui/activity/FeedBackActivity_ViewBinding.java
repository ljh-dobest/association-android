// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FeedBackActivity_ViewBinding<T extends FeedBackActivity> implements Unbinder {
  protected T target;

  private View view2131755536;

  private View view2131755537;

  @UiThread
  public FeedBackActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_feedback_back, "field 'llFeedbackBack' and method 'onViewClicked'");
    target.llFeedbackBack = Utils.castView(view, R.id.ll_feedback_back, "field 'llFeedbackBack'", AutoLinearLayout.class);
    view2131755536 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_feedback_comfirm, "field 'tvFeedbackComfirm' and method 'onViewClicked'");
    target.tvFeedbackComfirm = Utils.castView(view, R.id.tv_feedback_comfirm, "field 'tvFeedbackComfirm'", TextView.class);
    view2131755537 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etFeedbackContent = Utils.findRequiredViewAsType(source, R.id.et_feedback_content, "field 'etFeedbackContent'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llFeedbackBack = null;
    target.tvFeedbackComfirm = null;
    target.etFeedbackContent = null;

    view2131755536.setOnClickListener(null);
    view2131755536 = null;
    view2131755537.setOnClickListener(null);
    view2131755537 = null;

    this.target = null;
  }
}
