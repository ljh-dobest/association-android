package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.ContastsInfo;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class PhoneContactRvAdapter extends RecyclerView.Adapter<PhoneContactRvAdapter.MyViewHolder> {

    private List<ContastsInfo> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private PhoneContactRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(PhoneContactRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public PhoneContactRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<ContastsInfo> data){
        mDatas.clear();
        mDatas.addAll(data);
        notifyItemInserted(getItemCount());
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
         ContastsInfo contastsInfo=mDatas.get(position);
        holder.tv_contast_person_item_name.setText(contastsInfo.getName());
        holder.tv_contast_person_item_phone.setText(contastsInfo.getNumber());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.contast_person_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_contast_person_item_image)
        SelectableRoundedImageView iv_contast_person_item_image;
        @BindView(R.id.tv_contast_person_item_name)
        TextView tv_contast_person_item_name;
        @BindView(R.id.tv_contast_person_item_phone)
        TextView tv_contast_person_item_phone;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
