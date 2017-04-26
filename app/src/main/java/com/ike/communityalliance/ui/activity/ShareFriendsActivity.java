package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.ike.mylibrary.util.CommonUtils;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.SimpleAdapter;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.bean.UnreadMessgaeBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IShareFriendsView;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.ShareFriendsPresenter;
import com.squareup.picasso.Picasso;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.base.cache.disk.DiskLruCacheHelper;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareFriendsActivity extends BaseMvpActivity<IShareFriendsView, ShareFriendsPresenter> implements IShareFriendsView, XRefreshView.XRefreshViewListener, SimpleAdapter.OnItemClickLitener {
    @BindView(R.id.rv_share_Friends)
    RecyclerView rv_share_Friends;
    @BindView(R.id.iv_shareFriend_send)
    ImageView iv_shareFriend_send;
    @BindView(R.id.ll_shareFriend_back)
    LinearLayout ll_shareFriend_back;
    @BindView(R.id.xrefreshview_shareFriends)
    XRefreshView xrefreshview_shareFriends;
    @BindView(R.id.iv_shareFriend_msg)
    ImageView ivShareFriendMsg;
    @BindView(R.id.tv_shareFriend_msg)
    TextView tvShareFriendMsg;
    @BindView(R.id.iv_shareFriend_msg_arrow)
    ImageView ivShareFriendMsgArrow;
    @BindView(R.id.ll_shareFriend_msg)
    AutoLinearLayout llShareFriendMsg;
    private SimpleAdapter adapter;
    private List<TalkTalkBean> list;
     private  ArrayList<UnreadMessgaeBean> unreadMessgaeBeanList;
    private SharedPreferences sp;
    private String userId;
    private DiskLruCacheHelper helper;
    private int firstPage = 1;
    private int curPage = 1;
    private int insertPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_friends);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        ButterKnife.bind(this);
        try {
            helper = new DiskLruCacheHelper(this);
        } catch (IOException e) {
            showError(e.toString());
        }
        if (!CommonUtils.isNetConnect(this)) {
            showError("网络不可用");
        }
        presenter.getLocalData(helper);
        getTalkTalkData(userId, firstPage);
        getUnreadMessageData(userId);
        init();
    }

    @Override
    public ShareFriendsPresenter initPresenter() {
        return new ShareFriendsPresenter();
    }

    private void init() {
        list = new ArrayList<>();
        adapter = new SimpleAdapter(list, this);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_share_Friends.setLayoutManager(layoutManager);
        rv_share_Friends.setAdapter(adapter);
        rv_share_Friends.setItemAnimator(new DefaultItemAnimator());
        rv_share_Friends.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        initXrefreshView();
    }

    private void initXrefreshView() {
        xrefreshview_shareFriends.setPinnedTime(1000);
        xrefreshview_shareFriends.setMoveForHorizontal(true);
        xrefreshview_shareFriends.setPullLoadEnable(true);
        xrefreshview_shareFriends.setAutoLoadMore(false);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xrefreshview_shareFriends.enableReleaseToLoadMore(true);
        xrefreshview_shareFriends.enableRecyclerViewPullUp(true);
        xrefreshview_shareFriends.enablePullUpWhenLoadCompleted(true);
        xrefreshview_shareFriends.setXRefreshViewListener(this);
    }

    @OnClick({R.id.iv_shareFriend_send, R.id.ll_shareFriend_back,R.id.ll_shareFriend_msg})
    public void shareFriendsViewOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shareFriend_back:
                finish();
                break;
            case R.id.iv_shareFriend_send:
                Intent intent = new Intent(this, SendShareFriendsActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.ll_shareFriend_msg:
             //未读消息列表页面
                Intent intent1 = new Intent(this, UnreadMessageListActivity.class);
                intent1.putParcelableArrayListExtra("UnreadMessageList", unreadMessgaeBeanList);
                startActivityForResult(intent1,100);
                break;
        }
    }


    @Override
    public void getUnreadMessageData(String userId) {
            presenter.getUnreadMessageData(userId);
    }

    @Override
    public void setUnreadMessageData(List<UnreadMessgaeBean> data) {
        if(data.size()>0){
            unreadMessgaeBeanList= (ArrayList<UnreadMessgaeBean>) data;
            llShareFriendMsg.setVisibility(View.VISIBLE);
            tvShareFriendMsg.setText(data.size()+"条新消息");
            Picasso.with(this).load(HttpUtils.IMAGE_RUL+data.get(0).getUserPortraitUrl()).into(ivShareFriendMsg);
        }else{
            llShareFriendMsg.setVisibility(View.GONE);
        }
    }

    @Override
    public void getTalkTalkData(String userId, int page) {
        presenter.getTalkTalkData(userId, page);
    }

    @Override
    public void setTalkTalkData(List<TalkTalkBean> data) {
        xrefreshview_shareFriends.stopRefresh();
        adapter.setData(data);
        //本地缓存
        helper.put("talkTalkBean", (Serializable) data);
        insertPosition = data.size();
    }

    @Override
    public void showError(String errorString) {
        xrefreshview_shareFriends.stopRefresh();
        xrefreshview_shareFriends.stopLoadMore();
        T.showShort(this, errorString);
    }

    @Override
    public void setLocalData(List<TalkTalkBean> data) {
        adapter.setData(data);
    }

    @Override
    public void setLoadMoreData(List<TalkTalkBean> data) {
        xrefreshview_shareFriends.stopLoadMore();
        adapter.addMoreData(data);
        rv_share_Friends.scrollToPosition(insertPosition - 1);
        insertPosition = insertPosition + data.size();
    }


    @Override
    public void onRefresh() {
        getTalkTalkData(userId, firstPage);
        curPage = 1;
    }

    @Override
    public void onRefresh(boolean isPullDown) {

    }

    @Override
    public void onLoadMore(boolean isSilence) {
        curPage = curPage + 1;
        presenter.getTalkTalkData(userId, curPage);
    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (data != null) {
                TalkTalkBean talkTalkBean = (TalkTalkBean) data.getSerializableExtra("talkTalkBean");
                adapter.insert(talkTalkBean, 0);
                rv_share_Friends.scrollToPosition(0);
            }
        }else if(requestCode==100&&resultCode==RESULT_OK){
                  getUnreadMessageData(userId);
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ShareFriendContentActivity.class);
        TalkTalkBean talkTalk = adapter.getList().get(position);
        intent.putExtra("shareFriendContent", talkTalk);
        onRefresh();
        startActivity(intent);
    }

}
