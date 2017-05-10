// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class EvaluateActivity_ViewBinding<T extends EvaluateActivity> implements Unbinder {
  protected T target;

  private View view2131558565;

  private View view2131558566;

  @UiThread
  public EvaluateActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_evaluate_back, "field 'tv_evaluate_back' and method 'onEvaluateViewOnClick'");
    target.tv_evaluate_back = Utils.castView(view, R.id.tv_evaluate_back, "field 'tv_evaluate_back'", TextView.class);
    view2131558565 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEvaluateViewOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_evaluate_release, "field 'tv_evaluate_release' and method 'onEvaluateViewOnClick'");
    target.tv_evaluate_release = Utils.castView(view, R.id.tv_evaluate_release, "field 'tv_evaluate_release'", TextView.class);
    view2131558566 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEvaluateViewOnClick(p0);
      }
    });
    target.et_evaluate_content = Utils.findRequiredViewAsType(source, R.id.et_evaluate_content, "field 'et_evaluate_content'", EditText.class);
    target.ll_evaluate_insert = Utils.findRequiredViewAsType(source, R.id.ll_evaluate_insert, "field 'll_evaluate_insert'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_evaluate_back = null;
    target.tv_evaluate_release = null;
    target.et_evaluate_content = null;
    target.ll_evaluate_insert = null;

    view2131558565.setOnClickListener(null);
    view2131558565 = null;
    view2131558566.setOnClickListener(null);
    view2131558566 = null;

    this.target = null;
  }
}
