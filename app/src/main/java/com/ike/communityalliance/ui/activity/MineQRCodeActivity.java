package com.ike.communityalliance.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.squareup.picasso.Picasso;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineQRCodeActivity extends BaseActivity {

    @BindView(R.id.iv_mine_QR_back)
    ImageView ivMineQRBack;
    @BindView(R.id.iv_mine_QR_userIcon)
    XCRoundRectImageView ivMineQRUserIcon;
    @BindView(R.id.tv_mine_QR_name)
    TextView tvMineQRName;
    @BindView(R.id.tv_mine_QR_age)
    TextView tvMineQRAge;
    @BindView(R.id.iv_mine_QR_sex)
    ImageView ivMineQRSex;
    @BindView(R.id.iv_mine_QR_code)
    ImageView ivMineQRCode;
private String userId;
private String userName;
private String sex;
private String age;
private String userPortraitUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_qrcode);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        userId=getIntent().getStringExtra("userId");
        userName=getIntent().getStringExtra("userName");
        sex=getIntent().getStringExtra("sex");
        age=getIntent().getStringExtra("age");
        userPortraitUrl=getIntent().getStringExtra("userPortraitUrl");
        Bitmap codeImg= EncodingUtils.createQRCode(userId,400,400,null);
        Picasso.with(this).load(userPortraitUrl).into(ivMineQRUserIcon);
        tvMineQRName.setText(userName);
        tvMineQRAge.setText(age);
        ivMineQRSex.setImageResource(sex.equals("1")?R.drawable.mine_man:R.drawable.mine_women);
        ivMineQRCode.setImageBitmap(codeImg);
    }

    @OnClick(R.id.iv_mine_QR_back)
    public void onViewClicked() {
        finish();
    }
}
