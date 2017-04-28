// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VoteDetailActivity_ViewBinding<T extends VoteDetailActivity> implements Unbinder {
  protected T target;

  private View view2131755865;

  private View view2131755877;

  @UiThread
  public VoteDetailActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.listView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'listView'", ListView.class);
    view = Utils.findRequiredView(source, R.id.ll_vote_detail_back, "field 'll_vote_detail_back' and method 'onClick'");
    target.ll_vote_detail_back = Utils.castView(view, R.id.ll_vote_detail_back, "field 'll_vote_detail_back'", LinearLayout.class);
    view2131755865 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.ll_vote_detail_joinUsers = Utils.findRequiredViewAsType(source, R.id.ll_vote_detail_joinUsers, "field 'll_vote_detail_joinUsers'", LinearLayout.class);
    target.iv_vote_detail_icon = Utils.findRequiredViewAsType(source, R.id.iv_vote_detail_icon, "field 'iv_vote_detail_icon'", ImageView.class);
    target.iv_vote_detail_userHeader = Utils.findRequiredViewAsType(source, R.id.iv_vote_detail_userHeader, "field 'iv_vote_detail_userHeader'", ImageView.class);
    target.tvSelect = Utils.findRequiredViewAsType(source, R.id.tv_select, "field 'tvSelect'", TextView.class);
    target.tv_vote_detail_name = Utils.findRequiredViewAsType(source, R.id.tv_vote_detail_name, "field 'tv_vote_detail_name'", TextView.class);
    target.tv_vote_detail_time = Utils.findRequiredViewAsType(source, R.id.tv_vote_detail_time, "field 'tv_vote_detail_time'", TextView.class);
    target.tv_vote_detail_status = Utils.findRequiredViewAsType(source, R.id.tv_vote_detail_status, "field 'tv_vote_detail_status'", TextView.class);
    target.tv_vote_detail_delete = Utils.findRequiredViewAsType(source, R.id.tv_vote_detail_delete, "field 'tv_vote_detail_delete'", TextView.class);
    target.tv_vote_detail_title = Utils.findRequiredViewAsType(source, R.id.tv_vote_detail_title, "field 'tv_vote_detail_title'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_vote, "field 'btnVote' and method 'onClick'");
    target.btnVote = Utils.castView(view, R.id.btn_vote, "field 'btnVote'", Button.class);
    view2131755877 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.activityVoteDetail = Utils.findRequiredViewAsType(source, R.id.activity_vote_detail, "field 'activityVoteDetail'", LinearLayout.class);
    target.rv_votedetail_joinusers = Utils.findRequiredViewAsType(source, R.id.rv_votedetail_joinusers, "field 'rv_votedetail_joinusers'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.listView = null;
    target.ll_vote_detail_back = null;
    target.ll_vote_detail_joinUsers = null;
    target.iv_vote_detail_icon = null;
    target.iv_vote_detail_userHeader = null;
    target.tvSelect = null;
    target.tv_vote_detail_name = null;
    target.tv_vote_detail_time = null;
    target.tv_vote_detail_status = null;
    target.tv_vote_detail_delete = null;
    target.tv_vote_detail_title = null;
    target.btnVote = null;
    target.activityVoteDetail = null;
    target.rv_votedetail_joinusers = null;

    view2131755865.setOnClickListener(null);
    view2131755865 = null;
    view2131755877.setOnClickListener(null);
    view2131755877 = null;

    this.target = null;
  }
}
