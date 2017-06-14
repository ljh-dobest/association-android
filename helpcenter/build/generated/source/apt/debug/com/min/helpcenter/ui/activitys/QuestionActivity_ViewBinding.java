// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class QuestionActivity_ViewBinding<T extends QuestionActivity> implements Unbinder {
  protected T target;

  private View view2131558585;

  private View view2131558590;

  private View view2131558589;

  @UiThread
  public QuestionActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_question_back, "field 'll_question_back' and method 'onQuestionViewClick'");
    target.ll_question_back = Utils.castView(view, R.id.ll_question_back, "field 'll_question_back'", LinearLayout.class);
    view2131558585 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onQuestionViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_question_answer, "field 'tv_question_answer' and method 'onQuestionViewClick'");
    target.tv_question_answer = Utils.castView(view, R.id.tv_question_answer, "field 'tv_question_answer'", TextView.class);
    view2131558590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onQuestionViewClick(p0);
      }
    });
    target.iv_question_share = Utils.findRequiredViewAsType(source, R.id.iv_question_share, "field 'iv_question_share'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_question_good, "field 'iv_question_good' and method 'onQuestionViewClick'");
    target.iv_question_good = Utils.castView(view, R.id.iv_question_good, "field 'iv_question_good'", ImageView.class);
    view2131558589 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onQuestionViewClick(p0);
      }
    });
    target.rv_question = Utils.findRequiredViewAsType(source, R.id.rv_question, "field 'rv_question'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_question_back = null;
    target.tv_question_answer = null;
    target.iv_question_share = null;
    target.iv_question_good = null;
    target.rv_question = null;

    view2131558585.setOnClickListener(null);
    view2131558585 = null;
    view2131558590.setOnClickListener(null);
    view2131558590 = null;
    view2131558589.setOnClickListener(null);
    view2131558589 = null;

    this.target = null;
  }
}
