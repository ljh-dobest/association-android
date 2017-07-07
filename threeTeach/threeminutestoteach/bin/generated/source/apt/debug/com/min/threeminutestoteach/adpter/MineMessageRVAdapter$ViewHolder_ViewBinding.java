// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineMessageRVAdapter$ViewHolder_ViewBinding<T extends MineMessageRVAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MineMessageRVAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivMsgItemUserHeader = Utils.findRequiredViewAsType(source, R.id.iv_msg_item_userHeader, "field 'ivMsgItemUserHeader'", XCRoundRectImageView.class);
    target.tvMsgItemUserName = Utils.findRequiredViewAsType(source, R.id.tv_msg_item_userName, "field 'tvMsgItemUserName'", TextView.class);
    target.tvMsgItemStatus = Utils.findRequiredViewAsType(source, R.id.tv_msg_item_status, "field 'tvMsgItemStatus'", TextView.class);
    target.tvMsgItemTile = Utils.findRequiredViewAsType(source, R.id.tv_msg_item_tile, "field 'tvMsgItemTile'", TextView.class);
    target.tvMsgItemTime = Utils.findRequiredViewAsType(source, R.id.tv_msg_item_time, "field 'tvMsgItemTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivMsgItemUserHeader = null;
    target.tvMsgItemUserName = null;
    target.tvMsgItemStatus = null;
    target.tvMsgItemTile = null;
    target.tvMsgItemTime = null;

    this.target = null;
  }
}
