// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnswerRvAdapter$MyViewHolder_ViewBinding<T extends AnswerRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public AnswerRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivAnswerItemIcon = Utils.findRequiredViewAsType(source, R.id.iv_answer_item_icon, "field 'ivAnswerItemIcon'", CircleImageView.class);
    target.tvAnswerItemName = Utils.findRequiredViewAsType(source, R.id.tv_answer_item_name, "field 'tvAnswerItemName'", TextView.class);
    target.ivAnswerItemGood = Utils.findRequiredViewAsType(source, R.id.iv_answer_item_good, "field 'ivAnswerItemGood'", ImageView.class);
    target.tvAnswerItemGoodNum = Utils.findRequiredViewAsType(source, R.id.tv_answer_item_goodNum, "field 'tvAnswerItemGoodNum'", TextView.class);
    target.tvAnswerItemContent = Utils.findRequiredViewAsType(source, R.id.tv_answer_item_content, "field 'tvAnswerItemContent'", TextView.class);
    target.tvAnswerItemTime = Utils.findRequiredViewAsType(source, R.id.tv_answer_item_time, "field 'tvAnswerItemTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivAnswerItemIcon = null;
    target.tvAnswerItemName = null;
    target.ivAnswerItemGood = null;
    target.tvAnswerItemGoodNum = null;
    target.tvAnswerItemContent = null;
    target.tvAnswerItemTime = null;

    this.target = null;
  }
}
