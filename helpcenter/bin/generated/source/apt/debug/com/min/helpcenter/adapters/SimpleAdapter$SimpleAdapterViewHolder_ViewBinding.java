// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.helpcenter.R;
import com.min.helpcenter.views.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;
import jp.wasabeef.richeditor.RichEditor;

public class SimpleAdapter$SimpleAdapterViewHolder_ViewBinding<T extends SimpleAdapter.SimpleAdapterViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public SimpleAdapter$SimpleAdapterViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_helpCenter_item_icon = Utils.findRequiredViewAsType(source, R.id.iv_helpCenter_item_icon, "field 'iv_helpCenter_item_icon'", CircleImageView.class);
    target.tv_helpCenter_item_name = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_item_name, "field 'tv_helpCenter_item_name'", TextView.class);
    target.tv_helpCenter_item_title = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_item_title, "field 'tv_helpCenter_item_title'", TextView.class);
    target.tv_helpCenter_item_content = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_item_content, "field 'tv_helpCenter_item_content'", RichEditor.class);
    target.tv_helpCenter_responseNum = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_responseNum, "field 'tv_helpCenter_responseNum'", TextView.class);
    target.tv_helpCenter_item_goldNum = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_item_goldNum, "field 'tv_helpCenter_item_goldNum'", TextView.class);
    target.tv_helpCenter_item_time = Utils.findRequiredViewAsType(source, R.id.tv_helpCenter_item_time, "field 'tv_helpCenter_item_time'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_helpCenter_item_icon = null;
    target.tv_helpCenter_item_name = null;
    target.tv_helpCenter_item_title = null;
    target.tv_helpCenter_item_content = null;
    target.tv_helpCenter_responseNum = null;
    target.tv_helpCenter_item_goldNum = null;
    target.tv_helpCenter_item_time = null;

    this.target = null;
  }
}
