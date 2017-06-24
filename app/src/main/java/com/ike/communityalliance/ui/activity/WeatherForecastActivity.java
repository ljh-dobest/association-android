package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.WeatherBean;
import com.ike.communityalliance.bean.WeatherFuture;
import com.ike.communityalliance.bean.WeatherResultDataBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.communityalliance.utils.WeatherUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class WeatherForecastActivity extends AppCompatActivity implements AMapLocationListener, View.OnTouchListener {

    @BindView(R.id.tv_weather_area)
    TextView tvWeatherArea;
    @BindView(R.id.tv_weather_address)
    TextView tvWeatherAddress;
    @BindView(R.id.tv_weather_time)
    TextView tvWeatherTime;
    @BindView(R.id.iv_weather_status)
    ImageView ivWeatherStatus;
    @BindView(R.id.tv_weather_city)
    TextView tvWeatherCity;
    @BindView(R.id.tv_weather_currentCentigrade)
    TextView tvWeatherCurrentCentigrade;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    @BindView(R.id.tv_weather_centigrade)
    TextView tvWeatherCentigrade;
    @BindView(R.id.rl_wearther_activity)
    RelativeLayout rlWeartherActivity;
    @BindView(R.id.iv_weather_selectCity)
    ImageView ivWeatherSelectCity;
    @BindView(R.id.rv_weather_location)
    RelativeLayout rvWeatherLocation;
    private AMapLocationClient mlocationClient;
    private String city, area, street, time;
    private int screenWidth, screenHeight;
    private List<WeatherFuture> list = new ArrayList<>();
    private String timeOfDay;
    private String locationCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        initLocation();
    }

    private void initLocation() {
        mlocationClient = new AMapLocationClient(this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置返回地址信息，默认为true
        mLocationOption.setNeedAddress(true);
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
////设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(60000);
//设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位

//获取一次定位结果：
//该方法默认为false。
        mLocationOption.setOnceLocation(true);
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                time = DateUtils.currentHourMinute();
                locationCity=aMapLocation.getCity();
                city = aMapLocation.getCity();//城市信息
                area = aMapLocation.getDistrict();//城区信息
                street = aMapLocation.getStreet();//街道信息
                initData();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }

    private void initData() {
        mlocationClient.stopLocation();
        rlWeartherActivity.setOnTouchListener(this);
        if (Integer.valueOf(time.substring(0, 2)) > 12) {
            timeOfDay = "下午";
            tvWeatherTime.setText(timeOfDay + time);
        } else {
            timeOfDay = "上午";
            tvWeatherTime.setText(timeOfDay + time);
        }
        tvWeatherCity.setText(city);
        tvWeatherArea.setText(area);
        tvWeatherAddress.setText(street);
        getWeatherData();
    }

    private void getWeatherData() {
        HttpUtils.getWeatherData("http://www.freedt.cn/api/onebox/weather/query", "594c6e3bfee76f1a7dec5afe", city.replace("市", ""), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<WeatherBean>() {
                }.getType();
                WeatherBean weatherBean = gson.fromJson(response, type);
                list.clear();
                list.addAll(weatherBean.getResult().getData().getWeather());
                initWeatherData(weatherBean.getResult().getData());
            }
        });
    }

    private void initWeatherData(WeatherResultDataBean weatherResultData) {
        rlWeartherActivity.setBackgroundResource(WeatherUtils.getWeatherBg(weatherResultData.getRealtime().getWeather().getInfo(), timeOfDay));
        ivWeatherStatus.setImageResource(WeatherUtils.getWeatherIcon(weatherResultData.getRealtime().getWeather().getInfo()));
        tvWeatherCurrentCentigrade.setText(weatherResultData.getRealtime().getWeather().getTemperature());
        tvWeatherCentigrade.setText(weatherResultData.getWeather().get(0).getInfo().getNight().get(2) + "/" + weatherResultData.getWeather().get(0).getInfo().getDay().get(2) + "℃");
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //按住事件发生后执行代码的区域
                int y = (int) event.getY();
                if (y > 1800) {
                    showFuturePopWindow();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //移动事件发生后执行代码的区域
                break;
            }
            case MotionEvent.ACTION_UP: {
                //松开事件发生后执行代码的区域
                break;
            }
        }
        return false;
    }

    private void showFuturePopWindow() {
        WeatherFuturePopupWindow popupWindow = new WeatherFuturePopupWindow(this, screenWidth);
        popupWindow.setAddressList(list);
        popupWindow.showPopupWindowAtButton(R.id.rl_wearther_activity);
    }

    @OnClick(R.id.iv_weather_selectCity)
    public void onViewClicked() {
        startActivityForResult(new Intent(this, WeatherCityActivity.class), 33);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 33 && resultCode == RESULT_OK) {
            city = data.getStringExtra("city").replace("市", "");
            if (locationCity.replace("市", "").equals(city)) {
                mlocationClient.startLocation();
                rvWeatherLocation.setVisibility(View.VISIBLE);
            } else {
            rvWeatherLocation.setVisibility(View.GONE);
        }
            tvWeatherCity.setText(city + "市");
            getWeatherData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mlocationClient.stopLocation();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
