package com.min.threeminutestoteach.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WhoCanSeeActivity extends BaseActivity {
    @BindView(R.id.ll_mine_card_back)
    LinearLayout llMineCardBack;
    @BindView(R.id.iv_whoSee_all)
    ImageView ivWhoSeeAll;
    @BindView(R.id.rl_whoSee_all)
    RelativeLayout rlWhoSeeAll;
    @BindView(R.id.iv_whoSee_vip)
    ImageView ivWhoSeeVip;
    @BindView(R.id.rl_whoSee_vip)
    RelativeLayout rlWhoSeeVip;
    @BindView(R.id.tv_whoSee_complie)
    TextView tvWhoSeeComplie;
    private String whoSee = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_can_see);
        ButterKnife.bind(this);
        whoSee = getIntent().getStringExtra("whoSee");
        initView();
    }

    private void initView() {
        if (whoSee.equals("0")) {
            ivWhoSeeAll.setVisibility(View.VISIBLE);
            ivWhoSeeVip.setVisibility(View.INVISIBLE);
        } else {
            ivWhoSeeAll.setVisibility(View.INVISIBLE);
            ivWhoSeeVip.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.ll_mine_card_back, R.id.rl_whoSee_all, R.id.rl_whoSee_vip,R.id.tv_whoSee_complie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_card_back:
                finish();
                break;
            case R.id.tv_whoSee_complie:
                Intent intent=new Intent();
                intent.putExtra("whoSee",whoSee);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.rl_whoSee_all:
                ivWhoSeeAll.setVisibility(View.VISIBLE);
                ivWhoSeeVip.setVisibility(View.INVISIBLE);
                whoSee = "0";
                break;
            case R.id.rl_whoSee_vip:
                ivWhoSeeAll.setVisibility(View.INVISIBLE);
                ivWhoSeeVip.setVisibility(View.VISIBLE);
                whoSee = "1";
                break;
        }
    }


}
