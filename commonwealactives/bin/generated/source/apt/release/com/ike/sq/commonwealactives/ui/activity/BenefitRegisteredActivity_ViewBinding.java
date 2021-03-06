// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BenefitRegisteredActivity_ViewBinding implements Unbinder {
  private BenefitRegisteredActivity target;

  private View view2131624098;

  @UiThread
  public BenefitRegisteredActivity_ViewBinding(BenefitRegisteredActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BenefitRegisteredActivity_ViewBinding(final BenefitRegisteredActivity target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lt_main_title_left, "field 'ltMainTitleLeft' and method 'leftClick'");
    target.ltMainTitleLeft = Utils.castView(view, R.id.lt_main_title_left, "field 'ltMainTitleLeft'", TextView.class);
    view2131624098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.leftClick();
      }
    });
    target.ltMainTitle = Utils.findRequiredViewAsType(source, R.id.lt_main_title, "field 'ltMainTitle'", TextView.class);
    target.ltMainTitleRight = Utils.findRequiredViewAsType(source, R.id.lt_main_title_right, "field 'ltMainTitleRight'", TextView.class);
    target.gvApply = Utils.findRequiredViewAsType(source, R.id.gv_apply, "field 'gvApply'", GridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BenefitRegisteredActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ltMainTitleLeft = null;
    target.ltMainTitle = null;
    target.ltMainTitleRight = null;
    target.gvApply = null;

    view2131624098.setOnClickListener(null);
    view2131624098 = null;
  }
}
