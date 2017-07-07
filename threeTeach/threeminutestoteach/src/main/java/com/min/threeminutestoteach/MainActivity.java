package com.min.threeminutestoteach;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.adpter.MainLVAdapter;
import com.min.threeminutestoteach.base.BaseMvpActivity;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.interfaces.IThreeTeacheView;
import com.min.threeminutestoteach.presenter.ThreeTeachePresenter;
import com.min.threeminutestoteach.ui.activity.DetailPlayer;
import com.min.threeminutestoteach.ui.activity.MorePopupWindow;
import com.min.threeminutestoteach.ui.activity.SendTeacheContentActivity;
import com.min.threeminutestoteach.utils.DataUtils;
import com.min.threeminutestoteach.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<IThreeTeacheView,ThreeTeachePresenter> implements IThreeTeacheView,XRefreshView.XRefreshViewListener, AdapterView.OnItemClickListener {

    @BindView(R.id.ll_main_back)
    LinearLayout llMainBack;
    @BindView(R.id.iv_main_more)
    ImageView ivMainMore;
    @BindView(R.id.lv_main)
    ListView lvMain;
    @BindView(R.id.xrefreshview_main)
    XRefreshView xrefreshviewMain;
    @BindView(R.id.btn_main_send)
    Button btnMainSend;
    private List<String> imgList = new ArrayList<>();
    private MainLVAdapter adapter;
    private List<TeacheBean> list;
    private int curPage = 1;
    private String userId;
    private String checkVip="0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userId= getIntent().getStringExtra("loginid");
        checkVip=getIntent().getStringExtra("checkVip");
        if(checkVip==null||userId==null){
            T.showShort(this,"userId或checkVip传值失败");
            checkVip="0";
            userId="13824692192";
        }
        DataUtils.checkVip=checkVip;
        getThreeTeachData(userId,"1");
        initXrefreshView();
        initLV();
    }

    @Override
    public ThreeTeachePresenter initPresenter() {
        return new ThreeTeachePresenter();
    }

    private void initLV() {
        adapter = new MainLVAdapter(this);
        lvMain.setAdapter(adapter);
        lvMain.setOnItemClickListener(this);
    }

    private void initXrefreshView() {
        xrefreshviewMain.setPinnedTime(1000);
        xrefreshviewMain.setMoveForHorizontal(true);
        xrefreshviewMain.setPullLoadEnable(true);
        xrefreshviewMain.setAutoLoadMore(false);
        xrefreshviewMain.enableReleaseToLoadMore(true);
        xrefreshviewMain.enableRecyclerViewPullUp(true);
        xrefreshviewMain.enablePullUpWhenLoadCompleted(true);
        xrefreshviewMain.setXRefreshViewListener(this);
    }

    @OnClick({R.id.ll_main_back, R.id.iv_main_more,R.id.btn_main_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_back:
                finish();
                break;
            case R.id.iv_main_more:
                int WidthPixels = DisplayUtils.getScreenWidthPixels(this);
                MorePopupWindow chatPopupWindow =new MorePopupWindow(this,userId,WidthPixels/3);
                chatPopupWindow.showPopupWindow(ivMainMore);
                break;
            case R.id.btn_main_send:
                Intent intent=new Intent(this, SendTeacheContentActivity.class);
                intent.putExtra("userId",userId);
                startActivityForResult(intent,101);
                break;
        }
    }



    @Override
    public void onRefresh() {
        getThreeTeachData(userId,"1");
        curPage = 1;
    }

    @Override
    public void onRefresh(boolean isPullDown) {
    }

    @Override
    public void onLoadMore(boolean isSilence) {
        curPage = curPage + 1;
        loadMoreData(userId,curPage+"");
    }

    @Override
    public void onRelease(float direction) {

    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TeacheBean teacheBean=adapter.getTeacheList().get(position);
        Intent intent=new Intent(this, DetailPlayer.class);
        intent.putExtra("teacheBean",teacheBean);
        intent.putExtra("userId",userId);
        intent.putExtra("checkVip",checkVip);
        startActivity(intent);
    }

    @Override
    public void getThreeTeachData(String userId, String page) {
        presenter.getThreeTeachData(userId,page);
    }

    @Override
    public void showError(String errorString) {
        xrefreshviewMain.stopLoadMore();
        xrefreshviewMain.stopRefresh();
        T.showShort(this,errorString);
    }

    @Override
    public void setThreeTeacheData(List<TeacheBean> data) {
          adapter.setData(data);
        xrefreshviewMain.stopRefresh();
    }

    @Override
    public void loadMoreData(String userId, String page) {
        presenter.getThreeTeachData(userId,page);
    }

    @Override
    public void setMoreData(List<TeacheBean> data) {
             adapter.addData(data);
        xrefreshviewMain.stopLoadMore();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==101&&resultCode==RESULT_OK){
            onRefresh();
        }

    }
}
