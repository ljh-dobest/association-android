package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.ike.communityalliance.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class AddressListRvAdapter extends RecyclerView.Adapter<AddressListRvAdapter.MyViewHolder> {

    private List<PoiItem> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;


    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private AddressListRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(AddressListRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public AddressListRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public List<PoiItem>  getPoiItemList(){
        return mDatas;
    }
    public void setmDatas(List<PoiItem> data){
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
        PoiItem poiItem=mDatas.get(position);
        holder.tv_search_address_name.setText(poiItem.toString());
        holder.iv_search_address_icon.setVisibility(View.GONE);
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
