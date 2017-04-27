// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.image.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareFriendsContentRvAdapter$MyViewHolder_ViewBinding<T extends ShareFriendsContentRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ShareFriendsContentRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_shareFriendContent_item_icon = Utils.findRequiredViewAsType(source, R.id.iv_shareFriendContent_item_icon, "field 'iv_shareFriendContent_item_icon'", CircleImageView.class);
    target.tv_shareFriendContent_item_name = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_item_name, "field 'tv_shareFriendContent_item_name'", TextView.class);
    target.tv_shareFriendContent_item_time = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_item_time, "field 'tv_shareFriendContent_item_time'", TextView.class);
    target.tv_shareFriendContent_item_content = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_item_content, "field 'tv_shareFriendContent_item_content'", TextView.class);
    target.rv_shareFriendContent_item_replayUser = Utils.findRequiredViewAsType(source, R.id.rv_shareFriendContent_item_replayUser, "field 'rv_shareFriendContent_item_replayUser'", RecyclerView.class);
    target.iv_shareFriendContent_item_todicu = Utils.findRequiredViewAsType(source, R.id.iv_shareFriendContent_item_todicu, "field 'iv_shareFriendContent_item_todicu'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_shareFriendContent_item_icon = null;
    target.tv_shareFriendContent_item_name = null;
    target.tv_shareFriendContent_item_time = null;
    target.tv_shareFriendContent_item_content = null;
    target.rv_shareFriendContent_item_replayUser = null;
    target.iv_shareFriendContent_item_todicu = null;

    this.target = null;
  }
}
