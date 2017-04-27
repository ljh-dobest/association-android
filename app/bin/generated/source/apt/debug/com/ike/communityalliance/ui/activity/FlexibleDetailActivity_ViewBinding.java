// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FlexibleDetailActivity_ViewBinding<T extends FlexibleDetailActivity> implements Unbinder {
  protected T target;

  private View view2131755539;

  private View view2131755551;

  private View view2131755542;

  private View view2131755546;

  private View view2131755548;

  @UiThread
  public FlexibleDetailActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_activity_detail_back, "field 'll_activity_detail_back' and method 'onClick'");
    target.ll_activity_detail_back = Utils.castView(view, R.id.ll_activity_detail_back, "field 'll_activity_detail_back'", LinearLayout.class);
    view2131755539 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.iv_activity_detail_head = Utils.findRequiredViewAsType(source, R.id.iv_activity_detail_head, "field 'iv_activity_detail_head'", ImageView.class);
    target.tv_activity_detail_title = Utils.findRequiredViewAsType(source, R.id.tv_activity_detail_title, "field 'tv_activity_detail_title'", TextView.class);
    target.tv_activity_detail_time = Utils.findRequiredViewAsType(source, R.id.tv_activity_detail_time, "field 'tv_activity_detail_time'", TextView.class);
    target.tv_activity_detail_address = Utils.findRequiredViewAsType(source, R.id.tv_activity_detail_address, "field 'tv_activity_detail_address'", TextView.class);
    target.tv_activity_detail_publisher = Utils.findRequiredViewAsType(source, R.id.tv_activity_detail_publisher, "field 'tv_activity_detail_publisher'", TextView.class);
    target.tv_activity_detail_content = Utils.findRequiredViewAsType(source, R.id.tv_activity_detail_content, "field 'tv_activity_detail_content'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_activity_detail_moreJoinUsers, "field 'tv_activity_detail_moreJoinUsers' and method 'onClick'");
    target.tv_activity_detail_moreJoinUsers = Utils.castView(view, R.id.tv_activity_detail_moreJoinUsers, "field 'tv_activity_detail_moreJoinUsers'", TextView.class);
    view2131755551 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_activity_detail_applyFlexible, "field 'tv_activity_detail_applyFlexible' and method 'onClick'");
    target.tv_activity_detail_applyFlexible = Utils.castView(view, R.id.tv_activity_detail_applyFlexible, "field 'tv_activity_detail_applyFlexible'", TextView.class);
    view2131755542 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_activity_detail_up, "field 'll_activity_detail_up' and method 'onClick'");
    target.ll_activity_detail_up = Utils.castView(view, R.id.ll_activity_detail_up, "field 'll_activity_detail_up'", LinearLayout.class);
    view2131755546 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_activity_detail_down, "field 'll_activity_detail_down' and method 'onClick'");
    target.ll_activity_detail_down = Utils.castView(view, R.id.ll_activity_detail_down, "field 'll_activity_detail_down'", LinearLayout.class);
    view2131755548 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ll_activity_detail_contentimages = Utils.findRequiredViewAsType(source, R.id.ll_activity_detail_contentimages, "field 'll_activity_detail_contentimages'", LinearLayout.class);
    target.rvActivityUser = Utils.findRequiredViewAsType(source, R.id.rv_activity_user, "field 'rvActivityUser'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_activity_detail_back = null;
    target.iv_activity_detail_head = null;
    target.tv_activity_detail_title = null;
    target.tv_activity_detail_time = null;
    target.tv_activity_detail_address = null;
    target.tv_activity_detail_publisher = null;
    target.tv_activity_detail_content = null;
    target.tv_activity_detail_moreJoinUsers = null;
    target.tv_activity_detail_applyFlexible = null;
    target.ll_activity_detail_up = null;
    target.ll_activity_detail_down = null;
    target.ll_activity_detail_contentimages = null;
    target.rvActivityUser = null;

    view2131755539.setOnClickListener(null);
    view2131755539 = null;
    view2131755551.setOnClickListener(null);
    view2131755551 = null;
    view2131755542.setOnClickListener(null);
    view2131755542 = null;
    view2131755546.setOnClickListener(null);
    view2131755546 = null;
    view2131755548.setOnClickListener(null);
    view2131755548 = null;

    this.target = null;
  }
}
