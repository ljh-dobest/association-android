// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class MineMessageActivity_ViewBinding<T extends MineMessageActivity> implements Unbinder {
  protected T target;

  private View view2131558580;

  @UiThread
  public MineMessageActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_mineMessage_back, "field 'iv_mineMessage_back' and method 'onMineMessageViewClick'");
    target.iv_mineMessage_back = Utils.castView(view, R.id.iv_mineMessage_back, "field 'iv_mineMessage_back'", ImageView.class);
    view2131558580 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onMineMessageViewClick(p0);
      }
    });
    target.rg_mineMessage = Utils.findRequiredViewAsType(source, R.id.rg_mineMessage, "field 'rg_mineMessage'", RadioGroup.class);
    target.rb_mineMessage_myHelp = Utils.findRequiredViewAsType(source, R.id.rb_mineMessage_myHelp, "field 'rb_mineMessage_myHelp'", RadioButton.class);
    target.rb_mineMessage_myAnswer = Utils.findRequiredViewAsType(source, R.id.rb_mineMessage_myAnswer, "field 'rb_mineMessage_myAnswer'", RadioButton.class);
    target.vp_mineMessage = Utils.findRequiredViewAsType(source, R.id.vp_mineMessage, "field 'vp_mineMessage'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_mineMessage_back = null;
    target.rg_mineMessage = null;
    target.rb_mineMessage_myHelp = null;
    target.rb_mineMessage_myAnswer = null;
    target.vp_mineMessage = null;

    view2131558580.setOnClickListener(null);
    view2131558580 = null;

    this.target = null;
  }
}
