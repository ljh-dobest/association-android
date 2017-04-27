// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.MultiLineRadioGroup;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateInterestGroupActivity_ViewBinding<T extends CreateInterestGroupActivity> implements Unbinder {
  protected T target;

  private View view2131755531;

  private View view2131755533;

  @UiThread
  public CreateInterestGroupActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_create_interest_back, "field 'll_create_interest_back' and method 'onCreateInteresGroupViewClick'");
    target.ll_create_interest_back = Utils.castView(view, R.id.ll_create_interest_back, "field 'll_create_interest_back'", LinearLayout.class);
    view2131755531 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateInteresGroupViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_create_intreset_next, "field 'tv_create_intreset_next' and method 'onCreateInteresGroupViewClick'");
    target.tv_create_intreset_next = Utils.castView(view, R.id.tv_create_intreset_next, "field 'tv_create_intreset_next'", TextView.class);
    view2131755533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateInteresGroupViewClick(p0);
      }
    });
    target.mrg_interest_create = Utils.findRequiredViewAsType(source, R.id.mrg_interest_create, "field 'mrg_interest_create'", MultiLineRadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_create_interest_back = null;
    target.tv_create_intreset_next = null;
    target.mrg_interest_create = null;

    view2131755531.setOnClickListener(null);
    view2131755531 = null;
    view2131755533.setOnClickListener(null);
    view2131755533 = null;

    this.target = null;
  }
}
