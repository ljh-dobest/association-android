package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.network.HttpUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class WasMineClaimRvAdapter extends RecyclerView.Adapter<WasMineClaimRvAdapter.MyViewHolder> {

    private List<ClaimPeopleBean> mDatas=new ArrayList<>();
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

    private WasMineClaimRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(WasMineClaimRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public WasMineClaimRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<ClaimPeopleBean> data){
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
        ClaimPeopleBean claimPeopleBean=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+claimPeopleBean.getUserPortraitUrl()).into(holder.iv_mine_claim_userHeader);
        holder.tv_mine_claim_name.setText(claimPeopleBean.getNickname());
        holder.tv_mine_claim_time.setText(claimPeopleBean.getClaimFullName());

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.mine_claim_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mine_claim_userHeader)
        RoundedImageView iv_mine_claim_userHeader;
        @BindView(R.id.tv_mine_claim_name)
        TextView tv_mine_claim_name;
        @BindView(R.id.tv_mine_claim_time)
        TextView tv_mine_claim_time;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}