package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import java.util.Arrays;
import java.util.List;

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
    @BindView(R.id.rg_user_detail_more_like)
    RadioGroup rgUserDetailMoreLike;
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
    private String userId,otherUserId;
    private MoreUserDetailInfo moreUserDetailInfo;
    private List<String> hobbyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_user_detail_info);
        ButterKnife.bind(this);
        userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        otherUserId=getIntent().getStringExtra("friendId");
        getMoreUserDetail(userId,otherUserId);
    }

    private void getMoreUserDetail(String userId, String otherUserId) {
        HttpUtils.checkUserInfo("/selectUserInfo", userId, otherUserId, "0", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MoreUserDetailInfoActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<MoreUserDetailInfo>>() {
                }.getType();
                Code<MoreUserDetailInfo> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    moreUserDetailInfo=code.getData();
                    initData();
                } else {
                    T.showShort(MoreUserDetailInfoActivity.this,"服务器异常");
                }
            }
        });
    }

    private void initData() {
        tvUserDetailMoreInfoName.setText(moreUserDetailInfo.getFullName());
        tvUserDetailMoreInfoQQ.setText(moreUserDetailInfo.getQQ());
        tvUserDetailMoreWechat.setText(moreUserDetailInfo.getWechat());
        hobbyList= Arrays.asList(moreUserDetailInfo.getFavour().split(","));
        initHobby();
        tvUserDetailMoreInfoSchool.setText(moreUserDetailInfo.getFinishSchool());
        tvUserDetailMoreInfoConstellation.setText(moreUserDetailInfo.getConstellation());
        tvUserDetailMoreInfoBloodtype.setText(moreUserDetailInfo.getBloodType());
        tvUserDetailMoreInfoCompany.setText(moreUserDetailInfo.getCompany());
        tvUserDetailMoreInfoPosition.setText(moreUserDetailInfo.getPosition());
        tvUserDetailMoreInfoMarriage.setText(moreUserDetailInfo.getMarriage().equals("0")?"未婚":"已婚");
    }
    private void initHobby() {
        if(hobbyList==null){
            return;
        }
        for (int i = 0; i < rgUserDetailMoreLike.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) rgUserDetailMoreLike.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb= (CheckBox) ll.getChildAt(j);
                if(hobbyList.contains(rb.getText().toString())){
                    rb.setChecked(true);
                }
            }
        }
    }
    @OnClick(R.id.tv_user_detail_more_info_back)
    public void onViewClicked() {
        finish();
    }
}
