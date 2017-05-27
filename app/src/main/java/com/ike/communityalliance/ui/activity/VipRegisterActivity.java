package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.interfaces.IRegisterView;
import com.ike.communityalliance.presenter.RegisterPresenterImpl;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mob.MobSDK.getContext;

public class VipRegisterActivity extends BaseMvpActivity<IRegisterView,RegisterPresenterImpl> implements IRegisterView{

    @BindView(R.id.iv_vip_rg_back)
    ImageView ivVipRgBack;
    @BindView(R.id.et_vip_rg_phone)
    EditText etVipRgPhone;
    @BindView(R.id.et_vip_rg_username)
    EditText etVipRgUsername;
    @BindView(R.id.et_vip_rg_pwd)
    EditText etVipRgPwd;
    @BindView(R.id.et_vip_rg_inviteCode)
    EditText etVipRgInviteCode;
    @BindView(R.id.btn_vip_register)
    Button btnVipRegister;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_register);
        ButterKnife.bind(this);
    }

    @Override
    public RegisterPresenterImpl initPresenter() {
        return new RegisterPresenterImpl();
    }

    @OnClick({R.id.iv_vip_rg_back, R.id.btn_vip_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_vip_rg_back:
                finish();
                break;
            case R.id.btn_vip_register:
                LoadDialog.show(getContext());
                String password = etVipRgPwd.getText().toString();
                String mobile = etVipRgPhone.getText().toString();
                String nikeName=etVipRgUsername.getText().toString();
                String inviteCode = etVipRgInviteCode.getText().toString().trim();
                presenter.verifyRegisterInfo(nikeName,mobile,password,inviteCode);
                break;
        }
    }

    @Override
    public void showTextEmpty() {
        T.showShort(getContext(),"用户名、手机号码、密码、推荐码不能为空！");
        LoadDialog.dismiss(getContext());
    }

    @Override
    public void showRegisterError(String string) {
        T.showShort(getContext(),string);
        LoadDialog.dismiss(getContext());
    }

    @Override
    public void showPwdError() {
        T.showShort(getContext(),"密码不能少于4位");
        LoadDialog.dismiss(getContext());
    }

    @Override
    public void succeedToRegister() {
        LoadDialog.dismiss(getContext());
        T.showShort(getContext(),"注册成功~~");
//        Intent intent=new Intent(this, VerifyRecommedInfoActivity.class);
//        intent.putExtra("useId",etVipRgPhone.getText().toString());
//        intent.putExtra("recommendId",etVipRgInviteCode.getText().toString());
//        startActivity(intent);
        etVipRgPhone.setText("");
        etVipRgUsername.setText("");
        etVipRgPwd.setText("");
        etVipRgInviteCode.setText("");
        Intent intent=new Intent();
        intent.putExtra("mobile",etVipRgPhone.getText().toString().trim());
        intent.putExtra("pwd",etVipRgPwd.getText().toString().trim());
        setResult(RESULT_OK,intent);
        finish();
    }
}
