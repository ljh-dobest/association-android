package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.GroupFlexible;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class GroupFlexibleRvAdapter extends RecyclerView.Adapter<GroupFlexibleRvAdapter.MyViewHolder>{

    private List<GroupFlexible> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private GroupFlexibleRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(GroupFlexibleRvAdapter.OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public GroupFlexibleRvAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<GroupFlexible> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(GroupFlexible groupFlexible){
        mDatas.add(0,groupFlexible);
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
        GroupFlexible groupFlexible=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+"/"+groupFlexible.getActivesImage()).error(R.drawable.rc_image_error).into(holder.iv_group_activity_head);
        holder.tv_activitzy_place.setText(groupFlexible.getActivesAddress());
        holder.tv_activity_start_time.setText(groupFlexible.getActivesStart());
        holder.tv_activity_end_time.setText(groupFlexible.getActivesEnd());
        holder.tv_activity_content.setText(groupFlexible.getActivesContent());
         String curDate= getCurDate();
        int result=compare_date(curDate,groupFlexible.getActivesEnd());
        if(result==1){
            //当前时间大于结束时间,则活动已经结束
            holder.tv_activity_status.setText("已结束");
            holder.tv_activity_status.setBackgroundResource(R.mipmap.over);
        }else{
            holder.tv_activity_status.setText("进行中");
            holder.tv_activity_status.setBackgroundResource(R.mipmap.in_progress);
        }
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_activity,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

private String getCurDate(){
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
    String date=simpleDateFormat.format(curDate);
    return  date;
}
    /**
     * 两个时间对比
     */
    private int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_group_activity_head)
        SelectableRoundedImageView iv_group_activity_head;
        @BindView(R.id.tv_activity_name)
        TextView tv_activity_name;
        @BindView(R.id.tv_activity_place)
        TextView tv_activitzy_place;
        @BindView(R.id.tv_activity_start_time)
        TextView tv_activity_start_time;
        @BindView(R.id.tv_activity_end_time)
        TextView tv_activity_end_time;
        @BindView(R.id.tv_activity_content)
        TextView tv_activity_content;
        @BindView(R.id.tv_activity_status)
        TextView tv_activity_status;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
