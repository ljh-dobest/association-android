// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonalInformationActivity_ViewBinding<T extends PersonalInformationActivity> implements Unbinder {
  protected T target;

  private View view2131755668;

  private View view2131755669;

  private View view2131755670;

  private View view2131755685;

  @UiThread
  public PersonalInformationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.et_personal_info_back, "field 'etPersonalInfoBack' and method 'onViewClicked'");
    target.etPersonalInfoBack = Utils.castView(view, R.id.et_personal_info_back, "field 'etPersonalInfoBack'", TextView.class);
    view2131755668 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_personal_info_save, "field 'etPersonalInfoSave' and method 'onViewClicked'");
    target.etPersonalInfoSave = Utils.castView(view, R.id.et_personal_info_save, "field 'etPersonalInfoSave'", TextView.class);
    view2131755669 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_personal_info_userIcon, "field 'ivPersonalInfoUserIcon' and method 'onViewClicked'");
    target.ivPersonalInfoUserIcon = Utils.castView(view, R.id.iv_personal_info_userIcon, "field 'ivPersonalInfoUserIcon'", CircleImageView.class);
    view2131755670 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.pbPersonalInfoCreditScore = Utils.findRequiredViewAsType(source, R.id.pb_personal_info_creditScore, "field 'pbPersonalInfoCreditScore'", ProgressBar.class);
    target.tvPersonalInfoCreditScore = Utils.findRequiredViewAsType(source, R.id.tv_personal_info_creditScore, "field 'tvPersonalInfoCreditScore'", TextView.class);
    target.pbPersonalInfoExperience = Utils.findRequiredViewAsType(source, R.id.pb_personal_info_experience, "field 'pbPersonalInfoExperience'", ProgressBar.class);
    target.tvPersonalInfoExperience = Utils.findRequiredViewAsType(source, R.id.tv_personal_info_experience, "field 'tvPersonalInfoExperience'", TextView.class);
    target.tvPersonalInfoContributionScore = Utils.findRequiredViewAsType(source, R.id.tv_personal_info_contributionScore, "field 'tvPersonalInfoContributionScore'", TextView.class);
    target.tv_personal_info_account = Utils.findRequiredViewAsType(source, R.id.tv_personal_info_account, "field 'tv_personal_info_account'", TextView.class);
    target.etPersonalInfoNickName = Utils.findRequiredViewAsType(source, R.id.et_personal_info_nickName, "field 'etPersonalInfoNickName'", EditText.class);
    target.etPersonalInfoMobile = Utils.findRequiredViewAsType(source, R.id.et_personal_info_mobile, "field 'etPersonalInfoMobile'", EditText.class);
    target.rbPersonalInfoMan = Utils.findRequiredViewAsType(source, R.id.rb_personal_info_man, "field 'rbPersonalInfoMan'", RadioButton.class);
    target.rbPersonalInfoWomen = Utils.findRequiredViewAsType(source, R.id.rb_personal_info_women, "field 'rbPersonalInfoWomen'", RadioButton.class);
    target.rgPersonalInfoSex = Utils.findRequiredViewAsType(source, R.id.rg_personal_info_sex, "field 'rgPersonalInfoSex'", RadioGroup.class);
    target.spPersonalInfoProvince = Utils.findRequiredViewAsType(source, R.id.sp_personal_info_province, "field 'spPersonalInfoProvince'", Spinner.class);
    target.spPersonalInfoCitys = Utils.findRequiredViewAsType(source, R.id.sp_personal_info_citys, "field 'spPersonalInfoCitys'", Spinner.class);
    target.spPersonalInfoCountys = Utils.findRequiredViewAsType(source, R.id.sp_personal_info_countys, "field 'spPersonalInfoCountys'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.et_personal_info_birthday, "field 'etPersonalInfoBirthday' and method 'onViewClicked'");
    target.etPersonalInfoBirthday = Utils.castView(view, R.id.et_personal_info_birthday, "field 'etPersonalInfoBirthday'", EditText.class);
    view2131755685 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPersonalInfoAge = Utils.findRequiredViewAsType(source, R.id.et_personal_info_age, "field 'etPersonalInfoAge'", EditText.class);
    target.etPersonalInfoRecomendUser = Utils.findRequiredViewAsType(source, R.id.et_personal_info_recomendUser, "field 'etPersonalInfoRecomendUser'", TextView.class);
    target.etPersonalInfoClaimUser = Utils.findRequiredViewAsType(source, R.id.et_personal_info_claimUser, "field 'etPersonalInfoClaimUser'", TextView.class);
    target.tvPersonalInfoMoreInfo = Utils.findRequiredViewAsType(source, R.id.tv_personal_info_moreInfo, "field 'tvPersonalInfoMoreInfo'", TextView.class);
    target.et_personal_info_email = Utils.findRequiredViewAsType(source, R.id.et_personal_info_email, "field 'et_personal_info_email'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.etPersonalInfoBack = null;
    target.etPersonalInfoSave = null;
    target.ivPersonalInfoUserIcon = null;
    target.pbPersonalInfoCreditScore = null;
    target.tvPersonalInfoCreditScore = null;
    target.pbPersonalInfoExperience = null;
    target.tvPersonalInfoExperience = null;
    target.tvPersonalInfoContributionScore = null;
    target.tv_personal_info_account = null;
    target.etPersonalInfoNickName = null;
    target.etPersonalInfoMobile = null;
    target.rbPersonalInfoMan = null;
    target.rbPersonalInfoWomen = null;
    target.rgPersonalInfoSex = null;
    target.spPersonalInfoProvince = null;
    target.spPersonalInfoCitys = null;
    target.spPersonalInfoCountys = null;
    target.etPersonalInfoBirthday = null;
    target.etPersonalInfoAge = null;
    target.etPersonalInfoRecomendUser = null;
    target.etPersonalInfoClaimUser = null;
    target.tvPersonalInfoMoreInfo = null;
    target.et_personal_info_email = null;

    view2131755668.setOnClickListener(null);
    view2131755668 = null;
    view2131755669.setOnClickListener(null);
    view2131755669 = null;
    view2131755670.setOnClickListener(null);
    view2131755670 = null;
    view2131755685.setOnClickListener(null);
    view2131755685 = null;

    this.target = null;
  }
}
