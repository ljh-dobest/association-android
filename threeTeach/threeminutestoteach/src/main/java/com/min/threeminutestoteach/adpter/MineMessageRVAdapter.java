package com.min.threeminutestoteach.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.bean.MessageBean;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class MineMessageRVAdapter extends RecyclerView.Adapter<MineMessageRVAdapter.ViewHolder> {

    private List<MessageBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MineMessageRVAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<MessageBean> data) {
        mDatas.clear();
        mDatas = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void insertData(MessageBean commentBean) {
        mDatas.add(0, commentBean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        MessageBean messageBean = mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_URL + messageBean.getUserPortraitUrl()).into(holder.ivMsgItemUserHeader);
        holder.tvMsgItemUserName.setText(messageBean.getNickname());
        holder.tvMsgItemTile.setText(messageBean.getContent());
        holder.tvMsgItemTime.setText(messageBean.getCommentTime());
        if(messageBean.getStatus().equals("0")){
            holder.tvMsgItemStatus.setText("评论了你的");
        }
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_msg_item_userHeader)
        XCRoundRectImageView ivMsgItemUserHeader;
        @BindView(R.id.tv_msg_item_userName)
        TextView tvMsgItemUserName;
        @BindView(R.id.tv_msg_item_status)
        TextView tvMsgItemStatus;
        @BindView(R.id.tv_msg_item_tile)
        TextView tvMsgItemTile;
        @BindView(R.id.tv_msg_item_time)
        TextView tvMsgItemTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
