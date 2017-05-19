package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.SearchAddressRvAdapter;
import com.ike.communityalliance.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationSeacherActivity extends BaseActivity implements TextWatcher, Inputtips.InputtipsListener, SearchAddressRvAdapter.OnItemClickLitener {

    @BindView(R.id.et_location_search)
    EditText etLocationSearch;
    @BindView(R.id.tv_location_search_cancel)
    TextView tvLocationSearchCancel;
    @BindView(R.id.rv_location_search)
    RecyclerView rvLocationSearch;
    @BindView(R.id.tv_search_address_none)
    TextView tvSearchAddressNone;
    private SearchAddressRvAdapter adapter;
    private String city;
    private List<Tip> tipList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_seacher);
        ButterKnife.bind(this);
        etLocationSearch.addTextChangedListener(this);
        city = getIntent().getStringExtra("city");
        initRv();
    }

    private void initRv() {
        adapter = new SearchAddressRvAdapter(this);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvLocationSearch.setLayoutManager(layoutManager);
        rvLocationSearch.setAdapter(adapter);
        rvLocationSearch.setHasFixedSize(true);
        rvLocationSearch.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }

    @OnClick(R.id.tv_location_search_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        startSearchAddress(s.toString().trim());
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    /**
     * 开始搜索
     *
     * @param s
     */
    private void startSearchAddress(String s) {
        if (s.equals("")) {
            return;
        }
        InputtipsQuery inputquery = new InputtipsQuery(s, city);
        Inputtips inputTips = new Inputtips(this, inputquery);
        inputTips.setInputtipsListener(this);
        inputTips.requestInputtipsAsyn();
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        if (list.size() > 0) {
            tvSearchAddressNone.setVisibility(View.GONE);
            adapter.setmDatas(list);
            tipList = list;
        }else{
            adapter.clearData();
            tvSearchAddressNone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Tip tip=tipList.get(position);
        Intent intent=new Intent();
        intent.putExtra("tip",tip);
        setResult(RESULT_OK,intent);
        finish();
    }
}
