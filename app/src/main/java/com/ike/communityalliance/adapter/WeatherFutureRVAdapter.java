package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.WeatherFuture;
import com.ike.communityalliance.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class WeatherFutureRVAdapter extends RecyclerView.Adapter<WeatherFutureRVAdapter.ViewHolder> {

    private List<WeatherFuture> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public WeatherFutureRVAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<WeatherFuture> data) {
        mDatas.clear();
        mDatas = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size()>7?7:mDatas.size();
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
        WeatherFuture weatherFuture = mDatas.get(position);
        holder.ivWeatherRvIcon.setImageResource(WeatherUtils.getWeatherIcon(weatherFuture.getInfo().getDay().get(1)));
            holder.tvWeatherRvDay.setText("星期"+weatherFuture.getWeek());
             holder.tvWeatherRvCentigrade.setText(weatherFuture.getInfo().getNight().get(2)+"/"+weatherFuture.getInfo().getDay().get(2)+"℃");

    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.weather_future_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_weather_rv_icon)
        ImageView ivWeatherRvIcon;
        @BindView(R.id.tv_weather_rv_day)
        TextView tvWeatherRvDay;
        @BindView(R.id.tv_weather_rv_centigrade)
        TextView tvWeatherRvCentigrade;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
