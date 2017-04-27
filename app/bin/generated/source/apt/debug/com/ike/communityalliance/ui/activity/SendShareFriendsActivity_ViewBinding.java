// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.DemoGridView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SendShareFriendsActivity_ViewBinding<T extends SendShareFriendsActivity> implements Unbinder {
  protected T target;

  private View view2131755768;

  private View view2131755770;

  @UiThread
  public SendShareFriendsActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.gv_sendshareFriend = Utils.findRequiredViewAsType(source, R.id.gv_sendshareFriend, "field 'gv_sendshareFriend'", DemoGridView.class);
    view = Utils.findRequiredView(source, R.id.ll_sendshareFriend_back, "field 'll_sendshareFriend_back' and method 'sendShareFriendsOnclick'");
    target.ll_sendshareFriend_back = Utils.castView(view, R.id.ll_sendshareFriend_back, "field 'll_sendshareFriend_back'", LinearLayout.class);
    view2131755768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendShareFriendsOnclick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sendshareFriend_public, "field 'tv_sendshareFriend_public' and method 'sendShareFriendsOnclick'");
    target.tv_sendshareFriend_public = Utils.castView(view, R.id.tv_sendshareFriend_public, "field 'tv_sendshareFriend_public'", TextView.class);
    view2131755770 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sendShareFriendsOnclick(p0);
      }
    });
    target.et_sendshareFriend_content = Utils.findRequiredViewAsType(source, R.id.et_sendshareFriend_content, "field 'et_sendshareFriend_content'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.gv_sendshareFriend = null;
    target.ll_sendshareFriend_back = null;
    target.tv_sendshareFriend_public = null;
    target.et_sendshareFriend_content = null;

    view2131755768.setOnClickListener(null);
    view2131755768 = null;
    view2131755770.setOnClickListener(null);
    view2131755770 = null;

    this.target = null;
  }
}
