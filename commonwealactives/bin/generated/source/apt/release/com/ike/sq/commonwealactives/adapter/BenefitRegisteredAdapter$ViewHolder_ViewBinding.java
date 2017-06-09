// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BenefitRegisteredAdapter$ViewHolder_ViewBinding implements Unbinder {
  private BenefitRegisteredAdapter.ViewHolder target;

  @UiThread
  public BenefitRegisteredAdapter$ViewHolder_ViewBinding(BenefitRegisteredAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.ivUserPortraitUrl = Utils.findRequiredViewAsType(source, R.id.iv_userPortraitUrl, "field 'ivUserPortraitUrl'", ImageView.class);
    target.tvNickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickname'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenefitRegisteredAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivUserPortraitUrl = null;
    target.tvNickname = null;
  }
}
