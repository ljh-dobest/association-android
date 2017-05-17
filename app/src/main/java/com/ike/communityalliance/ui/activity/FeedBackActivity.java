package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
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
import okhttp3.Call;

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.ll_feedback_back)
    AutoLinearLayout llFeedbackBack;
    @BindView(R.id.tv_feedback_comfirm)
    TextView tvFeedbackComfirm;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
    }

    @OnClick({R.id.ll_feedback_back, R.id.tv_feedback_comfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_feedback_back:
                finish();
                break;
            case R.id.tv_feedback_comfirm:
                String content=etFeedbackContent.getText().toString();
                if(content.equals("")){
                    T.showShort(this,"反馈内容不为空！");
                    return;
                }
                LoadDialog.show(this);
                feedBack(content);
                break;
        }
    }

    private void feedBack(String content) {
        HttpUtils.postFeedBack("/platformFeedback", userId, content, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(FeedBackActivity.this,e.toString());
                LoadDialog.dismiss(FeedBackActivity.this);
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type=new TypeToken<Code<Object>>(){}.getType();
                Code<Object> code = gson.fromJson(response,type);
                if (code.getCode()==200){
                    LoadDialog.dismiss(FeedBackActivity.this);
                    etFeedbackContent.setText("");
                    T.showShort(FeedBackActivity.this,"反馈成功,感谢您的建议！");
                }else{
                    T.showShort(FeedBackActivity.this,"反馈失败");
                    LoadDialog.dismiss(FeedBackActivity.this);
                }
            }
        });
    }
}
