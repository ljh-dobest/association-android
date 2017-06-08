package com.ike.sq.commonwealactives.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.sq.commonwealactives.R;
import com.ike.sq.commonwealactives.base.adpater.BaseRecyclerViewAdapter;
import com.ike.sq.commonwealactives.bean.CommentsBean;
import com.ike.sq.commonwealactives.bean.MessageBean;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息列表item
 * <p>
 * Created by T-BayMax on 2017/3/16.
 */

public class MessageListAdapter extends BaseRecyclerViewAdapter<MessageListAdapter.ShareCommentListAdapterViewHolder, MessageBean> {
    private List<CommentsBean> list;
    private Context context;
    private int position;

    public MessageListAdapter(List<CommentsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ShareCommentListAdapterViewHolder holder, int position, boolean isItem) {
        CommentsBean bean = list.get(position);

        holder.itemView.setTag(bean);
        Picasso.with(context).load(HttpUtils.IMAGE_RUL + bean.getUserPortraitUrl())
                .into(holder.ivShareIcon);
        holder.tvShareUserName.setText(bean.getNickname());
        // holder.tvOperation.setText(bean.getContent());
        holder.tvShareTitle.setText(bean.getContent());
        holder.tvCommentTime.setText(bean.getCommentTime());

    }

    @Override
    public int getAdapterItemViewType(int position) {
        this.position = position;
        return position;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public ShareCommentListAdapterViewHolder getViewHolder(View view) {
        return new ShareCommentListAdapterViewHolder(view, false);
    }

    public void setData(List<CommentsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ShareCommentListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_list_itme_message, parent, false);

        ShareCommentListAdapterViewHolder vh = new ShareCommentListAdapterViewHolder(v, isItem);
        return vh;
    }

    public void insert(CommentsBean person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class ShareCommentListAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_share_icon)
        ImageView ivShareIcon;
        @BindView(R.id.tv_share_user_Name)
        TextView tvShareUserName;
        @BindView(R.id.tv_operation)
        TextView tvOperation;
        @BindView(R.id.tv_share_title)
        TextView tvShareTitle;
        @BindView(R.id.tv_comment_time)
        TextView tvCommentTime;

        public ShareCommentListAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(MessageListAdapter.this);

                AutoUtils.autoSize(itemView, AutoAttr.BASE_HEIGHT);

            }
        }
    }

    public CommentsBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}
