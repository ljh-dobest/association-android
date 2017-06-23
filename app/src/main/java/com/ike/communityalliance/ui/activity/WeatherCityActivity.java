package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.WeatherCityHotRVAdapter;
import com.ike.communityalliance.adapter.WeatherCitySearchRVAdapter;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.communityalliance.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherCityActivity extends AppCompatActivity implements TextWatcher, WeatherCityHotRVAdapter.OnItemClickLitener, WeatherCitySearchRVAdapter.OnItemClickLitener {
    @BindView(R.id.iv_weatherCity_back)
    ImageView ivWeatherCityBack;
    @BindView(R.id.et_weatherCity_search)
    EditText etWeatherCitySearch;
    @BindView(R.id.rv_weatherCity_hot)
    RecyclerView rvWeatherCityHot;
    @BindView(R.id.rv_weatherCity_search)
    RecyclerView rvWeatherCitySearch;
    private List<String> allCitys = new ArrayList<>();
    private List<String> searchResult=new ArrayList<>();
    private WeatherCitySearchRVAdapter citySearchAdapter;
    private WeatherCityHotRVAdapter   cityHotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_city);
        ButterKnife.bind(this);
        getAllCitys();
        initView();
    }

    private void initView() {
        cityHotAdapter=new WeatherCityHotRVAdapter(this, WeatherUtils.hotCitys);
        cityHotAdapter.setOnHotCityClickLitener(this);
        citySearchAdapter=new WeatherCitySearchRVAdapter(this);
        citySearchAdapter.setOnItemClickLitener(this);
        etWeatherCitySearch.addTextChangedListener(this);
        rvWeatherCityHot.setLayoutManager(new GridLayoutManager(this,4));
        rvWeatherCityHot.setAdapter(cityHotAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvWeatherCitySearch.setLayoutManager(layoutManager);
        rvWeatherCitySearch.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
          rvWeatherCitySearch.setAdapter(citySearchAdapter);
    }


    private void getAllCitys() {
        for (ProvinceBean provinceBean : DateUtils.provinceData) {
            if(provinceBean.getSub()==null){
                continue;
            }
            for (CityBean cityBean : provinceBean.getSub()) {
                if(cityBean!=null){
                    allCitys.add(cityBean.getName());
                }
            }
        }
    }

    @OnClick(R.id.iv_weatherCity_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(s.toString().equals("")){
            rvWeatherCityHot.setVisibility(View.VISIBLE);
            rvWeatherCitySearch.setVisibility(View.GONE);
        }else{
            rvWeatherCityHot.setVisibility(View.GONE);
            rvWeatherCitySearch.setVisibility(View.VISIBLE);
            searchCity(s);
            citySearchAdapter.setmDatas(searchResult);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {}
    private void searchCity(CharSequence s) {
        searchResult.clear();
        for (String allCity : allCitys) {
                if(allCity.contains(s)){
                    searchResult.add(allCity);
                }
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent();
        intent.putExtra("city",searchResult.get(position));
        setResult(RESULT_OK,intent);
        finish();
    }
    /**
     * 热门城市点击
     * @param view
     * @param position
     */
    @Override
    public void onHotCityItemClick(View view, int position) {
        Intent intent=new Intent();
        intent.putExtra("city",WeatherUtils.hotCitys[position]);
        setResult(RESULT_OK,intent);
        finish();
    }
}
