// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineTeachFragment_ViewBinding<T extends MineTeachFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MineTeachFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rvMineTeache = Utils.findRequiredViewAsType(source, R.id.rv_mineTeache, "field 'rvMineTeache'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rvMineTeache = null;

    this.target = null;
  }
}
