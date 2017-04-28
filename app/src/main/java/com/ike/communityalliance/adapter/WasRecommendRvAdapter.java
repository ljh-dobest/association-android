package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.RecommendInfoBean;
import com.ike.communityalliance.wedget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class WasRecommendRvAdapter extends RecyclerView.Adapter<WasRecommendRvAdapter.MyViewHolder> {

    private List<RecommendInfoBean> mDatas=new ArrayList<>();
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

    private WasRecommendRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(WasRecommendRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public WasRecommendRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<RecommendInfoBean> data){
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
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
         RecommendInfoBean recommendInfo=mDatas.get(position);
        holder.tv_was_recomend_item_name.setText(recommendInfo.getFullName());
        holder.tv_was_recomend_item_moblie.setText(recommendInfo.getMobile());
        holder.tv_was_recomend_item_recommenderId.setText(recommendInfo.getRecommendId());

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mine_was_recomed_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_was_recomend_item_userIcon)
        CircleImageView iv_was_recomend_item_userIcon;
        @BindView(R.id.tv_was_recomend_item_name)
        TextView tv_was_recomend_item_name;
        @BindView(R.id.tv_was_recomend_item_moblie)
        TextView tv_was_recomend_item_moblie;
        @BindView(R.id.tv_was_recomend_item_recommenderId)
        TextView tv_was_recomend_item_recommenderId;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
