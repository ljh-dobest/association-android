package com.ike.communityalliance.wedget.location;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.ike.communityalliance.bean.LocationEntity;
import com.ike.communityalliance.listener.OnLocationGetListener;

/**
 * Created by Min on 2016/12/22.
 * 封装定位请求
 */
public class LocationTask implements AMapLocationListener,OnLocationGetListener, LocationSource {
    private Context context;
    private AMapLocationClientOption mLocationOption=null;
    private AMapLocationClient mLocationClient;
    private static LocationTask mLocationTask;
    private RegeocodeTask mRegeocodeTask;

    //定位
    private LocationSource.OnLocationChangedListener mListener;

    private OnLocationGetListener mOnLocationGetlisGetListener;


    public LocationTask(Context context) {
        mRegeocodeTask=new RegeocodeTask(context);
        mRegeocodeTask.setOnLocationGetListener(this);
        this.context = context;
    }

    public static LocationTask getInstance(Context context){
        if(mLocationTask==null){
            mLocationTask=new LocationTask(context);
        }
        return mLocationTask;
    }

    public void setOnLocationGetListener(OnLocationGetListener onLocationGetListener){
        mOnLocationGetlisGetListener=onLocationGetListener;
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if(mListener!=null && aMapLocation!=null){
            if(aMapLocation!=null && aMapLocation.getErrorCode()==0){
                mListener.onLocationChanged(aMapLocation);
                LocationEntity entity=new LocationEntity();
                entity.latitue=aMapLocation.getLatitude();
                entity.longitude=aMapLocation.getLongitude();
                entity.address=aMapLocation.getAddress();
                entity.city=aMapLocation.getCity();
                mOnLocationGetlisGetListener.onLocationGet(entity);
            }
        }
    }

    //激活定位
    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener=listener;
        if(mLocationClient==null){
            mLocationClient=new AMapLocationClient(context);
            mLocationOption=new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            // 设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);
            mLocationOption.setInterval(5000);
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
    }

    //停止定位
    @Override
    public void deactivate() {
        mListener=null;
        if(mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient=null;
    }

    @Override
    public void onLocationGet(LocationEntity entity) {

    }
    @Override
    public void onRegecodeGet(LocationEntity entity) {

    }
}

