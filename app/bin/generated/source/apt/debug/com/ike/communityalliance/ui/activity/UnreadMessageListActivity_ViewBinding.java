// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UnreadMessageListActivity_ViewBinding<T extends UnreadMessageListActivity> implements Unbinder {
  protected T target;

  private View view2131755806;

  private View view2131755807;

  @UiThread
  public UnreadMessageListActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_unread_msg_back, "field 'llUnreadMsgBack' and method 'onViewClicked'");
    target.llUnreadMsgBack = Utils.castView(view, R.id.ll_unread_msg_back, "field 'llUnreadMsgBack'", AutoLinearLayout.class);
    view2131755806 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_unread_msg_clear, "field 'tvUnreadMsgClear' and method 'onViewClicked'");
    target.tvUnreadMsgClear = Utils.castView(view, R.id.tv_unread_msg_clear, "field 'tvUnreadMsgClear'", TextView.class);
    view2131755807 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvUnreadMsg = Utils.findRequiredViewAsType(source, R.id.rv_unread_msg, "field 'rvUnreadMsg'", RecyclerView.class);
    target.activity_unread_message_list = Utils.findRequiredViewAsType(source, R.id.activity_unread_message_list, "field 'activity_unread_message_list'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llUnreadMsgBack = null;
    target.tvUnreadMsgClear = null;
    target.rvUnreadMsg = null;
    target.activity_unread_message_list = null;

    view2131755806.setOnClickListener(null);
    view2131755806 = null;
    view2131755807.setOnClickListener(null);
    view2131755807 = null;

    this.target = null;
  }
}
