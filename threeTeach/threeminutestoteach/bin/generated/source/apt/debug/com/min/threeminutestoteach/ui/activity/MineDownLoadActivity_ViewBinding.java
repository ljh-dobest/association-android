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

public class MineDownLoadActivity_ViewBinding<T extends MineDownLoadActivity> implements Unbinder {
  protected T target;

  private View view2131493077;

  @UiThread
  public MineDownLoadActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_mine_download_back, "field 'llMineDownloadBack' and method 'onViewClicked'");
    target.llMineDownloadBack = Utils.castView(view, R.id.ll_mine_download_back, "field 'llMineDownloadBack'", LinearLayout.class);
    view2131493077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.rvMineDownload = Utils.findRequiredViewAsType(source, R.id.rv_mine_download, "field 'rvMineDownload'", SwipeMenuRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llMineDownloadBack = null;
    target.rvMineDownload = null;

    view2131493077.setOnClickListener(null);
    view2131493077 = null;

    this.target = null;
  }
}
