// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SendTeacheContentActivity_ViewBinding<T extends SendTeacheContentActivity> implements Unbinder {
  protected T target;

  private View view2131558561;

  private View view2131558562;

  private View view2131558567;

  private View view2131558571;

  private View view2131558572;

  private View view2131558568;

  @UiThread
  public SendTeacheContentActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.llSendTeacheBack = Utils.findRequiredViewAsType(source, R.id.ll_sendTeache_back, "field 'llSendTeacheBack'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_sendTeache_public, "field 'tvSendTeachePublic' and method 'onViewClicked'");
    target.tvSendTeachePublic = Utils.castView(view, R.id.tv_sendTeache_public, "field 'tvSendTeachePublic'", TextView.class);
    view2131558561 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSendTeacheBg = Utils.findRequiredViewAsType(source, R.id.tv_sendTeache_bg, "field 'tvSendTeacheBg'", TextView.class);
    target.ivSendTeacheBg = Utils.findRequiredViewAsType(source, R.id.iv_sendTeache_bg, "field 'ivSendTeacheBg'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_sendTeache_bg, "field 'rlSendTeacheBg' and method 'onViewClicked'");
    target.rlSendTeacheBg = Utils.castView(view, R.id.rl_sendTeache_bg, "field 'rlSendTeacheBg'", RelativeLayout.class);
    view2131558562 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etSendTeacheContent = Utils.findRequiredViewAsType(source, R.id.et_sendTeache_content, "field 'etSendTeacheContent'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_selected_vedio, "field 'btnSelectedVedio' and method 'onViewClicked'");
    target.btnSelectedVedio = Utils.castView(view, R.id.btn_selected_vedio, "field 'btnSelectedVedio'", Button.class);
    view2131558567 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_sendTeache_camera, "field 'ivSendTeacheCamera' and method 'onViewClicked'");
    target.ivSendTeacheCamera = Utils.castView(view, R.id.iv_sendTeache_camera, "field 'ivSendTeacheCamera'", ImageView.class);
    view2131558571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_sendTeache_picture, "field 'ivSendTeachePicture' and method 'onViewClicked'");
    target.ivSendTeachePicture = Utils.castView(view, R.id.iv_sendTeache_picture, "field 'ivSendTeachePicture'", ImageView.class);
    view2131558572 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llSendTeacheInsert = Utils.findRequiredViewAsType(source, R.id.ll_sendTeache_insert, "field 'llSendTeacheInsert'", LinearLayout.class);
    target.activitySend = Utils.findRequiredViewAsType(source, R.id.activity_send, "field 'activitySend'", ScrollView.class);
    target.etSendTeacheTitle = Utils.findRequiredViewAsType(source, R.id.et_sendTeache_title, "field 'etSendTeacheTitle'", EditText.class);
    target.ivSendTeacheVedio = Utils.findRequiredViewAsType(source, R.id.iv_sendTeache_vedio, "field 'ivSendTeacheVedio'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.fl_sendTeache_vedio, "field 'flSendTeacheVedio' and method 'onViewClicked'");
    target.flSendTeacheVedio = Utils.castView(view, R.id.fl_sendTeache_vedio, "field 'flSendTeacheVedio'", FrameLayout.class);
    view2131558568 = view;
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

    target.llSendTeacheBack = null;
    target.tvSendTeachePublic = null;
    target.tvSendTeacheBg = null;
    target.ivSendTeacheBg = null;
    target.rlSendTeacheBg = null;
    target.etSendTeacheContent = null;
    target.btnSelectedVedio = null;
    target.ivSendTeacheCamera = null;
    target.ivSendTeachePicture = null;
    target.llSendTeacheInsert = null;
    target.activitySend = null;
    target.etSendTeacheTitle = null;
    target.ivSendTeacheVedio = null;
    target.flSendTeacheVedio = null;

    view2131558561.setOnClickListener(null);
    view2131558561 = null;
    view2131558562.setOnClickListener(null);
    view2131558562 = null;
    view2131558567.setOnClickListener(null);
    view2131558567 = null;
    view2131558571.setOnClickListener(null);
    view2131558571 = null;
    view2131558572.setOnClickListener(null);
    view2131558572 = null;
    view2131558568.setOnClickListener(null);
    view2131558568 = null;

    this.target = null;
  }
}
