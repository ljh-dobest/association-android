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
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SimpleAdapter$SimpleAdapterViewHolder_ViewBinding<T extends SimpleAdapter.SimpleAdapterViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SimpleAdapter$SimpleAdapterViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_shareFriend_item_icon = Utils.findRequiredViewAsType(source, R.id.iv_shareFriend_item_icon, "field 'iv_shareFriend_item_icon'", XCRoundRectImageView.class);
    target.tv_shareFriend_item_name = Utils.findRequiredViewAsType(source, R.id.tv_shareFriend_item_name, "field 'tv_shareFriend_item_name'", TextView.class);
    target.tv_shareFriend_item_time = Utils.findRequiredViewAsType(source, R.id.tv_shareFriend_item_time, "field 'tv_shareFriend_item_time'", TextView.class);
    target.tv_shareFriend_item_title = Utils.findRequiredViewAsType(source, R.id.tv_shareFriend_item_title, "field 'tv_shareFriend_item_title'", TextView.class);
    target.rv_shareFriend_item_images = Utils.findRequiredViewAsType(source, R.id.rv_shareFriend_item_images, "field 'rv_shareFriend_item_images'", RecyclerView.class);
    target.tv_shareFriends_good = Utils.findRequiredViewAsType(source, R.id.tv_shareFriends_good, "field 'tv_shareFriends_good'", ImageView.class);
    target.tv_shareFriends_goodNum = Utils.findRequiredViewAsType(source, R.id.tv_shareFriends_goodNum, "field 'tv_shareFriends_goodNum'", TextView.class);
    target.tv_shareFriends_discu = Utils.findRequiredViewAsType(source, R.id.tv_shareFriends_discu, "field 'tv_shareFriends_discu'", ImageView.class);
    target.tv_shareFriends_discuNum = Utils.findRequiredViewAsType(source, R.id.tv_shareFriends_discuNum, "field 'tv_shareFriends_discuNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_shareFriend_item_icon = null;
    target.tv_shareFriend_item_name = null;
    target.tv_shareFriend_item_time = null;
    target.tv_shareFriend_item_title = null;
    target.rv_shareFriend_item_images = null;
    target.tv_shareFriends_good = null;
    target.tv_shareFriends_goodNum = null;
    target.tv_shareFriends_discu = null;
    target.tv_shareFriends_discuNum = null;

    this.target = null;
  }
}
