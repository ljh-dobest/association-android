package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.PossibleUnderstandPeopleRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.PossiblePeopleBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class PossibleUnderstandActivity extends BaseActivity {

    @BindView(R.id.ll_possible_back)
    LinearLayout llPossibleBack;
    @BindView(R.id.rv_possible)
    RecyclerView rvPossible;
    private SharedPreferences sp;
    private String userId;
    private String nickName;
   private  List<PossiblePeopleBean> possiblePeopleBeanList;
    private PossibleUnderstandPeopleRvAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_understand);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        nickName = sp.getString(Const.LOGIN_NICKNAME, "");
        initView();
    }

    private void initView() {
        getPossibleUnderstandPeople();
    }

    private void getPossibleUnderstandPeople() {
        HttpUtils.getPossibleUnderstandPeople("/knowFriends", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<PossiblePeopleBean>>>() {
                }.getType();
                Code<List<PossiblePeopleBean>> code = gson.fromJson(response, type);
                   if(code.getCode()==200){
                       possiblePeopleBeanList=code.getData();
                       initRvData();
                   }
            }
        });
    }

    private void initRvData() {
        adapter=new PossibleUnderstandPeopleRvAdapter(this,userId,nickName);
        adapter.setmDatas(possiblePeopleBeanList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvPossible.setLayoutManager(layoutManager);
        rvPossible.setAdapter(adapter);
        rvPossible.setItemAnimator(new DefaultItemAnimator());
        rvPossible.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }


    @OnClick(R.id.ll_possible_back)
    public void onViewClicked() {
        finish();
    }


}
