package com.min.threeminutestoteach.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BaseActivity;
import com.min.threeminutestoteach.ui.fragment.MineCollectFragment;
import com.min.threeminutestoteach.ui.fragment.MineTeachFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MineMessageActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
@BindView(R.id.iv_mineMessage_back)
    ImageView iv_mineMessage_back;
//    @BindView(R.id.tl_mineMessage)
//    TabLayout tl_mineMessage;
    @BindView(R.id.rg_mineMessage)
    RadioGroup rg_mineMessage;
    @BindView(R.id.rb_mineMessage_myHelp)
    RadioButton rb_mineMessage_myHelp;
    @BindView(R.id.rb_mineMessage_myAnswer)
    RadioButton rb_mineMessage_myAnswer;
    @BindView(R.id.vp_mineMessage)
    ViewPager vp_mineMessage;
    private List<Fragment> fragments=new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private boolean isSelected = false;
    private  String userId;
    private int[] tabBgs = {R.drawable.my_help_bg,R.drawable.my_answer_bg};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_message);
        ButterKnife.bind(this);
        userId=getIntent().getStringExtra("userId");
        initView();
    }

    private void initView() {
        rb_mineMessage_myHelp.setChecked(true);
        vp_mineMessage.setOffscreenPageLimit(2);
        MineTeachFragment mineTeachFragment =new MineTeachFragment();
        mineTeachFragment.setUserId(userId);
        MineCollectFragment mineCollectFragment =new MineCollectFragment();
        mineCollectFragment.setUserId(userId);
        fragments.add(mineTeachFragment);
        fragments.add(mineCollectFragment);
        titles.add("我的教学");
        titles.add("收藏教学");
        vp_mineMessage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        rg_mineMessage.setOnCheckedChangeListener(this);
        vp_mineMessage.addOnPageChangeListener(this);
    }
    private void changeTabStatus(TabLayout.Tab tab, boolean selected) {
        View view = tab.getCustomView();
        TextView txtTitle = (TextView) view.findViewById(R.id.tv_mineMessage_tab);
        if (selected) {
            txtTitle.setTextColor(getResources().getColor(R.color.black));
        } else {
            txtTitle.setTextColor(getResources().getColor(R.color.col_10DB9F));
        }
    }
    @OnClick({R.id.iv_mineMessage_back})
    public void onMineMessageViewClick(View view){
        switch (view.getId()) {
            case R.id.iv_mineMessage_back:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < rg_mineMessage.getChildCount(); i++) {
            RadioButton radioButton= (RadioButton) rg_mineMessage.getChildAt(i);
            radioButton.setTextColor(0xFF10DB9F);
            if(radioButton.isChecked()){
                radioButton.setTextColor(Color.BLACK);
                vp_mineMessage.setCurrentItem(i,true);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
         if (position==0){
             rb_mineMessage_myHelp.setChecked(true);
         }else{
             rb_mineMessage_myAnswer.setChecked(true);
         }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
