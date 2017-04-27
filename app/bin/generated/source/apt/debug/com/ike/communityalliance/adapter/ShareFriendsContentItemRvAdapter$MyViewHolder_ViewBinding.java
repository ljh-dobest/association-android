// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareFriendsContentItemRvAdapter$MyViewHolder_ViewBinding<T extends ShareFriendsContentItemRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ShareFriendsContentItemRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tv_replayusers_nickname = Utils.findRequiredViewAsType(source, R.id.tv_replayusers_nickname, "field 'tv_replayusers_nickname'", TextView.class);
    target.tv_replayusers_fromNickname = Utils.findRequiredViewAsType(source, R.id.tv_replayusers_fromNickname, "field 'tv_replayusers_fromNickname'", TextView.class);
    target.tv_replayusers_content = Utils.findRequiredViewAsType(source, R.id.tv_replayusers_content, "field 'tv_replayusers_content'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tv_replayusers_nickname = null;
    target.tv_replayusers_fromNickname = null;
    target.tv_replayusers_content = null;

    this.target = null;
  }
}
