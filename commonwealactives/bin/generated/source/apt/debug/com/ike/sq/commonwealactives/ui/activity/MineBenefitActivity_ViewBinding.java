// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.andview.refreshview.XRefreshView;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineBenefitActivity_ViewBinding implements Unbinder {
  private MineBenefitActivity target;

<<<<<<< HEAD
<<<<<<< HEAD
  private View view2131624099;

  private View view2131624248;

  private View view2131624097;
=======
=======
>>>>>>> ljh
  private View view2131624092;

  private View view2131624241;

  private View view2131624090;
<<<<<<< HEAD
>>>>>>> 23d16f3f07dab1d7ebb3306c7acfa1500b7a13d3
=======
>>>>>>> ljh

  @UiThread
  public MineBenefitActivity_ViewBinding(MineBenefitActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MineBenefitActivity_ViewBinding(final MineBenefitActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.lt_main_title_left, "field 'ltMainTitleLeft' and method 'onViewClicked'");
    target.ltMainTitleLeft = Utils.castView(view, R.id.lt_main_title_left, "field 'ltMainTitleLeft'", TextView.class);
<<<<<<< HEAD
<<<<<<< HEAD
    view2131624099 = view;
=======
    view2131624092 = view;
>>>>>>> 23d16f3f07dab1d7ebb3306c7acfa1500b7a13d3
=======
    view2131624092 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ltMainTitle = Utils.findRequiredViewAsType(source, R.id.lt_main_title, "field 'ltMainTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.lt_main_title_right, "field 'ltMainTitleRight' and method 'onViewClicked'");
    target.ltMainTitleRight = Utils.castView(view, R.id.lt_main_title_right, "field 'ltMainTitleRight'", TextView.class);
<<<<<<< HEAD
<<<<<<< HEAD
    view2131624248 = view;
=======
    view2131624241 = view;
>>>>>>> 23d16f3f07dab1d7ebb3306c7acfa1500b7a13d3
=======
    view2131624241 = view;
>>>>>>> ljh
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.recyclerViewTestRv = Utils.findRequiredViewAsType(source, R.id.recycler_view_test_rv, "field 'recyclerViewTestRv'", RecyclerView.class);
    target.xrefreshview = Utils.findRequiredViewAsType(source, R.id.xrefreshview, "field 'xrefreshview'", XRefreshView.class);
    view = Utils.findRequiredView(source, R.id.iv_top, "field 'ivTop' and method 'onViewClicked'");
    target.ivTop = Utils.castView(view, R.id.iv_top, "field 'ivTop'", ImageView.class);
<<<<<<< HEAD
<<<<<<< HEAD
    view2131624097 = view;
=======
    view2131624090 = view;
>>>>>>> 23d16f3f07dab1d7ebb3306c7acfa1500b7a13d3
=======
    view2131624090 = view;
>>>>>>> ljh
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
    MineBenefitActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ltMainTitleLeft = null;
    target.ltMainTitle = null;
    target.ltMainTitleRight = null;
    target.recyclerViewTestRv = null;
    target.xrefreshview = null;
    target.ivTop = null;

<<<<<<< HEAD
<<<<<<< HEAD
    view2131624099.setOnClickListener(null);
    view2131624099 = null;
    view2131624248.setOnClickListener(null);
    view2131624248 = null;
    view2131624097.setOnClickListener(null);
    view2131624097 = null;
=======
=======
>>>>>>> ljh
    view2131624092.setOnClickListener(null);
    view2131624092 = null;
    view2131624241.setOnClickListener(null);
    view2131624241 = null;
    view2131624090.setOnClickListener(null);
    view2131624090 = null;
<<<<<<< HEAD
>>>>>>> 23d16f3f07dab1d7ebb3306c7acfa1500b7a13d3
=======
>>>>>>> ljh
  }
}
