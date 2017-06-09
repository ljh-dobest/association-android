// Generated code from Butter Knife. Do not modify!
package com.min.threeminutestoteach.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.threeminutestoteach.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainLVAdapter$ViewHolder_ViewBinding<T extends MainLVAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MainLVAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.ivItem1Image = Utils.findRequiredViewAsType(source, R.id.iv_item1_image, "field 'ivItem1Image'", ImageView.class);
    target.tvItem1Title = Utils.findRequiredViewAsType(source, R.id.tv_item1_title, "field 'tvItem1Title'", TextView.class);
    target.tvItem1GoodNum = Utils.findRequiredViewAsType(source, R.id.tv_item1_goodNum, "field 'tvItem1GoodNum'", TextView.class);
    target.tvItem1DiscussNum = Utils.findRequiredViewAsType(source, R.id.tv_item1_discussNum, "field 'tvItem1DiscussNum'", TextView.class);
    target.tvItem1Time = Utils.findRequiredViewAsType(source, R.id.tv_item1_time, "field 'tvItem1Time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivItem1Image = null;
    target.tvItem1Title = null;
    target.tvItem1GoodNum = null;
    target.tvItem1DiscussNum = null;
    target.tvItem1Time = null;

    this.target = null;
  }
}
