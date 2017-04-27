// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineCardActivity_ViewBinding<T extends MineCardActivity> implements Unbinder {
  protected T target;

  private View view2131755623;

  @UiThread
  public MineCardActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_mine_card_back, "field 'llMineCardBack' and method 'onViewClicked'");
    target.llMineCardBack = Utils.castView(view, R.id.ll_mine_card_back, "field 'llMineCardBack'", AutoLinearLayout.class);
    view2131755623 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.ivMineCardUserIcon = Utils.findRequiredViewAsType(source, R.id.iv_mine_card_userIcon, "field 'ivMineCardUserIcon'", XCRoundRectImageView.class);
    target.tvMineCardName = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_name, "field 'tvMineCardName'", TextView.class);
    target.tvMineCardAge = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_age, "field 'tvMineCardAge'", TextView.class);
    target.ivMineCardSex = Utils.findRequiredViewAsType(source, R.id.iv_mine_card_sex, "field 'ivMineCardSex'", ImageView.class);
    target.tvMineCardAccount = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_account, "field 'tvMineCardAccount'", TextView.class);
    target.tvMineCardEmail = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_email, "field 'tvMineCardEmail'", TextView.class);
    target.tvMineCardPhone = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_phone, "field 'tvMineCardPhone'", TextView.class);
    target.tvMineCardBirthday = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_birthday, "field 'tvMineCardBirthday'", TextView.class);
    target.tvMineCardAddress = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_address, "field 'tvMineCardAddress'", TextView.class);
    target.tvMineCardRecommenerName = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_recommenerName, "field 'tvMineCardRecommenerName'", TextView.class);
    target.tvMineCardClaimerName = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_claimerName, "field 'tvMineCardClaimerName'", TextView.class);
    target.tvMineCardContributionNum = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_contributionNum, "field 'tvMineCardContributionNum'", TextView.class);
    target.tvMineCardReputation = Utils.findRequiredViewAsType(source, R.id.tv_mine_card_reputation, "field 'tvMineCardReputation'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llMineCardBack = null;
    target.ivMineCardUserIcon = null;
    target.tvMineCardName = null;
    target.tvMineCardAge = null;
    target.ivMineCardSex = null;
    target.tvMineCardAccount = null;
    target.tvMineCardEmail = null;
    target.tvMineCardPhone = null;
    target.tvMineCardBirthday = null;
    target.tvMineCardAddress = null;
    target.tvMineCardRecommenerName = null;
    target.tvMineCardClaimerName = null;
    target.tvMineCardContributionNum = null;
    target.tvMineCardReputation = null;

    view2131755623.setOnClickListener(null);
    view2131755623 = null;

    this.target = null;
  }
}
