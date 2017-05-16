package com.ike.communityalliance.ui.activity;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.ike.communityalliance.AppContext;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.LocationEntity;
import com.ike.communityalliance.listener.OnLocationGetListener;
import com.ike.communityalliance.utils.file.PermissionsUtil;
import com.ike.communityalliance.wedget.location.LocationTask;
import com.ike.communityalliance.wedget.location.RegeocodeTask;
import com.ike.mylibrary.util.T;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.message.LocationMessage;

public class AMAPLocationActivity extends ActionBarActivity implements OnLocationGetListener, PoiSearch.OnPoiSearchListener {

    private static final int REQUECT_CODE_LOCATION = 1002;
    private static final int REQUECT_CODE_COARSE_LOCATION = 1003;
    private static final int REQUECT_CODE_READ_EXTERNAL_STORAGE = 1004;
    private static final int REQUECT_CODE_READ_PHONE_STATE = 1005;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.location)
    EditText et_location;
    @BindView(R.id.myLocation)
    ImageView iv_enter;
    @BindView(R.id.btn_seach_location)
    Button btnSeachLocation;
    @BindView(R.id.ll_seacher_location)
    LinearLayout llSeacherLocation;

    private AMap aMap;
    private LatLng myLocation = null;
    private Marker centerMarker;
    static public final int REQUEST_CODE_ASK_PERMISSIONS = 101;
    private LocationMessage mMsg;
    private LocationTask mLocationTask;
    private RegeocodeTask mRegeocodeTask;
    private boolean model = false;
    private PoiSearch.Query query;//poi查询条件类
    private PoiSearch poiSearch;// POI搜索
    private PoiResult poiResult; // poi返回的结果
    private AMapLocationClientOption mLocationOption;
    //纬度
    private double latitude;
    //经度
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaplocation);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        //android6.0 打开位置权限
        initPermission();
    }


    //位置检索
    public void doSearchQuery(String content) {
        query = new PoiSearch.Query(content,"汽车维修|修车服务", "");
        query.setPageSize(10);//设置每页返回多少条条poiitem
        query.setPageNum(0);//设置查第一页
        poiSearch = new PoiSearch(this, query);
        //如果不为空值
        if (latitude != 0.0 && longitude != 0.0) {
            poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,
                    latitude), 6000));// 设置周边搜索的中心点以及区域
            poiSearch.setOnPoiSearchListener(this);// 设置数据返回的监听器
            poiSearch.searchPOIAsyn();// 开始搜索
        } else {
            T.showShort(this, "定位失败");
        }

    }

    private void initAmap() {
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        /**
         * 从通话界面进入
         */
        if (getIntent().hasExtra("location")) {
            mMsg = getIntent().getParcelableExtra("location");
            llSeacherLocation.setVisibility(View.GONE);
            iv_enter.setVisibility(View.GONE);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMsg.getLat(), mMsg.getLng()), 15));
            aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                    .position(new LatLng(mMsg.getLat(), mMsg.getLng())).title(mMsg.getPoi())
                    .snippet(mMsg.getLat() + "," + mMsg.getLng()).draggable(true));
            return;
        }

        //定位
        mLocationTask = LocationTask.getInstance(this);
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(this);
        mRegeocodeTask.setOnLocationGetListener(this);

        //显示定位按钮
        aMap.setLocationSource(mLocationTask);
        /*aMap.getUiSettings().setMyLocationButtonEnabled(true);*/
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);

        //比例尺
        aMap.getUiSettings().setScaleControlsEnabled(true);
        aMap.moveCamera(new CameraUpdateFactory().zoomTo(50));
    }


    //android6.0 打开位置权限
    private void initPermission() {
        PermissionsUtil.initPermissions(this, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS);
        PermissionsUtil.initPermissions(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        PermissionsUtil.initPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION);
        PermissionsUtil.initPermissions(this, Manifest.permission.ACCESS_WIFI_STATE);
    }

    @Override
    public void onLocationGet(LocationEntity entity) {
//        tv_location.setText(entity.address);
        latitude = entity.getLatitue();
        longitude = entity.getLongitude();
        mMsg = LocationMessage.obtain(latitude, longitude, entity.address, getMapUrl(latitude, longitude));
    }

    private Uri getMapUrl(double x, double y) {
        String url = "http://restapi.amap.com/v3/staticmap?location=" + y + "," + x +
                "&zoom=16&scale=2&size=408*240&markers=mid,,A:" + y + ","
                + x + "&key=" + "ee95e52bf08006f63fd29bcfbcf21df0";
        Log.e("getMapUrl", url);
        return Uri.parse(url);
    }

    @Override
    public void onRegecodeGet(LocationEntity entity) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initAmap();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @PermissionGrant(REQUECT_CODE_READ_PHONE_STATE)
    public void requestSdcardSuccess() {
        Toast.makeText(this, "GRANT ACCESS PHONE_STATE!", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_READ_PHONE_STATE)
    public void requestSdcardFailed() {
        Toast.makeText(this, "DENY ACCESS PHONE_STATE!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        T.showShort(this, poiResult.getPois().get(0).getAdName());
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
        T.showShort(this, poiItem.getAdName());
    }

    @OnClick({R.id.btn_seach_location, R.id.myLocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_seach_location:
                doSearchQuery(et_location.getText().toString());
                break;
            case R.id.myLocation:
                if (mMsg != null) {
                    initPermission();
                    AppContext.getInstance().getLastLocationCallback().onSuccess(mMsg);
                    AppContext.getInstance().setLastLocationCallback(null);
                    finish();
                } else {
                    AppContext.getInstance().getLastLocationCallback().onFailure("定位失败");
                }
                break;
        }
    }
}
