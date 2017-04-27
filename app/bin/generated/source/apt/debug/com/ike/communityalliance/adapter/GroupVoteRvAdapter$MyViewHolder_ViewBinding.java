// Generated code from Butter Knife. Do not modify!
package com.ike.communityalliance.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.communityalliance.R;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GroupVoteRvAdapter$MyViewHolder_ViewBinding<T extends GroupVoteRvAdapter.MyViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public GroupVoteRvAdapter$MyViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.iv_groupVote_image = Utils.findRequiredViewAsType(source, R.id.iv_groupVote_image, "field 'iv_groupVote_image'", XCRoundRectImageView.class);
    target.tv_groupVote_title = Utils.findRequiredViewAsType(source, R.id.tv_groupVote_title, "field 'tv_groupVote_title'", TextView.class);
    target.rg_groupVote = Utils.findRequiredViewAsType(source, R.id.rg_groupVote, "field 'rg_groupVote'", RadioGroup.class);
    target.btn_groupVote_vote = Utils.findRequiredViewAsType(source, R.id.btn_groupVote_vote, "field 'btn_groupVote_vote'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.iv_groupVote_image = null;
    target.tv_groupVote_title = null;
    target.rg_groupVote = null;
    target.btn_groupVote_vote = null;

    this.target = null;
  }
}
