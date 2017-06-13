package com.min.helpcenter.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.R;
import com.min.helpcenter.base.view.BaseActivity;
import com.min.helpcenter.bean.BaseBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.utils.T;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class EvaluateActivity extends BaseActivity {
@BindView(R.id.tv_evaluate_back)
    TextView tv_evaluate_back;
@BindView(R.id.tv_evaluate_release)
    TextView tv_evaluate_release;
@BindView(R.id.et_evaluate_content)
EditText et_evaluate_content;
@BindView(R.id.ll_evaluate_insert)
LinearLayout ll_evaluate_insert;
    private String seekId;
    private  String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        userId=getIntent().getStringExtra("userId");
        seekId=getIntent().getStringExtra("seekId");
    }

    @OnClick({R.id.tv_evaluate_back,R.id.tv_evaluate_release})
    public void onEvaluateViewOnClick(View view){
        switch (view.getId()) {
            case R.id.tv_evaluate_back:
                finish();
                break;
            case R.id.tv_evaluate_release:
                String content=et_evaluate_content.getText().toString();
                if(content.equals("")){
                    T.showShort(this,"内容不能为空");
                    return;
                }
                HttpUtils.answerSeekHelp("/answerSeekHelp", seekId, userId, content, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        T.showShort(EvaluateActivity.this,e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        Type type = new TypeToken<Code<BaseBean>>() {
                        }.getType();
                        Code<BaseBean> code = gson.fromJson(response,type);
                        switch (code.getCode()) {
                            case 200:
                                T.showShort(EvaluateActivity.this,"发布成功");
                                setResult(RESULT_OK);
                                finish();
                                break;
                            case 100:
                                T.showShort(EvaluateActivity.this,"用户已回答");
                                break;
                            case 0:
                                T.showShort(EvaluateActivity.this,"发布失败");
                                break;
                        }
                    }
                });
                break;
        }
    }
}
