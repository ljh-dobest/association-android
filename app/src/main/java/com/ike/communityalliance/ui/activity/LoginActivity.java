package com.ike.communityalliance.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.DBOpenHelper;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.db.GroupMemberDAOImpl;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.ike.communityalliance.interfaces.ILoginView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.LoginPresenterImpl;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * 登录
 */
public class LoginActivity extends BaseMvpActivity<ILoginView,LoginPresenterImpl> implements ILoginView {


    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_normal_register)
    TextView tvNormalRegister;
    @BindView(R.id.tv_vip_register)
    TextView tvVipRegister;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Groups> list = new ArrayList<>();
    private DBOpenHelper dbOpenHelper;  //SQLite
    private GroupsDAOImpl groupsDAO;
    private FriendInfoDAOImpl friendInfoDAO;
    private GroupMemberDAOImpl groupMemberDAO;
    private String userName;
    private String pwd;

    public static int MODE = Context.MODE_PRIVATE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }


    private void initView() {
        dbOpenHelper = new DBOpenHelper(this, "talk.db", null, 2);// 创建数据库文件
        dbOpenHelper.getWritableDatabase();
        groupsDAO = new GroupsDAOImpl(this);
        friendInfoDAO = new FriendInfoDAOImpl(this);
        groupMemberDAO = new GroupMemberDAOImpl(this);
        sharedPreferences =getSharedPreferences("config", MODE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==RESULT_OK){
           switch (requestCode) {
               case 100:
                  etLoginPhone.setText(data.getStringExtra("mobile"));
                  etLoginPwd.setText(data.getStringExtra("pwd"));
                   break;
               case 101:

                   break;
           }
       }
    }

    @OnClick({R.id.btn_login, R.id.tv_normal_register, R.id.tv_vip_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                LoadDialog.show(this);
                userName = etLoginPhone.getText().toString().trim();
                pwd = etLoginPwd.getText().toString().trim();
                presenter.verifyLoginInfo(userName, pwd);
                break;
            case R.id.tv_normal_register:
                startActivityForResult(new Intent(this,CommonRegisterActivity.class),100);
                break;
            case R.id.tv_vip_register:
                startActivityForResult(new Intent(this,VipRegisterActivity.class),101);
                break;
        }
    }

    @Override
    public void showUserNameOrPassWordEmpty(String errorString) {
        T.showShort(this,errorString);

    }

    @Override
    public void showFailedLogin(String errorString) {
        LoadDialog.dismiss(this);
        T.showShort(this,errorString);
    }

    @Override
    public void succeedToLogin(UserInfo userInfo) {
        LoadDialog.dismiss(this);
        saveData(userInfo);
        String status=userInfo.getStatus();
        if(status==null){
            status="1";
        }
        if(status.equals("0")){
            Intent intent=new Intent(this,VerifyRecommedInfoActivity.class);
            intent.putExtra("useId",userInfo.getUserId());
            intent.putExtra("recommendId",userInfo.getNumberId());
            intent.putExtra("fromLogin",true);
            startActivity(intent);
        }else{
            Intent intent=new Intent(this,LogoActivity.class);
            startActivity(intent);
        }
        finish();
    }
    private void saveData(UserInfo userInfo) {
        String useId=userInfo.getUserId();
        String recommedId=userInfo.getNumberId();
        String token = userInfo.getToken();
        String nickName = userInfo.getNickname();
        String userPortraitUrl= HttpUtils.IMAGE_RUL+userInfo.getUserPortraitUrl();
        String account=userInfo.getNumberId();
        String sex =userInfo.getSex();
        String phone = userInfo.getMobile();
        String address = userInfo.getAddress();
        String birthday = userInfo.getBirthday();
        String email=userInfo.getEmail();
        String age =userInfo.getAge();
        String experience=userInfo.getExperience();
        String creditScore=userInfo.getCreditScore();
        String contributionScore=userInfo.getContributionScore();
        String recommendUserId=userInfo.getRecommendUserId();
        String claimUserId=userInfo.getClaimUserId();
        editor.putString(Const.LOGIN_USERNAME, userName);
        editor.putString(Const.LOGIN_RECOMMEDID,recommedId);
        editor.putString(Const.LOGIN_PASSWORD, pwd);
        editor.putBoolean("login_message", true);
        editor.putString(Const.LOGIN_ID, useId);
        editor.putString(Const.LOGIN_TOKEN, token);
        editor.putString(Const.LOGIN_NICKNAME, nickName);
        editor.putString(Const.LOGIN_PORTRAIT, userPortraitUrl);
        editor.putString(Const.LOGIN_ACCOUNT, account);
        editor.putString(Const.userPortraitUrl,userPortraitUrl);
        editor.putString(Const.LOGIN_BIRTHDAY,birthday);
        editor.putString(Const.LOGIN_SEX,sex);
        editor.putString(Const.LOGIN_ADDRESS,address);
        editor.putString(Const.LOGIN_AGE,age);
        editor.putString(Const.LOGIN_PHONE,phone);
        editor.putString(Const.LOGIN_EMAIL,email);
        editor.putString(Const.LOGIN_EXPERIENCE,experience);
        editor.putString(Const.LOGIN_CREDITSCORE,creditScore);
        editor.putString(Const.LOGIN_CONTRIBUTIONSCORE,contributionScore);
        editor.putString(Const.LOGIN_RECOMMENDUSERID,recommendUserId);
        editor.putString(Const.LOGIN_CLAIMUSERID,claimUserId);
        editor.commit();
        RongIM.getInstance().refreshUserInfoCache(new io.rong.imlib.model.UserInfo(useId, nickName, Uri.parse(userPortraitUrl)));
    }
}














