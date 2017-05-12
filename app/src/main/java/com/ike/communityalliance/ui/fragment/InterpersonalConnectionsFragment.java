package com.ike.communityalliance.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.RelationShipActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 人脉关系
 * Created by T-BayMax on 2017/3/14.
 */

public class InterpersonalConnectionsFragment extends Fragment {


    @BindView(R.id.rl_relation_colleague)
    RelativeLayout rlRelationColleague;
    @BindView(R.id.rl_relation_schoolfellow)
    RelativeLayout rlRelationSchoolfellow;
    @BindView(R.id.rl_relation_countrymen)
    RelativeLayout rlRelationCountrymen;
    @BindView(R.id.rl_relation_relative)
    RelativeLayout rlRelationRelative;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_connections, container, false);
        ButterKnife.bind(this, containerView);
        return containerView;
    }


    @OnClick({R.id.rl_relation_colleague, R.id.rl_relation_schoolfellow, R.id.rl_relation_countrymen, R.id.rl_relation_relative})
    public void onViewClicked(View view) {
        Intent intent=new Intent(getContext(),RelationShipActivity.class);
        String title="";
        switch (view.getId()) {
            case R.id.rl_relation_colleague:
                intent.putExtra("type",2);
                title="同事";
                break;
            case R.id.rl_relation_schoolfellow:
                intent.putExtra("type",3);
                title="校友";
                break;
            case R.id.rl_relation_countrymen:
                intent.putExtra("type",2);
                title="同乡";
                break;
            case R.id.rl_relation_relative:
                intent.putExtra("type",1);
                title="亲人";
                break;
        }

        intent.putExtra("title",title);
        startActivity(intent);
    }
}
