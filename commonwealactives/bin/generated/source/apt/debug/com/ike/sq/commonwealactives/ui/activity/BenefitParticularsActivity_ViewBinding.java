// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BenefitParticularsActivity_ViewBinding implements Unbinder {
  private BenefitParticularsActivity target;

<<<<<<< HEAD
  private View view2131624099;

  private View view2131624101;

  private View view2131624102;

  private View view2131624103;

  private View view2131624104;

  private View view2131624115;
=======
  private View view2131624092;

  private View view2131624094;

  private View view2131624095;

  private View view2131624096;

  private View view2131624097;

  private View view2131624108;
>>>>>>> ljh

  @UiThread
  public BenefitParticularsActivity_ViewBinding(BenefitParticularsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BenefitParticularsActivity_ViewBinding(final BenefitParticularsActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lt_main_title_left, "field 'ltMainTitleLeft' and method 'onViewClicked'");
    target.ltMainTitleLeft = Utils.castView(view, R.id.lt_main_title_left, "field 'ltMainTitleLeft'", TextView.class);
<<<<<<< HEAD
    view2131624099 = view;
=======
    view2131624092 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_share, "field 'ivShare' and method 'onViewClicked'");
    target.ivShare = Utils.castView(view, R.id.iv_share, "field 'ivShare'", ImageView.class);
<<<<<<< HEAD
    view2131624101 = view;
=======
    view2131624094 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_like, "field 'ivLike' and method 'onViewClicked'");
    target.ivLike = Utils.castView(view, R.id.iv_like, "field 'ivLike'", ImageView.class);
<<<<<<< HEAD
    view2131624102 = view;
=======
    view2131624095 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_comment, "field 'ivComment' and method 'onViewClicked'");
    target.ivComment = Utils.castView(view, R.id.iv_comment, "field 'ivComment'", ImageView.class);
<<<<<<< HEAD
    view2131624103 = view;
=======
    view2131624096 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_add_register, "field 'tvAddRegister' and method 'onViewClicked'");
    target.tvAddRegister = Utils.castView(view, R.id.tv_add_register, "field 'tvAddRegister'", TextView.class);
<<<<<<< HEAD
    view2131624104 = view;
=======
    view2131624097 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.linearLayout = Utils.findRequiredViewAsType(source, R.id.linearLayout, "field 'linearLayout'", LinearLayout.class);
    target.ivActivesImage = Utils.findRequiredViewAsType(source, R.id.iv_actives_image, "field 'ivActivesImage'", ImageView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvStartTime = Utils.findRequiredViewAsType(source, R.id.tv_start_time, "field 'tvStartTime'", TextView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
    target.tvJoinUsersNumber = Utils.findRequiredViewAsType(source, R.id.tv_join_users_number, "field 'tvJoinUsersNumber'", TextView.class);
    target.tvCostMoney = Utils.findRequiredViewAsType(source, R.id.tv_cost_money, "field 'tvCostMoney'", TextView.class);
    target.ivUserPortraitUrl = Utils.findRequiredViewAsType(source, R.id.iv_user_portrait_url, "field 'ivUserPortraitUrl'", ImageView.class);
    target.tvNickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickname'", TextView.class);
    target.tvInitiator = Utils.findRequiredViewAsType(source, R.id.tv_initiator, "field 'tvInitiator'", TextView.class);
    target.tvJoinUsers = Utils.findRequiredViewAsType(source, R.id.tv_join_users, "field 'tvJoinUsers'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_more, "field 'tvMore' and method 'onViewClicked'");
    target.tvMore = Utils.castView(view, R.id.tv_more, "field 'tvMore'", TextView.class);
<<<<<<< HEAD
    view2131624115 = view;
=======
    view2131624108 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.gvUser = Utils.findRequiredViewAsType(source, R.id.gv_user, "field 'gvUser'", GridView.class);
    target.wvContent = Utils.findRequiredViewAsType(source, R.id.wv_content, "field 'wvContent'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenefitParticularsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ltMainTitleLeft = null;
    target.ivShare = null;
    target.ivLike = null;
    target.ivComment = null;
    target.tvAddRegister = null;
    target.linearLayout = null;
    target.ivActivesImage = null;
    target.tvStatus = null;
    target.tvTitle = null;
    target.tvStartTime = null;
    target.tvAddress = null;
    target.tvJoinUsersNumber = null;
    target.tvCostMoney = null;
    target.ivUserPortraitUrl = null;
    target.tvNickname = null;
    target.tvInitiator = null;
    target.tvJoinUsers = null;
    target.tvMore = null;
    target.gvUser = null;
    target.wvContent = null;

<<<<<<< HEAD
    view2131624099.setOnClickListener(null);
    view2131624099 = null;
    view2131624101.setOnClickListener(null);
    view2131624101 = null;
    view2131624102.setOnClickListener(null);
    view2131624102 = null;
    view2131624103.setOnClickListener(null);
    view2131624103 = null;
    view2131624104.setOnClickListener(null);
    view2131624104 = null;
    view2131624115.setOnClickListener(null);
    view2131624115 = null;
=======
    view2131624092.setOnClickListener(null);
    view2131624092 = null;
    view2131624094.setOnClickListener(null);
    view2131624094 = null;
    view2131624095.setOnClickListener(null);
    view2131624095 = null;
    view2131624096.setOnClickListener(null);
    view2131624096 = null;
    view2131624097.setOnClickListener(null);
    view2131624097 = null;
    view2131624108.setOnClickListener(null);
    view2131624108 = null;
>>>>>>> ljh
  }
}
