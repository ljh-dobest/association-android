package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.ike.communityalliance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class SearchAddressRvAdapter extends RecyclerView.Adapter<SearchAddressRvAdapter.MyViewHolder> {

    private List<Tip> mDatas=new ArrayList<>();
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

    private SearchAddressRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(SearchAddressRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public SearchAddressRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<Tip> data){
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
        Tip tip=mDatas.get(position);
        holder.tv_search_address_name.setText(tip.getName());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_address_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_search_address_icon)
        ImageView iv_search_address_icon;
        @BindView(R.id.tv_search_address_name)
        TextView tv_search_address_name;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
