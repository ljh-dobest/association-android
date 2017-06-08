package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.EditMorePersonalInfo;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IMorePersonalInfoView;
import com.ike.communityalliance.presenter.MorePersonalInfoPresenter;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.kyleduo.switchbutton.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MorePersonalInfoActivity extends BaseMvpActivity<IMorePersonalInfoView, MorePersonalInfoPresenter> implements IMorePersonalInfoView, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private final String[] industrys = new String[]{"其它", "互联网", "服务业", "金融", "教育", "银行", "医疗", "房地产", "贸易", "物流"};
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
    @BindView(R.id.et_personal_more_info_wechat)
    EditText etPersonalMoreInfoWechat;
    @BindView(R.id.et_personal_more_info_school)
    EditText etPersonalMoreInfoSchool;
    @BindView(R.id.tv_personal_more_info_school_status)
    TextView tvPersonalMoreInfoSchoolStatus;
    @BindView(R.id.sw_personal_more_info_school_status)
    SwitchButton swPersonalMoreInfoSchoolStatus;
    @BindView(R.id.et_personal_more_info_constellation)
    EditText etPersonalMoreInfoConstellation;

    @BindView(R.id.et_personal_more_info_bloodtype)
    EditText etPersonalMoreInfoBloodtype;

    @BindView(R.id.et_personal_more_info_company)
    EditText etPersonalMoreInfoCompany;

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
    @BindView(R.id.et_personal_more_info_industry)
    EditText etPersonalMoreInfoIndustry;
    @BindView(R.id.et_personal_more_info_birthday)
    EditText etPersonalMoreInfoBirthday;
    @BindView(R.id.tv_personal_more_info_birthday_status)
    TextView tvPersonalMoreInfoBirthdayStatus;
    @BindView(R.id.sw_personal_more_info_birthday_status)
    SwitchButton swPersonalMoreInfoBirthdayStatus;
    @BindView(R.id.et_personal_more_info_fatherName)
    EditText etPersonalMoreInfoFatherName;
    @BindView(R.id.tv_personal_more_info_fatherName_status)
    TextView tvPersonalMoreInfoFatherNameStatus;
    @BindView(R.id.sw_personal_more_info_fatherName_status)
    SwitchButton swPersonalMoreInfoFatherNameStatus;
    @BindView(R.id.et_personal_more_info_motherName)
    EditText etPersonalMoreInfoMotherName;
    @BindView(R.id.tv_personal_more_info_motherName_status)
    TextView tvPersonalMoreInfoMotherNameStatus;
    @BindView(R.id.sw_personal_more_info_motherName_status)
    SwitchButton swPersonalMoreInfoMotherNameStatus;
    @BindView(R.id.et_personal_more_info_spouseName)
    EditText etPersonalMoreInfoSpouseName;
    @BindView(R.id.tv_personal_more_info_spouseName_status)
    TextView tvPersonalMoreInfoSpouseNameStatus;
    @BindView(R.id.sw_personal_more_info_spouseName_status)
    SwitchButton swPersonalMoreInfoSpouseNameStatus;
    @BindView(R.id.et_personal_more_info_childrenName)
    EditText etPersonalMoreInfoChildrenName;
    @BindView(R.id.tv_personal_more_info_childrenName_status)
    TextView tvPersonalMoreInfoChildrenNameStatus;
    @BindView(R.id.sw_personal_more_info_childrenName_status)
    SwitchButton swPersonalMoreInfoChildrenNameStatus;
    @BindView(R.id.et_personal_more_info_childrenSchool)
    EditText etPersonalMoreInfoChildrenSchool;
    @BindView(R.id.tv_personal_more_info_childrenSchool_status)
    TextView tvPersonalMoreInfoChildrenSchoolStatus;
    @BindView(R.id.sw_personal_more_info_childrenSchool_status)
    SwitchButton swPersonalMoreInfoChildrenSchoolStatus;
    @BindView(R.id.et_personal_more_info_mobile)
    EditText etPersonalMoreInfoMobile;
    @BindView(R.id.tv_personal_more_info_mobile_status)
    TextView tvPersonalMoreInfoMobileStatus;
    @BindView(R.id.sw_personal_more_info_mobile_status)
    SwitchButton swPersonalMoreInfoMobileStatus;
    @BindView(R.id.sp_personal_more_province)
    Spinner spPersonalMoreProvince;
    @BindView(R.id.sp_personal_more_citys)
    Spinner spPersonalMoreCitys;
    @BindView(R.id.sp_personal_more_countys)
    Spinner spPersonalMoreCountys;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<String> options2Items = new ArrayList<>();
    private ArrayList<String> options3Items = new ArrayList<>();
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter county_adapter;
    private ArrayList<ProvinceBean> data;
    private ArrayList<CityBean> citys;
    private List<String> addressList=new ArrayList<>();
    private SharedPreferences sp;
    private String userId;
    private MorePersonalInfo morePersonalInfo;
    private String fullName, mobile, birthday, QQ, wechat, fatherName, motherName, company, position, industry, homeplace,
            finishSchool, constellation, bloodType, marriage="0", spouseName, childrenName, childrenSchool;
    private String SfullName = "0", Smobile = "0", Sbirthday = "0", Sposition = "0", SfatherName = "0", SmotherName = "0", SfinishSchool = "0",
            Smarriage = "0", SspouseName = "0", SchildrenName = "0", SchildrenSchool = "0";

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


    private void initProvinceData() {
        if (DateUtils.provinceData.size() == 0) {
            return;
        }
        data = DateUtils.provinceData;
        for (int i = 0; i < data.size(); i++) {
            options1Items.add(data.get(i).getName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //适配器
                province_adapter = new ArrayAdapter<String>(MorePersonalInfoActivity.this, R.layout.simple_spanner_item, options1Items);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spPersonalMoreProvince.setAdapter(province_adapter);
            }
        });
    }

    //初始化地址
    private void initAddress(String address) {
        if(address==null){
            return;
        }
        addressList = Arrays.asList(address.split(","));
        if (addressList.size() > 0) {
            int position = options1Items.indexOf(addressList.get(0));
            spPersonalMoreProvince.setSelection(position);
        }
    }

    private void initView() {
        swPersonalMoreInfoNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoSchoolStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoPositionStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoMarriageStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoBirthdayStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoMobileStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoFatherNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoMotherNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoSpouseNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoChildrenNameStatus.setOnCheckedChangeListener(this);
        swPersonalMoreInfoChildrenSchoolStatus.setOnCheckedChangeListener(this);
        spPersonalMoreProvince.setOnItemSelectedListener(this);
        spPersonalMoreCitys.setOnItemSelectedListener(this);
        spPersonalMoreCountys.setOnItemSelectedListener(this);
        initProvinceData();
    }


    private void initData() {
        if (morePersonalInfo.getFullName().getName() == null) {
            return;
        }
        etPersonalMoreInfoName.setText(morePersonalInfo.getFullName().getName());
        swPersonalMoreInfoNameStatus.setChecked(morePersonalInfo.getFullName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoQQ.setText(morePersonalInfo.getQQ());
        etPersonalMoreInfoWechat.setText(morePersonalInfo.getWechat());
        etPersonalMoreInfoSchool.setText(morePersonalInfo.getFinishSchool().getName());
        swPersonalMoreInfoSchoolStatus.setChecked(morePersonalInfo.getFinishSchool().getStatus() == 0 ? false : true);
        etPersonalMoreInfoBloodtype.setText(morePersonalInfo.getBloodType());
        etPersonalMoreInfoConstellation.setText(morePersonalInfo.getConstellation());
        etPersonalMoreInfoCompany.setText(morePersonalInfo.getCompany());
        etPersonalMoreInfoIndustry.setText(industrys[Integer.parseInt(morePersonalInfo.getIndustry())-1]);
        etPersonalMoreInfoPosition.setText(morePersonalInfo.getPosition().getName());
        swPersonalMoreInfoPositionStatus.setChecked(morePersonalInfo.getPosition().getStatus() == 0 ? false : true);
        etPersonalMoreInfoMarriage.setText(morePersonalInfo.getMarriage().getName().equals("0") ? "未婚" : "已婚");
        swPersonalMoreInfoMarriageStatus.setChecked(morePersonalInfo.getMarriage().getStatus() == 0 ? false : true);
        etPersonalMoreInfoBirthday.setText(morePersonalInfo.getBirthday().getName());
        swPersonalMoreInfoBirthdayStatus.setChecked(morePersonalInfo.getBirthday().getStatus() == 0 ? false : true);
        etPersonalMoreInfoMobile.setText(morePersonalInfo.getMobile().getName());
        swPersonalMoreInfoMobileStatus.setChecked(morePersonalInfo.getMobile().getStatus() == 0 ? false : true);
        etPersonalMoreInfoFatherName.setText(morePersonalInfo.getFatherName().getName());
        swPersonalMoreInfoFatherNameStatus.setChecked(morePersonalInfo.getFatherName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoMotherName.setText(morePersonalInfo.getMotherName().getName());
        swPersonalMoreInfoMotherNameStatus.setChecked(morePersonalInfo.getMotherName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoSpouseName.setText(morePersonalInfo.getSpouseName().getName());
        swPersonalMoreInfoSpouseNameStatus.setChecked(morePersonalInfo.getSpouseName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoChildrenName.setText(morePersonalInfo.getChildrenName().getName());
        swPersonalMoreInfoChildrenNameStatus.setChecked(morePersonalInfo.getChildrenName().getStatus() == 0 ? false : true);
        etPersonalMoreInfoChildrenSchool.setText(morePersonalInfo.getChildrenSchool().getName());
        swPersonalMoreInfoChildrenSchoolStatus.setChecked(morePersonalInfo.getChildrenSchool().getStatus() == 0 ? false : true);
        initAddress(morePersonalInfo.getHomeplace());
    }

    @OnClick({R.id.tv_personal_more_info_back, R.id.tv_personal_more_info_save,
            R.id.et_personal_more_info_marriage, R.id.et_personal_more_info_birthday
            , R.id.et_personal_more_info_industry})
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
                        marriage = i + "";
                        dialogInterface.dismiss();
                    }
                });
                dialog_creditScore.create().show();
                break;
            case R.id.et_personal_more_info_birthday:
                //隐藏软件盘，防止遮挡生日
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        birthday = dateFormat.format(date);
                        etPersonalMoreInfoBirthday.setText(birthday);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.tv_personal_more_info_save:
                LoadDialog.show(this);
                saveData();
                EditMorePersonalInfo editMorePersonalInfo = new EditMorePersonalInfo(userId, fullName, SfullName, mobile, Smobile,
                        birthday, Sbirthday, QQ, wechat, fatherName, SfatherName, motherName, SmotherName, finishSchool, SfinishSchool,
                        constellation, bloodType, marriage, Smarriage, spouseName, SspouseName, childrenName, SchildrenName, childrenSchool, SchildrenSchool,
                        company, position, Sposition, industry, homeplace);
                presenter.postMorePersonalInfo(editMorePersonalInfo);
                break;
            case R.id.et_personal_more_info_industry:
                //选择学历
                AlertDialog.Builder dialog_industry = new AlertDialog.Builder(this);
                dialog_industry.setTitle("选择行业");
                dialog_industry.setSingleChoiceItems(industrys, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        industry = i + 1 + "";
                        etPersonalMoreInfoIndustry.setText(industrys[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_industry.create().show();
                break;
        }
    }

    private void saveData() {
        fullName = etPersonalMoreInfoName.getText().toString();
        mobile = etPersonalMoreInfoMobile.getText().toString();
        birthday = etPersonalMoreInfoBirthday.getText().toString();
        QQ = etPersonalMoreInfoQQ.getText().toString();
        wechat = etPersonalMoreInfoWechat.getText().toString();
        finishSchool = etPersonalMoreInfoSchool.getText().toString();
        constellation = etPersonalMoreInfoConstellation.getText().toString();
        bloodType = etPersonalMoreInfoBloodtype.getText().toString();
        company = etPersonalMoreInfoCompany.getText().toString();
        position = etPersonalMoreInfoPosition.getText().toString();
        fatherName = etPersonalMoreInfoFatherName.getText().toString();
        motherName = etPersonalMoreInfoMotherName.getText().toString();
        spouseName = etPersonalMoreInfoSpouseName.getText().toString();
        childrenName = etPersonalMoreInfoChildrenName.getText().toString();
        childrenSchool = etPersonalMoreInfoChildrenSchool.getText().toString();
        homeplace = spPersonalMoreProvince.getSelectedItem().toString() + "," +
                spPersonalMoreCitys.getSelectedItem().toString();
        try {
            homeplace = homeplace + "," + spPersonalMoreCountys.getSelectedItem().toString();
        } catch (Exception e) {
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sw_personal_more_info_name_status:
                tvPersonalMoreInfoNameStatus.setText(isChecked ? "公开" : "非公开");
                SfullName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_school_status:
                tvPersonalMoreInfoSchoolStatus.setText(isChecked ? "公开" : "非公开");
                SfinishSchool = (isChecked ? "1" : "0");
                break;

            case R.id.sw_personal_more_info_position_status:
                tvPersonalMoreInfoPositionStatus.setText(isChecked ? "公开" : "非公开");
                Sposition = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_marriage_status:
                tvPersonalMoreInfoMarriageStatus.setText(isChecked ? "公开" : "非公开");
                Smarriage = (isChecked ? "1" : "0");
                break;

            case R.id.sw_personal_more_info_birthday_status:
                tvPersonalMoreInfoBirthdayStatus.setText(isChecked ? "公开" : "非公开");
                Sbirthday = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_mobile_status:
                tvPersonalMoreInfoMobileStatus.setText(isChecked ? "公开" : "非公开");
                Smobile = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_fatherName_status:
                tvPersonalMoreInfoFatherNameStatus.setText(isChecked ? "公开" : "非公开");
                SfatherName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_motherName_status:
                tvPersonalMoreInfoMotherNameStatus.setText(isChecked ? "公开" : "非公开");
                SmotherName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_spouseName_status:
                tvPersonalMoreInfoSpouseNameStatus.setText(isChecked ? "公开" : "非公开");
                SspouseName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_childrenName_status:
                tvPersonalMoreInfoChildrenNameStatus.setText(isChecked ? "公开" : "非公开");
                SchildrenName = (isChecked ? "1" : "0");
                break;
            case R.id.sw_personal_more_info_childrenSchool_status:
                tvPersonalMoreInfoChildrenSchoolStatus.setText(isChecked ? "公开" : "非公开");
                SchildrenSchool = (isChecked ? "1" : "0");
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
    }

    @Override
    public void saveSucceed() {
        LoadDialog.dismiss(this);
        T.showShort(this, "保存成功");
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_personal_more_province:     //设置二级联动
                setSecondText(spPersonalMoreCitys, position);
                if(addressList.size()>1){
                    int index=options2Items.indexOf(addressList.get(1));
                    spPersonalMoreCitys.setSelection(index);
                }
                break;
            case R.id.sp_personal_more_citys: //设置三级联动
                setThirdText(spPersonalMoreCountys,position);
                if(addressList.size()>2){
                    int index=options3Items.indexOf(addressList.get(2));
                    spPersonalMoreCountys.setSelection(index);
                }
                break;
        }
    }
    private void setSecondText(Spinner sp, int position) {
        options2Items.clear();
        citys = (ArrayList<CityBean>) data.get(position).getSub();
        for (int i = 0; i < citys.size(); i++) {
            options2Items.add(citys.get(i).getName());
        }
        city_adapter = new ArrayAdapter<String>(this,R.layout.simple_spanner_item, options2Items);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(city_adapter);
    }

    private void setThirdText(Spinner sp, int position) {
        options3Items.clear();
        ArrayList<CountyBean> country = (ArrayList<CountyBean>) citys.get(position).getSub();
        if (country == null) {
            options3Items.add(" ");
        } else {
            if(country.size()==0){
                options3Items.add("");
            }
            for (int i = 0; i < country.size(); i++) {
                options3Items.add(country.get(i).getName());
            }
        }
        county_adapter = new ArrayAdapter<String>(this,R.layout.simple_spanner_item, options3Items);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
