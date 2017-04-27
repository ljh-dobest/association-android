// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.image.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupVoteDetailRvAdapter$MyViewHolder_ViewBinding<T extends GroupVoteDetailRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public GroupVoteDetailRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_joinUser_icon = Utils.findRequiredViewAsType(source, R.id.iv_joinUser_icon, "field 'iv_joinUser_icon'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_joinUser_icon = null;

    this.target = null;
  }
}
