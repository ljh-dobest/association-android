package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.GroupVoteDetailRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupVote;
import com.ike.communityalliance.bean.Groups;
import com.ike.communityalliance.bean.JoinUsers;
import com.ike.communityalliance.bean.OptionBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.db.GroupsDAOImpl;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.CheckableLinearLayout;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 投票详情
 */
public class VoteDetailActivity extends BaseActivity {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.ll_vote_detail_back)
    LinearLayout ll_vote_detail_back;
    @BindView(R.id.ll_vote_detail_joinUsers)
    LinearLayout ll_vote_detail_joinUsers;
    @BindView(R.id.iv_vote_detail_icon)
    ImageView iv_vote_detail_icon;
    @BindView(R.id.iv_vote_detail_userHeader)
    ImageView iv_vote_detail_userHeader;
    @BindView(R.id.tv_select)
    TextView tvSelect;
    @BindView(R.id.tv_vote_detail_name)
    TextView tv_vote_detail_name;
    @BindView(R.id.tv_vote_detail_time)
    TextView tv_vote_detail_time;
    @BindView(R.id.tv_vote_detail_status)
    TextView tv_vote_detail_status;
    @BindView(R.id.tv_vote_detail_delete)
    TextView tv_vote_detail_delete;
    @BindView(R.id.tv_vote_detail_title)
    TextView tv_vote_detail_title;
    @BindView(R.id.btn_vote)
    Button btnVote;
    @BindView(R.id.activity_vote_detail)
    LinearLayout activityVoteDetail;
    @BindView(R.id.rv_votedetail_joinusers)
    RecyclerView rv_votedetail_joinusers;

    private String userId;
    private String group_id;
    private String vote_id;
    private String vote_title;
    private int mode;
    private List<Map<String, String>> data = new ArrayList<Map<String, String>>();
    private List<OptionBean> option;
    private List<JoinUsers> joinUsers=new ArrayList<>();
    private HashSet hashSet;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
      private GroupsDAOImpl sqLiteDAO;
    private GroupVoteDetailRvAdapter joinUsersAdapter;
    private GroupVote groupVote;
    private boolean isGroupMain=false;
    private int timeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_detail);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("config", this.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initView();
        LoadDialog.show(mContext);
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
        group_id = intent.getStringExtra("group_id");
        vote_id = intent.getStringExtra("vote_id");
        timeStatus=intent.getIntExtra("timeStatus",1);
        userId=getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
        isGroupMain=checkIsGroupMain();
        if(isGroupMain){
            tv_vote_detail_delete.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkIsGroupMain() {
        sqLiteDAO = new GroupsDAOImpl(mContext);
        Groups groups = sqLiteDAO.find(group_id);
       if(groups!=null){
           if (groups.getRole().equals("2")) {
               return true;
           }
       }
        return false;
    }

    private void initData() {
        HttpUtils.postVoteDetails("/voteDetails", group_id,vote_id,userId,new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/voteDetails------" + e);
                LoadDialog.dismiss(mContext);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<GroupVote>>() {
                }.getType();
                Code<GroupVote> code = gson.fromJson(response,type);
                if (code.getCode() == 200) {
                    groupVote = code.getData();
                    vote_id = groupVote.getVoteId();
                    vote_title = groupVote.getVoteTitle();
                    mode = groupVote.getMode();
                    option = groupVote.getOption();
                    joinUsers= groupVote.getJoinUsers();
                    LoadDialog.dismiss(mContext);
                } else {
                    T.showShort(mContext, "空");
                    LoadDialog.dismiss(mContext);
                }
                ini();
                initListView();
                if(joinUsers!=null){
                    initJoinUserList();
                }
            }
        });
    }

    private void initJoinUserList() {
        if(timeStatus==0){
            tv_vote_detail_status.setText("已结束");
            tv_vote_detail_status.setBackgroundResource(R.mipmap.vote_end);
        }
        ll_vote_detail_joinUsers.setVisibility(View.VISIBLE);
         joinUsersAdapter=new GroupVoteDetailRvAdapter(this);
        joinUsersAdapter.setmDatas(joinUsers);
        rv_votedetail_joinusers.setAdapter(joinUsersAdapter);
        LinearLayoutManager lm=new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        rv_votedetail_joinusers.setLayoutManager(lm);
    }

    private void initListView() {
        if(mode==0) {
            tvSelect.setText("单选");
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        }else{
            tvSelect.setText("多选");
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
        hashSet = new HashSet();
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取条目
                CheckableLinearLayout linearLayout = (CheckableLinearLayout) view.findViewById(R.id.ll_contain);
                if (linearLayout.isChecked()) {
                    if(mode==0) {
                        hashSet.clear();
                        hashSet.add(data.get(position).get("id"));
                    }else {
                        hashSet.add(data.get(position).get("id"));
                    }
                } else {
                    hashSet.remove(data.get(position).get("id"));
                }
                /**对于多选，建议创建集合，用于封装用户选中的条目position，存入时判定                     用户来回切换的状态*/
            }
        });
        setListViewHeightBasedOnChildren(listView);
    }
    //动态设置listview高度，使完全展示
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
    private void ini() {
        tv_vote_detail_name.setText(groupVote.getNickname());
        tv_vote_detail_time.setText(groupVote.getEndTime());
        tv_vote_detail_title.setText(vote_title);
        Picasso.with(this).load(HttpUtils.IMAGE_RUL+groupVote.getVoteImage()).into(iv_vote_detail_icon);
        Picasso.with(this).load(HttpUtils.IMAGE_RUL+groupVote.getUserPortraitUrl()).into(iv_vote_detail_userHeader);
       if(groupVote.getStatus()==0){
           btnVote.setVisibility(View.VISIBLE);
       }
        for (OptionBean optionBean : option) {
            Map<String, String> d = new HashMap<>();
            d.put("id", optionBean.getId());
            d.put("content", optionBean.getContent());
            data.add(d);
        }
    }

    @OnClick({R.id.ll_vote_detail_back, R.id.btn_vote})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_vote_detail_back:
                finish();
                break;
            case R.id.btn_vote:
                LoadDialog.show(mContext);
                postPeriod();
                break;
        }
    }

    private void postPeriod() {
        final Gson gson=new Gson();
        String vote_option=gson.toJson(hashSet);
        HttpUtils.postVote("/voteCollect", userId, group_id, vote_id, vote_option, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/voteCollect------"+e);
                LoadDialog.dismiss(mContext);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Type type=new TypeToken<Code<BaseBean>>(){}.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                switch (code.getCode()){
                    case 200:
                        T.showShort(mContext,"投票成功");
                         editor.putBoolean(vote_id,true);
                        editor.commit();
                        Intent intent=new Intent();
                        setResult(0,intent);
                        finish();
                        break;
                    case 0:
                        T.showShort(mContext,"未知失败");
                        LoadDialog.dismiss(mContext);
                        break;
                    case 101:
                        T.showShort(mContext,"投票时间已结束");
                        LoadDialog.dismiss(mContext);
                        break;
                    case 102:
                        T.showShort(mContext,"已投票，请勿重复提交");
                        LoadDialog.dismiss(mContext);
                        break;
                    case 103:
                        T.showShort(mContext,"投票已关闭或已投票已失效(创建者或群主删除投票)");
                        LoadDialog.dismiss(mContext);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public String getItem(int position) {
            return data.get(position).get("content");
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_vote_details, container, false);
            }
            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
            if(groupVote.getStatus()==1){
                TextView tv2=((TextView) convertView.findViewById(R.id.text2));
                tv2.setText(option.get(position).getCount()+"票");
            }
            return convertView;
        }
    }

}
