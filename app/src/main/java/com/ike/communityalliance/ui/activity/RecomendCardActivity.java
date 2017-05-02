package com.ike.communityalliance.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecomendCardActivity extends AppCompatActivity {

    @BindView(R.id.tv_recomend_card_back)
    TextView tvRecomendCardBack;
    @BindView(R.id.tv_recomend_card_share)
    TextView tvRecomendCardShare;
    @BindView(R.id.tv_recomend_card_code)
    TextView tvRecomendCardCode;
    @BindView(R.id.iv_recomend_card_code)
    ImageView ivRecomendCardCode;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomend_card);
        ButterKnife.bind(this);
     code=getIntent().getStringExtra("recommendId");
        initData();
    }

    private void initData() {
        tvRecomendCardCode.setText(code);
        Bitmap codeImg= EncodingUtils.createQRCode(code,222,222,null);
        ivRecomendCardCode.setImageBitmap(codeImg);
    }

    @OnClick({R.id.tv_recomend_card_back, R.id.tv_recomend_card_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recomend_card_back:
                finish();
                break;
            case R.id.tv_recomend_card_share:

                break;
        }
    }
}
