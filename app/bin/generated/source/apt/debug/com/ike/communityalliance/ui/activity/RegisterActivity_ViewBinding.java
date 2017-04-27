// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.mylibrary.widget.ClearWriteEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegisterActivity_ViewBinding<T extends RegisterActivity> implements Unbinder {
  protected T target;

  @UiThread
  public RegisterActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.textView = Utils.findRequiredViewAsType(source, R.id.textView, "field 'textView'", TextView.class);
    target.etNickname = Utils.findRequiredViewAsType(source, R.id.et_nickname, "field 'etNickname'", ClearWriteEditText.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.et_phone, "field 'etPhone'", ClearWriteEditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.et_code, "field 'etCode'", ClearWriteEditText.class);
    target.btnGetCord = Utils.findRequiredViewAsType(source, R.id.btn_get_cord, "field 'btnGetCord'", Button.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tvMessage'", TextView.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", ClearWriteEditText.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btn_register, "field 'btnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textView = null;
    target.etNickname = null;
    target.etPhone = null;
    target.etCode = null;
    target.btnGetCord = null;
    target.tvMessage = null;
    target.etPassword = null;
    target.btnRegister = null;

    this.target = null;
  }
}
