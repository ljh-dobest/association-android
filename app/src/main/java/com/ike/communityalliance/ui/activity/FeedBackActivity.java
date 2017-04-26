package com.ike.communityalliance.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends AppCompatActivity {
    @BindView(R.id.ll_feedback_back)
    AutoLinearLayout llFeedbackBack;
    @BindView(R.id.tv_feedback_comfirm)
    TextView tvFeedbackComfirm;
    @BindView(R.id.et_feedback_content)
    EditText etFeedbackContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_feedback_back, R.id.tv_feedback_comfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_feedback_back:
                finish();
                break;
            case R.id.tv_feedback_comfirm:
                String content=tvFeedbackComfirm.getText().toString();
                if(content.equals("")){
                    T.showShort(this,"反馈内容不为空！");
                    return;
                }
                break;
        }
    }
}
