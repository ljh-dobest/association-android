package com.min.helpcenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.min.helpcenter.R;
import com.min.helpcenter.bean.CommentBean;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.views.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class AnswerRvAdapter extends RecyclerView.Adapter<AnswerRvAdapter.MyViewHolder>{

    private List<CommentBean> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private AnswerRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(AnswerRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public AnswerRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<CommentBean> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(CommentBean commentBean){
        mDatas.add(0,commentBean);
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
      CommentBean commentBean=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_URL+commentBean.getUserPortraitUrl()).into(holder.iv_answer_item_icon);
        holder.tv_answer_item_name.setText(commentBean.getNickname());
        holder.tv_answer_item_time.setText(commentBean.getCommentTime());
        holder.tv_answer_item_content.setText(commentBean.getContent());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.answer_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_answer_item_icon)
        CircleImageView iv_answer_item_icon;
        @BindView(R.id.tv_answer_item_name)
        TextView tv_answer_item_name;
        @BindView(R.id.tv_answer_item_time)
        TextView tv_answer_item_time;
        @BindView(R.id.tv_answer_item_content)
        TextView tv_answer_item_content;
        @BindView(R.id.iv_answer_item_good)
        ImageView iv_answer_item_good;
        @BindView(R.id.tv_answer_item_goodNum)
        TextView tv_answer_item_goodNum;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
