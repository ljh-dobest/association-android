// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InterestMoreFragment_ViewBinding<T extends InterestMoreFragment> implements Unbinder {
  protected T target;

  @UiThread
  public InterestMoreFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rv_interestGroupMore = Utils.findRequiredViewAsType(source, R.id.rv_interestGroupMore, "field 'rv_interestGroupMore'", RecyclerView.class);
    target.rg_interestMore = Utils.findRequiredViewAsType(source, R.id.rg_interestMore, "field 'rg_interestMore'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rv_interestGroupMore = null;
    target.rg_interestMore = null;

    this.target = null;
  }
}
