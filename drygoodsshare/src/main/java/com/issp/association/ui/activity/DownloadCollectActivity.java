package com.issp.association.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.issp.association.R;
import com.issp.association.adapter.DownloadItemAdapter;
import com.issp.association.bean.DownloadBean;
import com.issp.association.greendao.gen.DownloadBeanDao;
import com.issp.association.utils.DataUtils;
import com.issp.association.utils.DisplayUtils;
import com.issp.association.utils.FileUtils;
import com.issp.association.utils.GreenDaoManager;
import com.issp.association.utils.T;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.issp.association.utils.Constants.MIME_MapTable;

/**
 * Created by T-BayMax on 2017/7/14.
 */

public class DownloadCollectActivity extends AutoLayoutActivity implements OnSwipeMenuItemClickListener {
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.rv_download)
    SwipeMenuRecyclerView rvDownload;

    DownloadItemAdapter adapter;
    private List<DownloadBean> list;
    private List<File> fileList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_collect);
        ButterKnife.bind(this);
        initView();
        initData();
        initClick();
    }

    private void initView() {
        ltMainTitle.setText("下载");
        ltMainTitleRight.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvDownload.setLayoutManager(layoutManager);
        rvDownload.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        rvDownload.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        rvDownload.setSwipeMenuItemClickListener(this);
        adapter = new DownloadItemAdapter();
        rvDownload.setHasFixedSize(true);
        rvDownload.setAdapter(adapter);
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = DisplayUtils.dp2px(DownloadCollectActivity.this, getResources().getDimensionPixelSize(R.dimen.border_70));

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(DownloadCollectActivity.this)
                        .setBackgroundDrawable(android.R.color.holo_red_light)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

            }
        }
    };

    private void initClick() {
        adapter.setOnItemClickListener(new DownloadItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openFile(fileList.get(position));
            }
        });
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        fileList.get(adapterPosition).delete();
        fileList.remove(adapterPosition);
        adapter.notifyItemRemoved(adapterPosition);
    }

    @OnClick(R.id.lt_main_title_left)
    public void onViewClicked() {
        this.finish();
    }

    private void initData() {
        list = new ArrayList<DownloadBean>(0);
        fileList = new ArrayList<File>(0);
        FileUtils fileUtils = new FileUtils(this);
        File files = fileUtils.creatSDDir("association");
        for (File file : files.listFiles()) {
            fileList.add(file);
            DownloadBean bean = new DownloadBean();
            bean.setTime(DataUtils.currentTimeDeatil(new Date(file.lastModified())));
            bean.setSize(file.length());
            bean.setName(file.getName());
            list.add(bean);
        }
        if (null != list)
            adapter.setData(list);
    }

    /****
     * 计算文件大小
     *
     * @param length
     * @return
     */
    public String ShowLongFileSzie(Long length) {
        if (length >= 1048576) {
            return (length / 1048576) + "MB";
        } else if (length >= 1024) {
            return (length / 1024) + "KB";
        } else if (length < 1024) {
            return length + "B";
        } else {
            return "0KB";
        }
    }

    /**
     * 根据文件后缀名获得对应的MIME类型。
     */
    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
   /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) {
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private void openFile(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //设置intent的Action属性
        intent.setAction(Intent.ACTION_VIEW);
        //获取文件file的MIME类型
        String type = getMIMEType(file);
        //设置intent的data和Type属性。
        intent.setDataAndType(/*uri*/Uri.parse(file.toString()), type);
        //跳转
        startActivity(intent);
    }
}
