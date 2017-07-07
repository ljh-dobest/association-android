package com.min.threeminutestoteach.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.LinearLayout;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.adpter.MineDownLoadRVAdapter;
import com.min.threeminutestoteach.base.BaseActivity;
import com.min.threeminutestoteach.bean.MineDownLoadBean;
import com.min.threeminutestoteach.listener.OnItemClickListener;
import com.min.threeminutestoteach.utils.DisplayUtils;
import com.min.threeminutestoteach.utils.FileUtil;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineDownLoadActivity extends BaseActivity implements OnSwipeMenuItemClickListener, OnItemClickListener {

    @BindView(R.id.ll_mine_download_back)
    LinearLayout llMineDownloadBack;
    @BindView(R.id.rv_mine_download)
    SwipeMenuRecyclerView rvMineDownload;
    private MineDownLoadRVAdapter adapter;
    private List<MineDownLoadBean> mineDownLoadList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_down_load);
        ButterKnife.bind(this);
        getMineDownLoadFiles();
        initView();
    }

    private void getMineDownLoadFiles() {
        File d = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "d");
        if (!d.exists()) {
            d.mkdirs();
        }
        File files[] = d.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory()) {
                   mineDownLoadList.add(new MineDownLoadBean(f.getName(), FileUtil.formatFileSize(f.length()),f.getAbsolutePath()));
                }
            }
        }
    }

    private void initView() {
        adapter=new MineDownLoadRVAdapter(mineDownLoadList);
        adapter.setOnItemClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvMineDownload.setLayoutManager(layoutManager);
        rvMineDownload.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        rvMineDownload.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        rvMineDownload.setSwipeMenuItemClickListener(this);
        rvMineDownload.setAdapter(adapter);

    }

    @OnClick(R.id.ll_mine_download_back)
    public void onViewClicked() {
        finish();
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
               File file=new File(mineDownLoadList.get(adapterPosition).getFilePath());
        if(file!=null){
            file.delete();
            mineDownLoadList.remove(adapterPosition);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(this,VedioActivity.class);
        intent.putExtra("filePath",mineDownLoadList.get(position).getFilePath());
        startActivity(intent);
    }
}
