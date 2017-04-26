package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.GroupVoteRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupVote;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.ItemDivider;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imlib.model.Conversation;
import okhttp3.Call;

/**
 * 投票活动
 */
public class GroupVoteActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.rv_group_vote)
    RecyclerView rvGroupVote;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;

    private GroupVoteRvAdapter adapter;
    private List<GroupVote> list = new ArrayList<>();

    private String groupId;
    private String voteId;
    private static final int REFRESH_COMPLETE=0;
    private int status;
    private Conversation.ConversationType conversationType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_vote);
        ButterKnife.bind(this);
        initView();
        initListView();

    }

    private void initView() {
        tvTitle.setText("投票活动");
        tvTitleRight.setVisibility(View.VISIBLE);
        tvTitleRight.setText("添加");
        Intent intent=getIntent();
        groupId = intent.getStringExtra("group_id");
        conversationType=Conversation.ConversationType.setValue(intent.getIntExtra("conversationType",0));
        swipeRefresh.setOnRefreshListener(this);

    }

    private void initListView() {
        String userId = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        HttpUtils.postVoteList("/voteList",userId, groupId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/vote_list------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<GroupVote>>>() {
                }.getType();
                Code<List<GroupVote>> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                           list=code.getData();
                    initAdapter();
                } else {
                    T.showShort(mContext, "没有活动");
                }
            }
        });
    }


    private void initAdapter() {
        adapter=new GroupVoteRvAdapter(this);
        adapter.setGroupId(groupId);
        adapter.setmDatas(list);
        rvGroupVote.setAdapter(adapter);
        LinearLayoutManager lm = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvGroupVote.setLayoutManager(lm);
        rvGroupVote.addItemDecoration(new ItemDivider(mContext, ItemDivider.VERTICAL_LIST));

        //点击事件
        adapter.setOnItemClickLitener(new GroupVoteRvAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, VoteDetailActivity.class);
                intent.putExtra("group_id", groupId);
                intent.putExtra("vote_id", list.get(position).getVoteId());
                intent.putExtra("timeStatus",list.get(position).getTimeStatus());
                startActivityForResult(intent,0);
            }
        });
    }

    @OnClick({R.id.iv_title_back, R.id.tv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.tv_title_right:
                Intent intent = new Intent(mContext, AddVoteActivity.class);
                intent.putExtra("group_id", groupId);
                intent.putExtra("conversationType", Conversation.ConversationType.GROUP.getValue());//群组类型消息
                startActivityForResult(intent, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            list.clear();
            initListView();
        }
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 0);
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE:
                    list.clear();
                    initListView();
                    swipeRefresh.setRefreshing(false);
            }
        }
    };
}
