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
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainLVAdapter$ViewHolder2_ViewBinding<T extends MainLVAdapter.ViewHolder2> implements Unbinder {
  protected T target;

  @UiThread
  public MainLVAdapter$ViewHolder2_ViewBinding(T target, View source) {
    this.target = target;

    target.ivItem2Icon = Utils.findRequiredViewAsType(source, R.id.iv_item2_icon, "field 'ivItem2Icon'", XCRoundRectImageView.class);
    target.tvItem2Name = Utils.findRequiredViewAsType(source, R.id.tv_item2_name, "field 'tvItem2Name'", TextView.class);
    target.tvItem2Title = Utils.findRequiredViewAsType(source, R.id.tv_item2_title, "field 'tvItem2Title'", TextView.class);
    target.tvItem2Details = Utils.findRequiredViewAsType(source, R.id.tv_item2_details, "field 'tvItem2Details'", TextView.class);
    target.ivItme2Image = Utils.findRequiredViewAsType(source, R.id.iv_itme2_image, "field 'ivItme2Image'", ImageView.class);
    target.tvItem2ShareNum = Utils.findRequiredViewAsType(source, R.id.tv_item2_shareNum, "field 'tvItem2ShareNum'", TextView.class);
    target.tvItem2GoodNum = Utils.findRequiredViewAsType(source, R.id.tv_item2_goodNum, "field 'tvItem2GoodNum'", TextView.class);
    target.tvItem2DiscussNum = Utils.findRequiredViewAsType(source, R.id.tv_item2_discussNum, "field 'tvItem2DiscussNum'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivItem2Icon = null;
    target.tvItem2Name = null;
    target.tvItem2Title = null;
    target.tvItem2Details = null;
    target.ivItme2Image = null;
    target.tvItem2ShareNum = null;
    target.tvItem2GoodNum = null;
    target.tvItem2DiscussNum = null;

    this.target = null;
  }
}
