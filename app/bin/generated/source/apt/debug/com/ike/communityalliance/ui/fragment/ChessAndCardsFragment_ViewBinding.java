// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChessAndCardsFragment_ViewBinding<T extends ChessAndCardsFragment> implements Unbinder {
  protected T target;

  @UiThread
  public ChessAndCardsFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.rv_interesGroup = Utils.findRequiredViewAsType(source, R.id.rv_interesGroup, "field 'rv_interesGroup'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rv_interesGroup = null;

    this.target = null;
  }
}
