// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.Utils;

public class AnswerMessageFragment_ViewBinding<T extends AnswerMessageFragment> implements Unbinder {
  protected T target;

  @UiThread
  public AnswerMessageFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rv_mineAnswer = Utils.findRequiredViewAsType(source, R.id.rv_mineAnswer, "field 'rv_mineAnswer'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rv_mineAnswer = null;

    this.target = null;
  }
}
