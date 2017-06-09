// Generated code from Butter Knife. Do not modify!
package com.min.helpcenter.ui.activitys;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.min.helpcenter.R;
import com.min.helpcenter.views.CircleImageView;
import com.zhy.autolayout.AutoLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;
import jp.wasabeef.richeditor.RichEditor;

public class QuestionActivity$HeaderView_ViewBinding<T extends QuestionActivity.HeaderView> implements Unbinder {
  protected T target;

  @UiThread
  public QuestionActivity$HeaderView_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_question_icon = Utils.findRequiredViewAsType(source, R.id.iv_question_icon, "field 'iv_question_icon'", ImageView.class);
    target.tv_question_name = Utils.findRequiredViewAsType(source, R.id.tv_question_name, "field 'tv_question_name'", TextView.class);
    target.tv_question_time = Utils.findRequiredViewAsType(source, R.id.tv_question_time, "field 'tv_question_time'", TextView.class);
    target.tv_question_title = Utils.findRequiredViewAsType(source, R.id.tv_question_title, "field 'tv_question_title'", TextView.class);
    target.tv_question_content = Utils.findRequiredViewAsType(source, R.id.tv_question_content, "field 'tv_question_content'", RichEditor.class);
    target.tv_question_responseNum = Utils.findRequiredViewAsType(source, R.id.tv_question_responseNum, "field 'tv_question_responseNum'", TextView.class);
    target.tv_question_goldNum = Utils.findRequiredViewAsType(source, R.id.tv_question_goldNum, "field 'tv_question_goldNum'", TextView.class);
    target.ivQuestionGoodItemIcon = Utils.findRequiredViewAsType(source, R.id.iv_question_good_item_icon, "field 'ivQuestionGoodItemIcon'", CircleImageView.class);
    target.tvQuestionGoodItemName = Utils.findRequiredViewAsType(source, R.id.tv_question_good_item_name, "field 'tvQuestionGoodItemName'", TextView.class);
    target.tvQuestionGoodItemTime = Utils.findRequiredViewAsType(source, R.id.tv_question_good_item_time, "field 'tvQuestionGoodItemTime'", TextView.class);
    target.tvQuestionGoodItemContent = Utils.findRequiredViewAsType(source, R.id.tv_question_good_item_content, "field 'tvQuestionGoodItemContent'", TextView.class);
    target.ivQuestionGoodItemGood = Utils.findRequiredViewAsType(source, R.id.iv_question_good_item_good, "field 'ivQuestionGoodItemGood'", ImageView.class);
    target.tvQuestionGoodItemGoodNum = Utils.findRequiredViewAsType(source, R.id.tv_question_good_item_goodNum, "field 'tvQuestionGoodItemGoodNum'", TextView.class);
    target.ivQuestionGoodItemDiscu = Utils.findRequiredViewAsType(source, R.id.iv_question_good_item_discu, "field 'ivQuestionGoodItemDiscu'", ImageView.class);
    target.tvQuestionGoodItemDiscuNum = Utils.findRequiredViewAsType(source, R.id.tv_question_good_item_discuNum, "field 'tvQuestionGoodItemDiscuNum'", TextView.class);
    target.llGoodAnswer = Utils.findRequiredViewAsType(source, R.id.ll_good_answer, "field 'llGoodAnswer'", AutoLinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_question_icon = null;
    target.tv_question_name = null;
    target.tv_question_time = null;
    target.tv_question_title = null;
    target.tv_question_content = null;
    target.tv_question_responseNum = null;
    target.tv_question_goldNum = null;
    target.ivQuestionGoodItemIcon = null;
    target.tvQuestionGoodItemName = null;
    target.tvQuestionGoodItemTime = null;
    target.tvQuestionGoodItemContent = null;
    target.ivQuestionGoodItemGood = null;
    target.tvQuestionGoodItemGoodNum = null;
    target.ivQuestionGoodItemDiscu = null;
    target.tvQuestionGoodItemDiscuNum = null;
    target.llGoodAnswer = null;

    this.target = null;
  }
}
