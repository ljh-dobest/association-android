package com.min.threeminutestoteach.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.adpter.MineTeacheRVAdapter;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.ui.activity.DetailPlayer;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Min on 2017/3/31.
 */

public class MineCollectFragment extends Fragment implements MineTeacheRVAdapter.OnItemClickLitener {

    @BindView(R.id.rv_mineTeache)
    RecyclerView rvMineTeache;
    private MineTeacheRVAdapter adapter;
    private  String userId;
    private List<TeacheBean> data=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View containerView = inflater.inflate(R.layout.fragment_mine_teach, container, false);
        ButterKnife.bind(this, containerView);
        initView();
        getMineCollectData();
        return containerView;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    private void initView() {
        adapter=new MineTeacheRVAdapter(getContext());
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvMineTeache.setLayoutManager(layoutManager);
        rvMineTeache.addItemDecoration(new DividerItemDecoration(getContext(), OrientationHelper.VERTICAL));
        rvMineTeache.setAdapter(adapter);
    }

    private void getMineCollectData() {
        HttpUtils.getMineCollectTeachData("/selectArticleCollection", userId,"9", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(getContext(), e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<List<TeacheBean>>>() {
                }.getType();
                Code<List<TeacheBean>> code = gson.fromJson(response, type);
                if(code.getCode()==200){
                    data=code.getData();
                    adapter.setmDatas(data);
                }else{
                    T.showShort(getContext(),"获取数据失败");
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(getContext(), DetailPlayer.class);
        intent.putExtra("teacheBean",data.get(position));
        intent.putExtra("userId",userId);
        startActivity(intent);
    }
}

