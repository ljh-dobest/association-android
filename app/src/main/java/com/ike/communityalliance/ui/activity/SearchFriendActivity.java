package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.SearchFriendRvAdapter;
import com.ike.communityalliance.adapter.SearchPossiblePeopleRvAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.PossiblePeopleBean;
import com.ike.communityalliance.bean.UserOrGroupBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.RecycleViewDivider;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 搜索好友----添加好友
 */
public class SearchFriendActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.et_friend)
    EditText etFriend;
    @BindView(R.id.iv_searchFriend_back)
    ImageView iv_searchFriend_back;
    @BindView(R.id.iv_searchFriend_scan_white)
    ImageView iv_searchFriend_scan_white;
    @BindView(R.id.btn_seach_seacher)
    Button btn_seach_seacher;
    @BindView(R.id.tv_search_more)
    TextView tv_search_more;
    @BindView(R.id.rv_search_possible_people)
    RecyclerView rvSearchPossiblePeople;
    @BindView(R.id.ll_possible_understand)
    LinearLayout llPossibleUnderstand;
    @BindView(R.id.rv_search_friends)
    RecyclerView rvSearchFriends;

    private String msg;
    private String status;
    private SharedPreferences sp;
    private String userId, nickName;
   private SearchFriendRvAdapter searchFriendRvAdapter;
    private SearchPossiblePeopleRvAdapter searchPossibleAdapter;
    private  List<PossiblePeopleBean> possiblePeopleBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        ButterKnife.bind(this);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        nickName = sp.getString(Const.LOGIN_NICKNAME, "");
        initView();
        getPossibleUnderstandPeople();
    }

    private void initView() {
        searchPossibleAdapter=new SearchPossiblePeopleRvAdapter(this,userId,nickName);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvSearchPossiblePeople.setLayoutManager(layoutManager);
        rvSearchPossiblePeople.setAdapter(searchPossibleAdapter);
        rvSearchPossiblePeople.addItemDecoration(new RecycleViewDivider(this,OrientationHelper.VERTICAL,14,getResources().getColor(R.color.col_ECEEF0)));

        searchFriendRvAdapter=new SearchFriendRvAdapter(this,userId,nickName);
        LinearLayoutManager layoutManager2= new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvSearchFriends.setLayoutManager(layoutManager2);
        rvSearchFriends.setAdapter(searchFriendRvAdapter);
        rvSearchPossiblePeople.addItemDecoration(new RecycleViewDivider(this,OrientationHelper.HORIZONTAL,2,getResources().getColor(R.color.col_ECEEF0)));
        etFriend.addTextChangedListener(this);
    }

    private void getPossibleUnderstandPeople() {
        HttpUtils.getPossibleUnderstandPeople("/knowFriends", userId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<PossiblePeopleBean>>>() {
                }.getType();
                Code<List<PossiblePeopleBean>> code = gson.fromJson(response, type);
                if(code.getCode()==200){
                    possiblePeopleBeanList=code.getData();
                    searchPossibleAdapter.setmDatas(possiblePeopleBeanList);
                }
            }
        });
    }

    private void searchFriends() {
        HttpUtils.PostSearchFriendRequest("/lookupUser", userId,msg, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LoadDialog.dismiss(mContext);
                T.showShort(mContext, "/lookupUser-----" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<UserOrGroupBean>>>() {
                }.getType();
                Code<List<UserOrGroupBean>> beanCode = gson.fromJson(response, type);
                int code = beanCode.getCode();
                if (code == 200) {
                    List<UserOrGroupBean> userOrGroupBeanList = beanCode.getData();
                   searchFriendRvAdapter.setmDatas(userOrGroupBeanList);
                    LoadDialog.dismiss(mContext);
                } else {
                    T.showShort(mContext, "用户不存在");
                    LoadDialog.dismiss(mContext);
                }
            }
        });
    }


    @OnClick({R.id.iv_searchFriend_back, R.id.iv_searchFriend_scan_white, R.id.btn_seach_seacher, R.id.tv_search_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_searchFriend_back:
                finish();
                break;
            case R.id.iv_searchFriend_scan_white:
                startActivityForResult(new Intent(mContext, CaptureActivity.class), 0);
                break;
            case R.id.btn_seach_seacher:
                msg = etFriend.getText().toString();
                searchFriendRvAdapter.setKeyword(msg);
                LoadDialog.show(mContext);
                searchFriends();
                llPossibleUnderstand.setVisibility(View.GONE);
                rvSearchFriends.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_search_more:
                startActivity(new Intent(this, PossibleUnderstandActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                msg=result.substring(6);
                etFriend.setText(msg);
                searchFriends();
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
     if(s.length()==0){
         llPossibleUnderstand.setVisibility(View.VISIBLE);
         rvSearchFriends.setVisibility(View.GONE);
     }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
