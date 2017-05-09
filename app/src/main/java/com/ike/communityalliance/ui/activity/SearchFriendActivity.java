package com.ike.communityalliance.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.UserOrGroupBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imageloader.core.ImageLoader;
import okhttp3.Call;

/**
 * 搜索好友----添加好友
 */
public class SearchFriendActivity extends BaseActivity {

    @BindView(R.id.et_friend)
    EditText etFriend;
    @BindView(R.id.iv_newFriendsOrGroup_item_header)
    SelectableRoundedImageView iv_newFriendsOrGroup_item_header;
    @BindView(R.id.tv_newFriendsOrGroup_item_groupName)
    TextView tv_newFriendsOrGroup_item_groupName;
    @BindView(R.id.btn_newFriendsOrGroup_join)
    Button btn_newFriendsOrGroup_join;
    @BindView(R.id.rl_newfriendOrGroup)
    RelativeLayout rl_newfriendOrGroup;
    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_searchFriend_scan_white)
    ImageView ivTitleRight;

    private String phone;
    private String headUri;
    private String userName;
    private String f_userid;
    private String status;
    private SharedPreferences sp;
    private String myUserId,myNickname;
    private KeyListener keyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        ButterKnife.bind(this);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        myUserId=sp.getString(Const.LOGIN_ID,"");
        myNickname=sp.getString(Const.LOGIN_NICKNAME,"");
        //ivTitleRight.setImageResource(R.mipmap.scan_white);

        etFriend.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 11||charSequence.length()==7) {
                    phone = charSequence.toString().trim();
//                    if (!AMUtils.isMobile(phone)) {
//                        T.showShort(mContext, "非手机号码");
//                        return;
//                    }
                    LoadDialog.show(mContext);
                    searchFriends();
                } else {
                    rl_newfriendOrGroup.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }



    private void searchFriends() {
        HttpUtils.PostSearchFriendRequest("/lookupUser", phone, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LoadDialog.dismiss(mContext);
                T.showShort(mContext, "/lookupUser-----"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
               Type type=new TypeToken<Code<UserOrGroupBean>>(){}.getType();
                Code<UserOrGroupBean> beanCode = gson.fromJson(response,type);
                int code=beanCode.getCode();
                if (code == 200) {
                    UserOrGroupBean userOrGroupBean=beanCode.getData();
                    if(userOrGroupBean==null){
                        T.showShort(mContext, "用户不存在");
                        LoadDialog.dismiss(mContext);
                        return;
                    }
                    status=userOrGroupBean.getStatus();
                    if(status.equals("0")){
                        f_userid=beanCode.getData().getUserId();
                        headUri = HttpUtils.IMAGE_RUL+beanCode.getData().getUserPortraitUrl();
                        userName = beanCode.getData().getNickname();
                        btn_newFriendsOrGroup_join.setText("添加好友");
                    }else{
                        f_userid=beanCode.getData().getGroupId();
                        headUri = HttpUtils.IMAGE_RUL+beanCode.getData().getGroupPortraitUrl();
                        userName = beanCode.getData().getGroupName();
                        btn_newFriendsOrGroup_join.setText("申请加入");
                    }
                    LoadDialog.dismiss(mContext);
                    rl_newfriendOrGroup.setVisibility(View.VISIBLE);
                    String image=beanCode.getData().getUserPortraitUrl();
                    if(!TextUtils.isEmpty(image)) {
                        ImageLoader.getInstance().displayImage(headUri, iv_newFriendsOrGroup_item_header);
                    }else {
                        iv_newFriendsOrGroup_item_header.setImageResource(R.mipmap.default_portrait);
                    }
                    tv_newFriendsOrGroup_item_groupName.setText(userName);
                    btn_newFriendsOrGroup_join.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (phone.equals(getSharedPreferences("config", MODE_PRIVATE).getString("loginphone", ""))) {
                                T.showShort(mContext, "不能添加自己为好友");
                                return;
                            }
                            final EditText editText = new EditText(mContext);
                            new AlertDialog.Builder(mContext)
                                    .setTitle("验证信息")
                                    .setView(editText)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            String provingMessage = editText.getText().toString();
                                            LoadDialog.show(mContext);
                                          if(status.equals("0")){
                                              sendFriendRequest(provingMessage);
                                          }else{
                                               sendGroupRequest(provingMessage);
                                          }
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();

                        }
                    });

                } else {
                    T.showShort(mContext, "用户不存在");
                    LoadDialog.dismiss(mContext);
                }
            }
        });
    }

    private void sendGroupRequest(String provingMessage) {
        HttpUtils.sendPostRequest("/addfriendRequest",status, myUserId,f_userid, provingMessage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/addfriendRequest-----"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> baseBean = gson.fromJson(response, type);
                int code = baseBean.getCode();
                switch (code) {
                    case 200:
                        T.showShort(mContext, "请求成功，请耐心等待审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你已加入该群组");
                        break;
                    case 0:
                        T.showShort(mContext,"群组添加失败");
                        break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }


    private void sendFriendRequest(String message) {
        HttpUtils.sendPostRequest("/addfriendRequest",status, myUserId, myNickname, f_userid, message, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/addfriendRequest-----"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> baseBean = gson.fromJson(response, type);
                int code = baseBean.getCode();
                switch (code) {
                    case 200:
                        T.showShort(mContext, "请求成功，请耐心等待对方审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你们已是好友");
                        break;
                    case 0:
                        T.showShort(mContext,"好友添加失败");
                         break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_right:
                startActivityForResult(new Intent(mContext, CaptureActivity.class),0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                String result=bundle.getString("result");
                etFriend.setText(result);
            }
        }
    }
}
