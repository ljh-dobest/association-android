package com.ike.sq.commonwealactives.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ike.sq.commonwealactives.R;
import com.ike.sq.commonwealactives.adapter.BenefitRegisteredAdapter;
import com.ike.sq.commonwealactives.base.view.BaseMvpActivity;
import com.ike.sq.commonwealactives.bean.BenefitBean;
import com.ike.sq.commonwealactives.interfaces.IBenefitParticularsView;
import com.ike.sq.commonwealactives.network.HttpUtils;
import com.ike.sq.commonwealactives.presenters.BenefitParticularsPresenter;
import com.ike.sq.commonwealactives.utils.T;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 平台活动详情
 * Created by T-BayMax on 2017/4/8.
 */

public class BenefitParticularsActivity extends BaseMvpActivity<IBenefitParticularsView, BenefitParticularsPresenter> implements IBenefitParticularsView {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.tv_add_register)
    TextView tvAddRegister;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.iv_actives_image)
    ImageView ivActivesImage;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_join_users_number)
    TextView tvJoinUsersNumber;
    @BindView(R.id.tv_cost_money)
    TextView tvCostMoney;
    @BindView(R.id.iv_user_portrait_url)
    ImageView ivUserPortraitUrl;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_initiator)
    TextView tvInitiator;
    @BindView(R.id.tv_join_users)
    TextView tvJoinUsers;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.gv_user)
    GridView gvUser;
    @BindView(R.id.wv_content)
    WebView wvContent;
    private Dialog mDialog;
    private BenefitBean bean;
    private String userId;
    private String activesId;

    BenefitRegisteredAdapter adapter;

    int joinUsersNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weal_actives_details);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        activesId = intent.getStringExtra("activesId");
        // bean = (PlatformBean) getIntent().getSerializableExtra("bean");
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("activesId", activesId);
        presenter.ParticularsPresenter(formData);
    }

    private void TakePhotoPopWin() {
        mDialog = new Dialog(BenefitParticularsActivity.this, R.style.dialog);
        mDialog.setContentView(R.layout.popwindow_add_register);// 设置View
        mDialog.setCanceledOnTouchOutside(false); // 设置点击外部不消失
        ViewGroup.LayoutParams layoutParams = getWindow().getAttributes();
        Window window = mDialog.getWindow();
        ViewGroup.LayoutParams attributes = window.getAttributes();
        layoutParams.height = attributes.height;// 获取Dialog View的高度，设置高度,View 的高度
        layoutParams.width = layoutParams.width;// 设置宽度，是屏幕的宽度
        int gravity = Gravity.BOTTOM;// 底部弹出
        window.setGravity(gravity);
        window.setLayout(layoutParams.width, layoutParams.height);
        window.setWindowAnimations(R.style.take_photo_anim);//设置弹出动画
        mDialog.show();
        final EditText etUserName = (EditText) mDialog.findViewById(R.id.et_userName);
        EditText etMobile = (EditText) mDialog.findViewById(R.id.et_mobile);
        EditText etWechat = (EditText) mDialog.findViewById(R.id.et_wechat);
        final EditText etCompany = (EditText) mDialog.findViewById(R.id.et_company);
        mDialog.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> formData = new HashMap<String, String>(0);
                formData.put("userId", userId);
                formData.put("activesId", activesId);
                formData.put("userName", etUserName.getText().toString().trim());
                formData.put("mobile", etUserName.getText().toString().trim());
                formData.put("wechat", etUserName.getText().toString().trim());
                formData.put("company", etCompany.getText().toString().trim());
                presenter.platformActivesJoin(formData);
            }
        });
        mDialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
    }


    @Override
    public BenefitParticularsPresenter initPresenter() {

        return new BenefitParticularsPresenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {
        T.showLong(BenefitParticularsActivity.this, errorString);
    }

    @Override
    public void setBenefitParticularsData(BenefitBean data) {
        tvTitle.setText(data.getTitle());
        tvAddress.setText("地点"+data.getAddress());
        tvStartTime.setText("活动时间："+data.getStartTime() + "至" + data.getEndTime());
        tvAddress.setText(data.getAddress());
        if (data.getCostMoney()==0) {
            tvCostMoney.setText("报名费：免费");
        }else {
            tvCostMoney.setText("报名费：￥" + data.getCostMoney() + "/人");
        }
        wvContent.loadData(data.getContent(), "text/html; charset=UTF-8", null);
        if (data.getJoinStatus() == 0) {
            tvAddRegister.setBackgroundResource(R.color.color_10);
            tvAddRegister.setText("立即报名");
        } else {
            tvAddRegister.setBackgroundResource(R.color.aam_item_border);
            tvAddRegister.setText("已报名");
        }
        joinUsersNumber = data.getJoinUsersNumber();
        tvJoinUsersNumber.setText("已报名: " + data.getJoinUsersNumber() + "/无限制");
        tvJoinUsers.setText("已报名 （" + data.getJoinUsersNumber() + "）");
        if (null != data.getActivesImage()) {
            Picasso.with(BenefitParticularsActivity.this).load(HttpUtils.IMAGE_RUL + data.getActivesImage())
                    .into(ivActivesImage);
        }
        if (data.getStatus() == 1) {
            ivLike.setImageResource(R.mipmap.img_comments_have_thumb_up_btn);
        } else {
            ivLike.setImageResource(R.mipmap.img_like_btn_no);
        }
        adapter = new BenefitRegisteredAdapter(data.getJoinUsers(), BenefitParticularsActivity.this);
        gvUser.setAdapter(adapter);
        data.setId(bean.getId());
        bean = data;
    }

    @Override
    public void BenefitActivesJoinSucceed(String data) {
        T.showLong(BenefitParticularsActivity.this, data);
        mDialog.dismiss();
    }

    @Override
    public void userPraise(String data) {
        if (bean.getStatus() == 0) {
            ivLike.setImageResource(R.mipmap.img_comments_have_thumb_up_btn);
            T.showLong(BenefitParticularsActivity.this, "点赞成功！");
            bean.setStatus(1);
        } else {
            ivLike.setImageResource(R.mipmap.img_like_btn_no);
            T.showLong(BenefitParticularsActivity.this, "取消点赞！");
            bean.setStatus(0);
        }
    }


    @OnClick({R.id.lt_main_title_left, R.id.tv_more, R.id.iv_share, R.id.iv_like,R.id.iv_comment, R.id.tv_add_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                BenefitParticularsActivity.this.finish();
                break;
            case R.id.tv_more:
                Intent intent = new Intent(BenefitParticularsActivity.this, BenefitRegisteredActivity.class);
                bean.setJoinUsersNumber(joinUsersNumber);
                intent.putExtra("userId",userId);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
            case R.id.iv_share:
                break;
            case R.id.iv_like:
                likeClick();
                break;
            case R.id.iv_comment:
                Intent feedIntent = new Intent(BenefitParticularsActivity.this, FeedForCommentActivity.class);
                feedIntent.putExtra("bean", bean);
                feedIntent.putExtra("userId", userId);
                startActivity(feedIntent);
                break;
            case R.id.tv_add_register:
                if (joinUsersNumber == 0) {
                    TakePhotoPopWin();
                } else {
                    T.showLong(BenefitParticularsActivity.this, "亲，你已经报过名了！");
                }
                break;
        }
    }


    private void likeClick() {
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("articleId", activesId);
        formData.put("type", "7");
        formData.put("status", bean.getStatus() == 0 ? "1" : "0");
        presenter.addUserPraise(formData);
    }
}
