// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.amap.api.maps.MapView;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AMAPLocationActivity_ViewBinding<T extends AMAPLocationActivity> implements Unbinder {
  protected T target;

  private View view2131755469;

  @UiThread
  public AMAPLocationActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.mapView = Utils.findRequiredViewAsType(source, R.id.map, "field 'mapView'", MapView.class);
    target.tv_location = Utils.findRequiredViewAsType(source, R.id.location, "field 'tv_location'", TextView.class);
    view = Utils.findRequiredView(source, R.id.myLocation, "field 'iv_enter' and method 'onClick'");
    target.iv_enter = Utils.castView(view, R.id.myLocation, "field 'iv_enter'", ImageView.class);
    view2131755469 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mapView = null;
    target.tv_location = null;
    target.iv_enter = null;

    view2131755469.setOnClickListener(null);
    view2131755469 = null;

    this.target = null;
  }
}
