package com.ike.communityalliance.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpFragment;
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
import com.ike.communityalliance.ui.activity.LogoActivity;
import com.ike.communityalliance.ui.activity.VerifyRecommedInfoActivity;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;


public class LoginFragment extends BaseMvpFragment<ILoginView,LoginPresenterImpl> implements ILoginView{
    @BindView(R.id.et_userName)
    EditText et_userName;
    @BindView(R.id.et_login_pw)
    EditText et_pw;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Groups> list = new ArrayList<>();
    private DBOpenHelper dbOpenHelper;  //SQLite
    private GroupsDAOImpl groupsDAO;
    private FriendInfoDAOImpl friendInfoDAO;
    private GroupMemberDAOImpl groupMemberDAO;
    private String userName;
    private String pwd;

    public static int MODE = Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View containerView=inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,containerView);
        dbOpenHelper = new DBOpenHelper(getContext(), "talk.db", null, 2);// 创建数据库文件
        dbOpenHelper.getWritableDatabase();
        groupsDAO = new GroupsDAOImpl(getContext());
        friendInfoDAO = new FriendInfoDAOImpl(getContext());
        groupMemberDAO = new GroupMemberDAOImpl(getContext());
        sharedPreferences = getContext().getSharedPreferences("config", MODE);
        editor = sharedPreferences.edit();
        return containerView;
    }

    @Override
    public LoginPresenterImpl initPresenter() {
        return new LoginPresenterImpl();
    }

    @OnClick(R.id.sign_in_btn)
    public void loginOnClick(View view) {
        LoadDialog.show(getContext());
        userName = et_userName.getText().toString().trim();
        pwd = et_pw.getText().toString().trim();
        presenter.verifyLoginInfo(userName, pwd);
    }

    @Override
    public void showUserNameOrPassWordEmpty(String errorString) {
        T.showShort(getContext(),errorString);
    }
    @Override
    public void showFailedLogin(String errorString) {
        LoadDialog.dismiss(getContext());
        T.showShort(getContext(),errorString);
    }

    @Override
    public void succeedToLogin(UserInfo userInfo) {
        LoadDialog.dismiss(getContext());
        saveData(userInfo);
        String status=userInfo.getStatus();
        if(status==null){
            status="1";
        }
        if(status.equals("0")){
            Intent intent=new Intent(getActivity(),VerifyRecommedInfoActivity.class);
            intent.putExtra("useId",userInfo.getUserId());
            intent.putExtra("fromLogin",true);
            startActivity(intent);
        }else{
            Intent intent=new Intent(getActivity(),LogoActivity.class);
            startActivity(intent);
        }
        T.showShort(getContext(),"登录成功~~");
        getActivity().finish();
    }

    private void saveData(UserInfo userInfo) {
        String useId=userInfo.getUserId();
        String token = userInfo.getToken();
        String nickName = userInfo.getNickname();
        String userPortraitUrl=HttpUtils.IMAGE_RUL+userInfo.getUserPortraitUrl();
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
