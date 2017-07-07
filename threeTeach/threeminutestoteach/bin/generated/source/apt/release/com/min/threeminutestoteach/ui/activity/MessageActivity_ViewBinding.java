// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageActivity_ViewBinding<T extends MessageActivity> implements Unbinder {
  protected T target;

  private View view2131493074;

  @UiThread
  public MessageActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_msg_back, "field 'llMsgBack' and method 'onViewClicked'");
    target.llMsgBack = Utils.castView(view, R.id.ll_msg_back, "field 'llMsgBack'", LinearLayout.class);
    view2131493074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.rvMsg = Utils.findRequiredViewAsType(source, R.id.rv_msg, "field 'rvMsg'", RecyclerView.class);
    target.tvMsgNull = Utils.findRequiredViewAsType(source, R.id.tv_msg_null, "field 'tvMsgNull'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llMsgBack = null;
    target.rvMsg = null;
    target.tvMsgNull = null;

    view2131493074.setOnClickListener(null);
    view2131493074 = null;

    this.target = null;
  }
}
