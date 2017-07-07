// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownLoadControlActivity_ViewBinding<T extends DownLoadControlActivity> implements Unbinder {
  protected T target;

  private View view2131493056;

  @UiThread
  public DownLoadControlActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_download_back, "field 'llDownloadBack' and method 'onViewClicked'");
    target.llDownloadBack = Utils.castView(view, R.id.ll_download_back, "field 'llDownloadBack'", LinearLayout.class);
    view2131493056 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvDownload = Utils.findRequiredViewAsType(source, R.id.rv_download, "field 'rvDownload'", SwipeMenuRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llDownloadBack = null;
    target.rvDownload = null;

    view2131493056.setOnClickListener(null);
    view2131493056 = null;

    this.target = null;
  }
}
