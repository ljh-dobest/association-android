// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DownloadListAdapter$ViewHolder_ViewBinding<T extends DownloadListAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public DownloadListAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.tvDownloadTitle = Utils.findRequiredViewAsType(source, R.id.tv_download_title, "field 'tvDownloadTitle'", TextView.class);
    target.tvDownloadStatus = Utils.findRequiredViewAsType(source, R.id.tv_download_status, "field 'tvDownloadStatus'", TextView.class);
    target.tvDownloadCurprogess = Utils.findRequiredViewAsType(source, R.id.tv_download_curprogess, "field 'tvDownloadCurprogess'", TextView.class);
    target.tvDownloadTotal = Utils.findRequiredViewAsType(source, R.id.tv_download_total, "field 'tvDownloadTotal'", TextView.class);
    target.pbDownload = Utils.findRequiredViewAsType(source, R.id.pb_download, "field 'pbDownload'", ProgressBar.class);
    target.ivDownloadStart = Utils.findRequiredViewAsType(source, R.id.iv_download_start, "field 'ivDownloadStart'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tvDownloadTitle = null;
    target.tvDownloadStatus = null;
    target.tvDownloadCurprogess = null;
    target.tvDownloadTotal = null;
    target.pbDownload = null;
    target.ivDownloadStart = null;

    this.target = null;
  }
}
