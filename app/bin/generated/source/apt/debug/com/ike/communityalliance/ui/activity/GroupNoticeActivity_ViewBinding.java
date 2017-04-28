// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupNoticeActivity_ViewBinding<T extends GroupNoticeActivity> implements Unbinder {
  protected T target;

  private View view2131756112;

  private View view2131756113;

  @UiThread
  public GroupNoticeActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756112 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_title_right, "field 'tvTitleRight' and method 'onClick'");
    target.tvTitleRight = Utils.castView(view, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    view2131756113 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etGroupNotice = Utils.findRequiredViewAsType(source, R.id.et_group_notice, "field 'etGroupNotice'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.etGroupNotice = null;

    view2131756112.setOnClickListener(null);
    view2131756112 = null;
    view2131756113.setOnClickListener(null);
    view2131756113 = null;

    this.target = null;
  }
}
