package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MorePersonalInfoActivity extends BaseMvpActivity<IMorePersonalInfoView, MorePersonalInfoPresenter> implements IMorePersonalInfoView, CompoundButton.OnCheckedChangeListener {

    private final String[] marrage = new String[]{"未婚", "已婚"};
    @BindView(R.id.tv_personal_more_info_back)
    TextView tvPersonalMoreInfoBack;
    @BindView(R.id.tv_personal_more_info_save)
    TextView tvPersonalMoreInfoSave;
    @BindView(R.id.et_personal_more_info_name)
    EditText etPersonalMoreInfoName;
    @BindView(R.id.tv_personal_more_info_name_status)
    TextView tvPersonalMoreInfoNameStatus;
    @BindView(R.id.sw_personal_more_info_name_status)
    SwitchButton swPersonalMoreInfoNameStatus;
    @BindView(R.id.et_personal_more_info_QQ)
    EditText etPersonalMoreInfoQQ;
    @BindView(R.id.tv_personal_more_info_QQ_status)
    TextView tvPersonalMoreInfoQQStatus;
    @BindView(R.id.sw_personal_more_info_QQ_status)
    SwitchButton swPersonalMoreInfoQQStatus;
    @BindView(R.id.et_personal_more_info_wechat)
    EditText etPersonalMoreInfoWechat;
    @BindView(R.id.tv_personal_more_info_wechat_status)
    TextView tvPersonalMoreInfoWechatStatus;
    @BindView(R.id.sw_personal_more_info_wechat_status)
    SwitchButton swPersonalMoreInfoWechatStatus;
    @BindView(R.id.rg_morePersonalInfo_like)
    RadioGroup rgMorePersonalInfoLike;
    @BindView(R.id.et_personal_more_info_school)
    EditText etPersonalMoreInfoSchool;
    @BindView(R.id.tv_personal_more_info_school_status)
    TextView tvPersonalMoreInfoSchoolStatus;
    @BindView(R.id.sw_personal_more_info_school_status)
    SwitchButton swPersonalMoreInfoSchoolStatus;
    @BindView(R.id.et_personal_more_info_constellation)
    EditText etPersonalMoreInfoConstellation;
    @BindView(R.id.tv_personal_more_info_constellation_status)
    TextView tvPersonalMoreInfoConstellationStatus;
    @BindView(R.id.sw_personal_more_info_constellation_status)
    SwitchButton swPersonalMoreInfoConstellationStatus;
    @BindView(R.id.et_personal_more_info_bloodtype)
    EditText etPersonalMoreInfoBloodtype;
    @BindView(R.id.tv_personal_more_info_bloodtype_status)
    TextView tvPersonalMoreInfoBloodtypeStatus;
    @BindView(R.id.sw_personal_more_info_bloodtype_status)
    SwitchButton swPersonalMoreInfoBloodtypeStatus;
    @BindView(R.id.et_personal_more_info_company)
    EditText etPersonalMoreInfoCompany;
    @BindView(R.id.tv_personal_more_info_company_status)
    TextView tvPersonalMoreInfoCompanyStatus;
    @BindView(R.id.sw_personal_more_info_company_status)
    SwitchButton swPersonalMoreInfoCompanyStatus;
    @BindView(R.id.et_personal_more_info_position)
    EditText etPersonalMoreInfoPosition;
    @BindView(R.id.tv_personal_more_info_position_status)
    TextView tvPersonalMoreInfoPositionStatus;
    @BindView(R.id.sw_personal_more_info_position_status)
    SwitchButton swPersonalMoreInfoPositionStatus;
    @BindView(R.id.et_personal_more_info_marriage)
    EditText etPersonalMoreInfoMarriage;
    @BindView(R.id.tv_personal_more_info_marriage_status)
    TextView tvPersonalMoreInfoMarriageStatus;
    @BindView(R.id.sw_personal_more_info_marriage_status)
    SwitchButton swPersonalMoreInfoMarriageStatus;
    private SharedPreferences sp;
    private String userId;
    private MorePersonalInfo morePersonalInfo;
    private String fullName;
    private String SfullName = "0";
    private String QQ;
    private String SQQ = "0";
    private String wechat;
    private String Swechat = "0";
    private String favour;
    private String Sfavour = "1";
    private String finishSchool;
    private String SfinishSchool = "0";
    private String constellation;
    private String Sconstellation = "0";
    private String bloodType;
    private String SbloodType = "0";
    private String marriage="0";
    private String Smarriage = "0";
    private String company;
    private String Scompany = "0";
    private String position;
    private String Sposition = "0";
private List<String> hobbys;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_personal_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
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
        if (morePersonalInfo.getFullName().getName() == null) {
            return;
        }
        etPersonalMoreInfoName.setText(morePersonalInfo.getFullName().getName());
        swPersonalMoreInfoNameStatus.setChecked(morePersonalInfo.getFullName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoQQ.setText(morePersonalInfo.getQQ().getName());
        swPersonalMoreInfoQQStatus.setChecked(morePersonalInfo.getQQ().getStatus() == 0 ? false : true);
        etPersonalMoreInfoWechat.setText(morePersonalInfo.getWechat().getName());
        swPersonalMoreInfoWechatStatus.setChecked(morePersonalInfo.getWechat().getStatus() == 0 ? false : true);
        etPersonalMoreInfoSchool.setText(morePersonalInfo.getFinishSchool().getName());
        swPersonalMoreInfoSchoolStatus.setChecked(morePersonalInfo.getFinishSchool().getStatus() == 0 ? false : true);
        etPersonalMoreInfoBloodtype.setText(morePersonalInfo.getBloodType().getName());
        swPersonalMoreInfoBloodtypeStatus.setChecked(morePersonalInfo.getBloodType().getStatus() == 0 ? false : true);
        etPersonalMoreInfoConstellation.setText(morePersonalInfo.getConstellation().getName());
        swPersonalMoreInfoConstellationStatus.setChecked(morePersonalInfo.getConstellation().getStatus() == 0 ? false : true);
        etPersonalMoreInfoCompany.setText(morePersonalInfo.getCompany().getName());
        swPersonalMoreInfoCompanyStatus.setChecked(morePersonalInfo.getCompany().getStatus() == 0 ? false : true);
        etPersonalMoreInfoPosition.setText(morePersonalInfo.getPosition().getName());
        swPersonalMoreInfoPositionStatus.setChecked(morePersonalInfo.getPosition().getStatus() == 0 ? false : true);
        etPersonalMoreInfoMarriage.setText(morePersonalInfo.getMarriage().getName().equals("0")?"未婚":"已婚");
        swPersonalMoreInfoMarriageStatus.setChecked(morePersonalInfo.getMarriage().getStatus() == 0 ? false : true);
        hobbys= Arrays.asList(morePersonalInfo.getFavour().getName().split(","));
                initHobby();
    }
    private void initHobby() {
        if(hobbys==null){
            return;
        }
        for (int i = 0; i < rgMorePersonalInfoLike.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) rgMorePersonalInfoLike.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb= (CheckBox) ll.getChildAt(j);
                if(hobbys.contains(rb.getText().toString())){
                    rb.setChecked(true);
                }
            }
        }
    }

    @OnClick({R.id.tv_personal_more_info_back, R.id.tv_personal_more_info_save,R.id.et_personal_more_info_marriage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_personal_more_info_back:
                finish();
                break;
            case R.id.et_personal_more_info_marriage:
                AlertDialog.Builder dialog_creditScore = new AlertDialog.Builder(this);
                dialog_creditScore.setTitle("选择婚姻状况");
                dialog_creditScore.setSingleChoiceItems(marrage, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        etPersonalMoreInfoMarriage.setText(marrage[i]);
                        marriage=i+"";
                        dialogInterface.dismiss();
                    }
                });
                dialog_creditScore.create().show();
                break;
            case R.id.tv_personal_more_info_save:
                LoadDialog.show(this);
                saveData();
                EditMorePersonalInfo editMorePersonalInfo = new EditMorePersonalInfo(userId, fullName, SfullName,
                        QQ, SQQ, wechat, Swechat, favour, Sfavour, finishSchool, SfinishSchool, constellation, Sconstellation,
                        bloodType, SbloodType, company, Scompany, position, Sposition, marriage, Smarriage);
                presenter.postMorePersonalInfo(editMorePersonalInfo);
                break;
        }
    }

    private void saveData() {
        fullName = etPersonalMoreInfoName.getText().toString();
        QQ = etPersonalMoreInfoQQ.getText().toString();
        wechat = etPersonalMoreInfoWechat.getText().toString();
        finishSchool = etPersonalMoreInfoSchool.getText().toString();
        constellation = etPersonalMoreInfoConstellation.getText().toString();
        bloodType = etPersonalMoreInfoBloodtype.getText().toString();
        company = etPersonalMoreInfoCompany.getText().toString();
        position = etPersonalMoreInfoPosition.getText().toString();
        presenter.getHobby(rgMorePersonalInfoLike);
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
        morePersonalInfo = info;
        initData();
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this, errorString);
        LoadDialog.dismiss(this);
    }

    @Override
    public void setHobby(String hobby) {
        this.favour = hobby;
    }

    @Override
    public void saveSucceed() {
        LoadDialog.dismiss(this);
        T.showShort(this, "保存成功");
        finish();
    }
}
