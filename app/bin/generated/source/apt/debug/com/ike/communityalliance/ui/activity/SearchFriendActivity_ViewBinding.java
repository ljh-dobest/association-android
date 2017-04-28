// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFriendActivity_ViewBinding<T extends SearchFriendActivity> implements Unbinder {
  protected T target;

  private View view2131756112;

  private View view2131756114;

  @UiThread
  public SearchFriendActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.etFriend = Utils.findRequiredViewAsType(source, R.id.et_friend, "field 'etFriend'", EditText.class);
    target.iv_newFriendsOrGroup_item_header = Utils.findRequiredViewAsType(source, R.id.iv_newFriendsOrGroup_item_header, "field 'iv_newFriendsOrGroup_item_header'", SelectableRoundedImageView.class);
    target.tv_newFriendsOrGroup_item_groupName = Utils.findRequiredViewAsType(source, R.id.tv_newFriendsOrGroup_item_groupName, "field 'tv_newFriendsOrGroup_item_groupName'", TextView.class);
    target.btn_newFriendsOrGroup_join = Utils.findRequiredViewAsType(source, R.id.btn_newFriendsOrGroup_join, "field 'btn_newFriendsOrGroup_join'", Button.class);
    target.rl_newfriendOrGroup = Utils.findRequiredViewAsType(source, R.id.rl_newfriendOrGroup, "field 'rl_newfriendOrGroup'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756112 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_title_right, "field 'ivTitleRight' and method 'onClick'");
    target.ivTitleRight = Utils.castView(view, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    view2131756114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etFriend = null;
    target.iv_newFriendsOrGroup_item_header = null;
    target.tv_newFriendsOrGroup_item_groupName = null;
    target.btn_newFriendsOrGroup_join = null;
    target.rl_newfriendOrGroup = null;
    target.ivTitleBack = null;
    target.tvTitle = null;
    target.ivTitleRight = null;

    view2131756112.setOnClickListener(null);
    view2131756112 = null;
    view2131756114.setOnClickListener(null);
    view2131756114 = null;

    this.target = null;
  }
}
