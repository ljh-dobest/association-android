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
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupMemberActivity_ViewBinding<T extends GroupMemberActivity> implements Unbinder {
  protected T target;

  private View view2131755590;

  @UiThread
  public GroupMemberActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_group_member_back, "field 'll_group_member_back' and method 'onClick'");
    target.ll_group_member_back = Utils.castView(view, R.id.ll_group_member_back, "field 'll_group_member_back'", LinearLayout.class);
    view2131755590 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.rv_groupmenber = Utils.findRequiredViewAsType(source, R.id.rv_groupmenber, "field 'rv_groupmenber'", RecyclerView.class);
    target.tv_group_member_title = Utils.findRequiredViewAsType(source, R.id.tv_group_member_title, "field 'tv_group_member_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_group_member_back = null;
    target.rv_groupmenber = null;
    target.tv_group_member_title = null;

    view2131755590.setOnClickListener(null);
    view2131755590 = null;

    this.target = null;
  }
}
