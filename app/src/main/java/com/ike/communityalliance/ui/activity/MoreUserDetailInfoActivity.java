package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.MoreUserDetailInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MoreUserDetailInfoActivity extends BaseActivity {

    @BindView(R.id.tv_user_detail_more_info_back)
    TextView tvUserDetailMoreInfoBack;
    @BindView(R.id.tv_user_detail_more_info_name)
    TextView tvUserDetailMoreInfoName;
    @BindView(R.id.tv_user_detail_more_info_QQ)
    TextView tvUserDetailMoreInfoQQ;
    @BindView(R.id.tv_user_detail_more_wechat)
    TextView tvUserDetailMoreWechat;
    @BindView(R.id.tv_user_detail_more_info_school)
    TextView tvUserDetailMoreInfoSchool;
    @BindView(R.id.tv_user_detail_more_info_constellation)
    TextView tvUserDetailMoreInfoConstellation;
    @BindView(R.id.tv_user_detail_more_info_bloodtype)
    TextView tvUserDetailMoreInfoBloodtype;
    @BindView(R.id.tv_user_detail_more_info_company)
    TextView tvUserDetailMoreInfoCompany;
    @BindView(R.id.tv_user_detail_more_info_position)
    TextView tvUserDetailMoreInfoPosition;
    @BindView(R.id.tv_user_detail_more_info_marriage)
    TextView tvUserDetailMoreInfoMarriage;
    @BindView(R.id.tv_user_detail_more_info_homeplace)
    TextView tvUserDetailMoreInfoHomeplace;
    @BindView(R.id.tv_user_detail_more_info_industry)
    TextView tvUserDetailMoreInfoIndustry;
    @BindView(R.id.tv_user_detail_more_info_fatherName)
    TextView tvUserDetailMoreInfoFatherName;
    @BindView(R.id.tv_user_detail_more_info_motherName)
    TextView tvUserDetailMoreInfoMotherName;
    @BindView(R.id.tv_user_detail_more_info_spouseName)
    TextView tvUserDetailMoreInfoSpouseName;
    @BindView(R.id.tv_user_detail_more_info_childrenName)
    TextView tvUserDetailMoreInfoChildrenName;
    @BindView(R.id.tv_user_detail_more_info_childrenSchool)
    TextView tvUserDetailMoreInfoChildrenSchool;
    @BindView(R.id.tv_user_detail_more_info_mobile)
    TextView tvUserDetailMoreInfoMobile;
    @BindView(R.id.tv_user_detail_more_info_birthday)
    TextView tvUserDetailMoreInfoBirthday;
    private String userId, otherUserId;
    private MoreUserDetailInfo morePersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_user_detail_info);
        ButterKnife.bind(this);
        userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        otherUserId = getIntent().getStringExtra("friendId");
        getMoreUserDetail(userId, otherUserId);
    }

    private void getMoreUserDetail(String userId, String otherUserId) {
        HttpUtils.checkUserInfo("/selectUserInfo", userId, otherUserId, "0", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MoreUserDetailInfoActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<MoreUserDetailInfo>>() {
                }.getType();
                Code<MoreUserDetailInfo> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    morePersonalInfo = code.getData();
                    initData();
                } else {
                    T.showShort(MoreUserDetailInfoActivity.this, "服务器异常");
                }
            }
        });
    }

    private void initData() {
        if (morePersonalInfo.getFullName() == null) {
            return;
        }
        tvUserDetailMoreInfoName.setText(morePersonalInfo.getFullName());
        tvUserDetailMoreInfoMobile.setText(morePersonalInfo.getMobile());
        tvUserDetailMoreInfoBirthday.setText(morePersonalInfo.getBirthday());
        tvUserDetailMoreInfoHomeplace.setText(morePersonalInfo.getHomeplace().replace(",",""));
        tvUserDetailMoreInfoQQ.setText(morePersonalInfo.getQQ());
        tvUserDetailMoreWechat.setText(morePersonalInfo.getWechat());
        tvUserDetailMoreInfoSchool.setText(morePersonalInfo.getFinishSchool());
        tvUserDetailMoreInfoConstellation.setText(morePersonalInfo.getConstellation());
        tvUserDetailMoreInfoBloodtype.setText(morePersonalInfo.getBloodType());
        tvUserDetailMoreInfoCompany.setText(morePersonalInfo.getCompany());
        tvUserDetailMoreInfoPosition.setText(morePersonalInfo.getPosition());
        if(morePersonalInfo.getMarriage()!=null){
            tvUserDetailMoreInfoMarriage.setText(morePersonalInfo.getMarriage().equals("0")?"未婚" : "已婚");
        }
        tvUserDetailMoreInfoIndustry.setText(morePersonalInfo.getIndustry());
        tvUserDetailMoreInfoFatherName.setText(morePersonalInfo.getFatherName());
        tvUserDetailMoreInfoName.setText(morePersonalInfo.getMotherName());
        tvUserDetailMoreInfoSpouseName.setText(morePersonalInfo.getSpouseName());
        tvUserDetailMoreInfoChildrenName.setText(morePersonalInfo.getChildrenName());
        tvUserDetailMoreInfoChildrenSchool.setText(morePersonalInfo.getChildrenSchool());
    }


    @OnClick(R.id.tv_user_detail_more_info_back)
    public void onViewClicked() {
        finish();
    }
}
