// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MineHelpOrQuestionRvAdapter$MyViewHolder_ViewBinding<T extends MineHelpOrQuestionRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MineHelpOrQuestionRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tv_mineMessage_item_title = Utils.findRequiredViewAsType(source, R.id.tv_mineMessage_item_title, "field 'tv_mineMessage_item_title'", TextView.class);
    target.tv_mineMessage_item_time = Utils.findRequiredViewAsType(source, R.id.tv_mineMessage_item_time, "field 'tv_mineMessage_item_time'", TextView.class);
    target.tv_mineMessage_item_content = Utils.findRequiredViewAsType(source, R.id.tv_mineMessage_item_content, "field 'tv_mineMessage_item_content'", TextView.class);
    target.tv_mineMessage_responseNum = Utils.findRequiredViewAsType(source, R.id.tv_mineMessage_responseNum, "field 'tv_mineMessage_responseNum'", TextView.class);
    target.tv_mineMessage_item_goldNum = Utils.findRequiredViewAsType(source, R.id.tv_mineMessage_item_goldNum, "field 'tv_mineMessage_item_goldNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_mineMessage_item_title = null;
    target.tv_mineMessage_item_time = null;
    target.tv_mineMessage_item_content = null;
    target.tv_mineMessage_responseNum = null;
    target.tv_mineMessage_item_goldNum = null;

    this.target = null;
  }
}
