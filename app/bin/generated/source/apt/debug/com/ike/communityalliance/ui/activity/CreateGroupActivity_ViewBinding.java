// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateGroupActivity_ViewBinding<T extends CreateGroupActivity> implements Unbinder {
  protected T target;

  private View view2131756106;

  private View view2131755529;

  @UiThread
  public CreateGroupActivity_ViewBinding(final T target, View source) {
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
    target.tvTitleRight = Utils.findRequiredViewAsType(source, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    target.etGroupName = Utils.findRequiredViewAsType(source, R.id.et_group_name, "field 'etGroupName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_create_group, "field 'btnCreateGroup' and method 'onClick'");
    target.btnCreateGroup = Utils.castView(view, R.id.btn_create_group, "field 'btnCreateGroup'", Button.class);
    view2131755529 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitleRight = null;
    target.etGroupName = null;
    target.btnCreateGroup = null;
    target.tvTitle = null;

    view2131756106.setOnClickListener(null);
    view2131756106 = null;
    view2131755529.setOnClickListener(null);
    view2131755529 = null;

    this.target = null;
  }
}
