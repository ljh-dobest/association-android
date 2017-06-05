package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IPersonalApplayView;
import com.ike.communityalliance.presenter.PersonalApplayPresenter;
import com.ike.mylibrary.util.T;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PersonalApplayVipActivity extends BaseMvpActivity<IPersonalApplayView, PersonalApplayPresenter> implements RadioGroup.OnCheckedChangeListener, IPersonalApplayView, AdapterView.OnItemSelectedListener {
    private final String[] creditScores = new String[]{"100", "90", "80", "70", "60", "50", "40", "30", "20", "10"};
    private final String[] relationships = new String[]{"亲人","同事", "校友", "同乡"};
    private final String[] degrees={"初中","高中","中技","中专","大专","本科","硕士","博士","MBA","EMBA","其他"};
    @BindView(R.id.et_recom_name)
    EditText et_recom_name;
    @BindView(R.id.et_recom_mobile)
    EditText et_recom_mobile;
    @BindView(R.id.et_recom_relationship)
    EditText et_recom_relationship;
    @BindView(R.id.et_recom_birthday)
    TextView et_recom_birthday;
    @BindView(R.id.et_recom_school)
    EditText et_recom_school;
    @BindView(R.id.et_recom_company)
    EditText et_recom_company;
    @BindView(R.id.et_recom_dadName)
    EditText et_recom_dadName;
    @BindView(R.id.et_recom_momName)
    EditText et_recom_momName;
    @BindView(R.id.et_recom_spouseName)
    EditText et_recom_spouseName;
    @BindView(R.id.et_recom_childrenName)
    EditText et_recom_childrenName;
    @BindView(R.id.et_recom_childrenSchool)
    EditText et_recom_childrenSchool;
    @BindView(R.id.et_verifyInfo_degree)
    EditText et_verifyInfo_degree;
    @BindView(R.id.et_recom_company_position)
    EditText et_recom_company_position;
    @BindView(R.id.et_recom_industry)
    EditText et_recom_industry;
    @BindView(R.id.et_recom_email)
    EditText et_recom_email;
    @BindView(R.id.et_recom_QQ)
    EditText et_recom_QQ;
    @BindView(R.id.et_recom_wechat)
    EditText et_recom_wechat;
    @BindView(R.id.rg_recom_sex)
    RadioGroup rg_recom_sex;
    @BindView(R.id.rg_recom_like)
    RadioGroup rg_recom_like;
    @BindView(R.id.rg_recom_character)
    RadioGroup rg_recom_character;
    @BindView(R.id.rg_recom_marriage)
    RadioGroup rg_recom_marriage;
    @BindView(R.id.ll_recom_relationship)
    LinearLayout ll_recom_relationship;
    @BindView(R.id.btn_recommend)
    Button btn_recommend;
    @BindView(R.id.ll_recomm_marriaged)
    LinearLayout ll_recomm_marriaged;
    @BindView(R.id.sp_recom_province)
    Spinner sp_recom_province;
    @BindView(R.id.sp_recom_city)
    Spinner sp_recom_city;
    @BindView(R.id.sp_recom_county)
    Spinner sp_recom_county;
    @BindView(R.id.sp_recom_jgprovince)
    Spinner sp_recom_jgprovince;
    @BindView(R.id.sp_recom_jgcitys)
    Spinner sp_recom_jgcitys;
    @BindView(R.id.sp_recom_jgcountys)
    Spinner sp_recom_jgcountys;
    @BindView(R.id.tv_recom_back)
    TextView tv_recom_back;

    private String userId;
    private String fullName;
    private String mobile;
    private String sex = "1";
    private String hobby;
    private ArrayList<String> address = new ArrayList<>();
    private String relationship;
    private String character;
    private String birthday;
    private String homeplace;
    private String finishSchool;
    private String company;
    private String fatherName;
    private String motherName;
    private String marriage = "0";
    private String spouseName;
    private String childrenName;
    private String childrenSchool;
    private String curDegreeCode="0";
    private String position;
    private String industry;
    private String email;
    private String wechat;
    private String QQ;
    private ArrayList<String> provinceItems = new ArrayList<>();
    private ArrayList<String> citysItems = new ArrayList<>();
    private ArrayList<String> countryItems = new ArrayList<>();
    private ArrayList<String> jgCitysItems = new ArrayList<>();
    private ArrayList<String> jgCountryItems = new ArrayList<>();
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter county_adapter;
    private ArrayList<ProvinceBean> data;
    private ArrayList<CityBean> citys;
    private SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_applay_vip);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        presenter.getParserData(this, "data.txt");
        initView();
    }

    @Override
    public PersonalApplayPresenter initPresenter() {
        return new PersonalApplayPresenter();
    }

    @Override
    public void showTextEmpty() {
        T.showShort(this, "必填项不能为空！");
    }

    @Override
    public void showRecommedError(String string) {
        T.showShort(this, string);
    }

    @Override
    public void succeedToRecommed(String recommendId) {
     T.showShort(this,"恭喜您！已成为VIP用户！");
        finish();
    }

    @Override
    public void initView() {
        rg_recom_marriage.setOnCheckedChangeListener(this);
        rg_recom_sex.setOnCheckedChangeListener(this);
        sp_recom_province.setOnItemSelectedListener(this);
        sp_recom_city.setOnItemSelectedListener(this);
        sp_recom_jgprovince.setOnItemSelectedListener(this);
        sp_recom_jgcitys.setOnItemSelectedListener(this);
    }

    //获取控件的信息
    @Override
    public void getViewData() {
        address.clear();
        fullName = et_recom_name.getText().toString().trim();
        mobile = et_recom_mobile.getText().toString().trim();
        address.add(sp_recom_province.getSelectedItem().toString());
        address.add(sp_recom_city.getSelectedItem().toString());
        try {
            address.add(sp_recom_county.getSelectedItem().toString());
        } catch (Exception e) {
            address.add("");
        }
        address.add("");
        homeplace = sp_recom_jgprovince.getSelectedItem().toString() + "," +
                sp_recom_jgcitys.getSelectedItem().toString();
        try {
            homeplace = homeplace + "," + sp_recom_jgcountys.getSelectedItem().toString();
        } catch (Exception e) {
        }
        getHobbys(rg_recom_like);
        getCharacters(rg_recom_character);
        birthday = et_recom_birthday.getText().toString().trim();
        finishSchool = et_recom_school.getText().toString().trim();
        company = et_recom_company.getText().toString().trim();
        fatherName = et_recom_dadName.getText().toString().trim();
        motherName = et_recom_momName.getText().toString().trim();
        spouseName = et_recom_spouseName.getText().toString().trim();
        childrenName = et_recom_childrenName.getText().toString().trim();
        childrenSchool = et_recom_childrenSchool.getText().toString().trim();
        position = et_recom_company_position.getText().toString().trim();
        industry = et_recom_industry.getText().toString().trim();
        email = et_recom_email.getText().toString().trim();
        QQ = et_recom_QQ.getText().toString().trim();
        wechat = et_recom_wechat.getText().toString().trim();
    }

    //获得解析好的省市区数据
    @Override
    public void getparserData(ArrayList<ProvinceBean> province) {
        data = province;
        for (int i = 0; i < data.size(); i++) {
            provinceItems.add(province.get(i).getName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //适配器
                province_adapter = new ArrayAdapter<String>(PersonalApplayVipActivity.this, R.layout.simple_spanner_item, provinceItems);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                sp_recom_province.setAdapter(province_adapter);
                sp_recom_jgprovince.setAdapter(province_adapter);
            }
        });

    }

    @Override
    public void getHobbys(ViewGroup group) {
        presenter.getHobby(group);
    }

    @Override
    public void setHobbys(String hobbys) {
        this.hobby = hobbys;
    }

    @Override
    public void getCharacters(ViewGroup group) {
        presenter.getCharacters(group);
    }

    @Override
    public void setCharacters(String characters) {
        this.character = characters;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rg_recom_sex://性别选项
                if (checkedId == R.id.rb_recom_man) {
                    sex = "1";
                } else {
                    sex = "2";
                }
                break;
            case R.id.rg_recom_marriage://婚姻选项
                if (checkedId == R.id.rb_recom_unmarriage) {
                    marriage = "0";
                    //不显示填写婚姻信息项
                    ll_recomm_marriaged.setVisibility(View.GONE);
                } else {
                    marriage = "1";
                    //显示填写婚姻信息项
                    ll_recomm_marriaged.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @OnClick({R.id.tv_recom_back, R.id.et_recom_birthday, R.id.btn_recommend, R.id.et_recom_relationship,R.id.et_verifyInfo_degree})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_recom_back:
                finish();
                break;
            case R.id.et_recom_birthday:
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
                        et_recom_birthday.setText(birthday);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.btn_recommend:
                getViewData();
                presenter.postVipInfo(new PersonalVipBean(userId, fullName, mobile, sex, hobby, address, relationship, character
                        ,birthday, homeplace, finishSchool, company, fatherName, motherName, marriage,
                        spouseName, childrenName, childrenSchool,curDegreeCode,position,industry,email,QQ,wechat));
                break;
            case R.id.et_recom_relationship:
                //弹出关系选择框
                AlertDialog.Builder dialog_relationship = new AlertDialog.Builder(this);
                dialog_relationship.setTitle("选择关系");
                dialog_relationship.setSingleChoiceItems(relationships, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_recom_relationship.setText(relationships[i]);
                        relationship = i + 1+"";
                        dialogInterface.dismiss();
                    }
                });
                dialog_relationship.create().show();
                break;
            case R.id.et_verifyInfo_degree:
                //选择学历
                android.app.AlertDialog.Builder dialog_degree= new android.app.AlertDialog.Builder(this);
                dialog_degree.setTitle("选择学历");
                dialog_degree.setSingleChoiceItems(degrees, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        curDegreeCode=i+1+"";
                        et_verifyInfo_degree.setText(degrees[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_degree.create().show();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_recom_province:     //设置二级联动
                setSecondText(sp_recom_city, position);
                break;
            case R.id.sp_recom_city: //设置三级联动
                setThirdText(sp_recom_county, position);
                break;
            case R.id.sp_recom_jgprovince:     //设置二级联动
                setJGSecondText(sp_recom_jgcitys, position);
                break;
            case R.id.sp_recom_jgcitys: //设置三级联动
                setJGThirdText(sp_recom_jgcountys, position);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



    //城市二级联动
    private void setSecondText(Spinner sp, int position) {
        citysItems.clear();
        citys = (ArrayList<CityBean>) data.get(position).getSub();
        for (int i = 0; i < citys.size(); i++) {
            citysItems.add(citys.get(i).getName());
        }
        city_adapter = new ArrayAdapter<String>(PersonalApplayVipActivity.this, R.layout.simple_spanner_item, citysItems);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(city_adapter);
    }
    //籍贯城市二级联动
    private void setJGSecondText(Spinner sp, int position) {
        jgCitysItems.clear();
        citys = (ArrayList<CityBean>) data.get(position).getSub();
        for (int i = 0; i < citys.size(); i++) {
            jgCitysItems.add(citys.get(i).getName());
        }
        city_adapter = new ArrayAdapter<String>(PersonalApplayVipActivity.this, R.layout.simple_spanner_item, jgCitysItems);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(city_adapter);
    }

    //县区三级联动
    private void setThirdText(Spinner sp, int position) {
        countryItems.clear();
        ArrayList<CountyBean> country = (ArrayList<CountyBean>) citys.get(position).getSub();
        if (country == null) {
            countryItems.add("");
        } else {
            if (country.size() == 0) {
                countryItems.add("");
            }
            for (int i = 0; i < country.size(); i++) {
                countryItems.add(country.get(i).getName());
            }
        }
        county_adapter = new ArrayAdapter<String>(PersonalApplayVipActivity.this, R.layout.simple_spanner_item, countryItems);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }

    //籍贯县区三级联动
    private void setJGThirdText(Spinner sp, int position) {
        jgCountryItems.clear();
        ArrayList<CountyBean> country = (ArrayList<CountyBean>) citys.get(position).getSub();
        if (country == null) {
            jgCountryItems.add("");
        } else {
            if (country.size() == 0) {
                jgCountryItems.add("");
            }
            for (int i = 0; i < country.size(); i++) {
                jgCountryItems.add(country.get(i).getName());
            }
        }
        county_adapter = new ArrayAdapter<String>(PersonalApplayVipActivity.this, R.layout.simple_spanner_item, jgCountryItems);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }

}
