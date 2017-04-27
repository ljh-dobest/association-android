// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.image.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserDetailActivity_ViewBinding<T extends UserDetailActivity> implements Unbinder {
  protected T target;

  private View view2131756106;

  private View view2131756107;

  private View view2131755811;

  private View view2131755812;

  private View view2131755813;

  private View view2131755819;

  private View view2131755820;

  @UiThread
  public UserDetailActivity_ViewBinding(final T target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_title_right, "field 'tvTitleRight' and method 'onClick'");
    target.tvTitleRight = Utils.castView(view, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    view2131756107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.civUserHead = Utils.findRequiredViewAsType(source, R.id.civ_user_head, "field 'civUserHead'", CircleImageView.class);
    target.tvUsername = Utils.findRequiredViewAsType(source, R.id.tv_username, "field 'tvUsername'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_call, "field 'llCall' and method 'onClick'");
    target.llCall = Utils.castView(view, R.id.ll_call, "field 'llCall'", LinearLayout.class);
    view2131755811 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_send_sms, "field 'llSendSms' and method 'onClick'");
    target.llSendSms = Utils.castView(view, R.id.ll_send_sms, "field 'llSendSms'", LinearLayout.class);
    view2131755812 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_send_email, "field 'llSendEmail' and method 'onClick'");
    target.llSendEmail = Utils.castView(view, R.id.ll_send_email, "field 'llSendEmail'", LinearLayout.class);
    view2131755813 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.textView1 = Utils.findRequiredViewAsType(source, R.id.textView1, "field 'textView1'", TextView.class);
    target.tvTelephone = Utils.findRequiredViewAsType(source, R.id.tv_telephone, "field 'tvTelephone'", TextView.class);
    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tv_email, "field 'tvEmail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_send_message, "field 'btnSendMessage' and method 'onClick'");
    target.btnSendMessage = Utils.castView(view, R.id.btn_send_message, "field 'btnSendMessage'", Button.class);
    view2131755819 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_delete_friend, "field 'btnDeleteFriend' and method 'onClick'");
    target.btnDeleteFriend = Utils.castView(view, R.id.btn_delete_friend, "field 'btnDeleteFriend'", Button.class);
    view2131755820 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.llEmail = Utils.findRequiredViewAsType(source, R.id.ll_email, "field 'llEmail'", LinearLayout.class);
    target.tvNickName = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.civUserHead = null;
    target.tvUsername = null;
    target.llCall = null;
    target.llSendSms = null;
    target.llSendEmail = null;
    target.textView1 = null;
    target.tvTelephone = null;
    target.tvEmail = null;
    target.btnSendMessage = null;
    target.btnDeleteFriend = null;
    target.llEmail = null;
    target.tvNickName = null;

    view2131756106.setOnClickListener(null);
    view2131756106 = null;
    view2131756107.setOnClickListener(null);
    view2131756107 = null;
    view2131755811.setOnClickListener(null);
    view2131755811 = null;
    view2131755812.setOnClickListener(null);
    view2131755812 = null;
    view2131755813.setOnClickListener(null);
    view2131755813 = null;
    view2131755819.setOnClickListener(null);
    view2131755819 = null;
    view2131755820.setOnClickListener(null);
    view2131755820 = null;

    this.target = null;
  }
}
