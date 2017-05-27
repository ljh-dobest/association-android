package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;

public class CommonRegisterActivity extends BaseActivity {

    @BindView(R.id.iv_commen_rg_back)
    ImageView ivCommenRgBack;
    @BindView(R.id.et_commen_rg_phone)
    EditText etCommenRgPhone;
    @BindView(R.id.et_commen_rg_username)
    EditText etCommenRgUsername;
    @BindView(R.id.et_commen_rg_code)
    EditText etCommenRgCode;
    @BindView(R.id.tv_commen_rg_get_code)
    TextView tvCommenRgGetCode;
    @BindView(R.id.btn_commen_registe)
    Button btnCommenRegiste;
    private static String APPKEY = "1e33fd00667a6";
    private static String APPSECRET = "e55f75db8d5e7cb366a6ad5f8dad968f";
    @BindView(R.id.et_commen_rg_pwd)
    EditText etCommenRgPwd;
    @BindView(R.id.tv_commen_rg_get_number)
    TextView tvCommenRgGetNumber;
    private int time = 120;
    private boolean flag = true;
    private String phone;
    private String iCord;
    private String nickname;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_register);
        ButterKnife.bind(this);
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eventHandler);
    }

    @OnClick({R.id.iv_commen_rg_back, R.id.tv_commen_rg_get_code, R.id.btn_commen_registe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_commen_rg_back:
                finish();
                break;
            case R.id.tv_commen_rg_get_code:
                getCode();
                break;
            case R.id.btn_commen_registe:
                nickname = etCommenRgUsername.getText().toString();
                password = etCommenRgPwd.getText().toString();
                phone = etCommenRgPhone.getText().toString();
                iCord = etCommenRgCode.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)) {
                    T.showShort(mContext, "昵称不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    T.showShort(mContext, "手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(password) || password.length() < 4) {
                    T.showShort(mContext, "密码不能为空且长度不能小于4");
                    return;
                }
                if (!TextUtils.isEmpty(iCord)) {
                    if (iCord.length() == 4) {
                        SMSSDK.submitVerificationCode("86", phone, iCord);
                        flag = false;
                    } else {
                        T.showShort(mContext, "请输入完整验证码");
                        etCommenRgCode.requestFocus();
                        return;
                    }
                } else {
                    T.showShort(mContext, "请输入验证码");
                    etCommenRgCode.requestFocus();
                    return;
                }
                LoadDialog.show(mContext);
                break;
        }
    }

    //点击获取验证码
    private void getCode() {
        phone = etCommenRgPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(phone)) {
            if (phone.length() == 11) {
                LoadDialog.show(mContext, "正在请求服务器中...");
                SMSSDK.getVerificationCode("86", phone);
            } else {
                Toast.makeText(CommonRegisterActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
                etCommenRgPhone.requestFocus();
            }
        } else {
            Toast.makeText(CommonRegisterActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
            etCommenRgPhone.requestFocus();
        }
    }

    //验证码送成功后提示文字
    private void reminderText() {
        LoadDialog.dismiss(mContext);
       tvCommenRgGetNumber.setVisibility(View.VISIBLE);
        tvCommenRgGetCode.setVisibility(View.GONE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (time > 0) {
                    tvCommenRgGetNumber.setText(time + "秒");
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                } else {
                    tvCommenRgGetNumber.setText("");
                    tvCommenRgGetCode.setVisibility(View.VISIBLE);
                    tvCommenRgGetNumber.setVisibility(View.GONE);
                    time = 120;
                }
            } else {
              //  T.showShort(CommonRegisterActivity.this,"验证通过`");
            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            Log.e("Result", "发送结果" + result);

            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    handlerText.sendEmptyMessage(2);
                    toPostRegister();
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }

            } else {
                if (flag) {
                    Toast.makeText(CommonRegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    etCommenRgPhone.requestFocus();
                    LoadDialog.dismiss(mContext);
                    return;
                } else {
                    ((Throwable) data).printStackTrace();
//                    int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
                    Toast.makeText(CommonRegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    etCommenRgCode.selectAll();
                    flag=true;
                    LoadDialog.dismiss(mContext);
//                    if (resId > 0) {
//                        Toast.makeText(RegisterActivity.this, resId + "", Toast.LENGTH_SHORT).show();
//                    }
                    return;
                }
            }
        }
    };

    private void toPostRegister() {
        HttpUtils.postRegisterRequest("/ordinaryRegister", nickname, phone, password, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(CommonRegisterActivity.this, "网络异常");
                LoadDialog.dismiss(CommonRegisterActivity.this);
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        T.showShort(CommonRegisterActivity.this, "注册成功");
                        LoadDialog.dismiss(CommonRegisterActivity.this);
                        Intent intent=new Intent();
                        intent.putExtra("mobile",etCommenRgPhone.getText().toString().trim());
                        intent.putExtra("pwd",etCommenRgPwd.getText().toString().trim());
                        setResult(RESULT_OK,intent);
                        finish();
                        break;
                    case 100:
                        T.showShort(CommonRegisterActivity.this, "用户已注册");
                        LoadDialog.dismiss(CommonRegisterActivity.this);
                        break;
                    case 0:
                        T.showShort(CommonRegisterActivity.this, "注册失败");
                        LoadDialog.dismiss(CommonRegisterActivity.this);
                        break;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
