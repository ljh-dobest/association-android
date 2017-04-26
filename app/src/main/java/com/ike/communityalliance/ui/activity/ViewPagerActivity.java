package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerActivity extends BaseActivity {
    private ViewPager mPager;
    private List<String> imgList;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        imgList=getIntent().getStringArrayListExtra("imgList");
         position=getIntent().getIntExtra("position",0);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(ViewPagerActivity.this);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Picasso.with(ViewPagerActivity.this).load(HttpUtils.IMAGE_RUL+imgList.get(position)).error(R.drawable.rc_default_portrait).into(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
        mPager.setCurrentItem(position);
    }
}
