package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.ike.mylibrary.util.CommonUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

import static com.ike.communityalliance.R.id.sp_personal_info_citys;

public class PersonalInformationActivity extends BaseMvpActivity<IPersonalInfoEditView, PersonalInfoEditPresenter> implements IPersonalInfoEditView, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

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
    @BindView(R.id.et_personal_info_recomendUser)
    TextView etPersonalInfoRecomendUser;
    @BindView(R.id.et_personal_info_claimUser)
    TextView etPersonalInfoClaimUser;
    @BindView(R.id.tv_personal_info_moreInfo)
    TextView tvPersonalInfoMoreInfo;
    @BindView(R.id.et_personal_info_email)
    EditText et_personal_info_email;
    @BindView(R.id.ll_personal_info_moreInfo)
    RelativeLayout ll_personal_info_moreInfo;
    @BindView(R.id.rg_personal_info_like)
    RadioGroup rgPersonalInfoLike;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<String> options2Items = new ArrayList<>();
    private ArrayList<String> options3Items = new ArrayList<>();
    private ArrayAdapter province_adapter;
    private ArrayAdapter city_adapter;
    private ArrayAdapter county_adapter;
    private ArrayList<ProvinceBean> data;
    private ArrayList<CityBean> citys;
    private String imgPath = "";
    private UserInfo userInfo;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String userId;
    private List<String> addressList;
    private String userPortraitUrl,account,nickName,sex,email,recommendUserId,
            address,experience,creditScore,contributionScore,claimUserId,favour;
    private List<String> hobbyList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        userId = sp.getString(Const.LOGIN_ID, "");
        initData();
        initView();
    }

    private void initData() {
        userPortraitUrl = sp.getString(Const.userPortraitUrl, "");
        account = sp.getString(Const.LOGIN_ACCOUNT, "");
        nickName = sp.getString(Const.LOGIN_NICKNAME, "");
        sex = sp.getString(Const.LOGIN_SEX, "");
        email = sp.getString(Const.LOGIN_EMAIL, "");
        address = sp.getString(Const.LOGIN_ADDRESS, "");
        experience = sp.getString(Const.LOGIN_EXPERIENCE, "");
        creditScore = sp.getString(Const.LOGIN_CREDITSCORE, "");
        contributionScore = sp.getString(Const.LOGIN_CONTRIBUTIONSCORE, "");
        recommendUserId = sp.getString(Const.LOGIN_RECOMMENDUSERID, "");
        claimUserId = sp.getString(Const.LOGIN_CLAIMUSERID, "");
        favour = sp.getString(Const.LOGIN_FAVOUR, "");
        hobbyList=Arrays.asList(favour.split(","));
        initHobby();
        Picasso.with(this).load(userPortraitUrl).into(ivPersonalInfoUserIcon);
        tv_personal_info_account.setText(account);
        etPersonalInfoNickName.setText(nickName);
        if (sex.equals("1")) {
            rbPersonalInfoMan.setChecked(true);
        } else if (sex.equals("2")) {
            rbPersonalInfoWomen.setChecked(true);
        } else {
            rbPersonalInfoMan.setChecked(false);
            rbPersonalInfoWomen.setChecked(false);
        }
        etPersonalInfoRecomendUser.setText(recommendUserId);
        etPersonalInfoClaimUser.setText(claimUserId);
        et_personal_info_email.setText(email);
        pbPersonalInfoCreditScore.setProgress(Integer.valueOf(creditScore));
        pbPersonalInfoExperience.setProgress(Integer.valueOf(experience));
        tvPersonalInfoContributionScore.setText(contributionScore);
        tvPersonalInfoCreditScore.setText(creditScore);
        tvPersonalInfoExperience.setText(experience);
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
                province_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, R.layout.simple_spanner_item, options1Items);
                //设置样式
                province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //加载适配器
                spPersonalInfoProvince.setAdapter(province_adapter);
                initAddress();
            }
        });
    }

    //初始化地址
    private void initAddress() {
        addressList = Arrays.asList(address.split(","));
        if (addressList.size() > 0) {
            int position = options1Items.indexOf(addressList.get(0));
            spPersonalInfoProvince.setSelection(position);
        }
    }
    //初始化爱好
    private void initHobby() {
        if(hobbyList==null){
            return;
        }
        for (int i = 0; i < rgPersonalInfoLike.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) rgPersonalInfoLike.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb= (CheckBox) ll.getChildAt(j);
                if(hobbyList.contains(rb.getText().toString())){
                    rb.setChecked(true);
                }
            }
        }
    }

    @OnClick({R.id.et_personal_info_back, R.id.et_personal_info_save,
            R.id.iv_personal_info_userIcon, R.id.ll_personal_info_moreInfo})
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
                                imgPath = imageRadioResultEvent.getResult().getOriginalPath();
                                Picasso.with(PersonalInformationActivity.this).load(new File(imgPath)).into(ivPersonalInfoUserIcon);
                            }
                        })
                        .openGallery();
                break;
            case R.id.et_personal_info_back:
                finish();
                setResult(RESULT_OK);
                break;
            case R.id.ll_personal_info_moreInfo:
                startActivity(new Intent(this, MorePersonalInfoActivity.class));
                break;
            case R.id.et_personal_info_save:
                LoadDialog.show(this);
                nickName = etPersonalInfoNickName.getText().toString();
                address = spPersonalInfoProvince.getSelectedItem().toString() + "," +
                        spPersonalInfoCitys.getSelectedItem().toString();
                try {
                    address = address + "," + spPersonalInfoCountys.getSelectedItem().toString();
                } catch (Exception e) {
                }
                favour=getHobby(rgPersonalInfoLike);
                if(favour.split(",").length>3){
                    T.showShort(this, "爱好最多只能选3项");
                    LoadDialog.dismiss(this);
                    return;
                }
                email = et_personal_info_email.getText().toString();
                if (address.equals("请选择,请选择, ")) {
                    T.showShort(this, "请输入详细地址信息");
                    LoadDialog.dismiss(this);
                    return;
                }
                if (!CommonUtils.isEmail(email)) {
                    T.showShort(this, "邮箱格式不正确");
                    LoadDialog.dismiss(this);
                    return;
                }
                if (imgPath.equals("")) {
                    userInfo = new UserInfo(userId,nickName,sex,address,email,favour);
                    postPersonalInfo(userInfo, "1");
                } else {
                    userInfo = new UserInfo(userId,nickName,imgPath,sex,address,email,favour);
                    postPersonalInfo(userInfo, "2");
                }
                break;
        }
    }

    private boolean isFirstHobby=true;
    //获取选择的爱好
    public String getHobby(ViewGroup group) {
        String hobbys="";
        for (int i = 0; i < group.getChildCount(); i++) {
            LinearLayout ll= (LinearLayout) group.getChildAt(i);
            for (int j= 1; j < ll.getChildCount(); j++) { //j从第一个开始，跳过Textview
                CheckBox rb= (CheckBox) ll.getChildAt(j);
                if (rb.isChecked()) {
                    if (isFirstHobby) {
                        hobbys = rb.getText().toString();
                        isFirstHobby = false;
                    } else {
                        hobbys = hobbys + "," + rb.getText().toString();
                    }
                }
            }
        }
        isFirstHobby=true;
        return  hobbys;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.sp_personal_info_province:     //设置二级联动
                setSecondText(spPersonalInfoCitys, position);
                if (addressList.size() > 1) {
                    int index = options2Items.indexOf(addressList.get(1));
                    spPersonalInfoCitys.setSelection(index);
                }
                break;
            case sp_personal_info_citys: //设置三级联动
                setThirdText(spPersonalInfoCountys, position);
                if (addressList.size() > 2) {
                    int index = options3Items.indexOf(addressList.get(2));
                    spPersonalInfoCountys.setSelection(index);
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
        city_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, R.layout.simple_spanner_item, options2Items);
        city_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(city_adapter);
    }

    private void setThirdText(Spinner sp, int position) {
        options3Items.clear();
        ArrayList<CountyBean> country = (ArrayList<CountyBean>) citys.get(position).getSub();
        if (country == null) {
            options3Items.add(" ");
        } else {
            if (country.size() == 0) {
                options3Items.add("");
            }
            for (int i = 0; i < country.size(); i++) {
                options3Items.add(country.get(i).getName());
            }
        }
        county_adapter = new ArrayAdapter<String>(PersonalInformationActivity.this, R.layout.simple_spanner_item, options3Items);
        county_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(county_adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_personal_info_man) {
            sex = "1";
        } else {
            sex = "2";
        }
    }

    @Override
    public void postPersonalInfo(UserInfo userInfo, String type) {
        presenter.postPersonalData(userInfo, type);
    }

    @Override
    public void succeedToEdit(String imgPath) {
        LoadDialog.dismiss(this);
        editor.putString(Const.LOGIN_NICKNAME, nickName);
        editor.putString(Const.userPortraitUrl, HttpUtils.IMAGE_RUL + imgPath);
        editor.putString(Const.LOGIN_SEX, sex);
        editor.putString(Const.LOGIN_ADDRESS, address);
        editor.putString(Const.LOGIN_EMAIL, email);
        editor.putString(Const.LOGIN_FAVOUR,favour);
        editor.commit();
        initData();
        T.showShort(this, "修改成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this, errorString);
        LoadDialog.dismiss(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_OK);
        }
        return super.onKeyDown(keyCode, event);
    }
}
