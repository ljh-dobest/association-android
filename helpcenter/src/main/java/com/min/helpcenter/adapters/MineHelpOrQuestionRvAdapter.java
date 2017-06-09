package com.min.helpcenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.min.helpcenter.R;
import com.min.helpcenter.bean.MineHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by Min on 2017/3/9.
 */

public class MineHelpOrQuestionRvAdapter extends RecyclerView.Adapter<MineHelpOrQuestionRvAdapter.MyViewHolder>{

    private List<MineHelp> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private MineHelpOrQuestionRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(MineHelpOrQuestionRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MineHelpOrQuestionRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<MineHelp> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(MineHelp mineHelp){
        mDatas.add(0,mineHelp);
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
                MineHelp mineHelp=mDatas.get(position);
        holder.tv_mineMessage_item_title.setText(mineHelp.getTitle());
        holder.tv_mineMessage_item_time.setText(mineHelp.getTime());
        holder.et_mineMessage_item_content.setHtml(mineHelp.getContent());
        holder.tv_mineMessage_responseNum.setText(mineHelp.getAnswers());
        holder.tv_mineMessage_item_goldNum.setText(mineHelp.getContributionCoin());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.myhelp_or_question_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_mineMessage_item_title)
        TextView tv_mineMessage_item_title;
        @BindView(R.id.tv_mineMessage_item_time)
        TextView tv_mineMessage_item_time;
        @BindView(R.id.et_mineMessage_item_content)
        RichEditor et_mineMessage_item_content;
        @BindView(R.id.tv_mineMessage_responseNum)
        TextView tv_mineMessage_responseNum;
        @BindView(R.id.tv_mineMessage_item_goldNum)
        TextView tv_mineMessage_item_goldNum;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
