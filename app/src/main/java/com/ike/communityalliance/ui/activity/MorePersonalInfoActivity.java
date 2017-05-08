package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.EditMorePersonalInfo;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IMorePersonalInfoView;
import com.ike.communityalliance.presenter.MorePersonalInfoPresenter;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MorePersonalInfoActivity extends BaseMvpActivity<IMorePersonalInfoView,MorePersonalInfoPresenter> implements IMorePersonalInfoView,CompoundButton.OnCheckedChangeListener {

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
    @BindView(R.id.tv_personal_more_info_school)
    TextView tvPersonalMoreInfoSchool;
    @BindView(R.id.tv_personal_more_info_constellation)
    TextView tvPersonalMoreInfoConstellation;
    @BindView(R.id.tv_personal_more_info_bloodtype)
    TextView tvPersonalMoreInfoBloodtype;
    @BindView(R.id.tv_personal_more_info_company)
    TextView tvPersonalMoreInfoCompany;
    @BindView(R.id.tv_personal_more_info_position)
    TextView tvPersonalMoreInfoPosition;
    @BindView(R.id.tv_personal_more_info_marriage)
    TextView tvPersonalMoreInfoMarriage;
    private SharedPreferences sp;
    private String userId;
    private MorePersonalInfo morePersonalInfo;
    private String fullName;
    private String SfullName="0";
    private String QQ;
    private String SQQ="0";
    private String wechat;
    private String Swechat="0";
    private String favour;
    private String Sfavour="1";
    private String finishSchool;
    private String SfinishSchool="0";
    private String constellation;
    private String Sconstellation="0";
    private String bloodType;
    private String SbloodType="0";
    private String marriage;
    private String Smarriage="0";
    private String company;
    private String Scompany="0";
    private String position;
    private String Sposition="0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_personal_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        fullName=sp.getString(Const.LOGIN_USERNAME,"");
      getMorePersonalInfoData(userId);
        initView();
    }

    @Override
    public MorePersonalInfoPresenter initPresenter() {
        return new MorePersonalInfoPresenter();
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

    private void initData() {
        tvPersonalMoreInfoName.setText(fullName);
        if (morePersonalInfo.getFullName() == null) {
            return;
        }
        tvPersonalMoreInfoName.setText(morePersonalInfo.getFullName().getName());
        swPersonalMoreInfoNameStatus.setChecked(morePersonalInfo.getFullName().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoQQ.setText(morePersonalInfo.getQQ().getName());
        swPersonalMoreInfoQQStatus.setChecked(morePersonalInfo.getQQ().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoWechat.setText(morePersonalInfo.getWechat().getName());
        swPersonalMoreInfoWechatStatus.setChecked(morePersonalInfo.getWechat().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoSchool.setText(morePersonalInfo.getFinishSchool().getName());
        swPersonalMoreInfoSchoolStatus.setChecked(morePersonalInfo.getFinishSchool().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoBloodtype.setText(morePersonalInfo.getBloodType().getName());
        swPersonalMoreInfoBloodtypeStatus.setChecked(morePersonalInfo.getBloodType().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoConstellation.setText(morePersonalInfo.getConstellation().getName());
        swPersonalMoreInfoConstellationStatus.setChecked(morePersonalInfo.getConstellation().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoCompany.setText(morePersonalInfo.getCompany().getName());
        swPersonalMoreInfoCompanyStatus.setChecked(morePersonalInfo.getCompany().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoPosition.setText(morePersonalInfo.getPosition().getName());
        swPersonalMoreInfoPositionStatus.setChecked(morePersonalInfo.getPosition().getStatus() == 0 ? false : true);
        tvPersonalMoreInfoMarriage.setText(morePersonalInfo.getMarriage().getName());
        swPersonalMoreInfoMarriageStatus.setChecked(morePersonalInfo.getMarriage().getStatus() == 0 ? false : true);

    }

    @OnClick({R.id.tv_personal_more_info_back, R.id.tv_personal_more_info_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personal_more_info_back:
                finish();
                break;
            case R.id.tv_personal_more_info_save:
                LoadDialog.show(this);
                saveData();
                EditMorePersonalInfo editMorePersonalInfo=new EditMorePersonalInfo(userId,fullName,SfullName,
                        QQ,SQQ,wechat,Swechat,finishSchool,favour,Sfavour,SfinishSchool,constellation,Sconstellation,
                        bloodType,SbloodType,company,Scompany,position,Sposition,marriage,Smarriage);
              presenter.postMorePersonalInfo(editMorePersonalInfo);
                break;
        }
    }

    private void saveData() {
        fullName=tvPersonalMoreInfoName.getText().toString();
        QQ=tvPersonalMoreInfoQQ.getText().toString();
        wechat=tvPersonalMoreInfoWechat.getText().toString();
        finishSchool=tvPersonalMoreInfoSchool.getText().toString();
        constellation=tvPersonalMoreInfoConstellation.getText().toString();
        bloodType=tvPersonalMoreInfoBloodtype.getText().toString();
        company=tvPersonalMoreInfoCompany.getText().toString();
        position=tvPersonalMoreInfoPosition.getText().toString();
        marriage=tvPersonalMoreInfoMarriage.getText().toString();
       presenter.getHobby(rgVerifyInfoLike);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sw_personal_more_info_name_status:
                tvPersonalMoreInfoNameStatus.setText(isChecked ? "公开" : "非公开");
                SfullName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_QQ_status:
                tvPersonalMoreInfoQQStatus.setText(isChecked ? "公开" : "非公开");
                SQQ = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_wechat_status:
                tvPersonalMoreInfoWechatStatus.setText(isChecked ? "公开" : "非公开");
                Swechat = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_school_status:
                tvPersonalMoreInfoSchoolStatus.setText(isChecked ? "公开" : "非公开");
                SfinishSchool = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_constellation_status:
                tvPersonalMoreInfoConstellationStatus.setText(isChecked ? "公开" : "非公开");
                Sconstellation = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_bloodtype_status:
                tvPersonalMoreInfoBloodtypeStatus.setText(isChecked ? "公开" : "非公开");
                SbloodType = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_company_status:
                tvPersonalMoreInfoCompanyStatus.setText(isChecked ? "公开" : "非公开");
                Scompany = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_position_status:
                tvPersonalMoreInfoPositionStatus.setText(isChecked ? "公开" : "非公开");
                Sposition = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_marriage_status:
                tvPersonalMoreInfoMarriageStatus.setText(isChecked ? "公开" : "非公开");
                Smarriage = (isChecked ? "1" : "0");
                break;
        }
    }

    @Override
    public void getMorePersonalInfoData(String userId) {
      presenter.getMorePersonalInfoData(userId);
    }

    @Override
    public void setMorePersoalInfoData(MorePersonalInfo info) {
        morePersonalInfo=info;
        initData();
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this,errorString);
        LoadDialog.dismiss(this);
    }

    @Override
    public void setHobby(String hobby) {
        this.favour=hobby;
    }

    @Override
    public void saveSucceed() {
        LoadDialog.dismiss(this);
        T.showShort(this,"保存成功");
        finish();
    }
}
