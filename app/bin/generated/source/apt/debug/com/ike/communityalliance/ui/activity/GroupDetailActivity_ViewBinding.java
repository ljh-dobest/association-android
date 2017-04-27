// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.DemoGridView;
import com.ike.communityalliance.wedget.SwitchButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupDetailActivity_ViewBinding<T extends GroupDetailActivity> implements Unbinder {
  protected T target;

  private View view2131756106;

  private View view2131755570;

  private View view2131755572;

  private View view2131755574;

  private View view2131755578;

  private View view2131755579;

  private View view2131755583;

  private View view2131755584;

  private View view2131755581;

  private View view2131755575;

  @UiThread
  public GroupDetailActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.mGridView = Utils.findRequiredViewAsType(source, R.id.gridview, "field 'mGridView'", DemoGridView.class);
    target.tvGroupMemberSize = Utils.findRequiredViewAsType(source, R.id.tv_group_member_size, "field 'tvGroupMemberSize'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_group_member_size_item, "field 'rlGroupMemberSizeItem' and method 'onClick'");
    target.rlGroupMemberSizeItem = Utils.castView(view, R.id.rl_group_member_size_item, "field 'rlGroupMemberSizeItem'", RelativeLayout.class);
    view2131755570 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvGroupName = Utils.findRequiredViewAsType(source, R.id.tv_group_name, "field 'tvGroupName'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_group_name, "field 'llGroupName' and method 'onClick'");
    target.llGroupName = Utils.castView(view, R.id.ll_group_name, "field 'llGroupName'", LinearLayout.class);
    view2131755572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_group_announcement, "field 'llGroupAnnouncement' and method 'onClick'");
    target.llGroupAnnouncement = Utils.castView(view, R.id.ll_group_announcement, "field 'llGroupAnnouncement'", LinearLayout.class);
    view2131755574 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sw_group_notfaction, "field 'swGroupNotfaction' and method 'onClick'");
    target.swGroupNotfaction = Utils.castView(view, R.id.sw_group_notfaction, "field 'swGroupNotfaction'", SwitchButton.class);
    view2131755578 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sw_group_top, "field 'swGroupTop' and method 'onClick'");
    target.swGroupTop = Utils.castView(view, R.id.sw_group_top, "field 'swGroupTop'", SwitchButton.class);
    view2131755579 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_group_quit, "field 'btnGroupQuit' and method 'onClick'");
    target.btnGroupQuit = Utils.castView(view, R.id.btn_group_quit, "field 'btnGroupQuit'", Button.class);
    view2131755583 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_group_dismiss, "field 'btnGroupDismiss' and method 'onClick'");
    target.btnGroupDismiss = Utils.castView(view, R.id.btn_group_dismiss, "field 'btnGroupDismiss'", Button.class);
    view2131755584 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_my_nickname, "field 'llMyNickname' and method 'onClick'");
    target.llMyNickname = Utils.castView(view, R.id.ll_my_nickname, "field 'llMyNickname'", LinearLayout.class);
    view2131755581 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvMyName = Utils.findRequiredViewAsType(source, R.id.tv_my_name, "field 'tvMyName'", TextView.class);
    target.tv_group_number = Utils.findRequiredViewAsType(source, R.id.tv_group_number, "field 'tv_group_number'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_group_setvicePrincipal, "field 'll_group_setvicePrincipal' and method 'onClick'");
    target.ll_group_setvicePrincipal = Utils.castView(view, R.id.ll_group_setvicePrincipal, "field 'll_group_setvicePrincipal'", LinearLayout.class);
    view2131755575 = view;
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

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.mGridView = null;
    target.tvGroupMemberSize = null;
    target.rlGroupMemberSizeItem = null;
    target.tvGroupName = null;
    target.llGroupName = null;
    target.llGroupAnnouncement = null;
    target.swGroupNotfaction = null;
    target.swGroupTop = null;
    target.btnGroupQuit = null;
    target.btnGroupDismiss = null;
    target.llMyNickname = null;
    target.tvMyName = null;
    target.tv_group_number = null;
    target.ll_group_setvicePrincipal = null;

    view2131756106.setOnClickListener(null);
    view2131756106 = null;
    view2131755570.setOnClickListener(null);
    view2131755570 = null;
    view2131755572.setOnClickListener(null);
    view2131755572 = null;
    view2131755574.setOnClickListener(null);
    view2131755574 = null;
    view2131755578.setOnClickListener(null);
    view2131755578 = null;
    view2131755579.setOnClickListener(null);
    view2131755579 = null;
    view2131755583.setOnClickListener(null);
    view2131755583 = null;
    view2131755584.setOnClickListener(null);
    view2131755584 = null;
    view2131755581.setOnClickListener(null);
    view2131755581 = null;
    view2131755575.setOnClickListener(null);
    view2131755575 = null;

    this.target = null;
  }
}
