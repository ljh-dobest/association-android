// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownLoadPopupWindow_ViewBinding<T extends DownLoadPopupWindow> implements Unbinder {
  protected T target;

  private View view2131493242;

  private View view2131493246;

  private View view2131493247;

  private View view2131493249;

  @UiThread
  public DownLoadPopupWindow_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_pop_close, "field 'ivPopClose' and method 'onViewClicked'");
    target.ivPopClose = Utils.castView(view, R.id.iv_pop_close, "field 'ivPopClose'", ImageView.class);
    view2131493242 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvPopFileSize = Utils.findRequiredViewAsType(source, R.id.tv_pop_fileSize, "field 'tvPopFileSize'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_pop_title, "field 'tvPopTitle' and method 'onViewClicked'");
    target.tvPopTitle = Utils.castView(view, R.id.tv_pop_title, "field 'tvPopTitle'", TextView.class);
    view2131493246 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_pop_download, "field 'ivPopDownload' and method 'onViewClicked'");
    target.ivPopDownload = Utils.castView(view, R.id.iv_pop_download, "field 'ivPopDownload'", ImageView.class);
    view2131493247 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivPopCompleted = Utils.findRequiredViewAsType(source, R.id.iv_pop_completed, "field 'ivPopCompleted'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_pop_downloadControl, "field 'tvPopDownloadControl' and method 'onViewClicked'");
    target.tvPopDownloadControl = Utils.castView(view, R.id.tv_pop_downloadControl, "field 'tvPopDownloadControl'", TextView.class);
    view2131493249 = view;
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

    target.ivPopClose = null;
    target.tvPopFileSize = null;
    target.tvPopTitle = null;
    target.ivPopDownload = null;
    target.ivPopCompleted = null;
    target.tvPopDownloadControl = null;

    view2131493242.setOnClickListener(null);
    view2131493242 = null;
    view2131493246.setOnClickListener(null);
    view2131493246 = null;
    view2131493247.setOnClickListener(null);
    view2131493247 = null;
    view2131493249.setOnClickListener(null);
    view2131493249 = null;

    this.target = null;
  }
}
