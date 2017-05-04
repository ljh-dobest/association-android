package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MorePersonalInfoActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.tv_personal_more_info_back)
    TextView tvPersonalMoreInfoBack;
    @BindView(R.id.tv_personal_more_info_save)
    TextView tvPersonalMoreInfoSave;
    @BindView(R.id.tv_personal_more_info_name)
    TextView tvPersonalMoreInfoName;
    @BindView(R.id.tv_personal_more_info_name_status)
    TextView tvPersonalMoreInfoNameStatus;
    @BindView(R.id.sw_personal_more_info_name_status)
    SwitchButton swPersonalMoreInfoNameStatus;
    @BindView(R.id.tv_personal_more_info_QQ)
    TextView tvPersonalMoreInfoQQ;
    @BindView(R.id.tv_personal_more_info_QQ_status)
    TextView tvPersonalMoreInfoQQStatus;
    @BindView(R.id.sw_personal_more_info_QQ_status)
    SwitchButton swPersonalMoreInfoQQStatus;
    @BindView(R.id.tv_personal_more_info_wechat)
    TextView tvPersonalMoreInfoWechat;
    @BindView(R.id.tv_personal_more_info_wechat_status)
    TextView tvPersonalMoreInfoWechatStatus;
    @BindView(R.id.sw_personal_more_info_wechat_status)
    SwitchButton swPersonalMoreInfoWechatStatus;
    @BindView(R.id.rg_verifyInfo_like)
    RadioGroup rgVerifyInfoLike;
    @BindView(R.id.tv_personal_more_info_school_status)
    TextView tvPersonalMoreInfoSchoolStatus;
    @BindView(R.id.sw_personal_more_info_school_status)
    SwitchButton swPersonalMoreInfoSchoolStatus;
    @BindView(R.id.tv_personal_more_info_constellation_status)
    TextView tvPersonalMoreInfoConstellationStatus;
    @BindView(R.id.sw_personal_more_info_constellation_status)
    SwitchButton swPersonalMoreInfoConstellationStatus;
    @BindView(R.id.tv_personal_more_info_bloodtype_status)
    TextView tvPersonalMoreInfoBloodtypeStatus;
    @BindView(R.id.sw_personal_more_info_bloodtype_status)
    SwitchButton swPersonalMoreInfoBloodtypeStatus;
    @BindView(R.id.tv_personal_more_info_company_status)
    TextView tvPersonalMoreInfoCompanyStatus;
    @BindView(R.id.sw_personal_more_info_company_status)
    SwitchButton swPersonalMoreInfoCompanyStatus;
    @BindView(R.id.tv_personal_more_info_position_status)
    TextView tvPersonalMoreInfoPositionStatus;
    @BindView(R.id.sw_personal_more_info_position_status)
    SwitchButton swPersonalMoreInfoPositionStatus;
    @BindView(R.id.tv_personal_more_info_marriage_status)
    TextView tvPersonalMoreInfoMarriageStatus;
    @BindView(R.id.sw_personal_more_info_marriage_status)
    SwitchButton swPersonalMoreInfoMarriageStatus;
    private SharedPreferences sp;
    private  String userId;
    private MorePersonalInfo morePersonalInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_personal_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        userId=sp.getString(Const.LOGIN_ID,"");
        getViewData();
        initView();
    }

    private void initView() {
        swPersonalMoreInfoNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoQQStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoWechatStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoSchoolStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoConstellationStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoCompanyStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoPositionStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoBloodtypeStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoMarriageStatus.setOnCheckedChangeListener(this);

    }

    private void getViewData() {
        HttpUtils.getMorePersonalInfo("/selectMoreUserInfo", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MorePersonalInfoActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<MorePersonalInfo>>() {
                }.getType();
                Code<MorePersonalInfo> result=gson.fromJson(response,type);
                if(result.getCode()==200){
                    morePersonalInfo=result.getData();
                    initData();
                }else{
                    T.showShort(MorePersonalInfoActivity.this,"请求失败");
                }
            }
        });

    }

    private void initData() {
        if(morePersonalInfo.getFullName()==null){
            return;
        }
        tvPersonalMoreInfoName.setText(morePersonalInfo.getFullName().getName());
        swPersonalMoreInfoNameStatus.setChecked( morePersonalInfo.getFullName().getStatus()==0?false:true);
        tvPersonalMoreInfoQQ.setText(morePersonalInfo.getQQ().getName());
        swPersonalMoreInfoQQStatus.setChecked( morePersonalInfo.getQQ().getStatus()==0?false:true);
        tvPersonalMoreInfoWechat.setText(morePersonalInfo.getWechat().getName());
        swPersonalMoreInfoWechatStatus.setChecked( morePersonalInfo.getWechat().getStatus()==0?false:true);
    }

    @OnClick({R.id.tv_personal_more_info_back, R.id.tv_personal_more_info_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personal_more_info_back:
                finish();
                break;
            case R.id.tv_personal_more_info_save:
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sw_personal_more_info_name_status:
                tvPersonalMoreInfoNameStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_QQ_status:
                tvPersonalMoreInfoQQStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_wechat_status:
                tvPersonalMoreInfoWechatStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_school_status:
                tvPersonalMoreInfoSchoolStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_constellation_status:
                tvPersonalMoreInfoConstellationStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_bloodtype_status:
                tvPersonalMoreInfoBloodtypeStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_company_status:
                tvPersonalMoreInfoCompanyStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_position_status:
                tvPersonalMoreInfoPositionStatus.setText(isChecked?"公开":"非公开");
                break;
            case R.id.sw_personal_more_info_marriage_status:
                tvPersonalMoreInfoMarriageStatus.setText(isChecked?"公开":"非公开");
                break;
        }
    }
}
