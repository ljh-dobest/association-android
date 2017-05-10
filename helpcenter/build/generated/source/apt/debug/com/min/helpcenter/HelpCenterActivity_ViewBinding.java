// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class HelpCenterActivity_ViewBinding<T extends HelpCenterActivity> implements Unbinder {
  protected T target;

  private View view2131558574;

  private View view2131558575;

  private View view2131558578;

  @UiThread
  public HelpCenterActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_helpCenter_back, "field 'iv_helpCenter_back' and method 'onHelpCenterViewClick'");
    target.iv_helpCenter_back = Utils.castView(view, R.id.iv_helpCenter_back, "field 'iv_helpCenter_back'", ImageView.class);
    view2131558574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onHelpCenterViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_helpCenter_more, "field 'iv_helpCenter_more' and method 'onHelpCenterViewClick'");
    target.iv_helpCenter_more = Utils.castView(view, R.id.iv_helpCenter_more, "field 'iv_helpCenter_more'", ImageView.class);
    view2131558575 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onHelpCenterViewClick(p0);
      }
    });
    target.rv_helpCenter = Utils.findRequiredViewAsType(source, R.id.rv_helpCenter, "field 'rv_helpCenter'", RecyclerView.class);
    target.xrefreshview_helpCenter = Utils.findRequiredViewAsType(source, R.id.xrefreshview_helpCenter, "field 'xrefreshview_helpCenter'", XRefreshView.class);
    view = Utils.findRequiredView(source, R.id.iv_helpCenter_iask, "field 'rl_helpCenter_iask' and method 'onHelpCenterViewClick'");
    target.rl_helpCenter_iask = Utils.castView(view, R.id.iv_helpCenter_iask, "field 'rl_helpCenter_iask'", ImageView.class);
    view2131558578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onHelpCenterViewClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_helpCenter_back = null;
    target.iv_helpCenter_more = null;
    target.rv_helpCenter = null;
    target.xrefreshview_helpCenter = null;
    target.rl_helpCenter_iask = null;

    view2131558574.setOnClickListener(null);
    view2131558574 = null;
    view2131558575.setOnClickListener(null);
    view2131558575 = null;
    view2131558578.setOnClickListener(null);
    view2131558578 = null;

    this.target = null;
  }
}
