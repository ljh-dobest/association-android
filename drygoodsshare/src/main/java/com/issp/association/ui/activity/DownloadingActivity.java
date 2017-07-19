package com.issp.association.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.issp.association.R;
import com.issp.association.adapter.DownloadItemAdapter;
import com.issp.association.adapter.DownloadingItemAdapter;
import com.issp.association.bean.DownloadBean;
import com.issp.association.greendao.gen.DownloadBeanDao;
import com.issp.association.utils.DisplayUtils;
import com.issp.association.utils.FileUtils;
import com.issp.association.utils.GreenDaoManager;
import com.issp.association.utils.T;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.action;

/**
 * Created by T-BayMax on 2017/7/14.
 */

public class DownloadingActivity extends AppCompatActivity implements OnSwipeMenuItemClickListener {
    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.rv_download)
    SwipeMenuRecyclerView rvDownload;

    DownloadingItemAdapter adapter;
    private List<DownloadBean> list;

    private String fileName;
    File file;
    FileUtils fileUtils;


    private ProgressBar taskPb;
    private TextView tvDownloadStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_collect);
        ButterKnife.bind(this);
        initView();
        initData();
        initClick();
        fileUtils = new FileUtils(DownloadingActivity.this);
        file = fileUtils.creatSDDir("association");
    }

    private void initView() {
        ltMainTitle.setText("管理下载");
        ltMainTitleRight.setVisibility(View.GONE);
        adapter = new DownloadingItemAdapter();
        rvDownload.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvDownload.setLayoutManager(layoutManager);
        rvDownload.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        rvDownload.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        rvDownload.setSwipeMenuItemClickListener(this);
    }

    private void initData() {
        list = GreenDaoManager.getInstance().getSession().getDownloadBeanDao().queryBuilder()
                .where(DownloadBeanDao.Properties.Sofar.ge(DownloadBeanDao.Properties.Size)).build().list();
        adapter.setData(list);
    }

    private void initClick() {
        adapter.setOnStartClickListener(new DownloadingItemAdapter.OnStartClickListener() {
            @Override
            public void onStartClick(DownloadingItemAdapter.ViewHolder viewHolder, DownloadBean bean) {
                taskPb = viewHolder.pbDownload;
                tvDownloadStatus = viewHolder.tvDownloadStatus;
                switch (viewHolder.status) {
                    case 0:
                        BaseDownloadTask task = FileDownloader.getImpl().create(bean.getUrl())
                                .setPath(bean.getPath())
                                .setCallbackProgressTimes(100)
                                .setListener(taskDownloadListener);

                        task.start();
                        break;
                    case 1:
                        FileDownloader.getImpl().pause(Integer.parseInt(bean.getId().toString()));
                        break;
                    case 2:
                        break;
                    case 3:
                        new File(bean.getPath()).delete();

                        updateNotDownloaded(FileDownloadStatus.INVALID_STATUS, 0, 0);
                        break;
                }
            }
        });
        rvDownload.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                DownloadBean bean = list.get(adapterPosition);
                fileName = bean.getName();
                delete(fileName);
                new File(FileDownloadUtils.getTempPath(bean.getPath())).delete();
                list.remove(adapterPosition);
                adapter.setData(list);
            }
        });
    }

    @OnClick(R.id.lt_main_title_left)
    public void onViewClicked() {
        this.finish();
    }

    /**
     * 菜单创建器。在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = DisplayUtils.dp2px(DownloadingActivity.this, 60);

            // 设置菜单方向为竖型的。
            swipeRightMenu.setOrientation(SwipeMenu.VERTICAL);
            SwipeMenuItem deleteItem = new SwipeMenuItem(DownloadingActivity.this)
                    .setBackgroundColor(android.R.color.holo_red_light)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(0)// 设置高度为0。
                    .setWeight(1);// 设置高度的Weight。
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };

    private FileDownloadListener taskDownloadListener = new FileDownloadSampleListener() {


        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.pending(task, soFarBytes, totalBytes);
            update(fileName, totalBytes, soFarBytes);
            updateDownloading(FileDownloadStatus.pending, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void started(BaseDownloadTask task) {
            super.started(task);


        }

        @Override
        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            super.connected(task, etag, isContinue, soFarBytes, totalBytes);

            update(fileName, totalBytes, soFarBytes);
            updateDownloading(FileDownloadStatus.connected, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.progress(task, soFarBytes, totalBytes);


            updateDownloading(FileDownloadStatus.progress, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            super.error(task, e);
            update(fileName, task.getLargeFileSoFarBytes()
                    , task.getLargeFileTotalBytes());
            updateNotDownloaded(FileDownloadStatus.error, task.getLargeFileSoFarBytes()
                    , task.getLargeFileTotalBytes());
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.paused(task, soFarBytes, totalBytes);
            update(fileName, totalBytes, soFarBytes);
            updateNotDownloaded(FileDownloadStatus.paused, soFarBytes, totalBytes);

        }

        @Override
        protected void completed(BaseDownloadTask task) {
            super.completed(task);

            updateDownloaded();
        }
    };
    private ImageView iv_download;

    /**
     * 下载停止状态
     *
     * @param status 状态
     * @param sofar  下载总量
     * @param total  应用总量
     */
    public void updateNotDownloaded(final int status, final long sofar, final long total) {
        if (sofar > 0 && total > 0) {
            final float percent = sofar
                    / (float) total;
            taskPb.setMax(100);
            taskPb.setProgress((int) (percent * 100));
        } else {
            taskPb.setMax(1);
            taskPb.setProgress(0);
        }
        tvDownloadStatus.setText(ShowLongFileSzie(sofar) + "/" + ShowLongFileSzie(total));
        switch (status) {
            case FileDownloadStatus.error:

                break;
            case FileDownloadStatus.paused:

                break;
            default:

                break;
        }
    }

    /**
     * 正在下载状态
     *
     * @param status 状态
     * @param sofar  下载总量
     * @param total  应用总量
     */
    public void updateDownloading(final int status, final long sofar, final long total) {
        final float percent = sofar
                / (float) total;
        taskPb.setMax(100);
        taskPb.setProgress((int) (percent * 100));

        tvDownloadStatus.setText(ShowLongFileSzie(sofar) + "/" + ShowLongFileSzie(total));
        switch (status) {
            case FileDownloadStatus.pending:

                break;
            case FileDownloadStatus.started:

                break;
            case FileDownloadStatus.connected:

                break;
            case FileDownloadStatus.progress:

                break;
            default:

                break;
        }


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
     * 下载完成状态
     */
    public void updateDownloaded() {
        taskPb.setMax(1);
        taskPb.setProgress(1);
        taskPb.setVisibility(View.GONE);
        T.showLong(DownloadingActivity.this, "下载完成");
    }

    private void update(String prevName, long size, long sofar) {
        DownloadBean bean = GreenDaoManager.getInstance().getSession().getDownloadBeanDao().queryBuilder()
                .where(DownloadBeanDao.Properties.Name.eq(prevName)).build().unique();
        if (bean != null) {
            bean.setSize(size);
            bean.setSofar(sofar);
            GreenDaoManager.getInstance().getSession().getDownloadBeanDao().update(bean);
            T.showLong(DownloadingActivity.this, "修改成功");
        } else {
            T.showLong(DownloadingActivity.this, "修改失败");
        }
    }


    private void delete(String name) {
        DownloadBeanDao userDao = GreenDaoManager.getInstance().getSession().getDownloadBeanDao();
        DownloadBean bean = userDao.queryBuilder().where(DownloadBeanDao.Properties.Name.eq(name)).build().unique();
        if (bean != null) {
            userDao.deleteByKey(bean.getId());
        }
    }


    private void insert(long id, String name, String path, long size, long sofar, String time,
                        String url) {
        DownloadBeanDao userDao = GreenDaoManager.getInstance().getSession().getDownloadBeanDao();
        DownloadBean bean = new DownloadBean(id, name, path, size, sofar, time, url);
        userDao.insert(bean);

        adapter.setData(userDao.queryBuilder().build().list());
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        delete(list.get(adapterPosition).getName());
    }
}
