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

public class GroupAddFlexibleActivity_ViewBinding<T extends GroupAddFlexibleActivity> implements Unbinder {
  protected T target;

  private View view2131756106;

  private View view2131756107;

  private View view2131755555;

  private View view2131755559;

  private View view2131755561;

  private View view2131755563;

  private View view2131755568;

  @UiThread
  public GroupAddFlexibleActivity_ViewBinding(final T target, View source) {
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
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_title_right, "field 'tvTitleRight' and method 'onClick'");
    target.tvTitleRight = Utils.castView(view, R.id.tv_title_right, "field 'tvTitleRight'", TextView.class);
    view2131756107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_group_activity_head, "field 'ivGroupActivityHead' and method 'onClick'");
    target.ivGroupActivityHead = Utils.castView(view, R.id.iv_group_activity_head, "field 'ivGroupActivityHead'", ImageView.class);
    view2131755555 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etActivityName = Utils.findRequiredViewAsType(source, R.id.et_activity_name, "field 'etActivityName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_activity_create_start_time, "field 'tvActivityStartTime' and method 'onClick'");
    target.tvActivityStartTime = Utils.castView(view, R.id.tv_activity_create_start_time, "field 'tvActivityStartTime'", TextView.class);
    view2131755559 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_activity_create_end_time, "field 'tvActivityEndTime' and method 'onClick'");
    target.tvActivityEndTime = Utils.castView(view, R.id.tv_activity_create_end_time, "field 'tvActivityEndTime'", TextView.class);
    view2131755561 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_activity_create_close_time, "field 'tv_activity_create_close_time' and method 'onClick'");
    target.tv_activity_create_close_time = Utils.castView(view, R.id.tv_activity_create_close_time, "field 'tv_activity_create_close_time'", TextView.class);
    view2131755563 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etActivityPlace = Utils.findRequiredViewAsType(source, R.id.et_activity_place, "field 'etActivityPlace'", EditText.class);
    target.tvSelectTime = Utils.findRequiredViewAsType(source, R.id.tv_select_time, "field 'tvSelectTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_activity_create_content, "method 'onClick'");
    view2131755568 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.tvTitleRight = null;
    target.ivGroupActivityHead = null;
    target.etActivityName = null;
    target.tvActivityStartTime = null;
    target.tvActivityEndTime = null;
    target.tv_activity_create_close_time = null;
    target.etActivityPlace = null;
    target.tvSelectTime = null;

    view2131756106.setOnClickListener(null);
    view2131756106 = null;
    view2131756107.setOnClickListener(null);
    view2131756107 = null;
    view2131755555.setOnClickListener(null);
    view2131755555 = null;
    view2131755559.setOnClickListener(null);
    view2131755559 = null;
    view2131755561.setOnClickListener(null);
    view2131755561 = null;
    view2131755563.setOnClickListener(null);
    view2131755563 = null;
    view2131755568.setOnClickListener(null);
    view2131755568 = null;

    this.target = null;
  }
}
