package com.ike.communityalliance.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.L;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.App;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.FriendInfo;
import com.ike.communityalliance.bean.GroupMember;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.CharacterParser;
import com.ike.communityalliance.wedget.Generate;
import com.ike.communityalliance.wedget.PinyinComparator;
import com.ike.communityalliance.wedget.SideBar;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.model.UserInfo;
import okhttp3.Call;

/**
 * 选择好友---建群--添加群好友
 */
public class SelectFriendsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_selected_friends)
    LinearLayout llSelectedFriends;
    @BindView(R.id.tv_no_friend)
    TextView tvNoFriend;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.sidrbar)
    SideBar sidrbar;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser mCharacterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    private List<FriendInfo> mSelectedFriend;
    private boolean isCreateGroup;
    private String groupId;

    private List<FriendInfo> sourceDataList = new ArrayList<>();

    private SelectFriendAdapter adapter;


    private ArrayList<UserInfo> addDisList, deleDisList;
    private List<FriendInfo> data_list = new ArrayList<>();
    private List<String> startDisList;
    private List<FriendInfo> createGroupList;
    private List<GroupMember> addGroupMemberList;
    private List<GroupMember> deleteGroupMemberList;
    private List<GroupMember> setvicePrincipalList;
    private boolean isAddGroupMember;
    private boolean isDeleteGroupMember;
    private boolean isSetvicePrincipal;
    private boolean isCreateInterestGroup;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_friends);
        ButterKnife.bind(this);
        userId=getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
        mSelectedFriend = new ArrayList<>();
        isCreateGroup = getIntent().getBooleanExtra("createGroup", false);
        groupId = getIntent().getStringExtra("GroupId");
        isAddGroupMember = getIntent().getBooleanExtra("isAddGroupMember", false);
        isDeleteGroupMember = getIntent().getBooleanExtra("isDeleteGroupMember", false);
        isSetvicePrincipal=getIntent().getBooleanExtra("isSetvicePrincipal",false);
        isCreateInterestGroup=getIntent().getBooleanExtra("createInterestGroup",false);
        if (isAddGroupMember || isDeleteGroupMember||isSetvicePrincipal) {
            initGroupMemberList();
        }
        addDisList = (ArrayList<UserInfo>) getIntent().getSerializableExtra("AddDiscuMember");
        deleDisList = (ArrayList<UserInfo>) getIntent().getSerializableExtra("DeleteDiscuMember");
//        LoadDialog.show(mContext);
        initView();
        /**
         * 根据进行的操作初始化数据,添加删除群成员和获取好友信息是异步操作,所以做了很多额外的处理
         * 数据添加后还需要过滤已经是群成员,讨论组成员的用户
         * 最后设置adapter显示
         * 后两个操作全都根据异步操作推后
         */
        initData();
    }

    //群成员
    private void initGroupMemberList() {
        HttpUtils.postGroupsRequest("/groupMember", groupId,userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "groupMember------" + "网络连接错误");
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<GroupMember>>>() {}.getType();
                Code<List<GroupMember>> code = gson.fromJson(response, type);
                if (code.getCode() == 200) {
                    if(isAddGroupMember){
                        addGroupMemberList=code.getData();
                        fillSourceDataListWithFriendsInfo();
                    }else if(isDeleteGroupMember){
                        deleteGroupMemberList = code.getData();  //图片没有http....
                        fillSourceDataListForDeleteGroupMember();
                    }else{
                        setvicePrincipalList=code.getData();
                        fillSourDataListForSetvicePrincipal();
                    }
                }else {
                    T.showShort(mContext,"group_member----selectFriendsAct---");
                }
            }
        });
    }

    private void fillSourDataListForSetvicePrincipal() {
        if(setvicePrincipalList!=null&&setvicePrincipalList.size()>0){
            for (GroupMember setMember : setvicePrincipalList) {
                if (setMember.getUserId().contains(getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, ""))) {
                    continue;
                }
                data_list.add(new FriendInfo(setMember.getUserId(),
                        setMember.getUserName(), HttpUtils.IMAGE_RUL+setMember.getUserPortraitUrl(),
                        null //TODO displayName 需要处理 暂为 null
                ));
            }
            fillSourceDataList();
            updateAdapter();
        }
    }

    private void fillSourceDataListForDeleteGroupMember() {
        if (deleteGroupMemberList != null && deleteGroupMemberList.size() > 0) {
            for (GroupMember deleteMember : deleteGroupMemberList) {
                if (deleteMember.getUserId().contains(getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, ""))) {
                    continue;
                }
                data_list.add(new FriendInfo(deleteMember.getUserId(),
                        deleteMember.getUserName(), HttpUtils.IMAGE_RUL+deleteMember.getUserPortraitUrl(),
                        null //TODO displayName 需要处理 暂为 null
                ));
            }
            fillSourceDataList();
            updateAdapter();
        }
    }

    private void fillSourceDataListWithFriendsInfo() {
        HttpUtils.postFriendsRequest("/friends", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/friends-----"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<FriendInfo>>>() {}.getType();
                Code<List<FriendInfo>> code =gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    List<FriendInfo> friendInfos=code.getData();
                    if(mListView!=null){
                        for(FriendInfo friendInfo:friendInfos){   //有http。。。。
                            data_list.add(new FriendInfo(friendInfo.getUserId(),friendInfo.getNickname(),
                                    HttpUtils.IMAGE_RUL+friendInfo.getUserPortraitUrl(),friendInfo.getDisplayName()));
                        }
                        fillSourceDataList();
                        filterSourceDataList();
                        updateAdapter();
                    }

                }
            }
        });
    }

    private void initView() {
        tvTitle.setText("选择群成员");
        ivBack.setOnClickListener(this);
        tvEnter.setOnClickListener(this);
        //实例化汉字转拼音
        mCharacterParser = CharacterParser.getInstance();
        pinyinComparator = PinyinComparator.getInstance();
        sidrbar.setTextView(tvDialog);
        //设置右侧触摸监听
        sidrbar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });
        adapter = new SelectFriendAdapter(mContext, sourceDataList);
        mListView.setAdapter(adapter);
    }

    //获取成员
    private void initData() {
        if (deleDisList != null && deleDisList.size() > 0) {
            for (int i = 0; i < deleDisList.size(); i++) {
                if (deleDisList.get(i).getUserId().contains(getSharedPreferences("config", MODE_PRIVATE).getString(Const.LOGIN_ID, ""))) {
                    continue;
                }
                data_list.add(new FriendInfo(deleDisList.get(i).getUserId(),
                        deleDisList.get(i).getName(),
                        HttpUtils.IMAGE_RUL+deleDisList.get(i).getPortraitUri().toString(),
                        null //TODO displayName 需要处理 暂为 null
                ));
            }
            /**
             * 以下3步是标准流程
             * 1.填充数据sourceDataList
             * 2.过滤数据,邀请新成员时需要过滤掉已经是成员的用户,但做删除操作时不需要这一步
             * 3.设置adapter显示
             */
            fillSourceDataList();
            filterSourceDataList();
            updateAdapter();
        } else if (!isDeleteGroupMember && !isAddGroupMember) {
            fillSourceDataListWithFriendsInfo();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                SelectFriendsActivity.this.finish();
                break;
            case R.id.tv_enter:
                initEnter();
                break;
            default:
                break;
        }
    }

    private void initEnter() {
        if (mCBFlag != null && sourceDataList != null && sourceDataList.size() > 0) {
            startDisList = new ArrayList<>();
            List<String> disNameList = new ArrayList<>();
            createGroupList = new ArrayList<>();
            for (int i = 0; i < sourceDataList.size(); i++) {
                if (mCBFlag.get(i)) {
                    startDisList.add(sourceDataList.get(i).getUserId());
                    disNameList.add(sourceDataList.get(i).getNickname());
                    createGroupList.add(sourceDataList.get(i));
                }
            }
            //设置副群主
            if(setvicePrincipalList!=null&&startDisList!=null&&sourceDataList.size()>0){
                AlertDialog dialog=new AlertDialog.Builder(mContext)
                        .setTitle("设置副群主")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                setPrincipal();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }else
            /**
             * 删除群成员
             */
            if (deleteGroupMemberList != null && startDisList != null && sourceDataList.size() > 0) {
                AlertDialog dialog=new AlertDialog.Builder(mContext)
                        .setTitle("删除该成员")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteGroupMember();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            } else
            /**
             * 添加群成员
             */
            if (addGroupMemberList != null && startDisList != null && startDisList.size() > 0) {
                //TODO 选中添加成员的数据添加到服务端数据库  返回本地也需要更改
                LoadDialog.show(mContext);
                L.e("-----------ssss",startDisList.get(0));
//                request(ADD_GROUP_MEMBER);
                addGroupMember();

            } else
            if (isCreateGroup) {
                if (createGroupList.size() > 0) {
                    Intent intent = new Intent(SelectFriendsActivity.this, CreateGroupActivity.class);
                    intent.putExtra("GroupMember", (Serializable) createGroupList);
                    startActivity(intent);
                    finish();
                } else {
                    T.showShort(mContext, "至少选一个好友");
                }
            }else if(isCreateInterestGroup){
                if (createGroupList.size() > 0) {
                    Intent intent = new Intent(SelectFriendsActivity.this, CreateInterestGroupActivity.class);
                    intent.putExtra("interestGroupMember", (Serializable) createGroupList);
                    startActivity(intent);
                    finish();
                } else {
                    T.showShort(mContext, "至少选一个好友");
                }
            }
        } else {
            Toast.makeText(SelectFriendsActivity.this, "无数据", Toast.LENGTH_SHORT).show();
        }
    }
  /**
   * 设置副群主*/
    private void setPrincipal() {
        final Gson gson=new Gson();
        String dd=gson.toJson(startDisList);
        HttpUtils.postVicePrincipal("/vicePrincipal", userId, dd, groupId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(SelectFriendsActivity.this,"/vicePrincipal");
            }

            @Override
            public void onResponse(String response, int id) {
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response, type);
                switch (code.getCode()) {
                    case 200:
                        T.showShort(mContext,"设置成功");
                        adapter.notifyDataSetChanged();
                        break;
                    case 0:
                        T.showShort(mContext,"设置失败");
                        break;
                    case 101:
                        T.showShort(mContext,"该用户已经是管理员");
                        break;
                    case 102:
                        T.showShort(mContext,"副群主已存在");
                        break;
                }
            }
        });

    }

    /**
     * 添加群组成员
     */
    private void addGroupMember() {
        final Gson gson=new Gson();
        String dd=gson.toJson(startDisList);
        HttpUtils.postAddGroupMember("/GroupPullUser", groupId, dd,userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/GroupPullUser-----"+e);
                LoadDialog.dismiss(mContext);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<Code<BaseBean>>(){}.getType();
                Code<BaseBean> code=gson.fromJson(response,type);
                if(code.getCode()==200){
                    Intent data = new Intent();
                    data.putExtra("newAddMember", (Serializable) createGroupList);
                    setResult(101, data);
                    LoadDialog.dismiss(mContext);
                    T.showShort(mContext,"添加成功");
                    finish();
                }else {
                    T.showShort(mContext,"添加失败");
                    LoadDialog.dismiss(mContext);
                }
            }
        });

    }

    /**
     * 删除群组成员
     */
    private void deleteGroupMember() {
        final Gson gson=new Gson();
        String sss=gson.toJson(startDisList);
        HttpUtils.postDelGroupMember("/kickGroupUser", sss, userId, groupId,  new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/kickGroupUser--------"+e);
                LoadDialog.dismiss(mContext);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<Code<Integer>>(){}.getType();
                Code<Integer> code=gson.fromJson(response,type);
                if(code.getCode()==200){
                    T.showShort(mContext,"删除成功");
                    finish();
                }else {
                    T.showShort(mContext,"删除失败");
                }
            }
        });
    }

    private void fillSourceDataList() {
        if (data_list != null && data_list.size() > 0) {
            sourceDataList = filledData(data_list); //过滤数据为有字母的字段  现在有字母 别的数据没有
        } else {
            tvNoFriend.setVisibility(View.VISIBLE);
        }

        //还原除了带字母字段的其他数据
        for (int i = 0; i < data_list.size(); i++) {
            sourceDataList.get(i).setNickname(data_list.get(i).getNickname());
            sourceDataList.get(i).setUserId(data_list.get(i).getUserId());
            sourceDataList.get(i).setUserPortraitUrl(data_list.get(i).getUserPortraitUrl());
            sourceDataList.get(i).setDisplayName(data_list.get(i).getDisplayName());
        }
        // 根据a-z进行排序源数据
        Collections.sort(sourceDataList, pinyinComparator);
    }

    //讨论组群组邀请新成员时需要过滤掉已经是成员的用户
    private void filterSourceDataList() {
        if (addDisList != null && addDisList.size() > 0) {
            for (UserInfo u : addDisList) {
                for (int i = 0; i < sourceDataList.size(); i++) {
                    if (sourceDataList.get(i).getUserId().contains(u.getUserId())) {
                        sourceDataList.remove(sourceDataList.get(i));
                    }
                }
            }
        } else if (addGroupMemberList != null && addGroupMemberList.size() > 0) {
            for (GroupMember addMember : addGroupMemberList) {
                for (int i = 0; i < sourceDataList.size(); i++) {
                    if (sourceDataList.get(i).getUserId().contains(addMember.getUserId())) {
                        sourceDataList.remove(sourceDataList.get(i));
                    }
                }
            }
        }
    }

    private void updateAdapter() {
        adapter.setData(sourceDataList);
        adapter.notifyDataSetChanged();
    }

    /**
     * 为ListView填充数据
     */
    private List<FriendInfo> filledData(List<FriendInfo> list) {
        List<FriendInfo> mFriendList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            FriendInfo friendModel = new FriendInfo();
            friendModel.setNickname(list.get(i).getNickname());
            //汉字转换成拼音
            String pinyin = null;
            if (!TextUtils.isEmpty(list.get(i).getDisplayName())) {
                pinyin = mCharacterParser.getSpelling(list.get(i).getDisplayName());
            } else if (!TextUtils.isEmpty(list.get(i).getNickname())) {
                pinyin = mCharacterParser.getSpelling(list.get(i).getNickname());
            } else {
                UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(list.get(i).getUserId());
                if (userInfo != null) {
                    pinyin = mCharacterParser.getSpelling(userInfo.getName());
                }
            }
            String sortString;
            if (!TextUtils.isEmpty(pinyin)) {
                sortString = pinyin.substring(0, 1).toUpperCase();
            } else {
                sortString = "#";
            }

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                friendModel.setLetters(sortString);
            } else {
                friendModel.setLetters("#");
            }

            mFriendList.add(friendModel);
        }
        return mFriendList;

    }


    //adapter
    public Map<Integer, Boolean> mCBFlag;
    public List<FriendInfo> adapterList = new ArrayList<>();

    class SelectFriendAdapter extends BaseAdapter implements SectionIndexer {
        private Context context;
        private List<CheckBox> checkBoxList = new ArrayList<>();

        public SelectFriendAdapter(Context context, List<FriendInfo> list) {
            this.context = context;
            adapterList = list;
            mCBFlag = new HashMap<>();
            init();
        }

        public void setData(List<FriendInfo> friends) {
            adapterList = friends;
            init();
        }

        void init() {
            for (int i = 0; i < adapterList.size(); i++) {
                mCBFlag.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return adapterList.size();
        }

        @Override
        public Object getItem(int position) {
            return adapterList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            final FriendInfo friendInfo = adapterList.get(position);
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_selected_freinds, viewGroup, false);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_friendname);
                viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.tv_catalog);
                viewHolder.mImageView = (SelectableRoundedImageView) convertView.findViewById(R.id.siv_frienduri);
                viewHolder.isSelect = (CheckBox) convertView.findViewById(R.id.sb_select);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            //根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);
            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                viewHolder.tvLetter.setVisibility(View.VISIBLE);
                viewHolder.tvLetter.setText(friendInfo.getLetters());
            } else {
                viewHolder.tvLetter.setVisibility(View.GONE);
            }

            viewHolder.isSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCBFlag.put(position, viewHolder.isSelect.isChecked());
                    updateSelectedSizeView(mCBFlag);
                    if (mSelectedFriend.contains(friendInfo)) {
                        int index = adapterList.indexOf(friendInfo);
                        if (index > -1) {
                            llSelectedFriends.removeViewAt(index);
                        }
                        mSelectedFriend.remove(friendInfo);
                    } else {
                        mSelectedFriend.add(friendInfo);
                        LinearLayout linearLayout = (LinearLayout) View.inflate(SelectFriendsActivity.this, R.layout.item_selected_friends_iv, null);
                        SelectableRoundedImageView iv = (SelectableRoundedImageView) linearLayout.findViewById(R.id.iv_selected_friends);
                        ImageLoader.getInstance().displayImage(TextUtils.isEmpty(HttpUtils.IMAGE_RUL+friendInfo.getUserPortraitUrl())
                                ? Generate.generateDefaultAvatar(friendInfo.getNickname(), friendInfo.getUserId())
                                : HttpUtils.IMAGE_RUL+friendInfo.getUserPortraitUrl(), iv);
                        linearLayout.removeView(iv);
                        llSelectedFriends.addView(iv);
                    }
                }
            });

            viewHolder.isSelect.setChecked(mCBFlag.get(position));
            String disPlayName=adapterList.get(position).getDisplayName();
            if (TextUtils.isEmpty(disPlayName)||disPlayName.equals("null")) {
                viewHolder.tvTitle.setText(adapterList.get(position).getNickname());
            } else {
                viewHolder.tvTitle.setText(adapterList.get(position).getDisplayName());
            }

            String url = adapterList.get(position).getUserPortraitUrl();
            if (!TextUtils.isEmpty(url)) {
                ImageLoader.getInstance().displayImage(url, viewHolder.mImageView, App.getOptions());
            } else {
                ImageLoader.getInstance().displayImage(Generate.generateDefaultAvatar(
                        adapterList.get(position).getNickname(), adapterList.get(position).getUserId()),
                        viewHolder.mImageView, App.getOptions());
            }

            return convertView;
        }

        private void updateSelectedSizeView(Map<Integer, Boolean> mCBFlag) {
            if (mCBFlag != null) {
                int size = 0;
                for (int i = 0; i < mCBFlag.size(); i++) {
                    if (mCBFlag.get(i)) {
                        size++;
                    }
                }
                if (size == 0) {
                    tvEnter.setText("确定");
                    llSelectedFriends.setVisibility(View.GONE);
                } else {
                    tvEnter.setText("确定(" + size + ")");
                    List<FriendInfo> selectedList = new ArrayList<>();
                    for (int i = 0; i < sourceDataList.size(); i++) {
                        if (mCBFlag.get(i)) {
                            selectedList.add(sourceDataList.get(i));
                        }
                    }
                    llSelectedFriends.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public Object[] getSections() {
            return new Object[0];
        }

        /**
         * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
         */
        @Override
        public int getPositionForSection(int sectionIndex) {
            for (int i = 0; i < getCount(); i++) {
                String sortStr = adapterList.get(i).getLetters();
                char firstChar = sortStr.toUpperCase().charAt(0);
                if (firstChar == sectionIndex) {
                    return i;
                }
            }

            return -1;
        }

        /**
         * 根据ListView的当前位置获取分类的首字母的Char ascii值
         */
        @Override
        public int getSectionForPosition(int i) {
            return adapterList.get(i).getLetters().charAt(0);
        }

        final class ViewHolder {
            /**
             * 首字母
             */
            TextView tvLetter;
            /**
             * 昵称
             */
            TextView tvTitle;
            /**
             * 头像
             */
            SelectableRoundedImageView mImageView;
            /**
             * userid
             */
//            TextView tvUserId;
            /**
             * 是否被选中的checkbox
             */
            CheckBox isSelect;
        }
    }

}
