// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.andview.refreshview.XRefreshView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131624099;

  private View view2131624248;

  private View view2131624097;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lt_main_title_left, "field 'ltMainTitleLeft' and method 'onViewClicked'");
    target.ltMainTitleLeft = Utils.castView(view, R.id.lt_main_title_left, "field 'ltMainTitleLeft'", TextView.class);
    view2131624099 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ltMainTitle = Utils.findRequiredViewAsType(source, R.id.lt_main_title, "field 'ltMainTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.lt_main_title_right, "field 'ltMainTitleRight' and method 'onViewClicked'");
    target.ltMainTitleRight = Utils.castView(view, R.id.lt_main_title_right, "field 'ltMainTitleRight'", TextView.class);
    view2131624248 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerViewTestRv = Utils.findRequiredViewAsType(source, R.id.recycler_view_test_rv, "field 'recyclerViewTestRv'", RecyclerView.class);
    target.xrefreshview = Utils.findRequiredViewAsType(source, R.id.xrefreshview, "field 'xrefreshview'", XRefreshView.class);
    view = Utils.findRequiredView(source, R.id.iv_top, "field 'ivTop' and method 'onViewClicked'");
    target.ivTop = Utils.castView(view, R.id.iv_top, "field 'ivTop'", ImageView.class);
    view2131624097 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ltMainTitleLeft = null;
    target.ltMainTitle = null;
    target.ltMainTitleRight = null;
    target.recyclerViewTestRv = null;
    target.xrefreshview = null;
    target.ivTop = null;

    view2131624099.setOnClickListener(null);
    view2131624099 = null;
    view2131624248.setOnClickListener(null);
    view2131624248 = null;
    view2131624097.setOnClickListener(null);
    view2131624097 = null;
  }
}
