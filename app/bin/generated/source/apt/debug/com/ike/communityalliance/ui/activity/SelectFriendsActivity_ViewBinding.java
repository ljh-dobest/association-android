// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.SideBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectFriendsActivity_ViewBinding<T extends SelectFriendsActivity> implements Unbinder {
  protected T target;

  @UiThread
  public SelectFriendsActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.llSelectedFriends = Utils.findRequiredViewAsType(source, R.id.ll_selected_friends, "field 'llSelectedFriends'", LinearLayout.class);
    target.tvNoFriend = Utils.findRequiredViewAsType(source, R.id.tv_no_friend, "field 'tvNoFriend'", TextView.class);
    target.mListView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'mListView'", ListView.class);
    target.tvDialog = Utils.findRequiredViewAsType(source, R.id.tv_dialog, "field 'tvDialog'", TextView.class);
    target.sidrbar = Utils.findRequiredViewAsType(source, R.id.sidrbar, "field 'sidrbar'", SideBar.class);
    target.tvEnter = Utils.findRequiredViewAsType(source, R.id.tv_enter, "field 'tvEnter'", TextView.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'ivBack'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llSelectedFriends = null;
    target.tvNoFriend = null;
    target.mListView = null;
    target.tvDialog = null;
    target.sidrbar = null;
    target.tvEnter = null;
    target.ivBack = null;
    target.tvTitle = null;

    this.target = null;
  }
}
