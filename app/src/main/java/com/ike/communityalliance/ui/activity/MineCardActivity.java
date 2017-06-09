package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ike.communityalliance.R.id.tv_mine_card_reputation;

public class MineCardActivity extends BaseActivity {

    @BindView(R.id.ll_mine_card_back)
    AutoLinearLayout llMineCardBack;
    @BindView(R.id.iv_mine_card_userIcon)
    XCRoundRectImageView ivMineCardUserIcon;
    @BindView(R.id.tv_mine_card_name)
    TextView tvMineCardName;
    @BindView(R.id.iv_mine_card_sex)
    ImageView ivMineCardSex;
    @BindView(R.id.tv_mine_card_account)
    TextView tvMineCardAccount;
    @BindView(R.id.tv_mine_card_email)
    TextView tvMineCardEmail;
    @BindView(R.id.tv_mine_card_address)
    TextView tvMineCardAddress;
    @BindView(R.id.tv_mine_card_recommenerName)
    TextView tvMineCardRecommenerName;
    @BindView(R.id.tv_mine_card_claimerName)
    TextView tvMineCardClaimerName;
    @BindView(R.id.tv_mine_card_contributionNum)
    TextView tvMineCardContributionNum;
    @BindView(tv_mine_card_reputation)
    TextView tvMineCardReputation;
    @BindView(R.id.tv_mine_card_phone)
    TextView tvMineCardPhone;
    @BindView(R.id.tv_mine_card_birthday)
    TextView tvMineCardBirthday;

    private SharedPreferences sp;
    private String userPortraitUrl, userName, sex, mobile, useId, email, favour, recommendUserId,
            birthday, address, experience, creditScore, contributionScore, claimUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_card);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        sp = getSharedPreferences("config", mContext.MODE_PRIVATE);
        userPortraitUrl = sp.getString(Const.userPortraitUrl, "");
        userName = sp.getString(Const.LOGIN_USERNAME, "");
        favour = sp.getString(Const.LOGIN_FAVOUR, "");
        sex = sp.getString(Const.LOGIN_SEX, "");
        useId = sp.getString(Const.LOGIN_ID, "");
        email = sp.getString(Const.LOGIN_EMAIL, "");
        mobile = sp.getString(Const.LOGIN_PHONE, "");
        birthday = sp.getString(Const.LOGIN_BIRTHDAY, "");
        address = sp.getString(Const.LOGIN_ADDRESS, "");
        experience = sp.getString(Const.LOGIN_EXPERIENCE, "");
        creditScore = sp.getString(Const.LOGIN_CREDITSCORE, "");
        contributionScore = sp.getString(Const.LOGIN_CONTRIBUTIONSCORE, "");
        recommendUserId = sp.getString(Const.LOGIN_RECOMMENDUSERID, "");
        claimUserId = sp.getString(Const.LOGIN_CLAIMUSERID, "");
        Picasso.with(mContext).load(userPortraitUrl).into(ivMineCardUserIcon);
        tvMineCardName.setText(userName);
        if (sex.equals("1")) {
            ivMineCardSex.setImageResource(R.drawable.mine_man);
        } else if (sex.equals("2")) {
            ivMineCardSex.setImageResource(R.drawable.mine_women);
        } else {
            ivMineCardSex.setVisibility(View.GONE);
        }
        tvMineCardAccount.setText(useId);
        tvMineCardEmail.setText(email);
        tvMineCardBirthday.setText(birthday);
        tvMineCardPhone.setText(mobile);
        tvMineCardAddress.setText(address.replace(",", ""));
        tvMineCardRecommenerName.setText(recommendUserId);
        tvMineCardClaimerName.setText(claimUserId);
        tvMineCardContributionNum.setText(contributionScore);
        tvMineCardReputation.setText(creditScore);
    }

    @OnClick(R.id.ll_mine_card_back)
    public void onViewClicked() {
        finish();
    }
}
