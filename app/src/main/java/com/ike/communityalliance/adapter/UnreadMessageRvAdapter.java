package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.UnreadMessgaeBean;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class UnreadMessageRvAdapter extends RecyclerView.Adapter<UnreadMessageRvAdapter.MyViewHolder> {

    private List<UnreadMessgaeBean> mDatas=new ArrayList<>();
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

    private UnreadMessageRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(UnreadMessageRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public UnreadMessageRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<UnreadMessgaeBean> data){
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
        UnreadMessgaeBean unreadMessgaeBean=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+unreadMessgaeBean.getUserPortraitUrl()).error(R.drawable.rc_image_error).into(holder.iv_unread_msg_item_icon);
       if(unreadMessgaeBean.getArticleImages()!=null){
           Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+unreadMessgaeBean.getArticleImages()).error(R.drawable.rc_image_error).into(holder.iv_unread_msg_item_image);
       }
         holder.tv_unread_msg_item_name.setText(unreadMessgaeBean.getNickname());
         holder.tv_unread_msg_item_content.setText(unreadMessgaeBean.getContent());
         holder.tv_unread_msg_item_time.setText(unreadMessgaeBean.getCommentTime());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.unread_message_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_unread_msg_item_icon)
        ImageView iv_unread_msg_item_icon;
        @BindView(R.id.iv_unread_msg_item_image)
        ImageView iv_unread_msg_item_image;
        @BindView(R.id.tv_unread_msg_item_name)
        TextView tv_unread_msg_item_name;
        @BindView(R.id.tv_unread_msg_item_content)
        TextView tv_unread_msg_item_content;
        @BindView(R.id.tv_unread_msg_item_time)
        TextView tv_unread_msg_item_time;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
