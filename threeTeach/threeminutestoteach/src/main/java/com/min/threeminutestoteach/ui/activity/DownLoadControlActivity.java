package com.min.threeminutestoteach.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.LinearLayout;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.adpter.DownloadListAdapter;
import com.min.threeminutestoteach.db.DBController;
import com.min.threeminutestoteach.listener.OnItemClickListener;
import com.min.threeminutestoteach.utils.DataUtils;
import com.min.threeminutestoteach.utils.DisplayUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.woblog.android.downloader.DownloadService;
import cn.woblog.android.downloader.callback.DownloadManager;
import cn.woblog.android.downloader.domain.DownloadInfo;

public class DownLoadControlActivity extends AppCompatActivity implements OnSwipeMenuItemClickListener, OnItemClickListener {

    @BindView(R.id.ll_download_back)
    LinearLayout llDownloadBack;
    @BindView(R.id.rv_download)
    SwipeMenuRecyclerView rvDownload;
    private DownloadListAdapter downloadListAdapter;
    private Activity mContext;
    private DBController dbController;
    private  DownloadManager downloadManager;
    private DownloadInfo downloadInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load_control);
        ButterKnife.bind(this);
        mContext = this;
        try {
            dbController = DBController.getInstance(getApplicationContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        downloadManager = DownloadService.getDownloadManager(getApplicationContext());
        initView();
    }

    private void initView() {
        downloadListAdapter = new DownloadListAdapter(this,dbController);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvDownload.setLayoutManager(layoutManager);
        rvDownload.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        rvDownload.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        rvDownload.setSwipeMenuItemClickListener(this);
        rvDownload.setAdapter(downloadListAdapter);
        downloadListAdapter.setData(DataUtils.DOWNLOAD_LIST);
        //downloadListAdapter.setOnItemClickListener(this);
    }

    @OnClick({R.id.ll_download_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_download_back:
                finish();
                break;
        }
    }
    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = DisplayUtils.dp2px(mContext,60);

            // 设置菜单方向为竖型的。
            swipeRightMenu.setOrientation(SwipeMenu.VERTICAL);
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundColor(R.color.red)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(0)// 设置高度为0。
                    .setWeight(1);// 设置高度的Weight。
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        try {
            dbController.deleteMyDownloadInfo(DataUtils.DOWNLOAD_LIST.get(adapterPosition).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        downloadInfo = downloadManager.getDownloadById(DataUtils.DOWNLOAD_LIST.get(adapterPosition).getUrl().hashCode());
        if(downloadInfo!=null){
            downloadManager.remove(downloadInfo);
            DataUtils.DOWNLOAD_LIST.remove(adapterPosition);
            downloadListAdapter.setData(DataUtils.DOWNLOAD_LIST);
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
