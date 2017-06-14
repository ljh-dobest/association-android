// Generated code from Butter Knife. Do not modify!
package com.ike.sq.commonwealactives.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ike.sq.commonwealactives.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SimpleAdapter$ProductAdapterViewHolder_ViewBinding implements Unbinder {
  private SimpleAdapter.ProductAdapterViewHolder target;

  @UiThread
  public SimpleAdapter$ProductAdapterViewHolder_ViewBinding(SimpleAdapter.ProductAdapterViewHolder target,
      View source) {
    this.target = target;

    target.llItem = Utils.findRequiredViewAsType(source, R.id.ll_item, "field 'llItem'", LinearLayout.class);
    target.ivUserPortraitUrl = Utils.findRequiredViewAsType(source, R.id.iv_user_portrait_url, "field 'ivUserPortraitUrl'", ImageView.class);
    target.tvNickname = Utils.findRequiredViewAsType(source, R.id.tv_nickname, "field 'tvNickname'", TextView.class);
    target.tvInitiator = Utils.findRequiredViewAsType(source, R.id.tv_initiator, "field 'tvInitiator'", TextView.class);
    target.ivActivesImage = Utils.findRequiredViewAsType(source, R.id.iv_actives_image, "field 'ivActivesImage'", ImageView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tv_status, "field 'tvStatus'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.ivShare = Utils.findRequiredViewAsType(source, R.id.iv_share, "field 'ivShare'", ImageView.class);
    target.tvShare = Utils.findRequiredViewAsType(source, R.id.tv_share, "field 'tvShare'", TextView.class);
    target.llShare = Utils.findRequiredViewAsType(source, R.id.ll_share, "field 'llShare'", LinearLayout.class);
    target.ivLikes = Utils.findRequiredViewAsType(source, R.id.iv_likes, "field 'ivLikes'", ImageView.class);
    target.tvLikes = Utils.findRequiredViewAsType(source, R.id.tv_likes, "field 'tvLikes'", TextView.class);
    target.llLikes = Utils.findRequiredViewAsType(source, R.id.ll_likes, "field 'llLikes'", LinearLayout.class);
    target.ivComment = Utils.findRequiredViewAsType(source, R.id.iv_comment, "field 'ivComment'", ImageView.class);
    target.tvComment = Utils.findRequiredViewAsType(source, R.id.tv_comment, "field 'tvComment'", TextView.class);
    target.llComment = Utils.findRequiredViewAsType(source, R.id.ll_comment, "field 'llComment'", LinearLayout.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tvAddress'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SimpleAdapter.ProductAdapterViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llItem = null;
    target.ivUserPortraitUrl = null;
    target.tvNickname = null;
    target.tvInitiator = null;
    target.ivActivesImage = null;
    target.tvStatus = null;
    target.tvTitle = null;
    target.tvContent = null;
    target.ivShare = null;
    target.tvShare = null;
    target.llShare = null;
    target.ivLikes = null;
    target.tvLikes = null;
    target.llLikes = null;
    target.ivComment = null;
    target.tvComment = null;
    target.llComment = null;
    target.tvAddress = null;
  }
}
