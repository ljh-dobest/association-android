package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bigkoo.pickerview.TimePickerView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.VerifyRecommedInfo;
import com.ike.communityalliance.interfaces.IVerifyRecommedInfoView;
import com.ike.communityalliance.presenter.VerifyRecommedInfoPresenter;
import com.ike.communityalliance.ui.Main2Activity;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyRecommedInfoActivity extends BaseMvpActivity<IVerifyRecommedInfoView, VerifyRecommedInfoPresenter> implements IVerifyRecommedInfoView, RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final String[] industrys = new String[]{"其它", "互联网", "服务业", "金融", "教育", "银行", "医疗", "房地产", "贸易", "物流"};
    private final String[] degrees = {"初中", "高中", "中技", "中专", "大专", "本科", "硕士", "博士", "MBA", "EMBA", "其他"};
    @BindView(R.id.et_verifyInfo_username)
    EditText et_verifyInfo_username;
    @BindView(R.id.et_verifyInfo_mobile)
    EditText et_verifyInfo_mobile;
    @BindView(R.id.rg_verifyInfo_sex)
    RadioGroup rg_verifyInfo_sex;
    @BindView(R.id.rg_verifyInfo_like)
    RadioGroup rg_verifyInfo_like;
    @BindView(R.id.sp_verifyInfo_province)
    Spinner sp_verifyInfo_province;
    @BindView(R.id.sp_verifyInfo_citys)
    Spinner sp_verifyInfo_citys;
    @BindView(R.id.sp_verifyInfo_countys)
    Spinner sp_verifyInfo_countys;
    @BindView(R.id.et_verifyInfo_birthday)
    EditText et_verifyInfo_birthday;
    @BindView(R.id.sp_verifyInfo_jgprovince)
    Spinner sp_verifyInfo_jgprovince;
    @BindView(R.id.sp_verifyInfo_jgcitys)
    Spinner sp_verifyInfo_jgcitys;
    @BindView(R.id.sp_verifyInfo_jgcountys)
    Spinner sp_verifyInfo_jgcountys;
    @BindView(R.id.et_verifyInfo_finishSchool)
    EditText et_verifyInfo_finishSchool;
    @BindView(R.id.et_verifyInfo_degree)
    EditText et_verifyInfo_degree;
    @BindView(R.id.et_verifyInfo_company)
    EditText et_verifyInfo_company;
    @BindView(R.id.et_verifyInfo_position)
    EditText et_verifyInfo_position;
    @BindView(R.id.et_verifyInfo_email)
    EditText et_verifyInfo_email;
    @BindView(R.id.et_verifyInfo_QQ)
    EditText et_verifyInfo_QQ;
    @BindView(R.id.et_verifyInfo_wechat)
    EditText et_verifyInfo_wechat;
    @BindView(R.id.btn_verifyRecommedInfo)
    Button btn_verifyRecommedInfo;
    @BindView(R.id.ll_verifyInfo_birthday)
    LinearLayout ll_verifyInfo_birthday;
    @BindView(R.id.et_verifyInfo_recommendId)
    EditText etVerifyInfoRecommendId;
    @BindView(R.id.rg_verifyInfo_character)
    RadioGroup rgVerifyInfoCharacter;
    @BindView(R.id.et_verifyInfo_industry)
    EditText etVerifyInfoIndustry;
    @BindView(R.id.et_verifyInfo_fatherName)
    EditText etVerifyInfoFatherName;
    @BindView(R.id.et_verifyInfo_motherName)
    EditText etVerifyInfoMotherName;
    @BindView(R.id.rb_verifyInfo_unmarriage)
    RadioButton rbVerifyInfoUnmarriage;
    @BindView(R.id.rb_verifyInfo_marriage)
    RadioButton rbVerifyInfoMarriage;
    @BindView(R.id.rg_verifyInfo_marriage)
    RadioGroup rgVerifyInfoMarriage;
    @BindView(R.id.et_verifyInfo_spouseName)
    EditText etVerifyInfoSpouseName;
    @BindView(R.id.et_verifyInfo_childrenName)
    EditText etVerifyInfoChildrenName;
    @BindView(R.id.et_verifyInfo_childrenSchool)
    EditText etVerifyInfoChildrenSchool;
    @BindView(R.id.ll_verifyInfo_marriaged)
    AutoLinearLayout llVerifyInfoMarriaged;
    @BindView(R.id.activity_claim_info)
    AutoLinearLayout activityClaimInfo;
    private String userId;
    private String fullName;
    private String mobile;
    private String sex = "1";
    private String hobby;
    private ArrayList<String> address = new ArrayList<>();
    private String birthday;
    private String homeplace;
    private String finishSchool;
    private String company;
    private String position;
    private String email;
    private String QQ;
    private String wechat;
    private String character;
    private String industry="1";
    private String fatherName;
    private String motherName;
    private String marriage = "0";
    private String spouseName;
    private String childrenName;
    private String childrenSchool;
    private ArrayList<String> provinceList = new ArrayList<>();
    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayList<String> countyList = new ArrayList<>();
    private ArrayList<String> jgcityList = new ArrayList<>();
    private ArrayList<String> jgcountyList = new ArrayList<>();
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter county_adapter;
    private ArrayList<ProvinceBean> data;
    private ArrayList<CityBean> citys;
    private PersonalVipBean verifyRecommedInfo;
    private VerifyRecommedInfo verifyInfo;
    private String curDegreeCode = "0";
    private List<String> hobbyList;
    private List<String> jgAddressList = new ArrayList<>();
    private boolean isFromLogin = false;
    private String recommendId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_recommed_info);
        ButterKnife.bind(this);
        userId = getIntent().getStringExtra("useId");
        recommendId = getIntent().getStringExtra("recommendId");
        isFromLogin = getIntent().getBooleanExtra("fromLogin", false);
//        if(userId!=null){
//            getVerifyInfo(userId,recommendId);
//        }
        initView();
        setProvinceData(DateUtils.provinceData);
    }

    private void initView() {
        rg_verifyInfo_sex.setOnCheckedChangeListener(this);
        rgVerifyInfoMarriage.setOnCheckedChangeListener(this);
        et_verifyInfo_birthday.setOnClickListener(this);
        btn_verifyRecommedInfo.setOnClickListener(this);
        sp_verifyInfo_province.setOnItemSelectedListener(this);
        sp_verifyInfo_jgprovince.setOnItemSelectedListener(this);
        sp_verifyInfo_citys.setOnItemSelectedListener(this);
        sp_verifyInfo_jgcitys.setOnItemSelectedListener(this);
        ll_verifyInfo_birthday.setOnClickListener(this);
        et_verifyInfo_degree.setOnClickListener(this);
        etVerifyInfoIndustry.setOnClickListener(this);
    }


    @Override
    public VerifyRecommedInfoPresenter initPresenter() {
        return new VerifyRecommedInfoPresenter();
    }

    @Override
    public void showTextEmpty() {
        T.showShort(this, "必填项不能空");
    }

    @Override
    public void showVerifyInfoError(String string) {
        T.showShort(this, string);
    }

    @Override
    public void succeedVerifyInfo() {
        if (isFromLogin) {
            startActivity(new Intent(this, Main2Activity.class));
            T.showShort(this, "信息确认完成");
            finish();
        } else {
            startActivity(new Intent(this, Main2Activity.class));
            finish();
        }
    }

    @Override
    public void showComfirmDialog() {
        final AlertDialog ComfirmDialog = new AlertDialog.Builder(this, R.style.DialogStyle).create();
        ComfirmDialog.show();
        Window window = ComfirmDialog.getWindow();
        window.setContentView(R.layout.comfirm_dialog_layout);
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        ImageView iv_comfirm_dialog_cancel = (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postVerifyRecommedInfo(verifyRecommedInfo);
                ComfirmDialog.dismiss();
            }
        });
        iv_comfirm_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComfirmDialog.dismiss();
            }
        });
    }

    @Override
    public void setHobby(String hobbys) {
        this.hobby = hobbys;
    }

    @Override
    public void setProvinceData(ArrayList<ProvinceBean> provinceData) {
        if (DateUtils.provinceData.size() == 0) {
            return;
        }
        this.data = provinceData;
        for (int i = 0; i < data.size(); i++) {
            provinceList.add(data.get(i).getName());
        }
                //适配器
                province_adapter = new ArrayAdapter<String>(VerifyRecommedInfoActivity.this, R.layout.simple_spanner_item, provinceList);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                sp_verifyInfo_province.setAdapter(province_adapter);
                sp_verifyInfo_jgprovince.setAdapter(province_adapter);
    }

    @Override
    public void getHobby(ViewGroup group) {
        presenter.getHobby(group);
    }

    @Override
    public void getVerifyInfo(String userId, String recommendId) {
        presenter.getVerifyRecommendInfo(userId, recommendId);
    }

    @Override
    public void setVerifyInfo(VerifyRecommedInfo verifyInfo) {
        this.verifyInfo = verifyInfo;
        int position = provinceList.indexOf(verifyInfo.getAddress().getFirstStage());
        sp_verifyInfo_province.setSelection(position);
        et_verifyInfo_username.setText(verifyInfo.getFullName());
        hobbyList = Arrays.asList(verifyInfo.getHobby().split(","));
        initHobby();
        if (verifyInfo.getSex().equals("1")) {
            RadioButton radioButton = (RadioButton) rg_verifyInfo_sex.getChildAt(0);
            radioButton.setChecked(true);
        } else {
            RadioButton radioButton = (RadioButton) rg_verifyInfo_sex.getChildAt(1);
            radioButton.setChecked(true);
        }
        et_verifyInfo_birthday.setText(verifyInfo.getBirthday());
        et_verifyInfo_mobile.setText(verifyInfo.getMobile());
        et_verifyInfo_company.setText(verifyInfo.getCompany());
        et_verifyInfo_finishSchool.setText(verifyInfo.getFinishSchool());
        jgAddressList = Arrays.asList(verifyInfo.getHomeplace().split(","));
        if (jgAddressList.size() > 0) {
            int index = provinceList.indexOf(jgAddressList.get(0));
            sp_verifyInfo_jgprovince.setSelection(index);
        }
    }

    @Override
    public void succeedToVip() {

    }

    @Override
    public void getCharacters(ViewGroup group) {
        presenter.getCharacters(group);
    }

    @Override
    public void setCharacters(String characters) {
        this.character = characters;
    }

    private void initHobby() {
        if (hobbyList == null) {
            return;
        }
        for (int i = 0; i < rg_verifyInfo_like.getChildCount(); i++) {
            LinearLayout ll = (LinearLayout) rg_verifyInfo_like.getChildAt(i);
            for (int j = 2; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb = (CheckBox) ll.getChildAt(j);
                if (hobbyList.contains(rb.getText().toString())) {
                    rb.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rg_verifyInfo_sex://性别选项
                if (checkedId == R.id.rb_verifyInfo_man) {
                    sex = "1";
                } else {
                    sex = "2";
                }
                break;
            case R.id.rg_verifyInfo_marriage://婚姻选项
                if (checkedId == R.id.rb_verifyInfo_unmarriage) {
                    marriage = "0";
                    //不显示填写婚姻信息项
                    llVerifyInfoMarriaged.setVisibility(View.GONE);
                } else {
                    marriage = "1";
                    //显示填写婚姻信息项
                    llVerifyInfoMarriaged.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_verifyInfo_birthday:
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
                        et_verifyInfo_birthday.setText(birthday);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.btn_verifyRecommedInfo:
                getViewData();
                if (hobby.split(",").length > 3) {
                    T.showShort(this, "爱好最多只能选3项");
                    LoadDialog.dismiss(this);
                    return;
                }
                if(character.split(",").length>2){
                    T.showShort(this, "性格最多只能选2项");
                    LoadDialog.dismiss(this);
                    return;
                }
                showComfirmDialog();
                break;
            case R.id.et_verifyInfo_degree:
                //选择学历
                AlertDialog.Builder dialog_creditScore = new AlertDialog.Builder(this);
                dialog_creditScore.setTitle("选择学历");
                dialog_creditScore.setSingleChoiceItems(degrees, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        curDegreeCode = i + 1 + "";
                        et_verifyInfo_degree.setText(degrees[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_creditScore.create().show();
                break;
            case R.id.et_verifyInfo_industry:
                //选择学历
                android.app.AlertDialog.Builder dialog_industry= new android.app.AlertDialog.Builder(this);
                dialog_industry.setTitle("选择行业");
                dialog_industry.setSingleChoiceItems(industrys, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        industry=i+1+"";
                        etVerifyInfoIndustry.setText(industrys[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_industry.create().show();
                break;
        }
    }

    private void getViewData() {
        address.clear();
        fullName = et_verifyInfo_username.getText().toString();
        mobile = et_verifyInfo_mobile.getText().toString();
        getHobby(rg_verifyInfo_like);
        getCharacters(rgVerifyInfoCharacter);
        address.add(sp_verifyInfo_province.getSelectedItem().toString());
        address.add(sp_verifyInfo_citys.getSelectedItem().toString());
        try {
            address.add(sp_verifyInfo_countys.getSelectedItem().toString());
        } catch (Exception e) {
            address.add("");
        }
        address.add("");
        birthday = et_verifyInfo_birthday.getText().toString();
        homeplace = sp_verifyInfo_jgprovince.getSelectedItem().toString() + "," +
                sp_verifyInfo_jgcitys.getSelectedItem().toString();
        try {
            homeplace = homeplace + "," + sp_verifyInfo_jgcountys.getSelectedItem().toString();
        } catch (Exception e) {
        }
        finishSchool = et_verifyInfo_finishSchool.getText().toString();
        company = et_verifyInfo_company.getText().toString();
        position = et_verifyInfo_position.getText().toString();
        email = et_verifyInfo_email.getText().toString();
        QQ = et_verifyInfo_QQ.getText().toString();
        wechat = et_verifyInfo_wechat.getText().toString();
        fatherName = etVerifyInfoFatherName.getText().toString().trim();
        motherName = etVerifyInfoMotherName.getText().toString().trim();
        spouseName = etVerifyInfoSpouseName.getText().toString().trim();
        childrenName = etVerifyInfoChildrenName.getText().toString().trim();
        childrenSchool = etVerifyInfoChildrenSchool.getText().toString().trim();
          verifyRecommedInfo=new PersonalVipBean(recommendId,userId, fullName, mobile, sex, hobby, address, "", character
                ,birthday, homeplace, finishSchool, company, fatherName, motherName, marriage,
                spouseName, childrenName, childrenSchool,curDegreeCode,position,industry,email,QQ,wechat);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_verifyInfo_province:     //设置地址二级联动
                cityList.clear();
                citys = (ArrayList<CityBean>) data.get(position).getSub();
                for (int i = 0; i < citys.size(); i++) {
                    cityList.add(citys.get(i).getName());
                }
                city_adapter = new ArrayAdapter<String>(this, R.layout.simple_spanner_item, cityList);
                city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_verifyInfo_citys.setAdapter(city_adapter);
                if (verifyInfo == null) {
                    return;
                }
                if (verifyInfo.getAddress().getSecondStage() != null) {
                    int index = cityList.indexOf(verifyInfo.getAddress().getSecondStage());
                    sp_verifyInfo_citys.setSelection(index);
                }

                break;
            case R.id.sp_verifyInfo_citys: //设置县区三级联动
                countyList.clear();
                ArrayList<CountyBean> country = (ArrayList<CountyBean>) citys.get(position).getSub();
                if (country == null) {
                    countyList.add(" ");
                } else {
                    for (int i = 0; i < country.size(); i++) {
                        countyList.add(country.get(i).getName());
                    }
                }
                county_adapter = new ArrayAdapter<String>(this, R.layout.simple_spanner_item, countyList);
                county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_verifyInfo_countys.setAdapter(county_adapter);
                if (verifyInfo == null) {
                    return;
                }
                if (verifyInfo.getAddress().getThirdStage() != null) {
                    int index = countyList.indexOf(verifyInfo.getAddress().getThirdStage());
                    sp_verifyInfo_countys.setSelection(index);
                }
                break;
            case R.id.sp_verifyInfo_jgprovince:     //设置二级联动
                jgcityList.clear();
                citys = (ArrayList<CityBean>) data.get(position).getSub();
                for (int i = 0; i < citys.size(); i++) {
                    jgcityList.add(citys.get(i).getName());
                }
                city_adapter = new ArrayAdapter<String>(this, R.layout.simple_spanner_item, jgcityList);
                city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_verifyInfo_jgcitys.setAdapter(city_adapter);
                if (jgAddressList.size() > 1) {
                    int index = jgcityList.indexOf(jgAddressList.get(1));
                    sp_verifyInfo_jgcitys.setSelection(index);
                }
                break;
            case R.id.sp_verifyInfo_jgcitys: //设置三级联动
                jgcountyList.clear();
                ArrayList<CountyBean> countrys = (ArrayList<CountyBean>) citys.get(position).getSub();
                if (countrys == null) {
                    jgcountyList.add(" ");
                } else {
                    for (int i = 0; i < countrys.size(); i++) {
                        jgcountyList.add(countrys.get(i).getName());
                    }
                }
                county_adapter = new ArrayAdapter<String>(this, R.layout.simple_spanner_item, jgcountyList);
                county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_verifyInfo_jgcountys.setAdapter(county_adapter);
                if (jgAddressList.size() > 2) {
                    int index = jgcountyList.indexOf(jgAddressList.get(2));
                    sp_verifyInfo_jgcountys.setSelection(index);
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
