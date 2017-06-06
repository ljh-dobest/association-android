package com.ike.communityalliance.ui.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.App;
import com.ike.communityalliance.AppContext;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.FriendListAdapter;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.Friend;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.FriendInfoDAOImpl;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.server.broadcast.BroadcastManager;
import com.ike.communityalliance.ui.activity.GroupListActivity;
import com.ike.communityalliance.ui.activity.PhoneContactActivity;
import com.ike.communityalliance.ui.activity.SearchFriendActivity;
import com.ike.communityalliance.ui.activity.UserDetailActivity;
import com.ike.communityalliance.wedget.CharacterParser;
import com.ike.communityalliance.wedget.Generate;
import com.ike.communityalliance.wedget.PinyinComparator;
import com.ike.communityalliance.wedget.SideBar;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.T;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imageloader.core.ImageLoader;
import okhttp3.Call;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by just on 2017/3/1.
 */
@RuntimePermissions
public class ContactFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.lv_friends)
    ListView mListView;
    @BindView(R.id.tv_group_dialog)
    TextView tvGroupDialog;  //中部展示的字母提示
    @BindView(R.id.sb)
    SideBar sb;
    @BindView(R.id.tv_show_no_friend)
    TextView tvShowNoFriend;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R.id.ll_friend_activity_back)
    LinearLayout ll_friend_activity_back;
    @BindView(R.id.iv_add_friends)
    ImageView ivAddFriends;
    private PinyinComparator mPinyinComparator;
    private List<FriendInfo> mSourceFriendList;
    private List<FriendInfo> mFriendList = new ArrayList<>(0);
    private List<FriendInfo> mFilteredFriendList;
    /**
     * 好友列表的 mFriendListAdapter
     */
    private FriendListAdapter mFriendListAdapter;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser mCharacterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */

    private String mId;
    private String mCacheName;
    private String header;
    private SharedPreferences sp;

    private View mHeadView;

    private TextView tvUnread, tvMe;
    private RelativeLayout rl_phoneContast, rlGroup, rlMeItem;
    private SelectableRoundedImageView sivMe;

    private static final int CLICK_CONTACT_FRAGMENT_FRIEND = 2;
    private static final int REFRESH_COMPLETE = 0;

    private FriendInfoDAOImpl friendInfoDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);
        friendInfoDAO = new FriendInfoDAOImpl(getActivity());
        mSourceFriendList = new ArrayList<>();
        mFriendList = new ArrayList<>();
        mFilteredFriendList = new ArrayList<>();
        initView();
        initText();
        friendInfoDAO.delete(mId);
        initData();
        refreshUIListener();
        return view;
    }

    private void initText() {
        sb.setTextView(tvGroupDialog);
        sp = getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        mId = sp.getString(Const.LOGIN_ID, "");
        mCacheName = sp.getString(Const.LOGIN_NICKNAME, "");
        header = sp.getString(Const.LOGIN_PORTRAIT, "");

        tvMe.setText(mCacheName);
        if (!TextUtils.isEmpty(header)) {
            Picasso.with(getContext()).load(header).into(sivMe);
        } else {
            sivMe.setImageResource(R.mipmap.default_portrait);
        }
    }


    private void initView() {
        //刷新
        mSwipeRefresh.setOnRefreshListener(this);
        ivAddFriends.setOnClickListener(this);
        //自己信息
        LayoutInflater inflater = LayoutInflater.from(getContext());
        mHeadView = inflater.inflate(R.layout.item_friend_list_header, null);
        rlGroup = (RelativeLayout) mHeadView.findViewById(R.id.rl_group);
        rl_phoneContast = (RelativeLayout) mHeadView.findViewById(R.id.rl_phoneContast);
        rlMeItem = (RelativeLayout) mHeadView.findViewById(R.id.rl_me_item);
        sivMe = (SelectableRoundedImageView) mHeadView.findViewById(R.id.siv_me);
        tvMe = (TextView) mHeadView.findViewById(R.id.tv_me);
        mListView.addHeaderView(mHeadView);
        rl_phoneContast.setOnClickListener(this);
        rlMeItem.setOnClickListener(this);
        rlGroup.setOnClickListener(this);
        ll_friend_activity_back.setOnClickListener(this);
        //设置右侧触摸监听
        sb.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = mFriendListAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }

            }
        });
    }

    private void initData() {
        /**
         * 好友列表
         */
        HttpUtils.postRequest("/friends", mId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(getContext(), "friends-----" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<FriendInfo>>>() {
                }.getType();
                Code<List<FriendInfo>> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    List<FriendInfo> list = code.getData();
                    for (FriendInfo friend : list) {
                        String userId = friend.getUserId();
                        String name = friend.getNickname();
                        String userPortraitUrl = HttpUtils.IMAGE_RUL + friend.getUserPortraitUrl();
                        String displayName = friend.getDisplayName();
                        String mobile = friend.getMobile();
                        String email = friend.getEmail();
                        FriendInfo friendInfo = new FriendInfo();
                        friendInfo.setMyId(mId);
                        friendInfo.setUserId(userId);
                        friendInfo.setNickname(name);
                        friendInfo.setUserPortraitUrl(userPortraitUrl);
                        friendInfo.setDisplayName(displayName);
                        friendInfo.setMobile(mobile);
                        friendInfo.setEmail(email);
                        mSourceFriendList.add(friendInfo);
                        //存进Sqlite
                        friendInfoDAO.save(friendInfo);
                        L.e("---------===", "插入成功");
                    }

                    //实例化汉字转拼音类
                    mCharacterParser = CharacterParser
                            .getInstance();
                    mPinyinComparator = PinyinComparator.getInstance();
                    initList();

                } else {
                    mFriendListAdapter = new FriendListAdapter(getContext(), mFriendList);
                    mListView.setAdapter(mFriendListAdapter);
                    tvShowNoFriend.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData2();
    }

    //有更改备注名
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEventChangeNameMessage(Friend event) {
        onRefresh();
    }

    private void initData2() {
        mSourceFriendList.clear();
        mFriendList.clear();
        mSourceFriendList = friendInfoDAO.findAll(mId);
//        mFriendListAdapter.notifyDataSetChanged();
//        if(mSourceFriendList.size()>0){
        //实例化汉字转拼音类
        mCharacterParser = CharacterParser
                .getInstance();
        mPinyinComparator = PinyinComparator.getInstance();
        initList();

    }

    private void initList() {
        if (mSourceFriendList != null && mSourceFriendList.size() > 0) {
            mFriendList = labelSourceFriendList(mSourceFriendList); //过滤数据为有字母的字段  现在有字母 别的数据没有
            tvShowNoFriend.setVisibility(View.GONE);
        } else {
            tvShowNoFriend.setVisibility(View.VISIBLE);
        }
        //还原除了带字母字段的其他数据
        for (int i = 0; i < mSourceFriendList.size(); i++) {
            mFriendList.get(i).setNickname(mSourceFriendList.get(i).getNickname());
            mFriendList.get(i).setUserId(mSourceFriendList.get(i).getUserId());
            mFriendList.get(i).setUserPortraitUrl(mSourceFriendList.get(i).getUserPortraitUrl());
            mFriendList.get(i).setDisplayName(mSourceFriendList.get(i).getDisplayName());
        }
        // 根据a-z进行排序源数据
        Collections.sort(mFriendList, mPinyinComparator);

        mFriendListAdapter = new FriendListAdapter(getContext(), mFriendList);
        mListView.setAdapter(mFriendListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListView.getHeaderViewsCount() > 0) {
                    startFriendDetailsPage(mFriendList.get(position - 1));
                } else {
                    startFriendDetailsPage(mFilteredFriendList.get(position));
                }
            }
        });

    }


    /**
     * 为ListView填充数据
     */
    private List<FriendInfo> labelSourceFriendList(List<FriendInfo> list) {
        List<FriendInfo> mFriendInfoList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            FriendInfo friendInfoModel = new FriendInfo();
            friendInfoModel.setNickname(list.get(i).getNickname());
            //汉字转换成拼音
            String pinyin = mCharacterParser.getSpelling(list.get(i).getNickname());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friendInfoModel.setLetters(sortString.toUpperCase());
            } else {
                friendInfoModel.setLetters("#");
            }

            mFriendInfoList.add(friendInfoModel);
        }
        return mFriendInfoList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr 需要过滤的 String
     */
    private void filterData(String filterStr) {
        List<FriendInfo> filterDateList = new ArrayList<>();


        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mFriendList;
        } else {
            filterDateList.clear();
            for (FriendInfo friendInfoModel : mFriendList) {
                String name = friendInfoModel.getNickname();
                String displayName = friendInfoModel.getDisplayName();
                if (!TextUtils.isEmpty(displayName)) {
                    if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr) || displayName.contains(filterStr) || mCharacterParser.getSpelling(displayName).startsWith(filterStr)) {
                        filterDateList.add(friendInfoModel);
                    }
                } else {
                    if (name.contains(filterStr) || mCharacterParser.getSpelling(name).startsWith(filterStr)) {
                        filterDateList.add(friendInfoModel);
                    }
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, mPinyinComparator);
        mFilteredFriendList = filterDateList;
        mFriendListAdapter.updateListView(filterDateList);
    }

    /**
     * 用户信息
     *
     * @param friend
     */
    private void startFriendDetailsPage(FriendInfo friend) {
        Intent intent = new Intent(getContext(), UserDetailActivity.class);
        intent.putExtra("type", CLICK_CONTACT_FRAGMENT_FRIEND);
        intent.putExtra("friends", friend);
        startActivity(intent);
    }

    private void refreshUIListener() {
        BroadcastManager.getInstance(getContext()).addAction(AppContext.UPDATE_FRIEND, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
//                    updateUI();
                }
            }
        });
        BroadcastManager.getInstance(getContext()).addAction(AppContext.UPDATE_RED_DOT, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    tvUnread.setVisibility(View.INVISIBLE);
                }
            }
        });
        BroadcastManager.getInstance(getContext()).addAction(Const.CHANGEINFO, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
                mId = sp.getString(Const.LOGIN_ID, "");
                mCacheName = sp.getString(Const.LOGIN_NICKNAME, "");
                String header = sp.getString(Const.LOGIN_PORTRAIT, "");
                tvMe.setText(mCacheName);
                ImageLoader.getInstance().displayImage(TextUtils.isEmpty(header) ?
                        Generate.generateDefaultAvatar(mCacheName, mId) : header, sivMe, App.getOptions());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            BroadcastManager.getInstance(getContext()).destroy(AppContext.UPDATE_FRIEND);
            BroadcastManager.getInstance(getContext()).destroy(AppContext.UPDATE_RED_DOT);
            BroadcastManager.getInstance(getContext()).destroy(Const.CHANGEINFO);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_phoneContast:
                toPhoneContacts();
                break;
            case R.id.rl_group:
                startActivity(new Intent(getContext(), GroupListActivity.class));
                break;
            case R.id.rl_me_item:
                T.showShort(getContext(), "不能和自己聊天喔！");
                break;
            case R.id.iv_add_friends:
                startActivity(new Intent(getContext(), SearchFriendActivity.class));
                break;
        }
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    void toPhoneContacts() {
        startActivity(new Intent(getContext(), PhoneContactActivity.class));
    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 0);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mSourceFriendList.clear();
                    mFriendList.clear();
                    initText();
                    friendInfoDAO.delete(mId);
                    initData();
                    mSwipeRefresh.setRefreshing(false);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
