// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.helpcenter.R;
import com.min.helpcenter.views.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnswerActivity_ViewBinding<T extends AnswerActivity> implements Unbinder {
  protected T target;

  private View view2131558541;

  private View view2131558536;

  private View view2131558548;

  @UiThread
  public AnswerActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tv_answer_title = Utils.findRequiredViewAsType(source, R.id.tv_answer_title, "field 'tv_answer_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_answer_accept, "field 'tv_answer_accept' and method 'onAnswerViewClick'");
    target.tv_answer_accept = Utils.castView(view, R.id.tv_answer_accept, "field 'tv_answer_accept'", TextView.class);
    view2131558541 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswerViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_answer_back, "field 'll_answer_back' and method 'onAnswerViewClick'");
    target.ll_answer_back = Utils.castView(view, R.id.ll_answer_back, "field 'll_answer_back'", LinearLayout.class);
    view2131558536 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswerViewClick(p0);
      }
    });
    target.iv_answer_icon = Utils.findRequiredViewAsType(source, R.id.iv_answer_icon, "field 'iv_answer_icon'", CircleImageView.class);
    target.tv_answer_name = Utils.findRequiredViewAsType(source, R.id.tv_answer_name, "field 'tv_answer_name'", TextView.class);
    target.tv_answer_time = Utils.findRequiredViewAsType(source, R.id.tv_answer_time, "field 'tv_answer_time'", TextView.class);
    target.tv_answer_content = Utils.findRequiredViewAsType(source, R.id.tv_answer_content, "field 'tv_answer_content'", TextView.class);
    target.tv_answer_goodNum = Utils.findRequiredViewAsType(source, R.id.tv_answer_goodNum, "field 'tv_answer_goodNum'", TextView.class);
    target.rv_Answer = Utils.findRequiredViewAsType(source, R.id.rv_Answer, "field 'rv_Answer'", RecyclerView.class);
    target.et_answer_evaluate = Utils.findRequiredViewAsType(source, R.id.et_answer_evaluate, "field 'et_answer_evaluate'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_answer_sendEvaluate, "field 'btn_answer_sendEvaluate' and method 'onAnswerViewClick'");
    target.btn_answer_sendEvaluate = Utils.castView(view, R.id.btn_answer_sendEvaluate, "field 'btn_answer_sendEvaluate'", Button.class);
    view2131558548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAnswerViewClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_answer_title = null;
    target.tv_answer_accept = null;
    target.ll_answer_back = null;
    target.iv_answer_icon = null;
    target.tv_answer_name = null;
    target.tv_answer_time = null;
    target.tv_answer_content = null;
    target.tv_answer_goodNum = null;
    target.rv_Answer = null;
    target.et_answer_evaluate = null;
    target.btn_answer_sendEvaluate = null;

    view2131558541.setOnClickListener(null);
    view2131558541 = null;
    view2131558536.setOnClickListener(null);
    view2131558536 = null;
    view2131558548.setOnClickListener(null);
    view2131558548 = null;

    this.target = null;
  }
}
