// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.DemoGridView;
import com.ike.communityalliance.wedget.SwitchButton;
import com.ike.mylibrary.widget.ClearWriteEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddVoteActivity_ViewBinding<T extends AddVoteActivity> implements Unbinder {
  protected T target;

  private View view2131755446;

  private View view2131755448;

  private View view2131755456;

  private View view2131755457;

  private View view2131755454;

  @UiThread
  public AddVoteActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_group_vote_create_back, "field 'll_group_vote_create_back' and method 'onClick'");
    target.ll_group_vote_create_back = Utils.castView(view, R.id.ll_group_vote_create_back, "field 'll_group_vote_create_back'", LinearLayout.class);
    view2131755446 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_group_vote_create_public, "field 'tv_group_vote_create_public' and method 'onClick'");
    target.tv_group_vote_create_public = Utils.castView(view, R.id.tv_group_vote_create_public, "field 'tv_group_vote_create_public'", TextView.class);
    view2131755448 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etVoteTitle = Utils.findRequiredViewAsType(source, R.id.et_vote_title, "field 'etVoteTitle'", ClearWriteEditText.class);
    target.rvVote = Utils.findRequiredViewAsType(source, R.id.rv_vote, "field 'rvVote'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.sw_vote_multiselect, "field 'swVoteMultiselect' and method 'onClick'");
    target.swVoteMultiselect = Utils.castView(view, R.id.sw_vote_multiselect, "field 'swVoteMultiselect'", SwitchButton.class);
    view2131755456 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tvVotePeriod = Utils.findRequiredViewAsType(source, R.id.tv_vote_period, "field 'tvVotePeriod'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_vote_period, "field 'llVotePeriod' and method 'onClick'");
    target.llVotePeriod = Utils.castView(view, R.id.ll_vote_period, "field 'llVotePeriod'", LinearLayout.class);
    view2131755457 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.llAddNew = Utils.findRequiredViewAsType(source, R.id.ll_add_new, "field 'llAddNew'", LinearLayout.class);
    target.etVoteContent1 = Utils.findRequiredViewAsType(source, R.id.et_vote_content_1, "field 'etVoteContent1'", ClearWriteEditText.class);
    target.etVoteContent2 = Utils.findRequiredViewAsType(source, R.id.et_vote_content_2, "field 'etVoteContent2'", ClearWriteEditText.class);
    view = Utils.findRequiredView(source, R.id.rl_add_content, "field 'rlAddContent' and method 'onClick'");
    target.rlAddContent = Utils.castView(view, R.id.rl_add_content, "field 'rlAddContent'", RelativeLayout.class);
    view2131755454 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.etAddContent = Utils.findRequiredViewAsType(source, R.id.et_add_content, "field 'etAddContent'", EditText.class);
    target.gv_groupVote_create_images = Utils.findRequiredViewAsType(source, R.id.gv_groupVote_create_images, "field 'gv_groupVote_create_images'", DemoGridView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ll_group_vote_create_back = null;
    target.tv_group_vote_create_public = null;
    target.etVoteTitle = null;
    target.rvVote = null;
    target.swVoteMultiselect = null;
    target.tvVotePeriod = null;
    target.llVotePeriod = null;
    target.llAddNew = null;
    target.etVoteContent1 = null;
    target.etVoteContent2 = null;
    target.rlAddContent = null;
    target.etAddContent = null;
    target.gv_groupVote_create_images = null;

    view2131755446.setOnClickListener(null);
    view2131755446 = null;
    view2131755448.setOnClickListener(null);
    view2131755448 = null;
    view2131755456.setOnClickListener(null);
    view2131755456 = null;
    view2131755457.setOnClickListener(null);
    view2131755457 = null;
    view2131755454.setOnClickListener(null);
    view2131755454 = null;

    this.target = null;
  }
}
