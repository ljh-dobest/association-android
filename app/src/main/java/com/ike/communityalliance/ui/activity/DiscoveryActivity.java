package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.TalkTalkBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoveryActivity extends AppCompatActivity {

    @BindView(R.id.iv_discovery_back)
    ImageView ivDiscoveryBack;
    @BindView(R.id.rl_discovery_friends)
    RelativeLayout rlDiscoveryFriends;
    @BindView(R.id.ll_discovery_interest_group)
    LinearLayout llDiscoveryInterestGroup;
    @BindView(R.id.iv_discovery_friends_point)
    ImageView ivDiscoveryFriendsPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.iv_discovery_back, R.id.rl_discovery_friends, R.id.ll_discovery_interest_group})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_discovery_back:
                finish();
                break;
            case R.id.rl_discovery_friends:
                startActivity( new Intent(this,ShareFriendsActivity.class));
                ivDiscoveryFriendsPoint.setVisibility(View.GONE);
                break;
            case R.id.ll_discovery_interest_group:
                Intent intent1 = new Intent(this, InteresitingActivity.class);
                startActivity(intent1);
                break;
        }
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventDicoverMessage(TalkTalkBean event) {
        ivDiscoveryFriendsPoint.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
