// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.andview.refreshview.XRefreshView;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageActivity_ViewBinding implements Unbinder {
  private MessageActivity target;

  private View view2131624098;

  @UiThread
  public MessageActivity_ViewBinding(MessageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MessageActivity_ViewBinding(final MessageActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lt_main_title_left, "field 'ltMainTitleLeft' and method 'leftClick'");
    target.ltMainTitleLeft = Utils.castView(view, R.id.lt_main_title_left, "field 'ltMainTitleLeft'", TextView.class);
    view2131624098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftClick();
      }
    });
    target.ltMainTitle = Utils.findRequiredViewAsType(source, R.id.lt_main_title, "field 'ltMainTitle'", TextView.class);
    target.ltMainTitleRight = Utils.findRequiredViewAsType(source, R.id.lt_main_title_right, "field 'ltMainTitleRight'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view_test_rv, "field 'recyclerView'", RecyclerView.class);
    target.xRefreshView = Utils.findRequiredViewAsType(source, R.id.xrefreshview, "field 'xRefreshView'", XRefreshView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ltMainTitleLeft = null;
    target.ltMainTitle = null;
    target.ltMainTitleRight = null;
    target.recyclerView = null;
    target.xRefreshView = null;

    view2131624098.setOnClickListener(null);
    view2131624098 = null;
  }
}
