package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.constant.Const;
import com.ike.mylibrary.util.T;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplayVIPActivity extends BaseActivity {

    @BindView(R.id.iv_applay_vip_back)
    ImageView ivApplayVipBack;
    @BindView(R.id.tv_apply_enterpriseVIP)
    TextView tvApplyEnterpriseVIP;
    @BindView(R.id.tv_apply_news_enterpriseVIP)
    TextView tvApplyNewsEnterpriseVIP;
    @BindView(R.id.tv_apply_personalVIP)
    TextView tvApplyPersonalVIP;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applay_vip);
        ButterKnife.bind(this);
        userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
    }

    @OnClick({R.id.iv_applay_vip_back, R.id.tv_apply_enterpriseVIP, R.id.tv_apply_news_enterpriseVIP, R.id.tv_apply_personalVIP})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_applay_vip_back:
                finish();
                break;
            case R.id.tv_apply_enterpriseVIP:
                T.showShort(this,"暂不支持企业级VIP");
                break;
            case R.id.tv_apply_news_enterpriseVIP:
                T.showShort(this,"暂不支持企业级VIP");
                break;
            case R.id.tv_apply_personalVIP:
                Intent intent=new Intent(this,PersonalApplayVipActivity.class);
                startActivity(intent);
                break;
        }
    }

}
