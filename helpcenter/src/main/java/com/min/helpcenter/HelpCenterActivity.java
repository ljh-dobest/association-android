package com.min.helpcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.min.helpcenter.adapters.SimpleAdapter;
import com.min.helpcenter.base.view.BaseMvpActivity;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.interfaces.IHelpCenterView;
import com.min.helpcenter.presenters.HelpCenterPresenter;
import com.min.helpcenter.ui.HelpCenterPopupWindow;
import com.min.helpcenter.ui.activitys.AskActivity;
import com.min.helpcenter.ui.activitys.QuestionActivity;
import com.min.helpcenter.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HelpCenterActivity extends BaseMvpActivity<IHelpCenterView,HelpCenterPresenter> implements IHelpCenterView, SimpleAdapter.OnItemClickLitener, XRefreshView.XRefreshViewListener {
@BindView(R.id.iv_helpCenter_back)
    ImageView iv_helpCenter_back;
@BindView(R.id.iv_helpCenter_more)
    ImageView iv_helpCenter_more;
@BindView(R.id.rv_helpCenter)
RecyclerView rv_helpCenter;
    @BindView(R.id.xrefreshview_helpCenter)
    XRefreshView xrefreshview_helpCenter;
    @BindView(R.id.iv_helpCenter_iask)
    ImageView rl_helpCenter_iask;
    private SimpleAdapter adapter;
    private List<HelpBean> list;
    private HelpCenterPopupWindow popupWindow;
    private  String userId;
    private int curPage=1;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        userId= getIntent().getStringExtra("loginid");
        userId="13824692192";
        initView();
        getHelpData(userId,1);
    }

    @Override
    public HelpCenterPresenter initPresenter() {
        return new HelpCenterPresenter();
    }

    private void initView() {
        list=new ArrayList<>();
        adapter=new SimpleAdapter(list,this);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_helpCenter.setLayoutManager(layoutManager);
        rv_helpCenter.setAdapter(adapter);
        rv_helpCenter.setItemAnimator(new DefaultItemAnimator());
        rv_helpCenter.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        initXrefreshView();
        popupWindow=new HelpCenterPopupWindow(this,userId);
    }

    private void initXrefreshView() {
        xrefreshview_helpCenter.setPinnedTime(1000);
        xrefreshview_helpCenter.setMoveForHorizontal(true);
        xrefreshview_helpCenter.setPullLoadEnable(true);
        xrefreshview_helpCenter.setAutoLoadMore(false);
        adapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        xrefreshview_helpCenter.enableReleaseToLoadMore(true);
        xrefreshview_helpCenter.enableRecyclerViewPullUp(true);
        xrefreshview_helpCenter.enablePullUpWhenLoadCompleted(true);
        xrefreshview_helpCenter.setXRefreshViewListener(this);
    }

    @Override
    public void getHelpData(String userId,int page) {
        presenter.getHelpData(userId,page);
    }

    @Override
    public void setHelpData(List<HelpBean> data){
            adapter.setData(data);
        xrefreshview_helpCenter.stopRefresh();
    }

    @Override
    public void setMoreHelpData(List<HelpBean> data) {
        adapter.addMoreData(data);
        xrefreshview_helpCenter.stopLoadMore();
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError(String errorString) {
        T.showShort(this,errorString);
    }


    @Override
    public void onItemClick(View view, int position) {
        HelpBean helpBean= adapter.getList().get(position);
        Intent intent=new Intent(this, QuestionActivity.class);
        intent.putExtra("helpBean",helpBean);
        intent.putExtra("userId",userId);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        curPage=1;
        presenter.getHelpData(userId,1);
    }

    @Override
    public void onRefresh(boolean isPullDown) {
    }
    @Override
    public void onLoadMore(boolean isSilence) {
        curPage = curPage + 1;
        presenter.getHelpData(userId,curPage);
    }
    @Override
    public void onRelease(float direction) {}
    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY) {}
    @OnClick({R.id.iv_helpCenter_back,R.id.iv_helpCenter_more,R.id.iv_helpCenter_iask})
    public void onHelpCenterViewClick(View view){
        switch (view.getId()) {
            case R.id.iv_helpCenter_back:
                finish();
                break;
            case R.id.iv_helpCenter_more:
                  popupWindow.showAsDropDown(iv_helpCenter_more,0,0);
                break;
            case R.id.iv_helpCenter_iask:
                Intent intent=new Intent(this, AskActivity.class);
                intent.putExtra("userId",userId);
                startActivityForResult(intent,88);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==88&&resultCode==RESULT_OK){
            getHelpData(userId,1);
        }
    }
}
