package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.ui.fragment.ChessAndCardsFragment;
import com.ike.communityalliance.ui.fragment.InterestMoreFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InteresitingActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
@BindView(R.id.tl_interesting)
TabLayout tl_interesting;
    @BindView(R.id.vp_interesting)
    ViewPager vp_interesting;
    @BindView(R.id.iv_interest_createGroup)
    ImageView iv_interest_createGroup;
    @BindView(R.id.ll_interest_back)
    LinearLayout ll_interest_back;
    private List<String> mTitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intersiting);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initTitle();
        initFragments();
    }

    //初始化碎片
    private void initFragments() {
        tl_interesting.setTabMode(TabLayout.MODE_FIXED);
        vp_interesting.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //此方法用来显示tab上的名字
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
            @Override
            public Fragment getItem(int position) {
                if(position<mTitle.size()-1){
                    ChessAndCardsFragment chessAndCardsFragment=new ChessAndCardsFragment();
                    chessAndCardsFragment.setHobbyId(position+1+"");
                    return chessAndCardsFragment;
                }
                InterestMoreFragment interestMoreFragment=new InterestMoreFragment();
                interestMoreFragment.setHobbyId(position+1+"");
                return interestMoreFragment;
            }

            @Override
            public int getCount() {
                return mTitle.size();
            }
        });
        tl_interesting.setupWithViewPager(vp_interesting);
        tl_interesting.addOnTabSelectedListener(this);
    }
    //初始化标题
    private void initTitle() {
        mTitle=new ArrayList<>();
        mTitle.add("棋牌");
        mTitle.add("游戏");
        mTitle.add("数码");
        mTitle.add("理财");
        mTitle.add("更多");
    }
     @OnClick({R.id.iv_interest_createGroup,R.id.ll_interest_back})
    public void interestOnClick(View view){
         switch (view.getId()) {
             case R.id.ll_interest_back:
                 finish();
                 break;
             case R.id.iv_interest_createGroup:
                 Intent intent=new Intent(this,SelectFriendsActivity.class);
                 intent.putExtra("createInterestGroup",true);
                 startActivity(intent);
                 finish();
                 break;
         }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_interesting.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
