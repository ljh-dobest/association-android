// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.min.helpcenter.R;

import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import jp.wasabeef.richeditor.RichEditor;

public class AskActivity_ViewBinding<T extends AskActivity> implements Unbinder {
  protected T target;

  private View view2131558550;

  private View view2131558552;

  private View view2131558558;

  private View view2131558557;

  @UiThread
  public AskActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.activity_ask = Utils.findRequiredViewAsType(source, R.id.activity_ask, "field 'activity_ask'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.ll_ask_back, "field 'll_ask_back' and method 'onAskViewClick'");
    target.ll_ask_back = Utils.castView(view, R.id.ll_ask_back, "field 'll_ask_back'", LinearLayout.class);
    view2131558550 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAskViewClick(p0);
      }
    });
    target.ll_ask_insert = Utils.findRequiredViewAsType(source, R.id.ll_ask_insert, "field 'll_ask_insert'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_ask_release, "field 'tv_ask_release' and method 'onAskViewClick'");
    target.tv_ask_release = Utils.castView(view, R.id.tv_ask_release, "field 'tv_ask_release'", TextView.class);
    view2131558552 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAskViewClick(p0);
      }
    });
    target.et_ask_content = Utils.findRequiredViewAsType(source, R.id.et_ask_content, "field 'et_ask_content'", EditText.class);
    target.et_ask_explain = Utils.findRequiredViewAsType(source, R.id.et_ask_explain, "field 'et_ask_explain'", RichEditor.class);
    view = Utils.findRequiredView(source, R.id.iv_ask_gold, "field 'iv_ask_gold' and method 'onAskViewClick'");
    target.iv_ask_gold = Utils.castView(view, R.id.iv_ask_gold, "field 'iv_ask_gold'", ImageView.class);
    view2131558558 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAskViewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_ask_picture, "field 'iv_ask_picture' and method 'onAskViewClick'");
    target.iv_ask_picture = Utils.castView(view, R.id.iv_ask_picture, "field 'iv_ask_picture'", ImageView.class);
    view2131558557 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAskViewClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.activity_ask = null;
    target.ll_ask_back = null;
    target.ll_ask_insert = null;
    target.tv_ask_release = null;
    target.et_ask_content = null;
    target.et_ask_explain = null;
    target.iv_ask_gold = null;
    target.iv_ask_picture = null;

    view2131558550.setOnClickListener(null);
    view2131558550 = null;
    view2131558552.setOnClickListener(null);
    view2131558552 = null;
    view2131558558.setOnClickListener(null);
    view2131558558 = null;
    view2131558557.setOnClickListener(null);
    view2131558557 = null;

    this.target = null;
  }
}
