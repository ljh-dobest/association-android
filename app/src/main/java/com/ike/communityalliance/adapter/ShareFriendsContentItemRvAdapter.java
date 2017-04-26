package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.ReplyUserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class ShareFriendsContentItemRvAdapter extends RecyclerView.Adapter<ShareFriendsContentItemRvAdapter.MyViewHolder>{

    private List<ReplyUserBean> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;



    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position,ReplyUserBean replyUserBean);
    }

    private ShareFriendsContentItemRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(ShareFriendsContentItemRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public ShareFriendsContentItemRvAdapter(Context context,List<ReplyUserBean> data){
        this. mContext=context;
        mDatas=data;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<ReplyUserBean> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final ReplyUserBean replyUserBean=mDatas.get(position);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos,replyUserBean);
                }
            });
        }
        holder.tv_replayusers_nickname.setText(replyUserBean.getNickname());
        holder.tv_replayusers_fromNickname.setText(replyUserBean.getFromNickname());
        holder.tv_replayusers_content.setText(":"+replyUserBean.getContent());

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.share_friends_content_rv_item_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_replayusers_nickname)
        TextView tv_replayusers_nickname;
        @BindView(R.id.tv_replayusers_fromNickname)
        TextView tv_replayusers_fromNickname;
        @BindView(R.id.tv_replayusers_content)
        TextView tv_replayusers_content;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
