package com.ike.communityalliance.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.ContastsInfo;
import com.ike.communityalliance.bean.Friend;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.interfaces.IUserDetailView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.UserDetailPresnter;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.ike.mylibrary.util.T;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import okhttp3.Call;

/**
 * 用户详情页
 */
public class UserDetailActivity extends BaseMvpActivity<IUserDetailView, UserDetailPresnter> implements IUserDetailView {


    @BindView(R.id.ll_user_detail_back)
    AutoLinearLayout llUserDetailBack;
    @BindView(R.id.iv_user_detail_userIcon)
    XCRoundRectImageView ivUserDetailUserIcon;
    @BindView(R.id.tv_user_detail_name)
    TextView tvUserDetailName;
    @BindView(R.id.tv_user_detail_age)
    TextView tvUserDetailAge;
    @BindView(R.id.iv_user_detail_sex)
    ImageView ivUserDetailSex;
    @BindView(R.id.tv_user_detail_account)
    TextView tvUserDetailAccount;
    @BindView(R.id.tv_user_detail_email)
    TextView tvUserDetailEmail;
    @BindView(R.id.tv_user_detail_phone)
    TextView tvUserDetailPhone;
    @BindView(R.id.tv_user_detail_birthday)
    TextView tvUserDetailBirthday;
    @BindView(R.id.tv_user_detail_address)
    TextView tvUserDetailAddress;
    @BindView(R.id.tv_user_detail_recommenerName)
    TextView tvUserDetailRecommenerName;
    @BindView(R.id.tv_user_detail_claimerName)
    TextView tvUserDetailClaimerName;
    @BindView(R.id.tv_user_detail_contributionNum)
    TextView tvUserDetailContributionNum;
    @BindView(R.id.tv_user_detail_creditScore)
    TextView tvUserDetailCreditScore;
    @BindView(R.id.tv_user_detail_intimacy)
    TextView tvUserDetailIntimacy;
    @BindView(R.id.btn_send_message)
    Button btnSendMessage;
    @BindView(R.id.btn_delete_friend)
    Button btnDeleteFriend;
    @BindView(R.id.tv_user_detail_displayName)
    TextView tvUserDetailDisplayName;
    @BindView(R.id.ll_user_detail_displayName)
    AutoLinearLayout llUserDetailDisplayName;
    @BindView(R.id.ll_user_detail_moreInfo)
    AutoLinearLayout llUserDetailMoreInfo;
    @BindView(R.id.btn_user_detail_recommed)
    Button btnUserDetailRecommed;
    @BindView(R.id.btn_user_detail_addFriends)
    Button btnUserDetailAddFriends;
    private String userId,userName;
    private FriendInfo friendInfo;
    private FriendInfoDAOImpl friendInfoDAO;
    private boolean isChangeName = false;
    private Context mContext;
    private String mId;
    private Boolean isPhoneContact = false;
    private ContastsInfo contastsInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        mContext = this;
        mId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        friendInfoDAO = new FriendInfoDAOImpl(mContext);
        isPhoneContact=getIntent().getBooleanExtra("isPhoneContact",false);
        if (isPhoneContact) {
            contastsInfo=getIntent().getParcelableExtra("contastsInfo");
           checkPhoneConact(mId,contastsInfo.getNumber());
        } else {
            friendInfo = getIntent().getParcelableExtra("friends");
            checkPhoneConact(mId,friendInfo.getUserId());

        }
    }

    @Override
    public UserDetailPresnter initPresenter() {
        return new UserDetailPresnter();
    }


    @OnClick({R.id.ll_user_detail_back, R.id.btn_send_message, R.id.btn_delete_friend,
            R.id.ll_user_detail_displayName,R.id.ll_user_detail_moreInfo,R.id.btn_user_detail_recommed, R.id.btn_user_detail_addFriends})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_user_detail_back:
                if (isChangeName) {
                    EventBus.getDefault().postSticky(new Friend());
                }
                finish();
                break;
            case R.id.ll_user_detail_displayName:
                Intent intent = new Intent(this, SetDisplayNameActivity.class);
                intent.putExtra("friendInfo", friendInfo);
                startActivityForResult(intent, 11);
                break;
            case R.id.ll_user_detail_moreInfo:
                Intent intent1 = new Intent(this, MoreUserDetailInfoActivity.class);
                intent1.putExtra("friendId", userId);
                startActivity(intent1);
                break;
            case R.id.btn_user_detail_recommed://马上推荐
               startActivity(new Intent(this,RecommendActivity.class));
                break;
            case R.id.btn_user_detail_addFriends://添加好友
                startActivity(new Intent(this,SearchFriendActivity.class));
                break;
            case R.id.btn_send_message:   //发信息
                RongIM.getInstance().startPrivateChat(mContext, userId, userName);
                break;
            case R.id.btn_delete_friend:
                new AlertDialog.Builder(mContext)
                        .setTitle("删除好友")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteFriends();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            default:
                break;
        }
    }

    private void deleteFriends() {
        HttpUtils.postDelFriendRequest("/deleteUser", mId, userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "deleteUser-----" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<FriendInfo>>() {
                }.getType();
                Code<FriendInfo> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    friendInfoDAO.deleteOne(userId);
                    T.showShort(mContext, "删除成功");
                    finish();
                } else {
                    T.showShort(mContext, "删除失败");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {
            if (data != null) {
                String displayName = data.getStringExtra("displayName");
                tvUserDetailName.setText(displayName);
                tvUserDetailDisplayName.setText(displayName);
                isChangeName = true;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isChangeName) {
            EventBus.getDefault().postSticky(new Friend());
        }
        super.onBackPressed();
    }

    @Override
    public void checkPhoneConact(String userId, String mobile) {
        presenter.getCheckPhoneResult(userId, mobile);
    }

    @Override
    public void checkResult(UserInfo userInfo) {
        initData(userInfo);
    }

    private void initData(UserInfo userInfo) {
        userId=userInfo.getUserId();
        userName=userInfo.getNickname();
        llUserDetailDisplayName.setVisibility(View.VISIBLE);
        llUserDetailMoreInfo.setVisibility(View.VISIBLE);
        Picasso.with(this).load(HttpUtils.IMAGE_RUL+userInfo.getUserPortraitUrl()).into(ivUserDetailUserIcon);
        ivUserDetailSex.setImageResource(userInfo.getSex().equals("2")?R.drawable.mine_women:R.drawable.mine_man);
        if(TextUtils.isEmpty(userInfo.getFriendNickname())||userInfo.getFriendNickname().equals("")){
            tvUserDetailName.setText(userInfo.getNickname());
            tvUserDetailDisplayName.setText(userInfo.getNickname());
        }else{
            tvUserDetailName.setText(userInfo.getFriendNickname());
            tvUserDetailDisplayName.setText(userInfo.getFriendNickname());
        }
        tvUserDetailAge.setText(userInfo.getAge());
        tvUserDetailAccount.setText(userInfo.getNumberId());
        tvUserDetailEmail.setText(userInfo.getEmail());
        tvUserDetailBirthday.setText(userInfo.getBirthday());
        tvUserDetailAddress.setText(userInfo.getAddress());
        tvUserDetailRecommenerName.setText(userInfo.getRecommendUserId());
        tvUserDetailClaimerName.setText(userInfo.getClaimUserId());
        tvUserDetailContributionNum.setText(userInfo.getContributionScore());
        tvUserDetailCreditScore.setText(userInfo.getCreditScore());
        tvUserDetailIntimacy.setText(userInfo.getIntimacy());
        initButtonVisable(userInfo.getStatus());
    }

    private void initButtonVisable(String status) {
        switch (status) {
            case "0":
                btnUserDetailAddFriends.setVisibility(View.VISIBLE);
                break;
            case "1":
                btnSendMessage.setVisibility(View.VISIBLE);
                btnDeleteFriend.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void showError(String error) {
        T.showShort(this, error);
    }

    @Override
    public void mobileUnRegister() {
       btnUserDetailRecommed.setVisibility(View.VISIBLE);
        tvUserDetailName.setText(contastsInfo.getName());
        tvUserDetailPhone.setText(contastsInfo.getNumber());
    }

}
