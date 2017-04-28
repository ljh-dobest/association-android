// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding<T extends SettingActivity> implements Unbinder {
  protected T target;

  private View view2131755779;

  private View view2131755780;

  private View view2131755782;

  @UiThread
  public SettingActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_setting_back, "field 'llSettingBack' and method 'onViewClicked'");
    target.llSettingBack = Utils.castView(view, R.id.ll_setting_back, "field 'llSettingBack'", AutoLinearLayout.class);
    view2131755779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_setting_comfirm, "field 'tvSettingComfirm' and method 'onViewClicked'");
    target.tvSettingComfirm = Utils.castView(view, R.id.tv_setting_comfirm, "field 'tvSettingComfirm'", TextView.class);
    view2131755780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.swSettingMsg = Utils.findRequiredViewAsType(source, R.id.sw_setting_msg, "field 'swSettingMsg'", SwitchButton.class);
    view = Utils.findRequiredView(source, R.id.btn_setting_out, "field 'btnSettingOut' and method 'onViewClicked'");
    target.btnSettingOut = Utils.castView(view, R.id.btn_setting_out, "field 'btnSettingOut'", Button.class);
    view2131755782 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llSettingBack = null;
    target.tvSettingComfirm = null;
    target.swSettingMsg = null;
    target.btnSettingOut = null;

    view2131755779.setOnClickListener(null);
    view2131755779 = null;
    view2131755780.setOnClickListener(null);
    view2131755780 = null;
    view2131755782.setOnClickListener(null);
    view2131755782 = null;

    this.target = null;
  }
}
