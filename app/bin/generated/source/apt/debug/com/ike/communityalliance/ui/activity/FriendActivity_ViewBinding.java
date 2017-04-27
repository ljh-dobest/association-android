// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.SideBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FriendActivity_ViewBinding<T extends FriendActivity> implements Unbinder {
  protected T target;

  @UiThread
  public FriendActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.etSearch = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'etSearch'", EditText.class);
    target.mListView = Utils.findRequiredViewAsType(source, R.id.lv_friends, "field 'mListView'", ListView.class);
    target.tvGroupDialog = Utils.findRequiredViewAsType(source, R.id.tv_group_dialog, "field 'tvGroupDialog'", TextView.class);
    target.sb = Utils.findRequiredViewAsType(source, R.id.sb, "field 'sb'", SideBar.class);
    target.tvShowNoFriend = Utils.findRequiredViewAsType(source, R.id.tv_show_no_friend, "field 'tvShowNoFriend'", TextView.class);
    target.mSwipeRefresh = Utils.findRequiredViewAsType(source, R.id.swipeRefresh, "field 'mSwipeRefresh'", SwipeRefreshLayout.class);
    target.ll_friend_activity_back = Utils.findRequiredViewAsType(source, R.id.ll_friend_activity_back, "field 'll_friend_activity_back'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etSearch = null;
    target.mListView = null;
    target.tvGroupDialog = null;
    target.sb = null;
    target.tvShowNoFriend = null;
    target.mSwipeRefresh = null;
    target.ll_friend_activity_back = null;

    this.target = null;
  }
}
