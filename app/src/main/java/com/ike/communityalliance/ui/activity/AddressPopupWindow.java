package com.ike.communityalliance.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.ike.communityalliance.AppContext;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.AddressListRvAdapter;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.SearchAddressRvAdapter;
import com.ike.communityalliance.base.BasePopup;

import io.rong.message.LocationMessage;


/**
 * Created by Min on 2016/11/24.
 *  认领中心下拉
 */

public class AddressPopupWindow extends BasePopup implements View.OnClickListener, SearchAddressRvAdapter.OnItemClickLitener, AddressListRvAdapter.OnItemClickLitener {
    private AddressListRvAdapter adapter;
   private RecyclerView rv_address_list;
    private LocationMessage mMsg;

    public AddressPopupWindow(Activity activity, int Width,int height) {
        super(activity, Width,height);
    }
   public  void setAddressList(PoiResult poiResult){
       adapter.setmDatas(poiResult.getPois());
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
        View view=inflater.inflate(R.layout.address_list_popwindow,null);
        view.setBackgroundColor(mActivity.getResources().getColor(R.color.white));
        rv_address_list= (RecyclerView) view.findViewById(R.id.rv_address_list);
        initRv();
        return view;
    }

    private void initRv() {
        adapter = new AddressListRvAdapter(mActivity);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_address_list.setLayoutManager(layoutManager);
        rv_address_list.setAdapter(adapter);
        rv_address_list.setHasFixedSize(true);
        rv_address_list.addItemDecoration(new DividerItemDecoration(mActivity, OrientationHelper.VERTICAL));
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
        PoiItem poiItem=adapter.getPoiItemList().get(position);
        String address=poiItem.getProvinceName()+poiItem.getCityName()+poiItem.getTitle()+poiItem.getDirection();
        mMsg = LocationMessage.obtain(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude(),address, getMapUrl(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude()));
        if (mMsg != null) {
            AppContext.getInstance().getLastLocationCallback().onSuccess(mMsg);
            AppContext.getInstance().setLastLocationCallback(null);
            mActivity.finish();
        } else {
            AppContext.getInstance().getLastLocationCallback().onFailure("定位失败");
        }
    }
    private Uri getMapUrl(double x, double y) {
        String url = "http://restapi.amap.com/v3/staticmap?location=" + y + "," + x +
                "&zoom=16&scale=2&size=408*240&markers=mid,,A:" + y + ","
                + x + "&key=" + "ee95e52bf08006f63fd29bcfbcf21df0";
        Log.e("getMapUrl", url);
        return Uri.parse(url);
    }
}
