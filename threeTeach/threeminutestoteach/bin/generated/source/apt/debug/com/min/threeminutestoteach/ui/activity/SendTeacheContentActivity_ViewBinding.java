// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.wang.avi.AVLoadingIndicatorView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SendTeacheContentActivity_ViewBinding<T extends SendTeacheContentActivity> implements Unbinder {
  protected T target;

  private View view2131493086;

  private View view2131493087;

  private View view2131493090;

  private View view2131493098;

  private View view2131493092;

  private View view2131493088;

  @UiThread
  public SendTeacheContentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_sendTeache_back, "field 'llSendTeacheBack' and method 'onViewClicked'");
    target.llSendTeacheBack = Utils.castView(view, R.id.ll_sendTeache_back, "field 'llSendTeacheBack'", LinearLayout.class);
    view2131493086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sendTeache_public, "field 'tvSendTeachePublic' and method 'onViewClicked'");
    target.tvSendTeachePublic = Utils.castView(view, R.id.tv_sendTeache_public, "field 'tvSendTeachePublic'", TextView.class);
    view2131493087 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sendTeache_bg, "field 'tvSendTeacheBg' and method 'onViewClicked'");
    target.tvSendTeacheBg = Utils.castView(view, R.id.tv_sendTeache_bg, "field 'tvSendTeacheBg'", TextView.class);
    view2131493090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivSendTeacheBg = Utils.findRequiredViewAsType(source, R.id.iv_sendTeache_bg, "field 'ivSendTeacheBg'", ImageView.class);
    target.etSendTeacheTitle = Utils.findRequiredViewAsType(source, R.id.et_sendTeache_title, "field 'etSendTeacheTitle'", EditText.class);
    target.etSendTeacheContent = Utils.findRequiredViewAsType(source, R.id.et_sendTeache_content, "field 'etSendTeacheContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_senTeache_see, "field 'tvSenTeacheSee' and method 'onViewClicked'");
    target.tvSenTeacheSee = Utils.castView(view, R.id.tv_senTeache_see, "field 'tvSenTeacheSee'", TextView.class);
    view2131493098 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llSendTeacheInsert = Utils.findRequiredViewAsType(source, R.id.ll_sendTeache_insert, "field 'llSendTeacheInsert'", LinearLayout.class);
    target.viewLoading = Utils.findRequiredViewAsType(source, R.id.view_loading, "field 'viewLoading'", AVLoadingIndicatorView.class);
    view = Utils.findRequiredView(source, R.id.rl_sendTeache_video, "field 'rlSendTeacheVideo' and method 'onViewClicked'");
    target.rlSendTeacheVideo = Utils.castView(view, R.id.rl_sendTeache_video, "field 'rlSendTeacheVideo'", RelativeLayout.class);
    view2131493092 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSendTeacheWhoSee = Utils.findRequiredViewAsType(source, R.id.tv_sendTeache_whoSee, "field 'tvSendTeacheWhoSee'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_sendTeache_icon, "field 'tvSendTeacheIcon' and method 'onViewClicked'");
    target.tvSendTeacheIcon = Utils.castView(view, R.id.tv_sendTeache_icon, "field 'tvSendTeacheIcon'", TextView.class);
    view2131493088 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivSendTeacheIcon = Utils.findRequiredViewAsType(source, R.id.iv_sendTeache_icon, "field 'ivSendTeacheIcon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.llSendTeacheBack = null;
    target.tvSendTeachePublic = null;
    target.tvSendTeacheBg = null;
    target.ivSendTeacheBg = null;
    target.etSendTeacheTitle = null;
    target.etSendTeacheContent = null;
    target.tvSenTeacheSee = null;
    target.llSendTeacheInsert = null;
    target.viewLoading = null;
    target.rlSendTeacheVideo = null;
    target.tvSendTeacheWhoSee = null;
    target.tvSendTeacheIcon = null;
    target.ivSendTeacheIcon = null;

    view2131493086.setOnClickListener(null);
    view2131493086 = null;
    view2131493087.setOnClickListener(null);
    view2131493087 = null;
    view2131493090.setOnClickListener(null);
    view2131493090 = null;
    view2131493098.setOnClickListener(null);
    view2131493098 = null;
    view2131493092.setOnClickListener(null);
    view2131493092 = null;
    view2131493088.setOnClickListener(null);
    view2131493088 = null;

    this.target = null;
  }
}
