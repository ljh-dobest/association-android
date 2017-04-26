package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.ClaimCenterRecyclerAdapter;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IClaimCenterView;
import com.ike.communityalliance.presenter.ClaimCenterPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClaimActiviy extends BaseMvpActivity<IClaimCenterView,ClaimCenterPresenter> implements IClaimCenterView, ClaimCenterRecyclerAdapter.OnItemClickLitener {
@BindView(R.id.rv_calim)
RecyclerView rv_calim;
    @BindView(R.id.iv_claim_back)
    ImageView iv_claim_back;
    @BindView(R.id.iv_claim_more)
    ImageView iv_claim_more;
    private ClaimCenterRecyclerAdapter adapter;
    private ArrayList<ClaimPeopleBean> data;
    private SharedPreferences sp;
    private String userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.claim_activiy);
        ButterKnife.bind(this);
       sp =this.getSharedPreferences("config", MODE_PRIVATE);
        userId=sp.getString(Const.LOGIN_ID,"");
        initData();
        initView();
    }

    private void initData() {
        presenter.getClaimPeopleData(userId);
    }

    private void initView() {
        adapter=new ClaimCenterRecyclerAdapter(this);
        //设置布局管理器
        rv_calim.setLayoutManager(new GridLayoutManager(this,3));
        //设置Adapter
        rv_calim.setAdapter(adapter);
        adapter.setOnItemClickLitener(this);
    }

   @OnClick(value = {R.id.iv_claim_back,R.id.iv_claim_more})
    public void claimViewOnCliick(View view){
       switch (view.getId()) {
           case R.id.iv_claim_back:
               finish();
               break;
           case R.id.iv_claim_more:
               ClaimPopupWindow chatPopupWindow =new ClaimPopupWindow(this);
               chatPopupWindow.showPopupWindow(iv_claim_more);
               break;
       }

   }


    @Override
    public ClaimCenterPresenter initPresenter() {
        return new ClaimCenterPresenter();
    }

    @Override
    public void setPeoplesData(ArrayList<ClaimPeopleBean> data) {
     this.data=data;
        adapter.setmDatas(data);
    }

    @Override
    public void showLoading() {
        LoadDialog.show(this);
    }

    @Override
    public void hideLoading() {
        LoadDialog.dismiss(this);
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this,errorString);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(this,ClaimInfoActivity.class);
        ClaimPeopleBean claimPeopleBean=data.get(position);
        intent.putExtra("claimPeopleBean",claimPeopleBean);
        startActivity(intent);
    }
}
