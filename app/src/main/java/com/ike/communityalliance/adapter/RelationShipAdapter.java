package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.RelationBean;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by T-BayMax on 2017/5/10.
 */

public class RelationShipAdapter extends BaseRecyclerAdapter<RelationShipAdapter.ViewHolder> {


    private List<RelationshipBean> mData;
    private Context mContext;
    private LayoutInflater inflater;

    public OnItemClickListener onItemClickListener;

    public RelationShipAdapter(Context context, List<RelationshipBean> data) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.mData = data;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view, false);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = inflater.inflate(R.layout.view_list_item_relationship, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, isItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position, boolean isItem) {
        if (isItem) {
            final RelationshipBean bean = mData.get(position);
            holder.tvNickname.setText("昵    称：" + bean.getNickname());
            holder.tvUserId.setText("账    号：" + bean.getUserId());
            holder.tvRecommendUser.setText("推荐人：" + bean.getRecommendUser());
            if (null!=bean.getUserPortraitUrl()){
                Picasso.with(mContext).load(HttpUtils.IMAGE_RUL + bean.getUserPortraitUrl())
                        .into(holder.ivUserPortraitUrl);
            }
            if (bean.getSex() == 2) {
                holder.ivSex.setImageResource(R.drawable.mine_women);
            } else {
                holder.ivSex.setImageResource(R.drawable.mine_man);
            }
            if (bean.getStatus() == 0) {
                holder.tvAddFriends.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorAccent));
            } else {
                holder.tvAddFriends.setBackgroundColor(ContextCompat.getColor(mContext, R.color.aam_item_border));
            }
            holder.rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder, bean);
                }
            });
            holder.tvAddFriends.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onAddFriendsClick(holder, bean);
                }
            });
        }
    }


    @Override
    public int getAdapterItemCount() {
        return mData.size();
    }

    public void setData(List<RelationshipBean> data) {
        mData.clear();
        this.mData.addAll(data);
        this.notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_userPortraitUrl)
        ImageView ivUserPortraitUrl;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.iv_sex)
        ImageView ivSex;
        @BindView(R.id.tv_userId)
        TextView tvUserId;
        @BindView(R.id.tv_recommend_user)
        TextView tvRecommendUser;
        @BindView(R.id.tv_add_friends)
        TextView tvAddFriends;
        @BindView(R.id.rl_item)
        RelativeLayout rlItem;

        public ViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                ButterKnife.bind(this, itemView);
                AutoUtils.autoSize(itemView, AutoAttr.BASE_HEIGHT);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onItemClick(ViewHolder viewHolder, RelationshipBean bean);

        public void onAddFriendsClick(ViewHolder viewHolder, RelationshipBean bean);

    }
}
