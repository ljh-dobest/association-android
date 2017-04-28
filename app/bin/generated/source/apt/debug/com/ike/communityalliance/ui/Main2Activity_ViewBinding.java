// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Main2Activity_ViewBinding<T extends Main2Activity> implements Unbinder {
  protected T target;

  private View view2131756119;

  private View view2131756120;

  @UiThread
  public Main2Activity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.main_vp = Utils.findRequiredViewAsType(source, R.id.main_vp, "field 'main_vp'", ViewPager.class);
    target.rl_main_header = Utils.findRequiredViewAsType(source, R.id.rl_main_header, "field 'rl_main_header'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_main_recommed, "field 'iv_main_recomend' and method 'onViewClicked'");
    target.iv_main_recomend = Utils.castView(view, R.id.iv_main_recommed, "field 'iv_main_recomend'", ImageView.class);
    view2131756119 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_main_chat_more, "field 'iv_main_chat_more' and method 'onViewClicked'");
    target.iv_main_chat_more = Utils.castView(view, R.id.iv_main_chat_more, "field 'iv_main_chat_more'", ImageView.class);
    view2131756120 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tv_main_title = Utils.findRequiredViewAsType(source, R.id.tv_main_title, "field 'tv_main_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tabLayout = null;
    target.main_vp = null;
    target.rl_main_header = null;
    target.iv_main_recomend = null;
    target.iv_main_chat_more = null;
    target.tv_main_title = null;

    view2131756119.setOnClickListener(null);
    view2131756119 = null;
    view2131756120.setOnClickListener(null);
    view2131756120 = null;

    this.target = null;
  }
}
