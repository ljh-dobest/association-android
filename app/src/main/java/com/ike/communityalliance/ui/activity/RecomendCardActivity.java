package com.ike.communityalliance.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.network.HttpUtils;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
        ShareSDK.initSDK(this,"1d7a4e9e033cd");
     code=getIntent().getStringExtra("recommendId");
        initData();
    }

    private void initData() {
        tvRecomendCardCode.setText(code);
        Bitmap codeImg= EncodingUtils.createQRCode(code,222,222,null);
        saveMyBitmap(codeImg,code);
        ivRecomendCardCode.setImageBitmap(codeImg);
    }

    @OnClick({R.id.tv_recomend_card_back, R.id.tv_recomend_card_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_recomend_card_back:
                finish();
                break;
            case R.id.tv_recomend_card_share:
                OnekeyShare oks = new OnekeyShare();
                oks.disableSSOWhenAuthorize();
                oks.setTitle("推荐成功，马上注册!!!");
                oks.setTitleUrl("http://sharesdk.cn");
                oks.setText(tvRecomendCardCode.getText().toString());
     oks.setImagePath(HttpUtils.CACHE_PATH+"/"+code+".jpg");
                oks.setUrl("http://sharesdk.cn");
                oks.setComment("评论文本");
                oks.setSite(getString(R.string.app_name));
                oks.setSiteUrl("http://sharesdk.cn");
                oks.show(this);
                break;
        }
    }
    public void saveMyBitmap(Bitmap mBitmap,String bitName)  {
        File f = new File( HttpUtils.CACHE_PATH+"/"+bitName + ".jpg");
        if(!f.exists()){//判断文件是否真正存在,如果不存在,创建一个;
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
