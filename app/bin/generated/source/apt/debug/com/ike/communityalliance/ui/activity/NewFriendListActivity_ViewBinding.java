// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewFriendListActivity_ViewBinding<T extends NewFriendListActivity> implements Unbinder {
  protected T target;

  private View view2131756112;

  private View view2131756114;

  @UiThread
  public NewFriendListActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_title_back, "field 'ivTitleBack' and method 'onClick'");
    target.ivTitleBack = Utils.castView(view, R.id.iv_title_back, "field 'ivTitleBack'", ImageView.class);
    view2131756112 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_title_right, "field 'ivTitleRight' and method 'onClick'");
    target.ivTitleRight = Utils.castView(view, R.id.iv_title_right, "field 'ivTitleRight'", ImageView.class);
    view2131756114 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.isData = Utils.findRequiredViewAsType(source, R.id.isData, "field 'isData'", TextView.class);
    target.mListView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'mListView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivTitleBack = null;
    target.tvTitle = null;
    target.ivTitleRight = null;
    target.isData = null;
    target.mListView = null;

    view2131756112.setOnClickListener(null);
    view2131756112 = null;
    view2131756114.setOnClickListener(null);
    view2131756114 = null;

    this.target = null;
  }
}
