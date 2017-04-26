package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.CommonUtils;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.NewFriendListAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.AllAddFriends;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.network.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 新的好友列表
 */
public class NewFriendListActivity extends BaseActivity implements NewFriendListAdapter.OnItemButtonClick {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_right)
    ImageView ivTitleRight;
    @BindView(R.id.isData)
    TextView isData;
    @BindView(R.id.listView)
    ListView mListView;
//    @BindView(R.id.rv_new_friends)
//    RecyclerView rv_new_friends;

    private String userid;
    private String friendId;
    private List<AllAddFriends> list = new ArrayList<>();
    private AllAddFriends allAddFriends;

    private NewFriendListAdapter adapter;
//    private BaseRecyclerAdapter<AllAddFriends> adapter;

    private FriendInfoDAOImpl friendInfoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend_list);
        ButterKnife.bind(this);
        friendInfoDAO=new FriendInfoDAOImpl(mContext);
        initView();
        if (!CommonUtils.isNetConnect(mContext)) {
            T.showShort(mContext, R.string.no_network);
            return;
        }
        LoadDialog.show(mContext);
        userid = getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, "");
        initData();
        adapter = new NewFriendListAdapter(mContext);
        mListView.setAdapter(adapter);
    }

    private void initData() {
        HttpUtils.postAddFriendsRequest("/allAddfriendRequest", userid, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/allAddfriendRequest--------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                L.e("addfridedall","申请所有人列表"+response);
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<AllAddFriends>>>() {
                }.getType();
                Code<List<AllAddFriends>> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    list = code.getData();
                    if (list.size() == 0) {
                        isData.setVisibility(View.VISIBLE);
                        LoadDialog.dismiss(mContext);
                        return;
                    }
                    Collections.sort(list, new Comparator<AllAddFriends>() {
                        @Override
                        public int compare(AllAddFriends allAddFriends, AllAddFriends t1) {
                            Date date1 = stringToDate(allAddFriends);
                            Date date2 = stringToDate(t1);
                            if (date1.before(date2)) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    adapter.removeAll();
                    adapter.addData(list);
                    adapter.notifyDataSetChanged();
                    adapter.setOnItemButtonClick(NewFriendListActivity.this);
                    LoadDialog.dismiss(mContext);
//                    initAdapter(list);
                } else {
                    LoadDialog.dismiss(mContext);
                    isData.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRequest(final String friendId, final int status) {
        HttpUtils.postEnterFriendRequest("/confirmFriend", userid, friendId, status, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/confirmFriend------" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<FriendInfo>>() {}.getType();
                Code<FriendInfo> code =gson.fromJson(response,type);
                int codeStatus=code.getCode();
                switch (codeStatus){
                    case 1000:
                        T.showShort(mContext, "拒绝成功");
                        LoadDialog.dismiss(mContext);
                        finish();
                        break;
                    case 200:
                        FriendInfo friends=code.getData();
                            String friendId=friends.getUserId();
                            String friendName=friends.getNickname();
                            String friendPortraitUri=HttpUtils.IMAGE_RUL+friends.getUserPortraitUrl();
                            String friendDisplayName=friends.getDisplayName();
                            String friendPhone=friends.getMobile();
                            String friendEmail=friends.getEmail();
                            FriendInfo friendInfo=friends;
                            friendInfo.setMyId(userid);
                            friendInfo.setUserId(friendId);
                            friendInfo.setNickname(friendName);
                            friendInfo.setUserId(friendPortraitUri);
                            friendInfo.setDisplayName(friendDisplayName);
                            friendInfo.setMobile(friendPhone);
                            friendInfo.setEmail(friendEmail);
                            friendInfoDAO.save(friendInfo);
                        T.showShort(mContext, "你们现在是好友了");
                        LoadDialog.dismiss(mContext);
                        finish();
                        break;
                    case 2000:
                        T.showShort(mContext, "忽略成功");
                        LoadDialog.dismiss(mContext);
                        finish();
                        break;
                    default:
                        T.showShort(mContext, "请求失败");
                        LoadDialog.dismiss(mContext);
                        break;

                }
            }
        });
    }

    private Date stringToDate(AllAddFriends allAddFriends) {
        String updatedAt = allAddFriends.getAddtime();
        String updatedAtDateStr = updatedAt.substring(0, 10) + " " + updatedAt.substring(11, 16);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date updateAtDate = null;
        try {
            updateAtDate = simpleDateFormat.parse(updatedAtDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateAtDate;
    }


    private void initView() {
        tvTitle.setText("新的朋友");
        ivTitleRight.setVisibility(View.VISIBLE);
        ivTitleRight.setImageResource(R.mipmap.de_address_new_friend);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.iv_title_right:
                startActivity(new Intent(mContext, SearchFriendActivity.class));
                break;
        }
    }

    @Override
    public boolean onButtonRefuseClick(int position, View view, int status) {
        LoadDialog.show(mContext);
        friendId = list.get(position).getUserId();
        initRequest(friendId, 0);
        return false;
    }

    @Override
    public boolean onButtonAgreeClick(int position, View view, int status) {
        LoadDialog.show(mContext);
        friendId = list.get(position).getUserId();
        initRequest(friendId, 1);
        return false;
    }

    @Override
    public boolean onButtonIgnoreClick(int position, View view, int status) {
        LoadDialog.show(mContext);
        friendId = list.get(position).getUserId();
        initRequest(friendId, 2);
        return false;
    }
}
