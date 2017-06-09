package com.ike.communityalliance.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.ui.activity.SelectFriendsActivity;
import com.ike.mylibrary.util.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by just on 2017/3/1.
 */

public class InterestGroupFragment extends Fragment implements TabLayout.OnTabSelectedListener{
    @BindView(R.id.tl_interesting)
    TabLayout tl_interesting;
    @BindView(R.id.vp_interesting)
    ViewPager vp_interesting;
    @BindView(R.id.iv_interest_createGroup)
    ImageView iv_interest_createGroup;
    @BindView(R.id.ll_interest_back)
    LinearLayout ll_interest_back;
    private List<String> mTitle;
    private  String userId;
    private SharedPreferences sp;
    private String checkVip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View containerView=inflater.inflate(R.layout.activity_intersiting,container,false);
        ButterKnife.bind(this,containerView);
        sp=getContext().getSharedPreferences("config",getContext().MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID,"");
        checkVip=sp.getString(Const.LOGIN_VIP,"");
        init();
        return containerView;
    }

    private void init() {
        initTitle();
        initFragments();
    }

    //初始化碎片
    private void initFragments() {
        tl_interesting.setTabMode(TabLayout.MODE_FIXED);
        vp_interesting.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
                break;
            case R.id.iv_interest_createGroup:
                if(checkVip.equals("0")){
                    T.showShort(getContext(),"只有VIP用户才能创建兴趣联盟");
                    return;
                }
                Intent intent=new Intent(getContext(),SelectFriendsActivity.class);
                intent.putExtra("createInterestGroup",true);
                startActivity(intent);
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
