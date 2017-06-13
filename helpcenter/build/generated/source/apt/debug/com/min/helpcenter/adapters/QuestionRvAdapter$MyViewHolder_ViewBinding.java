// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.min.helpcenter.R;
import com.min.helpcenter.views.CircleImageView;

import butterknife.Unbinder;
import butterknife.internal.Utils;

public class QuestionRvAdapter$MyViewHolder_ViewBinding<T extends QuestionRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public QuestionRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_question_item_icon = Utils.findRequiredViewAsType(source, R.id.iv_question_item_icon, "field 'iv_question_item_icon'", CircleImageView.class);
    target.tv_question_item_name = Utils.findRequiredViewAsType(source, R.id.tv_question_item_name, "field 'tv_question_item_name'", TextView.class);
    target.tv_question_item_time = Utils.findRequiredViewAsType(source, R.id.tv_question_item_time, "field 'tv_question_item_time'", TextView.class);
    target.tv_question_item_content = Utils.findRequiredViewAsType(source, R.id.tv_question_item_content, "field 'tv_question_item_content'", TextView.class);
    target.iv_question_item_good = Utils.findRequiredViewAsType(source, R.id.iv_question_item_good, "field 'iv_question_item_good'", ImageView.class);
    target.tv_question_item_goodNum = Utils.findRequiredViewAsType(source, R.id.tv_question_item_goodNum, "field 'tv_question_item_goodNum'", TextView.class);
    target.iv_question_item_discu = Utils.findRequiredViewAsType(source, R.id.iv_question_item_discu, "field 'iv_question_item_discu'", ImageView.class);
    target.tv_question_item_discuNum = Utils.findRequiredViewAsType(source, R.id.tv_question_item_discuNum, "field 'tv_question_item_discuNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_question_item_icon = null;
    target.tv_question_item_name = null;
    target.tv_question_item_time = null;
    target.tv_question_item_content = null;
    target.iv_question_item_good = null;
    target.tv_question_item_goodNum = null;
    target.iv_question_item_discu = null;
    target.tv_question_item_discuNum = null;

    this.target = null;
  }
}
