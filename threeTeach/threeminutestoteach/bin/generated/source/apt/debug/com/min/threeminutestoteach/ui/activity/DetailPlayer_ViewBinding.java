// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.LandLayoutVideo;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailPlayer_ViewBinding<T extends DetailPlayer> implements Unbinder {
  protected T target;

  private View view2131493044;

  private View view2131493046;

  private View view2131493048;

  private View view2131493050;

  private View view2131493052;

  @UiThread
  public DetailPlayer_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.postDetailNestedScroll = Utils.findRequiredViewAsType(source, R.id.post_detail_nested_scroll, "field 'postDetailNestedScroll'", NestedScrollView.class);
    target.activityDetailPlayer = Utils.findRequiredViewAsType(source, R.id.activity_detail_player, "field 'activityDetailPlayer'", RelativeLayout.class);
    target.tvTeachContentTitle = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_title, "field 'tvTeachContentTitle'", TextView.class);
    target.ivTeachContentUserHeader = Utils.findRequiredViewAsType(source, R.id.iv_teachContent_userHeader, "field 'ivTeachContentUserHeader'", XCRoundRectImageView.class);
    target.tvTeachContentUserName = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_userName, "field 'tvTeachContentUserName'", TextView.class);
    target.tvTeachContentTime = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_time, "field 'tvTeachContentTime'", TextView.class);
    target.tvTeachContentDetails = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_details, "field 'tvTeachContentDetails'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_teachContent_share, "field 'ivTeachContentShare' and method 'onViewClicked'");
    target.ivTeachContentShare = Utils.castView(view, R.id.iv_teachContent_share, "field 'ivTeachContentShare'", ImageView.class);
    view2131493044 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTeachContentShareNum = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_shareNum, "field 'tvTeachContentShareNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_teachContent_good, "field 'ivTeachContentGood' and method 'onViewClicked'");
    target.ivTeachContentGood = Utils.castView(view, R.id.iv_teachContent_good, "field 'ivTeachContentGood'", ImageView.class);
    view2131493046 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTeachContentGoodNum = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_goodNum, "field 'tvTeachContentGoodNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_teachContent_discuss, "field 'ivTeachContentDiscuss' and method 'onViewClicked'");
    target.ivTeachContentDiscuss = Utils.castView(view, R.id.iv_teachContent_discuss, "field 'ivTeachContentDiscuss'", ImageView.class);
    view2131493048 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTeachContentDiscussNum = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_discussNum, "field 'tvTeachContentDiscussNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_teachContent_collect, "field 'ivTeachContentCollect' and method 'onViewClicked'");
    target.ivTeachContentCollect = Utils.castView(view, R.id.iv_teachContent_collect, "field 'ivTeachContentCollect'", ImageView.class);
    view2131493050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvTeachContentCollectNum = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_collectNum, "field 'tvTeachContentCollectNum'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_teachContent_download, "field 'ivTeachContentDownload' and method 'onViewClicked'");
    target.ivTeachContentDownload = Utils.castView(view, R.id.iv_teachContent_download, "field 'ivTeachContentDownload'", ImageView.class);
    view2131493052 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.grayLayout = Utils.findRequiredView(source, R.id.gray_layout, "field 'grayLayout'");
    target.detailPlayer = Utils.findRequiredViewAsType(source, R.id.detail_player, "field 'detailPlayer'", LandLayoutVideo.class);
    target.tvTeachContentDownloadNum = Utils.findRequiredViewAsType(source, R.id.tv_teachContent_downloadNum, "field 'tvTeachContentDownloadNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.postDetailNestedScroll = null;
    target.activityDetailPlayer = null;
    target.tvTeachContentTitle = null;
    target.ivTeachContentUserHeader = null;
    target.tvTeachContentUserName = null;
    target.tvTeachContentTime = null;
    target.tvTeachContentDetails = null;
    target.ivTeachContentShare = null;
    target.tvTeachContentShareNum = null;
    target.ivTeachContentGood = null;
    target.tvTeachContentGoodNum = null;
    target.ivTeachContentDiscuss = null;
    target.tvTeachContentDiscussNum = null;
    target.ivTeachContentCollect = null;
    target.tvTeachContentCollectNum = null;
    target.ivTeachContentDownload = null;
    target.grayLayout = null;
    target.detailPlayer = null;
    target.tvTeachContentDownloadNum = null;

    view2131493044.setOnClickListener(null);
    view2131493044 = null;
    view2131493046.setOnClickListener(null);
    view2131493046 = null;
    view2131493048.setOnClickListener(null);
    view2131493048 = null;
    view2131493050.setOnClickListener(null);
    view2131493050 = null;
    view2131493052.setOnClickListener(null);
    view2131493052 = null;

    this.target = null;
  }
}
