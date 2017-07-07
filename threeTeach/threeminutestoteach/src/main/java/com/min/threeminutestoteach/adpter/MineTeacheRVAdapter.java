package com.min.threeminutestoteach.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.utils.DateUtils;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class MineTeacheRVAdapter extends RecyclerView.Adapter<MineTeacheRVAdapter.ViewHolder> {

    private List<TeacheBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MineTeacheRVAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<TeacheBean> data) {
        mDatas.clear();
        mDatas = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void insertData(TeacheBean commentBean) {
        mDatas.add(0, commentBean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        TeacheBean teacheBean = mDatas.get(position);
       Picasso.with(mContext).load(HttpUtils.IMAGE_URL+teacheBean.getImageUrl()).into(holder.ivItem1Image);
       holder.tvItem1Title.setText(teacheBean.getTitle());
       holder.tvItem1UserName.setText(teacheBean.getNickname());
       holder.tvItem1Time.setText("/ "+DateUtils.getMonthAndDay(teacheBean.getTime()).replace("-","'"));
//        holder.tv_answer_item_content.setText(commentBean.getContent());
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_lv_item1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item1_image)
        ImageView ivItem1Image;
        @BindView(R.id.tv_item1_title)
        TextView tvItem1Title;
        @BindView(R.id.tv_item1_userName)
        TextView tvItem1UserName;
        @BindView(R.id.tv_item1_time)
        TextView tvItem1Time;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
