// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.andview.refreshview.XRefreshView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131558543;

  private View view2131558544;

  private View view2131558548;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_main_back, "field 'llMainBack' and method 'onViewClicked'");
    target.llMainBack = Utils.castView(view, R.id.ll_main_back, "field 'llMainBack'", LinearLayout.class);
    view2131558543 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_main_more, "field 'ivMainMore' and method 'onViewClicked'");
    target.ivMainMore = Utils.castView(view, R.id.iv_main_more, "field 'ivMainMore'", ImageView.class);
    view2131558544 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.lvMain = Utils.findRequiredViewAsType(source, R.id.lv_main, "field 'lvMain'", ListView.class);
    target.xrefreshviewMain = Utils.findRequiredViewAsType(source, R.id.xrefreshview_main, "field 'xrefreshviewMain'", XRefreshView.class);
    view = Utils.findRequiredView(source, R.id.btn_main_send, "field 'btnMainSend' and method 'onViewClicked'");
    target.btnMainSend = Utils.castView(view, R.id.btn_main_send, "field 'btnMainSend'", Button.class);
    view2131558548 = view;
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

    target.llMainBack = null;
    target.ivMainMore = null;
    target.lvMain = null;
    target.xrefreshviewMain = null;
    target.btnMainSend = null;

    view2131558543.setOnClickListener(null);
    view2131558543 = null;
    view2131558544.setOnClickListener(null);
    view2131558544 = null;
    view2131558548.setOnClickListener(null);
    view2131558548 = null;

    this.target = null;
  }
}
