package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.GroupVote;
import com.ike.communityalliance.bean.OptionBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.ui.activity.VoteDetailActivity;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class GroupVoteRvAdapter extends RecyclerView.Adapter<GroupVoteRvAdapter.MyViewHolder> {

    private List<GroupVote> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private String groupId;
    public void setGroupId(String groupId){
        this.groupId=groupId;
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private GroupVoteRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(GroupVoteRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public GroupVoteRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<GroupVote> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(GroupVote groupVote){
        mDatas.add(0,groupVote);
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
        final GroupVote groupVote=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+groupVote.getVoteImage()).error(R.drawable.rc_image_error).into(holder.iv_groupVote_image);
         holder.tv_groupVote_title.setText(groupVote.getVoteTitle());
        if(groupVote.getStatus()==1&&groupVote.getTimeStatus()==1){
           //活动正在进行且已投票
          holder.btn_groupVote_vote.setText("已投票,看结果");
            holder.btn_groupVote_vote.setBackgroundResource(R.mipmap.was_vote);
        }else if(groupVote.getTimeStatus()==0){
            holder.btn_groupVote_vote.setText("投票截止,看结果");
            holder.btn_groupVote_vote.setBackgroundResource(R.mipmap.vote_end);
        }
        List<OptionBean> options=groupVote.getOption();
        for (int i = 0; i < options.size(); i++) {
            if(i<options.size()){
                RadioButton rb= (RadioButton) holder.rg_groupVote.getChildAt(i);
                rb.setText(options.get(i).getContent());
                rb.setEnabled(false);
                rb.setFocusableInTouchMode(false);
                rb.setVisibility(View.VISIBLE);
            }
        }
        holder.btn_groupVote_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VoteDetailActivity.class);
                intent.putExtra("group_id", groupId);
                intent.putExtra("vote_id",groupVote.getVoteId());
               mContext.startActivity(intent);
            }
        });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_vote,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_groupVote_image)
        XCRoundRectImageView iv_groupVote_image;
        @BindView(R.id.tv_groupVote_title)
        TextView tv_groupVote_title;
        @BindView(R.id.rg_groupVote)
        RadioGroup rg_groupVote;
        @BindView(R.id.btn_groupVote_vote)
        Button btn_groupVote_vote;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
