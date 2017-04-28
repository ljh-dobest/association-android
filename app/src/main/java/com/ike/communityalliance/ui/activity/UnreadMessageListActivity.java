









package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.UnreadMessageRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.UnreadMessgaeBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.listener.OnFinishCommentListener;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class UnreadMessageListActivity extends BaseActivity implements UnreadMessageRvAdapter.OnItemClickLitener, OnFinishCommentListener {

    @BindView(R.id.ll_unread_msg_back)
    AutoLinearLayout llUnreadMsgBack;
    @BindView(R.id.tv_unread_msg_clear)
    TextView tvUnreadMsgClear;
    @BindView(R.id.rv_unread_msg)
    RecyclerView rvUnreadMsg;
    @BindView(R.id.activity_unread_message_list)
    LinearLayout activity_unread_message_list;
   private List<UnreadMessgaeBean> list;
    private UnreadMessageRvAdapter adapter;
    private CommentPopuWindow commentPopupWindow;
    private SharedPreferences sp;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unread_message_list);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        commentPopupWindow=new CommentPopuWindow(this,activity_unread_message_list,this);
     list=getIntent().getParcelableArrayListExtra("UnreadMessageList");
        initView();
        clearUnreadList(userId);
    }

    private void initView() {
        adapter=new UnreadMessageRvAdapter(this);
        if(list!=null){
            adapter.setmDatas(list);
        }
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvUnreadMsg.setLayoutManager(layoutManager);
        rvUnreadMsg.setAdapter(adapter);
        rvUnreadMsg.setItemAnimator(new DefaultItemAnimator());
        rvUnreadMsg.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }


    @OnClick({R.id.ll_unread_msg_back, R.id.tv_unread_msg_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_unread_msg_back:
                setResult(RESULT_OK,new Intent());
                finish();
                break;
            case R.id.tv_unread_msg_clear:
                adapter.clearData();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
          commentPopupWindow.setCommentArticle(false);
        commentPopupWindow.setUseId(userId);
        commentPopupWindow.setCommentId(list.get(position).getId());
        commentPopupWindow.setArticleId(list.get(position).getArticleId());
        commentPopupWindow.showPopupWindow();
    }

    @Override
    public void finishComment() {
    }

    public void clearUnreadList(String userId){
        HttpUtils.clearUnreadMsg("/clearMsg", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(UnreadMessageListActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
            }
        });
    }
}
