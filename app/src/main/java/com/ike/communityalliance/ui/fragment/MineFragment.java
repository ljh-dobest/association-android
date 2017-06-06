package com.ike.communityalliance.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpFragment;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IMineFragmentView;
import com.ike.communityalliance.presenter.MineFragmentPresenter;
import com.ike.communityalliance.ui.activity.ApplayVIPActivity;
import com.ike.communityalliance.ui.activity.FeedBackActivity;
import com.ike.communityalliance.ui.activity.MineCardActivity;
import com.ike.communityalliance.ui.activity.MineQRCodeActivity;
import com.ike.communityalliance.ui.activity.MineRecomendActivity;
import com.ike.communityalliance.ui.activity.PersonalInformationActivity;
import com.ike.communityalliance.ui.activity.RecommendActivity;
import com.ike.communityalliance.ui.activity.RelationMapActivity;
import com.ike.communityalliance.ui.activity.SettingActivity;
import com.ike.communityalliance.ui.activity.SignPickerActivity;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.jrmf360.rylib.JrmfClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by just on 2017/3/1.
 */

public class MineFragment extends BaseMvpFragment<IMineFragmentView, MineFragmentPresenter> implements IMineFragmentView {
    @BindView(R.id.iv_mine_card)
    ImageView ivMineCard;
    @BindView(R.id.iv_mine_userIcon)
    XCRoundRectImageView ivMineUserIcon;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.tv_mine_age)
    TextView tvMineAge;
    @BindView(R.id.iv_mine_sex)
    ImageView ivMineSex;
    @BindView(R.id.tv_mine_account)
    TextView tvMineAccount;
    @BindView(R.id.tv_mine_email)
    TextView tvMineEmail;
    @BindView(R.id.tv_mine_phone)
    TextView tvMinePhone;
    @BindView(R.id.tv_mine_birthday)
    TextView tvMineBirthday;
    @BindView(R.id.tv_mine_address)
    TextView tvMineAddress;
    @BindView(R.id.tv_mine_recommenerName)
    TextView tvMineRecommenerName;
    @BindView(R.id.tv_mine_claimerName)
    TextView tvMineClaimerName;
    @BindView(R.id.tv_mine_contributionNum)
    TextView tvMineContributionNum;
    @BindView(R.id.tv_mine_creditScore)
    TextView tv_mine_creditScore;
    @BindView(R.id.ll_mine_recommend)
    LinearLayout llMineRecommend;
    @BindView(R.id.ll_mine_wasRecomend)
    LinearLayout ll_mine_wasRecomend;
    @BindView(R.id.ll_mine_contacts)
    LinearLayout llMineContacts;
    @BindView(R.id.ll_mine_wallet)
    LinearLayout ll_mine_wallet;
    @BindView(R.id.ll_mine_feedback)
    LinearLayout llMineFeedback;
    @BindView(R.id.ll_mine_setting)
    LinearLayout llMineSetting;
    @BindView(R.id.tv_mine_sign)
    TextView tvMineSign;
    @BindView(R.id.iv_mine_edit)
    ImageView iv_mine_edit;
    @BindView(R.id.ll_mine_QR_code)
    LinearLayout llMineQRCode;
    @BindView(R.id.ll_mine_applay_vip)
    LinearLayout llMineApplayVip;
    private SharedPreferences sp;
    private Context mContext;
    private String userPortraitUrl, userName, nickName, age, sex, useId, email, recommendUserId,
            birthday, address, mobile, experience, creditScore, contributionScore, claimUserId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.mine_fragment, container, false);
        ButterKnife.bind(this, containerView);
        initView();
        return containerView;
    }

    private void initView() {
        mContext = getContext();
        sp = mContext.getSharedPreferences("config", Context.MODE_PRIVATE);
        userPortraitUrl = sp.getString(Const.userPortraitUrl, "");
        nickName = sp.getString(Const.LOGIN_NICKNAME, "");
        age = sp.getString(Const.LOGIN_AGE, "");
        sex = sp.getString(Const.LOGIN_SEX, "");
        useId = sp.getString(Const.LOGIN_ID, "");
        email = sp.getString(Const.LOGIN_EMAIL, "");
        birthday = sp.getString(Const.LOGIN_BIRTHDAY, "");
        address = sp.getString(Const.LOGIN_ADDRESS, "");
        mobile = sp.getString(Const.LOGIN_PHONE, "");
        experience = sp.getString(Const.LOGIN_EXPERIENCE, "");
        creditScore = sp.getString(Const.LOGIN_CREDITSCORE, "");
        contributionScore = sp.getString(Const.LOGIN_CONTRIBUTIONSCORE, "");
        recommendUserId = sp.getString(Const.LOGIN_RECOMMENDUSERID, "");
        claimUserId = sp.getString(Const.LOGIN_CLAIMUSERID, "");
        Picasso.with(mContext).load(userPortraitUrl).into(ivMineUserIcon);
        tvMineName.setText(nickName);
        tvMineAge.setText(age);
        if (sex.equals("1")) {
            ivMineSex.setImageResource(R.drawable.mine_man);
        } else if (sex.equals("2")) {
            ivMineSex.setImageResource(R.drawable.mine_women);
        } else {
            ivMineSex.setVisibility(View.GONE);
        }
        tvMineAccount.setText(useId);
        tvMineEmail.setText(email);
        tvMineBirthday.setText(birthday);
        tvMinePhone.setText(mobile);
        tvMineAddress.setText(address.replace(",", ""));
        tvMineRecommenerName.setText(recommendUserId);
        tvMineClaimerName.setText(claimUserId);
        tvMineContributionNum.setText(contributionScore);
        tv_mine_creditScore.setText(creditScore);
    }

    @Override
    public MineFragmentPresenter initPresenter() {
        return new MineFragmentPresenter();
    }


    @Override
    public void setData(ArrayList<UserInfo> data) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String errorString) {
    }

    @OnClick({R.id.iv_mine_card, R.id.ll_mine_recommend, R.id.ll_mine_contacts, R.id.ll_mine_wasRecomend,
            R.id.ll_mine_feedback, R.id.ll_mine_setting, R.id.tv_mine_sign, R.id.iv_mine_edit,
            R.id.ll_mine_wallet,R.id.ll_mine_QR_code,R.id.ll_mine_applay_vip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_sign:
                startActivity(new Intent(getContext(), SignPickerActivity.class));
                break;
            case R.id.iv_mine_card:
                startActivity(new Intent(getContext(), MineCardActivity.class));
                break;
            case R.id.iv_mine_edit:
                startActivityForResult(new Intent(getActivity(), PersonalInformationActivity.class), 110);
                break;
            case R.id.ll_mine_recommend:
                startActivity(new Intent(getContext(), RecommendActivity.class));
                break;
            case R.id.ll_mine_wasRecomend:
                startActivity(new Intent(getContext(), MineRecomendActivity.class));
                break;
            case R.id.ll_mine_wallet:
                JrmfClient.intentWallet(getActivity());
                break;
            case R.id.ll_mine_contacts:
                startActivity(new Intent(getContext(), RelationMapActivity.class));
                break;
            case R.id.ll_mine_QR_code:
                Intent intent =new Intent(getContext(), MineQRCodeActivity.class);
                intent.putExtra("userId",useId);
                intent.putExtra("userPortraitUrl",userPortraitUrl);
                intent.putExtra("userName",nickName);
                intent.putExtra("sex",sex);
                intent.putExtra("age",age);
                startActivity(intent);
                break;
            case R.id.ll_mine_feedback:
                startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;
            case R.id.ll_mine_applay_vip:
                startActivity(new Intent(getContext(), ApplayVIPActivity.class));
                break;
            case R.id.ll_mine_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 110) {
            initView();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
