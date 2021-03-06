package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
    private String checkVip;
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
                checkVip=getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_VIP, "");
                if(checkVip.equals("1")){
                    setResult(RESULT_OK);
                }
                finish();
                break;
            case R.id.tv_apply_enterpriseVIP:
                T.showShort(this,"暂不支持企业级VIP");
                break;
            case R.id.tv_apply_news_enterpriseVIP:
                T.showShort(this,"暂不支持企业级VIP");
                break;
            case R.id.tv_apply_personalVIP:
                checkVip=getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_VIP, "");
                if(checkVip.equals("1")){
                    T.showShort(this,"您已经是VIP用户了");
                    return;
                }
                Intent intent=new Intent(this,PersonalApplayVipActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            checkVip=getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_VIP, "");
            if(checkVip.equals("1")){
                setResult(RESULT_OK);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
