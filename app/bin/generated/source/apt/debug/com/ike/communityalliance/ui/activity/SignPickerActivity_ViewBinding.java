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
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignPickerActivity_ViewBinding<T extends SignPickerActivity> implements Unbinder {
  protected T target;

  private View view2131755807;

  private View view2131755805;

  @UiThread
  public SignPickerActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.picker = Utils.findRequiredViewAsType(source, R.id.my_datepicker, "field 'picker'", MaterialCalendarView.class);
    view = Utils.findRequiredView(source, R.id.btn_signpicker_sign, "field 'btn_signpicker_sign' and method 'onSignViewClick'");
    target.btn_signpicker_sign = Utils.castView(view, R.id.btn_signpicker_sign, "field 'btn_signpicker_sign'", Button.class);
    view2131755807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSignViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_signpicker_back, "field 'tv_signpicker_back' and method 'onSignViewClick'");
    target.tv_signpicker_back = Utils.castView(view, R.id.tv_signpicker_back, "field 'tv_signpicker_back'", TextView.class);
    view2131755805 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSignViewClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.picker = null;
    target.btn_signpicker_sign = null;
    target.tv_signpicker_back = null;

    view2131755807.setOnClickListener(null);
    view2131755807 = null;
    view2131755805.setOnClickListener(null);
    view2131755805 = null;

    this.target = null;
  }
}
