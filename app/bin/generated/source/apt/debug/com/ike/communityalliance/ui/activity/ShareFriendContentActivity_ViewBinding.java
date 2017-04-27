// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ShareFriendContentActivity_ViewBinding<T extends ShareFriendContentActivity> implements Unbinder {
  protected T target;

  private View view2131755779;

  private View view2131755784;

  @UiThread
  public ShareFriendContentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_sendshareFriendContent_back, "field 'll_sendshareFriendContent_back' and method 'ShareFriendsContentViewClcik'");
    target.ll_sendshareFriendContent_back = Utils.castView(view, R.id.ll_sendshareFriendContent_back, "field 'll_sendshareFriendContent_back'", LinearLayout.class);
    view2131755779 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ShareFriendsContentViewClcik(p0);
      }
    });
    target.iv_shareFriendContent_icon = Utils.findRequiredViewAsType(source, R.id.iv_shareFriendContent_icon, "field 'iv_shareFriendContent_icon'", XCRoundRectImageView.class);
    target.tv_shareFriendContent_name = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_name, "field 'tv_shareFriendContent_name'", TextView.class);
    target.tv_shareFriendContent_time = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_time, "field 'tv_shareFriendContent_time'", TextView.class);
    target.tv_shareFriendContent_title = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_title, "field 'tv_shareFriendContent_title'", TextView.class);
    target.rv_shareFriendContent_images = Utils.findRequiredViewAsType(source, R.id.rv_shareFriendContent_images, "field 'rv_shareFriendContent_images'", RecyclerView.class);
    target.tv_shareFriendContent_goodNum = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_goodNum, "field 'tv_shareFriendContent_goodNum'", TextView.class);
    target.tv_shareFriendContent_discuNum = Utils.findRequiredViewAsType(source, R.id.tv_shareFriendContent_discuNum, "field 'tv_shareFriendContent_discuNum'", TextView.class);
    target.iv_shareFriendContent_good = Utils.findRequiredViewAsType(source, R.id.iv_shareFriendContent_good, "field 'iv_shareFriendContent_good'", ImageView.class);
    target.iv_shareFriendContent_discu = Utils.findRequiredViewAsType(source, R.id.iv_shareFriendContent_discu, "field 'iv_shareFriendContent_discu'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_shareFriendContent_todicu, "field 'iv_shareFriendContent_todicu' and method 'ShareFriendsContentViewClcik'");
    target.iv_shareFriendContent_todicu = Utils.castView(view, R.id.iv_shareFriendContent_todicu, "field 'iv_shareFriendContent_todicu'", ImageView.class);
    view2131755784 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.ShareFriendsContentViewClcik(p0);
      }
    });
    target.rv_shareFriendContent = Utils.findRequiredViewAsType(source, R.id.rv_shareFriendContent, "field 'rv_shareFriendContent'", RecyclerView.class);
    target.rl_shareFriendContent_main = Utils.findRequiredViewAsType(source, R.id.rl_shareFriendContent_main, "field 'rl_shareFriendContent_main'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_sendshareFriendContent_back = null;
    target.iv_shareFriendContent_icon = null;
    target.tv_shareFriendContent_name = null;
    target.tv_shareFriendContent_time = null;
    target.tv_shareFriendContent_title = null;
    target.rv_shareFriendContent_images = null;
    target.tv_shareFriendContent_goodNum = null;
    target.tv_shareFriendContent_discuNum = null;
    target.iv_shareFriendContent_good = null;
    target.iv_shareFriendContent_discu = null;
    target.iv_shareFriendContent_todicu = null;
    target.rv_shareFriendContent = null;
    target.rl_shareFriendContent_main = null;

    view2131755779.setOnClickListener(null);
    view2131755779 = null;
    view2131755784.setOnClickListener(null);
    view2131755784 = null;

    this.target = null;
  }
}
