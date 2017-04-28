// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.andview.refreshview.XRefreshView;
import com.ike.communityalliance.R;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareFriendsActivity_ViewBinding<T extends ShareFriendsActivity> implements Unbinder {
  protected T target;

  private View view2131755798;

  private View view2131755797;

  private View view2131755799;

  @UiThread
  public ShareFriendsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.rv_share_Friends = Utils.findRequiredViewAsType(source, R.id.rv_share_Friends, "field 'rv_share_Friends'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_shareFriend_send, "field 'iv_shareFriend_send' and method 'shareFriendsViewOnClick'");
    target.iv_shareFriend_send = Utils.castView(view, R.id.iv_shareFriend_send, "field 'iv_shareFriend_send'", ImageView.class);
    view2131755798 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareFriendsViewOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_shareFriend_back, "field 'll_shareFriend_back' and method 'shareFriendsViewOnClick'");
    target.ll_shareFriend_back = Utils.castView(view, R.id.ll_shareFriend_back, "field 'll_shareFriend_back'", LinearLayout.class);
    view2131755797 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareFriendsViewOnClick(p0);
      }
    });
    target.xrefreshview_shareFriends = Utils.findRequiredViewAsType(source, R.id.xrefreshview_shareFriends, "field 'xrefreshview_shareFriends'", XRefreshView.class);
    target.ivShareFriendMsg = Utils.findRequiredViewAsType(source, R.id.iv_shareFriend_msg, "field 'ivShareFriendMsg'", ImageView.class);
    target.tvShareFriendMsg = Utils.findRequiredViewAsType(source, R.id.tv_shareFriend_msg, "field 'tvShareFriendMsg'", TextView.class);
    target.ivShareFriendMsgArrow = Utils.findRequiredViewAsType(source, R.id.iv_shareFriend_msg_arrow, "field 'ivShareFriendMsgArrow'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_shareFriend_msg, "field 'llShareFriendMsg' and method 'shareFriendsViewOnClick'");
    target.llShareFriendMsg = Utils.castView(view, R.id.ll_shareFriend_msg, "field 'llShareFriendMsg'", AutoLinearLayout.class);
    view2131755799 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.shareFriendsViewOnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.rv_share_Friends = null;
    target.iv_shareFriend_send = null;
    target.ll_shareFriend_back = null;
    target.xrefreshview_shareFriends = null;
    target.ivShareFriendMsg = null;
    target.tvShareFriendMsg = null;
    target.ivShareFriendMsgArrow = null;
    target.llShareFriendMsg = null;

    view2131755798.setOnClickListener(null);
    view2131755798 = null;
    view2131755797.setOnClickListener(null);
    view2131755797 = null;
    view2131755799.setOnClickListener(null);
    view2131755799 = null;

    this.target = null;
  }
}
