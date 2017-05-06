package com.ike.sq.commonwealactives.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ike.sq.commonwealactives.R;
import com.ike.sq.commonwealactives.base.adpater.BaseRecyclerViewAdapter;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.ike.sq.commonwealactives.utils.CircleTransform;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 分享列表item
 * <p>
 * Created by T-BayMax on 2017/3/16.
 */

public class SimpleAdapter extends BaseRecyclerViewAdapter<SimpleAdapter.ProductAdapterViewHolder, BenefitBean> {


    private List<BenefitBean> list;
    private Context context;
    private int position;

    private OnItemClickListener onItemClickListener;


    public SimpleAdapter(List<BenefitBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ProductAdapterViewHolder holder, int position, boolean isItem) {
        if (isItem) {
            BenefitBean bean = list.get(position);

            holder.llItem.setTag(bean);
            holder.llLikes.setTag(bean);
            holder.llComment.setTag(bean);
            if (null!=bean.getUserPortraitUrl())
            Picasso.with(context).load(HttpUtils.IMAGE_RUL + bean.getUserPortraitUrl())
                    .transform(new CircleTransform()).into(holder.ivUserPortraitUrl);

            holder.tvNickname.setText(bean.getNickname());
            holder.tvTitle.setText(bean.getTitle());
            holder.tvContent.setText(bean.getContent());
          //  holder.tvShare.setText(bean.gets);
            holder.tvLikes.setText(bean.getLikes()+"");
            holder.tvComment.setText(bean.getCommentNumber()+"");
            holder.tvAddress.setText(bean.getAddress());
            switch (bean.getStatus()) {
                case 0:
                    holder.tvStatus.setText("进行中");
                    break;
                case 1:
                    holder.tvStatus.setText("已结束");
                    break;
                case 2:
                    holder.tvStatus.setText("未开始");
                    break;
            }
            switch (bean.getLikesStatus()) {
                case 0:
                    holder.ivLikes.setImageResource(R.mipmap.img_like_btn);
                    break;
                case 1:
                    holder.ivLikes.setImageResource(R.mipmap.img_have_thumb_up_btn);
                    break;
                case 2:
                    holder.ivLikes.setImageResource(R.mipmap.img_like_btn_no);
                    break;
                case 3:
                    holder.ivLikes.setImageResource(R.mipmap.img_comments_have_thumb_up_btn);
                    break;
            }
            Picasso.with(context).load(HttpUtils.IMAGE_RUL + bean.getActivesImage())
                    .into(holder.ivActivesImage);
            holder.llItem.setOnClickListener(this);
            holder.llComment.setOnClickListener(this);
            holder.llLikes.setOnClickListener(this);
        }

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
    public ProductAdapterViewHolder getViewHolder(View view) {
        return new ProductAdapterViewHolder(view, false);
    }

    public void setData(List<BenefitBean> list, int page) {
        if (page == 1) {
            this.list = list;
        } else {
            this.list.addAll(list);
        }

        notifyDataSetChanged();
    }

    @Override
    public ProductAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_list_itme_weal_actives, parent, false);
        return new ProductAdapterViewHolder(v, isItem);
    }

    public void insert(BenefitBean person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class ProductAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_item)
        LinearLayout llItem;
        @BindView(R.id.iv_user_portrait_url)
        ImageView ivUserPortraitUrl;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_initiator)
        TextView tvInitiator;
        @BindView(R.id.iv_actives_image)
        ImageView ivActivesImage;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.iv_share)
        ImageView ivShare;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.ll_share)
        LinearLayout llShare;
        @BindView(R.id.iv_likes)
        ImageView ivLikes;
        @BindView(R.id.tv_likes)
        TextView tvLikes;
        @BindView(R.id.ll_likes)
        LinearLayout llLikes;
        @BindView(R.id.iv_comment)
        ImageView ivComment;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.ll_comment)
        LinearLayout llComment;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        public ProductAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {

                AutoUtils.autoSize(itemView, AutoAttr.BASE_HEIGHT);

                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(SimpleAdapter.this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_likes:
                onItemClickListener.onLikeClick(v, (BenefitBean) v.getTag());
                break;
            case R.id.ll_comment:
                onItemClickListener.onCommentClick(v, (BenefitBean) v.getTag());
                break;
            case R.id.ll_item:
                onItemClickListener.onItemClick(v, (BenefitBean) v.getTag());
                break;

        }
    }

    public BenefitBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, BenefitBean bean);

        public void onLikeClick(View view, BenefitBean bean);

        public void onCommentClick(View view, BenefitBean bean);
    }
}