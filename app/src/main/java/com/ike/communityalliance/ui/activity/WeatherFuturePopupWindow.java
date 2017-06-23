package com.ike.communityalliance.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.AddressListRvAdapter;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.SearchAddressRvAdapter;
import com.ike.communityalliance.adapter.WeatherFutureRVAdapter;
import com.ike.communityalliance.base.BasePopup;
import com.ike.communityalliance.bean.WeatherFuture;

import java.util.List;


/**
 * Created by Min on 2016/11/24.
 *  上拉弹出一周天气预报
 */

public class WeatherFuturePopupWindow extends BasePopup implements View.OnClickListener, SearchAddressRvAdapter.OnItemClickLitener, AddressListRvAdapter.OnItemClickLitener, WeatherFutureRVAdapter.OnItemClickLitener {
    private WeatherFutureRVAdapter adapter;
   private RecyclerView rvWeatherFuture;

    public WeatherFuturePopupWindow(Activity activity,int width) {
        super(activity,width);
    }
   public  void setAddressList(List<WeatherFuture> weatherFutureList){
       adapter.setmDatas(weatherFutureList);
   }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void setTitleText() {

    }
    @Override
    public View getView() {
        LayoutInflater inflater= (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.weather_future_popwindow,null);
        rvWeatherFuture= (RecyclerView) view.findViewById(R.id.rv_weather_future);
        initRv();
        return view;
    }

    private void initRv() {
        adapter = new WeatherFutureRVAdapter(mActivity);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvWeatherFuture.setLayoutManager(layoutManager);
        rvWeatherFuture.setAdapter(adapter);
        rvWeatherFuture.setHasFixedSize(true);
        rvWeatherFuture.addItemDecoration(new DividerItemDecoration(mActivity, OrientationHelper.VERTICAL));
    }

    @Override
    public Animation setAnima() {
        return null;
    }

    @Override
    public View getCancelButton() {
        return null;
    }

    @Override
    public View getCompleteButton() {
        return null;
    }


    @Override
    public void onItemClick(View view, int position) {

    }

}
