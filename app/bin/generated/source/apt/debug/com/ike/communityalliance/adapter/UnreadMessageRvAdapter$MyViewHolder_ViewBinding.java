// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UnreadMessageRvAdapter$MyViewHolder_ViewBinding<T extends UnreadMessageRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public UnreadMessageRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_unread_msg_item_icon = Utils.findRequiredViewAsType(source, R.id.iv_unread_msg_item_icon, "field 'iv_unread_msg_item_icon'", ImageView.class);
    target.iv_unread_msg_item_image = Utils.findRequiredViewAsType(source, R.id.iv_unread_msg_item_image, "field 'iv_unread_msg_item_image'", ImageView.class);
    target.tv_unread_msg_item_name = Utils.findRequiredViewAsType(source, R.id.tv_unread_msg_item_name, "field 'tv_unread_msg_item_name'", TextView.class);
    target.tv_unread_msg_item_content = Utils.findRequiredViewAsType(source, R.id.tv_unread_msg_item_content, "field 'tv_unread_msg_item_content'", TextView.class);
    target.tv_unread_msg_item_time = Utils.findRequiredViewAsType(source, R.id.tv_unread_msg_item_time, "field 'tv_unread_msg_item_time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_unread_msg_item_icon = null;
    target.iv_unread_msg_item_image = null;
    target.tv_unread_msg_item_name = null;
    target.tv_unread_msg_item_content = null;
    target.tv_unread_msg_item_time = null;

    this.target = null;
  }
}
