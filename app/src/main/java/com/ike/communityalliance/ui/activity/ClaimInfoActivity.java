package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.ClaimInfoBean;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IClaimInfoView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.ClaimInfoPresenter;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClaimInfoActivity extends BaseMvpActivity<IClaimInfoView,ClaimInfoPresenter> implements IClaimInfoView, RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    private final String[] relationships=new String[]{"亲人","情侣","同事","校友","老乡"};
    private final String[] creditScores= new String[]{"100", "90", "80","70","60","50","40","30","20","10"};
    private final String[] degrees={"初中","高中","中技","中专","大专","本科","硕士","博士","MBA","EMBA","其他"};
    @BindView(R.id.tv_claim_back)
    TextView tv_claim_back;
    @BindView(R.id.iv_claim_userHeader)
    RoundedImageView iv_claim_userHeader;
    @BindView(R.id.tv_claiminfo_othername)
    TextView tv_claiminfo_othername;
    @BindView(R.id.et_claiminfo_username)
    EditText et_claiminfo_username;
    @BindView(R.id.et_claiminfo_mobile)
    EditText et_claiminfo_mobile;
    @BindView(R.id.rg_claimInfo_sex)
    RadioGroup rg_claimInfo_sex;
    @BindView(R.id.rg_claimInfo_like)
    RadioGroup rg_claimInfo_like;
    @BindView(R.id.sp_claimInfo_province)
    Spinner sp_claimInfo_province;
    @BindView(R.id.sp_claimInfo_citys)
    Spinner sp_claimInfo_citys;
    @BindView(R.id.sp_claimInfo_countys)
    Spinner sp_claimInfo_countys;
    @BindView(R.id.et_claiminfo_relationship)
    EditText et_claiminfo_relationship;
    @BindView(R.id.et_claiminfo_creditScore)
    EditText et_claiminfo_creditScore;
    @BindView(R.id.et_claiminfo_birthday)
    EditText et_claiminfo_birthday;
    @BindView(R.id.sp_claimInfo_jgprovince)
    Spinner sp_claimInfo_jgprovince;
    @BindView(R.id.sp_claimInfo_jgcitys)
    Spinner sp_claimInfo_jgcitys;
    @BindView(R.id.sp_claimInfo_jgcountys)
    Spinner sp_claimInfo_jgcountys;
    @BindView(R.id.et_claiminfo_finishSchool)
    EditText et_claiminfo_finishSchool;
    @BindView(R.id.et_claiminfo_company)
    EditText et_claiminfo_company;
    @BindView(R.id.et_claiminfo_position)
    EditText et_claiminfo_position;
    @BindView(R.id.et_claiminfo_email)
    EditText et_claiminfo_email;
    @BindView(R.id.et_claiminfo_QQ)
    EditText et_claiminfo_QQ;
    @BindView(R.id.et_claiminfo_wechat)
    EditText et_claiminfo_wechat;
    @BindView(R.id.btn_verifyclaim)
    Button btn_verifyclaim;
    @BindView(R.id.ll_claiminfo_birthday)
    LinearLayout ll_claiminfo_birthday;
    @BindView(R.id.et_claiminfo_degree)
    EditText et_claiminfo_degree;
    private String userId;
    private String claimUserId;
    private String fullName;
    private String mobile;
    private String sex="1";
    private String hobby;
    private ArrayList<String> address=new ArrayList<>();
    private String relationship="1";
    private String creditScore;
    private String birthday;
    private String homeplace;
    private String finishSchool;
    private String degree;
    private String company;
    private String position;
    private String email;
    private String QQ;
    private String wechat;
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
    private ClaimInfoBean claimInfo;
    private ClaimPeopleBean claimPeopleBean;
    private SharedPreferences sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_info);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        claimPeopleBean=intent.getParcelableExtra("claimPeopleBean");
        sp =getSharedPreferences("config",MODE_PRIVATE);
        userId=sp.getString(Const.LOGIN_ID,"");
        presenter.getParserData(this,"data.txt");
        initView();
        initData();
    }

    private void initData() {
        Picasso.with(this).load(HttpUtils.IMAGE_RUL+claimPeopleBean.getUserPortraitUrl()).into(iv_claim_userHeader);
        tv_claiminfo_othername.setText(claimPeopleBean.getNickname());
    }

    private void initView() {
        rg_claimInfo_sex.setOnCheckedChangeListener(this);
        et_claiminfo_birthday.setOnClickListener(this);
        btn_verifyclaim.setOnClickListener(this);
        sp_claimInfo_province.setOnItemSelectedListener(this);
        sp_claimInfo_jgprovince.setOnItemSelectedListener(this);
        sp_claimInfo_citys.setOnItemSelectedListener(this);
        sp_claimInfo_jgcitys.setOnItemSelectedListener(this);
        tv_claim_back.setOnClickListener(this);
        et_claiminfo_relationship.setOnClickListener(this);
        et_claiminfo_creditScore.setOnClickListener(this);
        et_claiminfo_degree.setOnClickListener(this);
    }


    @Override
    public ClaimInfoPresenter initPresenter() {
        return new ClaimInfoPresenter();
    }

    @Override
    public void showTextEmpty() {
        T.showShort(this,"必填项不能为空");
    }

    @Override
    public void showSucceedClaim() {
        showComfirmDialog("认领成功");
    }

    @Override
    public void showFailClaim() {
        showComfirmDialog("认领失败");
    }

    @Override
    public void showWaitClaim() {
        showComfirmDialog("认领消息已发送，等待对方审核~");
    }

    @Override
    public void getHobbys(ViewGroup group) {
          presenter.getHobbys(group);
    }

    @Override
    public void setHobbys(String hobbys) {
        hobby=hobbys;
    }

    @Override
    public void setprovinceData(ArrayList<ProvinceBean> data) {
        this.data=data;
        for (int i = 0; i < data.size(); i++) {
            provinceItems.add(data.get(i).getName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //适配器
                province_adapter = new ArrayAdapter<String>(ClaimInfoActivity.this,R.layout.simple_spanner_item, provinceItems);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                sp_claimInfo_province.setAdapter(province_adapter);
                sp_claimInfo_jgprovince.setAdapter(province_adapter);
            }
        });
    }

    @Override
    public void showLoading() {
        LoadDialog.show(this);
    }

    @Override
    public void hideLoading() {
        LoadDialog.dismiss(this);
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this,errorString);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.rg_claimInfo_sex://性别选项
                if(checkedId == R.id.rg_claimInfo_sex){
                    sex="1";
                }else{
                    sex="2";
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_claiminfo_birthday:
                //隐藏软件盘，防止遮挡生日
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                        birthday = dateFormat.format(date);
                        et_claiminfo_birthday.setText(birthday);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.et_claiminfo_relationship:
                //弹出关系选择框
                android.app.AlertDialog.Builder dialog_relationship = new android.app.AlertDialog.Builder(this);
                dialog_relationship.setTitle("选择关系");
                dialog_relationship.setSingleChoiceItems(relationships, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_claiminfo_relationship.setText(relationships[i]);
                        relationship=i+1+"";
                        dialogInterface.dismiss();
                    }
                });
                dialog_relationship.create().show();
                break;
            case R.id.et_claiminfo_creditScore:
                //弹出信誉分选择框
                android.app.AlertDialog.Builder dialog_creditScore = new android.app.AlertDialog.Builder(this);
                dialog_creditScore.setTitle("选择信誉分");
                dialog_creditScore.setSingleChoiceItems(creditScores, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        et_claiminfo_creditScore.setText(creditScores[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_creditScore.create().show();
                break;
            case R.id.et_claiminfo_degree:
                //选择学历
                android.app.AlertDialog.Builder dialog_degree = new android.app.AlertDialog.Builder(this);
                dialog_degree.setTitle("选择学历");
                dialog_degree.setSingleChoiceItems(degrees, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        degree=i+1+"";
                        et_claiminfo_degree.setText(degrees[i]);
                        dialogInterface.dismiss();
                    }
                });
                dialog_degree.create().show();
                break;
            case R.id.btn_verifyclaim:
                getViewData();
                presenter.postClaimPeopleInfo(claimInfo);
                break;
            case R.id.tv_claim_back:
              finish();
                break;
        }
    }

    private void getViewData() {
        address.clear();
        claimUserId=claimPeopleBean.getUserId();
        fullName=et_claiminfo_username.getText().toString();
        mobile=et_claiminfo_mobile.getText().toString();
        getHobbys(rg_claimInfo_like);
        address.add(sp_claimInfo_province.getSelectedItem().toString());
        address.add(sp_claimInfo_citys.getSelectedItem().toString());
        try {
            address.add(sp_claimInfo_countys.getSelectedItem().toString());
        }catch (Exception e){
            address.add("");
        }
        creditScore=et_claiminfo_creditScore.getText().toString();
        birthday=et_claiminfo_birthday.getText().toString();
        homeplace=sp_claimInfo_jgprovince.getSelectedItem().toString()+","+
                sp_claimInfo_jgcitys.getSelectedItem().toString();
        try {
            homeplace=homeplace+","+sp_claimInfo_jgcountys.getSelectedItem().toString();
        }catch (Exception e){
        }
        finishSchool=et_claiminfo_finishSchool.getText().toString();
        company=et_claiminfo_company.getText().toString();
        position=et_claiminfo_position.getText().toString();
        email=et_claiminfo_email.getText().toString();
        QQ=et_claiminfo_QQ.getText().toString();
        wechat=et_claiminfo_wechat.getText().toString();
        claimInfo=new ClaimInfoBean( userId,claimUserId,fullName,mobile,sex,
                hobby, address, creditScore,relationship,birthday,homeplace,finishSchool,
                degree, company, position,email,QQ,wechat);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_claimInfo_province:     //设置二级联动
                setSecondText(sp_claimInfo_citys, position);
                break;
            case R.id.sp_claimInfo_citys: //设置三级联动
                setThirdText(sp_claimInfo_countys,position);
                break;
            case R.id.sp_claimInfo_jgprovince:     //设置二级联动
                setJGSecondText(sp_claimInfo_jgcitys, position);
                break;
            case R.id.sp_claimInfo_jgcitys: //设置三级联动
                setJGThirdText(sp_claimInfo_jgcountys,position);
                break;
        }
    }
    //城市二级联动
    private void setSecondText(Spinner sp, int position) {
        citysItems.clear();
        citys = (ArrayList<CityBean>) data.get(position).getSub();
        for (int i = 0; i < citys.size(); i++) {
            citysItems.add(citys.get(i).getName());
        }
        city_adapter = new ArrayAdapter<String>(ClaimInfoActivity.this, R.layout.simple_spanner_item, citysItems);
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
        city_adapter = new ArrayAdapter<String>(ClaimInfoActivity.this, R.layout.simple_spanner_item, jgCitysItems);
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
        county_adapter = new ArrayAdapter<String>(ClaimInfoActivity.this, R.layout.simple_spanner_item, countryItems);
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
        county_adapter = new ArrayAdapter<String>(ClaimInfoActivity.this, R.layout.simple_spanner_item, jgCountryItems);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void showComfirmDialog(String msg) {
        final AlertDialog ComfirmDialog = new AlertDialog.Builder(this,R.style.DialogStyle).create();
        ComfirmDialog.show();
        Window window = ComfirmDialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setContentView(R.layout.comfirm_dialog_layout);
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        TextView tv_comfirm_dialog_title1= (TextView) window.findViewById(R.id.tv_comfirm_dialog_title1);
        TextView tv_comfirm_dialog_title2= (TextView) window.findViewById(R.id.tv_comfirm_dialog_title2);
        tv_comfirm_dialog_title1.setText(msg);
        tv_comfirm_dialog_title2.setVisibility(View.GONE);
        ImageView iv_comfirm_dialog_cancel= (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
