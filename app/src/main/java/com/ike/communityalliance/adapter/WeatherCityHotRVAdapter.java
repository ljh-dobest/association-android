package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class WeatherCityHotRVAdapter extends RecyclerView.Adapter<WeatherCityHotRVAdapter.ViewHolder> {

    private String[] mDatas;
    private Context mContext;
    private LayoutInflater inflater;
    private String groupId;

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public interface OnItemClickLitener {
        void onHotCityItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnHotCityClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public WeatherCityHotRVAdapter(Context context,String[] cityHot) {
        this.mContext = context;
        this.mDatas=cityHot;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.length;
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onHotCityItemClick(holder.itemView, pos);
                }
            });
        }
         holder.tvWeatherCityName.setText(mDatas[position]);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.weather_citysearch_rv_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_weather_cityName)
        TextView tvWeatherCityName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
