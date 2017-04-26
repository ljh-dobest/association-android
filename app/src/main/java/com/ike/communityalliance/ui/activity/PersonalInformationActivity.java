package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CityBean;
import com.ike.communityalliance.bean.CountyBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IPersonalInfoEditView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.PersonalInfoEditPresenter;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.communityalliance.wedget.CircleImageView;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

import static com.ike.communityalliance.R.id.et_personal_info_birthday;
import static com.ike.communityalliance.R.id.sp_personal_info_citys;

public class PersonalInformationActivity extends BaseMvpActivity<IPersonalInfoEditView,PersonalInfoEditPresenter> implements IPersonalInfoEditView, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.et_personal_info_back)
    TextView etPersonalInfoBack;
    @BindView(R.id.et_personal_info_save)
    TextView etPersonalInfoSave;
    @BindView(R.id.iv_personal_info_userIcon)
    CircleImageView ivPersonalInfoUserIcon;
    @BindView(R.id.pb_personal_info_creditScore)
    ProgressBar pbPersonalInfoCreditScore;
    @BindView(R.id.tv_personal_info_creditScore)
    TextView tvPersonalInfoCreditScore;
    @BindView(R.id.pb_personal_info_experience)
    ProgressBar pbPersonalInfoExperience;
    @BindView(R.id.tv_personal_info_experience)
    TextView tvPersonalInfoExperience;
    @BindView(R.id.tv_personal_info_contributionScore)
    TextView tvPersonalInfoContributionScore;
    @BindView(R.id.tv_personal_info_account)
    TextView tv_personal_info_account;
    @BindView(R.id.et_personal_info_nickName)
    EditText etPersonalInfoNickName;
    @BindView(R.id.et_personal_info_mobile)
    EditText etPersonalInfoMobile;
    @BindView(R.id.rb_personal_info_man)
    RadioButton rbPersonalInfoMan;
    @BindView(R.id.rb_personal_info_women)
    RadioButton rbPersonalInfoWomen;
    @BindView(R.id.rg_personal_info_sex)
    RadioGroup rgPersonalInfoSex;
    @BindView(R.id.sp_personal_info_province)
    Spinner spPersonalInfoProvince;
    @BindView(sp_personal_info_citys)
    Spinner spPersonalInfoCitys;
    @BindView(R.id.sp_personal_info_countys)
    Spinner spPersonalInfoCountys;
    @BindView(et_personal_info_birthday)
    EditText etPersonalInfoBirthday;
    @BindView(R.id.et_personal_info_age)
    EditText etPersonalInfoAge;
    @BindView(R.id.et_personal_info_recomendUser)
    TextView etPersonalInfoRecomendUser;
    @BindView(R.id.et_personal_info_claimUser)
    TextView etPersonalInfoClaimUser;
    @BindView(R.id.tv_personal_info_moreInfo)
    TextView tvPersonalInfoMoreInfo;
    private ArrayList<String> options1Items=new ArrayList<>();
    private ArrayList<String> options2Items=new ArrayList<>();
    private ArrayList<String> options3Items=new ArrayList<>();
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter county_adapter;
    private ArrayList<ProvinceBean> data;
    private ArrayList<CityBean> citys;
    private String imgPath="";
    private UserInfo userInfo;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private  String userId;
    private String userPortraitUrl,account,nickName,age,sex,useId,email,recommendUserId,
            birthday,address,mobile,experience,creditScore,contributionScore,claimUserId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        editor = sp.edit();
        userId=sp.getString(Const.LOGIN_ID,"");
        initData();
        initView();
    }

    private void initData() {
        userPortraitUrl=sp.getString(Const.userPortraitUrl,"");
        account=sp.getString(Const.LOGIN_ACCOUNT,"");
        nickName=sp.getString(Const.LOGIN_NICKNAME,"");
        age=sp.getString(Const.LOGIN_AGE,"");
        sex=sp.getString(Const.LOGIN_SEX,"");
        useId=sp.getString(Const.LOGIN_ID,"");
        email=sp.getString(Const.LOGIN_EMAIL,"");
        birthday=sp.getString(Const.LOGIN_BIRTHDAY,"");
        address=sp.getString(Const.LOGIN_ADDRESS,"");
        mobile=sp.getString(Const.LOGIN_PHONE,"");
        experience=sp.getString(Const.LOGIN_EXPERIENCE,"");
        creditScore=sp.getString(Const.LOGIN_CREDITSCORE,"");
        contributionScore=sp.getString(Const.LOGIN_CONTRIBUTIONSCORE,"");
        recommendUserId=sp.getString(Const.LOGIN_RECOMMENDUSERID,"");
        claimUserId=sp.getString(Const.LOGIN_CLAIMUSERID,"");
        Picasso.with(this).load(userPortraitUrl).into(ivPersonalInfoUserIcon);
        tv_personal_info_account.setText(account);
        etPersonalInfoNickName.setText(nickName);
        etPersonalInfoAge.setText(age);
        if(sex.equals("1")){
            rbPersonalInfoMan.setChecked(true);
        }else if (sex.equals("2")){
            rbPersonalInfoWomen.setChecked(true);
        }else{
            rbPersonalInfoMan.setChecked(false);
            rbPersonalInfoWomen.setChecked(false);
        }
        etPersonalInfoBirthday.setText(birthday);
        etPersonalInfoMobile.setText(mobile);
        etPersonalInfoRecomendUser.setText(recommendUserId);
        etPersonalInfoClaimUser.setText(claimUserId);
        pbPersonalInfoCreditScore.setProgress(Integer.valueOf(creditScore));
        pbPersonalInfoExperience.setProgress( Integer.valueOf(experience));
        tvPersonalInfoContributionScore.setText(contributionScore);
    }

    @Override
    public PersonalInfoEditPresenter initPresenter() {
        return new PersonalInfoEditPresenter();
    }

    private void initView() {
        spPersonalInfoProvince.setOnItemSelectedListener(this);
        spPersonalInfoCitys.setOnItemSelectedListener(this);
        spPersonalInfoCountys.setOnItemSelectedListener(this);
        rgPersonalInfoSex.setOnCheckedChangeListener(this);
        initProvinceData();
    }

    private void initProvinceData() {
        if(DateUtils.provinceData.size()==0){
            return;
        }
        data=DateUtils.provinceData;
        for (int i = 0; i < data.size(); i++) {
            options1Items.add(data.get(i).getName());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //适配器
                province_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, android.R.layout.simple_spinner_item, options1Items);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spPersonalInfoProvince.setAdapter(province_adapter);
            }
        });
    }


    @OnClick({R.id.et_personal_info_back, R.id.et_personal_info_save,R.id.et_personal_info_birthday,R.id.iv_personal_info_userIcon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_personal_info_userIcon:
                //自定义方法的单选
                RxGalleryFinal
                        .with(this)
                        .image()
                        .radio()
                        .crop()
                        .imageLoader(ImageLoaderType.PICASSO)
                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                imgPath=imageRadioResultEvent.getResult().getOriginalPath();
                                Picasso.with(PersonalInformationActivity.this).load(new File(imgPath)).into(ivPersonalInfoUserIcon);
                            }
                        })
                        .openGallery();
                break;
            case R.id.et_personal_info_back:
                finish();
                setResult(RESULT_OK);
                break;
            case R.id.et_personal_info_birthday:
                TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
                        birthday = dateFormat.format(date);
                        etPersonalInfoBirthday.setText(birthday);
                    }
                }).build();
                pvTime.show();
                break;
            case R.id.et_personal_info_save:
                 LoadDialog.show(this);
                nickName=etPersonalInfoNickName.getText().toString();
                mobile=etPersonalInfoMobile.getText().toString();
                address=spPersonalInfoProvince.getSelectedItem().toString()+spPersonalInfoCitys.getSelectedItem().toString()+spPersonalInfoCountys.getSelectedItem().toString();
                birthday=etPersonalInfoBirthday.getText().toString();
                age=etPersonalInfoAge.getText().toString();
                if(imgPath.equals("")){
                    userInfo=new UserInfo(userId,nickName,sex,mobile,birthday,address,email,age);
                     postPersonalInfo(userInfo,"1");
                }else{
                    userInfo=new UserInfo(userId,nickName,imgPath,sex,mobile,address,birthday,email,age);
                    postPersonalInfo(userInfo,"2");
                }
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_personal_info_province:     //设置二级联动
                setSecondText(spPersonalInfoCitys, position);
                break;
            case sp_personal_info_citys: //设置三级联动
                setThirdText(spPersonalInfoCountys,position);
                break;
        }
    }
    private void setSecondText(Spinner sp, int position) {
        options2Items.clear();
        citys = (ArrayList<CityBean>) data.get(position).getSub();
        for (int i = 0; i < citys.size(); i++) {
            options2Items.add(citys.get(i).getName());
        }
        city_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, android.R.layout.simple_spinner_item, options2Items);
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
        county_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, android.R.layout.simple_spinner_item, options3Items);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId==R.id.rb_personal_info_man){
            sex="1";
        }else{
            sex="2";
        }
    }

    @Override
    public void postPersonalInfo(UserInfo userInfo, String type) {
        presenter.postPersonalData(userInfo,type);
    }

    @Override
    public void succeedToEdit(String imgPath) {
        editor.putString(Const.LOGIN_NICKNAME, nickName);
        editor.putString(Const.userPortraitUrl,HttpUtils.IMAGE_RUL+imgPath);
        editor.putString(Const.LOGIN_BIRTHDAY,birthday);
        editor.putString(Const.LOGIN_SEX,sex);
        editor.putString(Const.LOGIN_ADDRESS,address);
        editor.putString(Const.LOGIN_AGE,age);
        editor.putString(Const.LOGIN_PHONE,mobile);
        editor.putString(Const.LOGIN_EMAIL,email);
        editor.commit();
        initData();
        T.showShort(this,"修改成功");
        LoadDialog.dismiss(this);
    }

    @Override
    public void showError(String errorString) {
        initData();
        T.showShort(this,"修改失败");
        LoadDialog.dismiss(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        setResult(RESULT_OK);
        return super.onKeyDown(keyCode, event);
    }
}
