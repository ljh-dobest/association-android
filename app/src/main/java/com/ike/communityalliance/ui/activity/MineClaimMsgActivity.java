package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.MineClaimMsgRvAdapter;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.MineCliamMsgBean;
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

public class MineClaimMsgActivity extends AppCompatActivity implements MineClaimMsgRvAdapter.OnfinishComfirLitener {

    @BindView(R.id.iv_mine_claim_msg_back)
    ImageView ivMineClaimMsgBack;
    @BindView(R.id.rv_mine_claim_msg)
    ListView rvMineClaimMsg;
    @BindView(R.id.tv_mine_claim_msg_none)
    TextView tvMineClaimMsgNone;
    private String userId;
    private SharedPreferences sp;
    private MineClaimMsgRvAdapter adapter;
    private List<MineCliamMsgBean> mineCliamMsgList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_claim_msg);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
         getMineClaimMsg();
    }

    private void getMineClaimMsg() {

        HttpUtils.getMineClaimMsgInfo("/allNews", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MineClaimMsgActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<MineCliamMsgBean>>>() {
                }.getType();
                Code<List<MineCliamMsgBean>> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        mineCliamMsgList = code.getData();
                        initRv();
                        break;
                    case 0:
                        T.showShort(MineClaimMsgActivity.this, "还没有认领的消息~");
                        break;
                }
            }
        });

    }

    private void initRv() {
        if(mineCliamMsgList.size()==0){
            tvMineClaimMsgNone.setVisibility(View.VISIBLE);
        }else{
            tvMineClaimMsgNone.setVisibility(View.GONE);
        }
        //设置Adapter
        adapter = new MineClaimMsgRvAdapter(this);
        adapter.setOnFinishComfirListener(this);
        adapter.setUserId(userId);
        adapter.setData(mineCliamMsgList);
        rvMineClaimMsg.setAdapter(adapter);
    }


    @OnClick(R.id.iv_mine_claim_msg_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void finishComfir() {
        getMineClaimMsg();
    }
}
