package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.WasMineClaimRvAdapter;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.mob.MobSDK.getContext;

public class MineClaimActivity extends AppCompatActivity {

    @BindView(R.id.iv_mine_claim_back)
    ImageView ivMineClaimBack;
    @BindView(R.id.rv_mine_calim)
    RecyclerView rvMineCalim;
    @BindView(R.id.tv_mine_claim_none)
    TextView tvMineClaimNone;
    private String userId;
    private SharedPreferences sp;
    private List<ClaimPeopleBean> claimPeopleBeanList = new ArrayList<>();
    private WasMineClaimRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_claim);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        getMineClainInfo(userId);
    }

    private void getMineClainInfo(String userId) {
        HttpUtils.getWasClaimPeopleInfo("/allFriendsClaim", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MineClaimActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<ClaimPeopleBean>>>() {
                }.getType();
                Code<List<ClaimPeopleBean>> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        claimPeopleBeanList = code.getData();
                        initRv();
                        break;
                    case 0:
                        T.showShort(MineClaimActivity.this, "还没有认领的用户~");
                        break;
                }
            }
        });
    }

    private void initRv() {
        if (claimPeopleBeanList.size() == 0) {
            tvMineClaimNone.setVisibility(View.VISIBLE);
        } else {
            tvMineClaimNone.setVisibility(View.GONE);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        rvMineCalim.setLayoutManager(layoutManager);
        //设置Adapter
        adapter = new WasMineClaimRvAdapter(this);
        adapter.setmDatas(claimPeopleBeanList);
        rvMineCalim.setAdapter(adapter);
    }

    @OnClick(R.id.iv_mine_claim_back)
    public void onViewClicked() {
        finish();
    }
}
