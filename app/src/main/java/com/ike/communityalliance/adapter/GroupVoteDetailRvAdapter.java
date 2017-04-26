package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.JoinUsers;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class GroupVoteDetailRvAdapter extends RecyclerView.Adapter<GroupVoteDetailRvAdapter.MyViewHolder> {

    private List<JoinUsers> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private GroupVoteDetailRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(GroupVoteDetailRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public GroupVoteDetailRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<JoinUsers> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(JoinUsers joinUsers){
        mDatas.add(0,joinUsers);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        JoinUsers joinUsers=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+joinUsers.getUserPortraitUrl()).error(R.drawable.rc_image_error).into(holder.iv_joinUser_icon);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_vote_or_flexible_joinuser,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_joinUser_icon)
        CircleImageView iv_joinUser_icon;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
