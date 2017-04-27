// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.DemoGridView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddFlexibleContentActivity_ViewBinding<T extends AddFlexibleContentActivity> implements Unbinder {
  protected T target;

  private View view2131755440;

  private View view2131755442;

  @UiThread
  public AddFlexibleContentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_activity_content_back, "field 'll_activity_content_back' and method 'activityContentOnclick'");
    target.ll_activity_content_back = Utils.castView(view, R.id.ll_activity_content_back, "field 'll_activity_content_back'", LinearLayout.class);
    view2131755440 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.activityContentOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_activity_content_complie, "field 'tv_activity_content_complie' and method 'activityContentOnclick'");
    target.tv_activity_content_complie = Utils.castView(view, R.id.tv_activity_content_complie, "field 'tv_activity_content_complie'", TextView.class);
    view2131755442 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.activityContentOnclick(p0);
      }
    });
    target.et_activity_create_content = Utils.findRequiredViewAsType(source, R.id.et_activity_create_content, "field 'et_activity_create_content'", EditText.class);
    target.gv_activity_create_images = Utils.findRequiredViewAsType(source, R.id.gv_activity_create_images, "field 'gv_activity_create_images'", DemoGridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_activity_content_back = null;
    target.tv_activity_content_complie = null;
    target.et_activity_create_content = null;
    target.gv_activity_create_images = null;

    view2131755440.setOnClickListener(null);
    view2131755440 = null;
    view2131755442.setOnClickListener(null);
    view2131755442 = null;

    this.target = null;
  }
}
