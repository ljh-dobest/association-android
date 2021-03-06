package com.issp.association.crowdfunding.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.issp.association.crowdfunding.R;
import com.issp.association.crowdfunding.base.view.BaseMvpActivity;
import com.issp.association.crowdfunding.interfaces.IProductIDCardView;
import com.issp.association.crowdfunding.presenters.ProductIdCardPresenter;
import com.issp.association.crowdfunding.utils.AMUtils;
import com.issp.association.crowdfunding.utils.DisplayUtils;
import com.issp.association.crowdfunding.utils.IDCardUtil;
import com.issp.association.crowdfunding.utils.T;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 验证身份证
 * Created by T-BayMax on 2017/4/22.
 */

public class ProductIDCardActivity extends BaseMvpActivity<IProductIDCardView, ProductIdCardPresenter> implements IProductIDCardView {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_idcard)
    EditText etIdcard;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_idcard);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        userId=intent.getStringExtra("userId");
        ltMainTitleLeft.setText("返回");
        ltMainTitle.setText("身份验证");
        ltMainTitleRight.setCompoundDrawables(null, null, null, null);

    }
    public void showComfirmDialog() {

        final AlertDialog ComfirmDialog = new AlertDialog.Builder(this).create();
        ComfirmDialog.show();
        Window window = ComfirmDialog.getWindow();
        WindowManager.LayoutParams lp = ComfirmDialog.getWindow().getAttributes();
        lp.width = DisplayUtils.dp2px(ProductIDCardActivity.this, 300);//定义宽度
        lp.height = DisplayUtils.dp2px(ProductIDCardActivity.this, 200);//定义高度
        ComfirmDialog.getWindow().setAttributes(lp);
        window.setContentView(R.layout.comfirm_dialog_layout);
        TextView tv_reminder = (TextView) window.findViewById(R.id.tv_reminder);
        tv_reminder.setText("信息提交成功\n火速审核");
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        ImageView iv_comfirm_dialog_cancel = (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        iv_comfirm_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComfirmDialog.dismiss();
            }
        });
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {

            T.showLong(ProductIDCardActivity.this, errorString);

    }

    @Override
    public void productIDcardView(String data) {
        T.showLong(ProductIDCardActivity.this, data);
        Intent intent=new Intent(ProductIDCardActivity.this,AddCrowdFundingActivity.class);
        startActivity(intent);
    }

    @Override
    public void checkIdCardView(String data) {

    }

    @Override
    public ProductIdCardPresenter initPresenter() {
        return new ProductIdCardPresenter();
    }

    @OnClick({R.id.lt_main_title_left, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                ProductIDCardActivity.this.finish();
                break;
            case R.id.tv_submit:
                showComfirmDialog();
                break;
        }
    }

    private void submit() {

        String userName = etUserName.getText().toString().trim();
        String idCard = etIdcard.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String info = IDCardUtil.IDCardValidate(idCard);
        if (userName.equals("")){
            T.showLong(ProductIDCardActivity.this,"请输入姓名");
        }
        if (!info.equals("YES")) {
            T.showLong(ProductIDCardActivity.this, "身份证输出不正确");
            return;
        }
        if (!AMUtils.isMobile(mobile)) {
            T.showLong(ProductIDCardActivity.this, "手机号码不正确");
            return;
        }
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("userName", userName);
        formData.put("idcard", idCard);
        formData.put("mobile", mobile);
        presenter.addProductIdCard(formData);

    }
}
