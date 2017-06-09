package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.SendShareFriendsGVAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.DateUtils;
import com.ike.communityalliance.wedget.DemoGridView;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;
import okhttp3.Call;

public class SendShareFriendsActivity extends BaseActivity {
    private static final int REQUECT_CODE_CAMERA = 1002;
    @BindView(R.id.gv_sendshareFriend)
    DemoGridView gv_sendshareFriend;
    @BindView(R.id.ll_sendshareFriend_back)
    LinearLayout ll_sendshareFriend_back;
    @BindView(R.id.tv_sendshareFriend_public)
    TextView tv_sendshareFriend_public;
    @BindView(R.id.et_sendshareFriend_content)
    EditText et_sendshareFriend_content;
    @BindView(R.id.tv_sendshareFriend_whoSee)
    TextView tvSendshareFriendWhoSee;
    @BindView(R.id.tv_sendshareFriend_see)
    TextView tvSendshareFriendSee;
    private SendShareFriendsGVAdapter adapter;
    private SharedPreferences sp;
    private String userId;
    private String userName;
    private String userPortraitUrl;
    private List<String> filePaths;
    private String whoSee="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_share_friends);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        userName = sp.getString(Const.LOGIN_NICKNAME, "");
        userPortraitUrl = sp.getString(Const.userPortraitUrl, "");
        ModelUtils.setDebugModel(true);
        initView();
    }

    private void initView() {
        adapter = new SendShareFriendsGVAdapter(mContext);
        gv_sendshareFriend.setAdapter(adapter);
    }

    @OnClick({R.id.ll_sendshareFriend_back, R.id.tv_sendshareFriend_public,R.id.tv_sendshareFriend_see})
    public void sendShareFriendsOnclick(View view) {
        switch (view.getId()) {
            case R.id.ll_sendshareFriend_back:
                finish();
                break;
            case R.id.tv_sendshareFriend_see:
              Intent intent=new Intent(this,WhoCanSeeActivity.class);
                intent.putExtra("whoSee",whoSee);
                startActivityForResult(intent,11);
                break;
            case R.id.tv_sendshareFriend_public:
                LoadDialog.show(this);
                final String content = et_sendshareFriend_content.getText().toString();
                filePaths = adapter.getImaList();
                Map<String, File> imgFiles = initFiles();
                if (content.equals("") && filePaths.size() == 0) {
                    T.showShort(this, "内容不为空");
                    return;
                }
                HttpUtils.publishShareFriends("/releaseFriendsCircle", userId, content,whoSee,imgFiles, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        T.showShort(SendShareFriendsActivity.this, e.toString());
                        LoadDialog.dismiss(SendShareFriendsActivity.this);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<Code<TalkTalkBean>>() {
                        }.getType();
                        Code<TalkTalkBean> code = gson.fromJson(response, type);
                        if (code.getCode() == 200) {
                            T.showShort(SendShareFriendsActivity.this, "发布成功");
                            String date = DateUtils.getNowDate();
                            TalkTalkBean talkTalkBean = new TalkTalkBean(code.getData().getId(), userId, userName, userPortraitUrl, content, date, 0, 0, 0, code.getData().getImages());
                            Intent intent = new Intent();
                            intent.putExtra("talkTalkBean", talkTalkBean);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            T.showShort(SendShareFriendsActivity.this, "发布失败");
                        }
                        LoadDialog.dismiss(SendShareFriendsActivity.this);
                    }
                });
                break;
        }
    }


    /**
     * 获取选取的图片文件
     *
     * @return
     */
    private Map<String, File> initFiles() {
        Map<String, File> map = new HashMap<>();
        filePaths = adapter.getImaList();
        for (int i = 0; i < filePaths.size(); i++) {
            File file = new File(filePaths.get(i));
            String fileName = file.getName();
            map.put(fileName, file);
        }
        return map;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode==11&&resultCode==RESULT_OK){
                whoSee=data.getStringExtra("whoSee");
                if(whoSee.equals("0")){
                    tvSendshareFriendWhoSee.setText("全平台可见");
                    tvSendshareFriendSee.setText("公开");
                }else{
                    tvSendshareFriendWhoSee.setText("仅VIP可见");
                    tvSendshareFriendSee.setText("非公开");
                }
            }
    }
}
