// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomePageLVAdapter$ViewHolder2_ViewBinding<T extends HomePageLVAdapter.ViewHolder2> implements Unbinder {
  protected T target;

  @UiThread
  public HomePageLVAdapter$ViewHolder2_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_main_platform_img = Utils.findRequiredViewAsType(source, R.id.iv_main_platform_img, "field 'iv_main_platform_img'", ImageView.class);
    target.tv_main_platform_status = Utils.findRequiredViewAsType(source, R.id.tv_main_platform_status, "field 'tv_main_platform_status'", TextView.class);
    target.tv_main_platform_title = Utils.findRequiredViewAsType(source, R.id.tv_main_platform_title, "field 'tv_main_platform_title'", TextView.class);
    target.tv_main_platform_address = Utils.findRequiredViewAsType(source, R.id.tv_main_platform_address, "field 'tv_main_platform_address'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_main_platform_img = null;
    target.tv_main_platform_status = null;
    target.tv_main_platform_title = null;
    target.tv_main_platform_address = null;

    this.target = null;
  }
}
