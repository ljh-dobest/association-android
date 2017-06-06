package com.ike.communityalliance.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.db.GroupMemberDAOImpl;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.kyleduo.switchbutton.SwitchButton;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imageloader.core.ImageLoader;

import static com.jrmf360.rylib.wallet.ui.BaseActivity.userId;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.ll_setting_back)
    AutoLinearLayout llSettingBack;
    @BindView(R.id.tv_setting_comfirm)
    TextView tvSettingComfirm;
    @BindView(R.id.sw_setting_msg)
    SwitchButton swSettingMsg;
    @BindView(R.id.btn_setting_out)
    Button btnSettingOut;
    private SharedPreferences sp;

    private GroupsDAOImpl groupsDAO;
    private GroupMemberDAOImpl groupMemberDAO;
    private FriendInfoDAOImpl friendInfoDAO;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        groupsDAO = new GroupsDAOImpl(this);
        friendInfoDAO = new FriendInfoDAOImpl(this);
        groupMemberDAO = new GroupMemberDAOImpl(this);
        sp =this.getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        userId=sp.getString(Const.LOGIN_ID,"");
       initView();
    }

    private void initView() {
        swSettingMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    @OnClick({R.id.ll_setting_back, R.id.tv_setting_comfirm, R.id.btn_setting_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_back:
                finish();
                break;
            case R.id.tv_setting_comfirm:
                break;
            case R.id.btn_setting_out:
                new AlertDialog.Builder(this)
                        .setTitle("退出")
                        .setPositiveButton("确定退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                sp.edit().clear().commit();
                                groupsDAO.delete(userId);
                                friendInfoDAO.delete(userId);
                                groupMemberDAO.delete(userId);
                                ImageLoader.getInstance().clearDiskCache();
                                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                break;
        }
    }
}
