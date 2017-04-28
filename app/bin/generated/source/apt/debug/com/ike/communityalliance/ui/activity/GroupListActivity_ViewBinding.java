// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupListActivity_ViewBinding<T extends GroupListActivity> implements Unbinder {
  protected T target;

  private View view2131756112;

  private View view2131756114;

  @UiThread
  public GroupListActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756112 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_title_right, "field 'ivTitleRight' and method 'onClick'");
    target.ivTitleRight = Utils.castView(view, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    view2131756114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.rvGroupList = Utils.findRequiredViewAsType(source, R.id.rv_group_list, "field 'rvGroupList'", RecyclerView.class);
    target.tvNoGroup = Utils.findRequiredViewAsType(source, R.id.tv_no_group, "field 'tvNoGroup'", TextView.class);
    target.swipeRefresh = Utils.findRequiredViewAsType(source, R.id.swipeRefresh, "field 'swipeRefresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.ivTitleRight = null;
    target.tvTitle = null;
    target.rvGroupList = null;
    target.tvNoGroup = null;
    target.swipeRefresh = null;

    view2131756112.setOnClickListener(null);
    view2131756112 = null;
    view2131756114.setOnClickListener(null);
    view2131756114 = null;

    this.target = null;
  }
}
