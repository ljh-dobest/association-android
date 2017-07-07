// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.views.MyGSYVideoPlayer;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VedioActivity_ViewBinding<T extends VedioActivity> implements Unbinder {
  protected T target;

  @UiThread
  public VedioActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.gsyVideoPlayer = Utils.findRequiredViewAsType(source, R.id.gsy_video_player, "field 'gsyVideoPlayer'", MyGSYVideoPlayer.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.gsyVideoPlayer = null;

    this.target = null;
  }
}
