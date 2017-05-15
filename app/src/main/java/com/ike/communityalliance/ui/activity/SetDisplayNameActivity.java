package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.MorePersonalInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;

public class SetDisplayNameActivity extends AppCompatActivity {

    @BindView(R.id.ll_set_dispaly_name_back)
    AutoLinearLayout llSetDispalyNameBack;
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.et_set_displayName)
    EditText etSetDisplayName;
    private FriendInfo friendInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_display_name);
        ButterKnife.bind(this);
        friendInfo=getIntent().getParcelableExtra("friendInfo");
    }

    @OnClick({R.id.ll_set_dispaly_name_back, R.id.tv_set})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_set_dispaly_name_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.tv_set:
               if(friendInfo==null){
                   return;
               }
                LoadDialog.show(this);
               String displayName=etSetDisplayName.getText().toString();
                changeDiaplayName(displayName);
                break;
        }
    }
    //修改备注名
    private void changeDiaplayName(final String displayName) {
        String myId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        HttpUtils.postChangeFriendName("/editFriendName", myId, friendInfo.getUserId(), displayName, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(SetDisplayNameActivity.this, "/editFriendName----" + e);
                LoadDialog.dismiss(SetDisplayNameActivity.this);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<MorePersonalInfo>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    LoadDialog.dismiss(SetDisplayNameActivity.this);
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(friendInfo.getUserId(), displayName, Uri.parse(friendInfo.getUserPortraitUrl())));
                    Intent intent=new Intent();
                    intent.putExtra("displayName",displayName);
                  setResult(RESULT_OK,intent);
                    finish();
                } else {
                    LoadDialog.dismiss(SetDisplayNameActivity.this);
                    T.showShort(SetDisplayNameActivity.this, "修改失败");
                }
            }
        });
    }
}
