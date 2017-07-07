package com.min.threeminutestoteach.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.adpter.MineMessageRVAdapter;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.bean.MessageBean;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MessageActivity extends AppCompatActivity {

    @BindView(R.id.ll_msg_back)
    LinearLayout llMsgBack;
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.tv_msg_null)
    TextView tvMsgNull;
    private String userId;
    private MineMessageRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        userId = getIntent().getStringExtra("userId");
        initView();
        getMessageData();
    }

    private void initView() {
        adapter = new MineMessageRVAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvMsg.setLayoutManager(layoutManager);
        rvMsg.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        rvMsg.setAdapter(adapter);

    }

    private void getMessageData() {
        HttpUtils.getMessageData("/allNews", userId, "9", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(MessageActivity.this, e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<MessageBean>>>() {
                }.getType();
                Code<List<MessageBean>> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    if(code.getData().size()>0) {
                        adapter.setmDatas(code.getData());
                        tvMsgNull.setVisibility(View.GONE);
                    }else {
                        tvMsgNull.setVisibility(View.VISIBLE);
                    }
                } else {

                }
            }
        });
    }

    @OnClick(R.id.ll_msg_back)
    public void onViewClicked() {
        finish();
    }


}
