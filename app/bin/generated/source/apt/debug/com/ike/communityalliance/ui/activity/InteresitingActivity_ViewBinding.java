// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InteresitingActivity_ViewBinding<T extends InteresitingActivity> implements Unbinder {
  protected T target;

  private View view2131755599;

  private View view2131755598;

  @UiThread
  public InteresitingActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tl_interesting = Utils.findRequiredViewAsType(source, R.id.tl_interesting, "field 'tl_interesting'", TabLayout.class);
    target.vp_interesting = Utils.findRequiredViewAsType(source, R.id.vp_interesting, "field 'vp_interesting'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.iv_interest_createGroup, "field 'iv_interest_createGroup' and method 'interestOnClick'");
    target.iv_interest_createGroup = Utils.castView(view, R.id.iv_interest_createGroup, "field 'iv_interest_createGroup'", ImageView.class);
    view2131755599 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.interestOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_interest_back, "field 'll_interest_back' and method 'interestOnClick'");
    target.ll_interest_back = Utils.castView(view, R.id.ll_interest_back, "field 'll_interest_back'", LinearLayout.class);
    view2131755598 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.interestOnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tl_interesting = null;
    target.vp_interesting = null;
    target.iv_interest_createGroup = null;
    target.ll_interest_back = null;

    view2131755599.setOnClickListener(null);
    view2131755599 = null;
    view2131755598.setOnClickListener(null);
    view2131755598 = null;

    this.target = null;
  }
}
