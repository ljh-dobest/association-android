// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupFlexibleRvAdapter$MyViewHolder_ViewBinding<T extends GroupFlexibleRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public GroupFlexibleRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_group_activity_head = Utils.findRequiredViewAsType(source, R.id.iv_group_activity_head, "field 'iv_group_activity_head'", SelectableRoundedImageView.class);
    target.tv_activity_name = Utils.findRequiredViewAsType(source, R.id.tv_activity_name, "field 'tv_activity_name'", TextView.class);
    target.tv_activitzy_place = Utils.findRequiredViewAsType(source, R.id.tv_activity_place, "field 'tv_activitzy_place'", TextView.class);
    target.tv_activity_start_time = Utils.findRequiredViewAsType(source, R.id.tv_activity_start_time, "field 'tv_activity_start_time'", TextView.class);
    target.tv_activity_end_time = Utils.findRequiredViewAsType(source, R.id.tv_activity_end_time, "field 'tv_activity_end_time'", TextView.class);
    target.tv_activity_content = Utils.findRequiredViewAsType(source, R.id.tv_activity_content, "field 'tv_activity_content'", TextView.class);
    target.tv_activity_status = Utils.findRequiredViewAsType(source, R.id.tv_activity_status, "field 'tv_activity_status'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_group_activity_head = null;
    target.tv_activity_name = null;
    target.tv_activitzy_place = null;
    target.tv_activity_start_time = null;
    target.tv_activity_end_time = null;
    target.tv_activity_content = null;
    target.tv_activity_status = null;

    this.target = null;
  }
}
