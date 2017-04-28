// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding<T extends MineFragment> implements Unbinder {
  protected T target;

  private View view2131756124;

  private View view2131756140;

  private View view2131756141;

  private View view2131756142;

  private View view2131756143;

  private View view2131756144;

  private View view2131756145;

  private View view2131756123;

  private View view2131756130;

  @UiThread
  public MineFragment_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_mine_card, "field 'ivMineCard' and method 'onViewClicked'");
    target.ivMineCard = Utils.castView(view, R.id.iv_mine_card, "field 'ivMineCard'", ImageView.class);
    view2131756124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivMineUserIcon = Utils.findRequiredViewAsType(source, R.id.iv_mine_userIcon, "field 'ivMineUserIcon'", XCRoundRectImageView.class);
    target.tvMineName = Utils.findRequiredViewAsType(source, R.id.tv_mine_name, "field 'tvMineName'", TextView.class);
    target.tvMineAge = Utils.findRequiredViewAsType(source, R.id.tv_mine_age, "field 'tvMineAge'", TextView.class);
    target.ivMineSex = Utils.findRequiredViewAsType(source, R.id.iv_mine_sex, "field 'ivMineSex'", ImageView.class);
    target.tvMineAccount = Utils.findRequiredViewAsType(source, R.id.tv_mine_account, "field 'tvMineAccount'", TextView.class);
    target.tvMineEmail = Utils.findRequiredViewAsType(source, R.id.tv_mine_email, "field 'tvMineEmail'", TextView.class);
    target.tvMinePhone = Utils.findRequiredViewAsType(source, R.id.tv_mine_phone, "field 'tvMinePhone'", TextView.class);
    target.tvMineBirthday = Utils.findRequiredViewAsType(source, R.id.tv_mine_birthday, "field 'tvMineBirthday'", TextView.class);
    target.tvMineAddress = Utils.findRequiredViewAsType(source, R.id.tv_mine_address, "field 'tvMineAddress'", TextView.class);
    target.tvMineRecommenerName = Utils.findRequiredViewAsType(source, R.id.tv_mine_recommenerName, "field 'tvMineRecommenerName'", TextView.class);
    target.tvMineClaimerName = Utils.findRequiredViewAsType(source, R.id.tv_mine_claimerName, "field 'tvMineClaimerName'", TextView.class);
    target.tvMineContributionNum = Utils.findRequiredViewAsType(source, R.id.tv_mine_contributionNum, "field 'tvMineContributionNum'", TextView.class);
    target.tv_mine_creditScore = Utils.findRequiredViewAsType(source, R.id.tv_mine_creditScore, "field 'tv_mine_creditScore'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_mine_recommend, "field 'llMineRecommend' and method 'onViewClicked'");
    target.llMineRecommend = Utils.castView(view, R.id.ll_mine_recommend, "field 'llMineRecommend'", LinearLayout.class);
    view2131756140 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine_wasRecomend, "field 'll_mine_wasRecomend' and method 'onViewClicked'");
    target.ll_mine_wasRecomend = Utils.castView(view, R.id.ll_mine_wasRecomend, "field 'll_mine_wasRecomend'", LinearLayout.class);
    view2131756141 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine_contacts, "field 'llMineContacts' and method 'onViewClicked'");
    target.llMineContacts = Utils.castView(view, R.id.ll_mine_contacts, "field 'llMineContacts'", LinearLayout.class);
    view2131756142 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine_wallet, "field 'll_mine_wallet' and method 'onViewClicked'");
    target.ll_mine_wallet = Utils.castView(view, R.id.ll_mine_wallet, "field 'll_mine_wallet'", LinearLayout.class);
    view2131756143 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine_feedback, "field 'llMineFeedback' and method 'onViewClicked'");
    target.llMineFeedback = Utils.castView(view, R.id.ll_mine_feedback, "field 'llMineFeedback'", LinearLayout.class);
    view2131756144 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_mine_setting, "field 'llMineSetting' and method 'onViewClicked'");
    target.llMineSetting = Utils.castView(view, R.id.ll_mine_setting, "field 'llMineSetting'", LinearLayout.class);
    view2131756145 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_mine_sign, "field 'tvMineSign' and method 'onViewClicked'");
    target.tvMineSign = Utils.castView(view, R.id.tv_mine_sign, "field 'tvMineSign'", TextView.class);
    view2131756123 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_mine_edit, "field 'iv_mine_edit' and method 'onViewClicked'");
    target.iv_mine_edit = Utils.castView(view, R.id.iv_mine_edit, "field 'iv_mine_edit'", ImageView.class);
    view2131756130 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivMineCard = null;
    target.ivMineUserIcon = null;
    target.tvMineName = null;
    target.tvMineAge = null;
    target.ivMineSex = null;
    target.tvMineAccount = null;
    target.tvMineEmail = null;
    target.tvMinePhone = null;
    target.tvMineBirthday = null;
    target.tvMineAddress = null;
    target.tvMineRecommenerName = null;
    target.tvMineClaimerName = null;
    target.tvMineContributionNum = null;
    target.tv_mine_creditScore = null;
    target.llMineRecommend = null;
    target.ll_mine_wasRecomend = null;
    target.llMineContacts = null;
    target.ll_mine_wallet = null;
    target.llMineFeedback = null;
    target.llMineSetting = null;
    target.tvMineSign = null;
    target.iv_mine_edit = null;

    view2131756124.setOnClickListener(null);
    view2131756124 = null;
    view2131756140.setOnClickListener(null);
    view2131756140 = null;
    view2131756141.setOnClickListener(null);
    view2131756141 = null;
    view2131756142.setOnClickListener(null);
    view2131756142 = null;
    view2131756143.setOnClickListener(null);
    view2131756143 = null;
    view2131756144.setOnClickListener(null);
    view2131756144 = null;
    view2131756145.setOnClickListener(null);
    view2131756145 = null;
    view2131756123.setOnClickListener(null);
    view2131756123 = null;
    view2131756130.setOnClickListener(null);
    view2131756130 = null;

    this.target = null;
  }
}
