package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.LoadViewPagerAdapter;
import com.ike.communityalliance.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告页面
 */
public class LoadActivity extends BaseActivity {
    private ImageView[] imageViews = new ImageView[3];
    private ViewPager pager;
    private LoadViewPagerAdapter adapter;
    private List<View> list = new ArrayList<View>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView iv_load_go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        sharedPreferences = getSharedPreferences("load", LoadActivity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean bool=sharedPreferences.getBoolean("boolean",false);
        if(bool){
            startActivity(new Intent(LoadActivity.this,LogoActivity.class));
            finish();
        }
        initView();
        setImage(0);
        ViewPagerView();
    }

    private void ViewPagerView() {
        iv_load_go= (ImageView) findViewById(R.id.iv_load_go);
        pager = (ViewPager) findViewById(R.id.vp_load);
        List<View> list = new ArrayList<View>();
        list.add(getLayoutInflater().inflate(R.layout.load_image_1, null));
        list.add(getLayoutInflater().inflate(R.layout.load_image_2, null));
        list.add(getLayoutInflater().inflate(R.layout.load_image_3, null));
        adapter = new LoadViewPagerAdapter(list);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(listener);
       iv_load_go.setOnClickListener(onClickListener);
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setImage(position);
            if(position==2){
               iv_load_go.setVisibility(View.VISIBLE);
            }else{
                iv_load_go.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoadActivity.this, LoginActivity.class);
            startActivity(intent);
            editor.putBoolean("boolean", true);
            editor.commit();
            finish();
        }
    };


    private void setImage(int index) {
        switch (index) {
            case 0:
                imageViews[0].setImageResource(R.mipmap.load_checked);
                imageViews[1].setImageResource(R.mipmap.load_unchecked);
                imageViews[2].setImageResource(R.mipmap.load_unchecked);
                break;
            case 1:
                imageViews[0].setImageResource(R.mipmap.load_unchecked);
                imageViews[1].setImageResource(R.mipmap.load_checked);
                imageViews[2].setImageResource(R.mipmap.load_unchecked);
                break;
            case 2:
                imageViews[0].setImageResource(R.mipmap.load_unchecked);
                imageViews[1].setImageResource(R.mipmap.load_unchecked);
                imageViews[2].setImageResource(R.mipmap.load_checked);
                break;
            default:
                break;
        }
    }

    private void initView() {
        imageViews[0] = (ImageView) findViewById(R.id.load_image_round1);
        imageViews[1] = (ImageView) findViewById(R.id.load_image_round2);
        imageViews[2] = (ImageView) findViewById(R.id.load_image_round3);
    }
}
