package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.WasRecommendRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.RecommendInfoBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MineRecomendActivity extends BaseActivity implements WasRecommendRvAdapter.OnItemClickLitener {

    @BindView(R.id.ll_mine_wasRecomend_back)
    AutoLinearLayout llMineWasRecomendBack;
    @BindView(R.id.rv_mine_wasRecomend)
    RecyclerView rvMineWasRecomend;
    @BindView(R.id.activity_mine_recomend)
    AutoLinearLayout activityMineRecomend;
    private SharedPreferences sp;
    private String userId;
    private List<RecommendInfoBean> recommendInfoList;
    private WasRecommendRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_recomend);
        ButterKnife.bind(this);
        sp = mContext.getSharedPreferences("config",mContext.MODE_PRIVATE);
        userId=sp.getString(Const.LOGIN_ID,"");
        initData();
    }

    private void initData() {
        HttpUtils.getWasRecommedInfo("/allRecommendsUsers", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MineRecomendActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<RecommendInfoBean>>>() {
                }.getType();
                Code<List<RecommendInfoBean>> code = gson.fromJson(response, type);
                if(code.getCode()==200){
                     recommendInfoList=code.getData();
                    initView();
                }else{
                    T.showShort(MineRecomendActivity.this,"你还没有推荐的人哦！赶快推荐好友吧~~");
                }
            }
        });
    }

    private void initView() {
        adapter=new WasRecommendRvAdapter(this);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvMineWasRecomend.setLayoutManager(lm);
        rvMineWasRecomend.setAdapter(adapter);
        adapter.setmDatas(recommendInfoList);
        rvMineWasRecomend.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }

    @OnClick(R.id.ll_mine_wasRecomend_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(View view, int position) {
        String recommendId=recommendInfoList.get(position).getRecommendId();
        Intent intent = new Intent(this, RecomendCardActivity.class);
        intent.putExtra("recommendId", recommendId);
        startActivity(intent);
    }
}
