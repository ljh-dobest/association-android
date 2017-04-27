// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ClaimFriendsActivity_ViewBinding<T extends ClaimFriendsActivity> implements Unbinder {
  protected T target;

  private View view2131756106;

  @UiThread
  public ClaimFriendsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.swipeRefresh = Utils.findRequiredViewAsType(source, R.id.swipeRefresh, "field 'swipeRefresh'", SwipeRefreshLayout.class);
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.swipeRefresh = null;
    target.listView = null;

    view2131756106.setOnClickListener(null);
    view2131756106 = null;

    this.target = null;
  }
}
