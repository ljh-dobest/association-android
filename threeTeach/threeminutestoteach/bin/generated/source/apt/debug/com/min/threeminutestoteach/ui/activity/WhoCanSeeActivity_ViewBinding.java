// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WhoCanSeeActivity_ViewBinding<T extends WhoCanSeeActivity> implements Unbinder {
  protected T target;

  private View view2131493101;

  private View view2131493103;

  private View view2131493105;

  private View view2131493102;

  @UiThread
  public WhoCanSeeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_mine_card_back, "field 'llMineCardBack' and method 'onViewClicked'");
    target.llMineCardBack = Utils.castView(view, R.id.ll_mine_card_back, "field 'llMineCardBack'", LinearLayout.class);
    view2131493101 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivWhoSeeAll = Utils.findRequiredViewAsType(source, R.id.iv_whoSee_all, "field 'ivWhoSeeAll'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_whoSee_all, "field 'rlWhoSeeAll' and method 'onViewClicked'");
    target.rlWhoSeeAll = Utils.castView(view, R.id.rl_whoSee_all, "field 'rlWhoSeeAll'", RelativeLayout.class);
    view2131493103 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivWhoSeeVip = Utils.findRequiredViewAsType(source, R.id.iv_whoSee_vip, "field 'ivWhoSeeVip'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_whoSee_vip, "field 'rlWhoSeeVip' and method 'onViewClicked'");
    target.rlWhoSeeVip = Utils.castView(view, R.id.rl_whoSee_vip, "field 'rlWhoSeeVip'", RelativeLayout.class);
    view2131493105 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_whoSee_complie, "field 'tvWhoSeeComplie' and method 'onViewClicked'");
    target.tvWhoSeeComplie = Utils.castView(view, R.id.tv_whoSee_complie, "field 'tvWhoSeeComplie'", TextView.class);
    view2131493102 = view;
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

    target.llMineCardBack = null;
    target.ivWhoSeeAll = null;
    target.rlWhoSeeAll = null;
    target.ivWhoSeeVip = null;
    target.rlWhoSeeVip = null;
    target.tvWhoSeeComplie = null;

    view2131493101.setOnClickListener(null);
    view2131493101 = null;
    view2131493103.setOnClickListener(null);
    view2131493103 = null;
    view2131493105.setOnClickListener(null);
    view2131493105 = null;
    view2131493102.setOnClickListener(null);
    view2131493102 = null;

    this.target = null;
  }
}
