package com.min.threeminutestoteach.adpter;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.db.DBController;
import com.min.threeminutestoteach.domain.MyBusinessInfLocal;
import com.min.threeminutestoteach.listener.MyDownloadListener;
import com.min.threeminutestoteach.listener.OnItemClickListener;
import com.min.threeminutestoteach.utils.FileUtil;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.io.File;
import java.lang.ref.SoftReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.woblog.android.downloader.DownloadService;
import cn.woblog.android.downloader.callback.DownloadManager;
import cn.woblog.android.downloader.domain.DownloadInfo;
import cn.woblog.android.downloader.domain.DownloadInfo.Builder;

import static cn.woblog.android.downloader.domain.DownloadInfo.STATUS_COMPLETED;
import static cn.woblog.android.downloader.domain.DownloadInfo.STATUS_REMOVED;
import static cn.woblog.android.downloader.domain.DownloadInfo.STATUS_WAIT;

/**
 * Created by renpingqing on 17/1/19.
 */
public class DownloadListAdapter extends SwipeMenuAdapter<DownloadListAdapter.ViewHolder>{
  private List<MyBusinessInfLocal> data = new ArrayList<>();
    private static final String TAG = "DownloadListAdapter";
    private final Context context;
    private final DownloadManager downloadManager;
    private DBController dbController;

    public DownloadListAdapter(Context context,DBController dbController) {
        this.context = context;
        this.dbController=dbController;
        downloadManager = DownloadService.getDownloadManager(context.getApplicationContext());
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void setData(List<MyBusinessInfLocal> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public MyBusinessInfLocal getData(int position) {
        return data.get(position);
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
      return LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item, parent, false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        ViewHolder viewHolder = new ViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bindData(getData(position), position, context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        @BindView(R.id.tv_download_title)
        TextView tvDownloadTitle;
        @BindView(R.id.tv_download_status)
        TextView tvDownloadStatus;
        @BindView(R.id.tv_download_curprogess)
        TextView tvDownloadCurprogess;
        @BindView(R.id.tv_download_total)
        TextView tvDownloadTotal;
        @BindView(R.id.pb_download)
        ProgressBar pbDownload;
        @BindView(R.id.iv_download_start)
        ImageView ivDownloadStart;
        private DownloadInfo downloadInfo;
        OnItemClickListener mOnItemClickListener;
        ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }
        @SuppressWarnings("unchecked")
        public void bindData(final MyBusinessInfLocal data, int position, final Context context) {
            tvDownloadTitle.setText(data.getName());

            // Get download task status
            downloadInfo = downloadManager.getDownloadById(data.getUrl().hashCode());

            // Set a download listener
            if (downloadInfo != null) {
                downloadInfo
                        .setDownloadListener(new MyDownloadListener(new SoftReference(ViewHolder.this)) {
                            //  Call interval about one second
                            @Override
                            public void onRefresh() {
                                if (getUserTag() != null && getUserTag().get() != null) {
                                    ViewHolder viewHolder = (ViewHolder) getUserTag().get();
                                    viewHolder.refresh();
                                }
                            }
                        });
            }

            refresh();

//      Download button
            ivDownloadStart.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (downloadInfo != null) {

                        switch (downloadInfo.getStatus()) {
                            case DownloadInfo.STATUS_NONE:
                            case DownloadInfo.STATUS_PAUSED:
                            case DownloadInfo.STATUS_ERROR:

                                //resume downloadInfo
                                downloadManager.resume(downloadInfo);
                                break;

                            case DownloadInfo.STATUS_DOWNLOADING:
                            case DownloadInfo.STATUS_PREPARE_DOWNLOAD:
                            case STATUS_WAIT:
                                //pause downloadInfo
                                downloadManager.pause(downloadInfo);
                                break;
//                            case DownloadInfo.STATUS_COMPLETED:
//                                downloadManager.remove(downloadInfo);
//                                break;
                        }
                    } else {
//            Create new download task
                        File d = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "d");
                        if (!d.exists()) {
                            d.mkdirs();
                        }
                        String path = d.getAbsolutePath().concat("/").concat(data.getName()+".mp4");
                        downloadInfo = new Builder().setUrl(data.getUrl())
                                .setPath(path)
                                .build();
                        downloadInfo
                                .setDownloadListener(new MyDownloadListener(new SoftReference(ViewHolder.this)) {

                                    @Override
                                    public void onRefresh() {
                                        notifyDownloadStatus();

                                        if (getUserTag() != null && getUserTag().get() != null) {
                                            ViewHolder viewHolder = (ViewHolder) getUserTag().get();
                                            viewHolder.refresh();
                                        }
                                    }
                                });
                        downloadManager.download(downloadInfo);

                        //save extra info to my database.
                        MyBusinessInfLocal myBusinessInfLocal = new MyBusinessInfLocal(
                                data.getUrl().hashCode(), data.getName(), data.getIcon(), data.getUrl());
                        try {
                            dbController.createOrUpdateMyDownloadInfo(myBusinessInfLocal);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        }

        private void notifyDownloadStatus() {
            if (downloadInfo.getStatus() == STATUS_REMOVED) {
                try {
                    dbController.deleteMyDownloadInfo(downloadInfo.getUri().hashCode());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        private void refresh() {
            if (downloadInfo == null) {
                tvDownloadTotal.setText("");
                pbDownload.setProgress(0);
                ivDownloadStart.setImageResource(R.mipmap.download_start);
                tvDownloadStatus.setText("点击下载");
            } else {
                switch (downloadInfo.getStatus()) {
                    case DownloadInfo.STATUS_NONE:
                        ivDownloadStart.setImageResource(R.mipmap.download_start);
                        tvDownloadStatus.setText("");
                        break;
                    case DownloadInfo.STATUS_PAUSED:
                    case DownloadInfo.STATUS_ERROR:
                        ivDownloadStart.setImageResource(R.mipmap.download_start);
                        tvDownloadStatus.setText("下载暂停");
                        try {
                            pbDownload.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                          //  tvDownloadCurprogess.setText((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize())+"");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tvDownloadTotal.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                                .formatFileSize(downloadInfo.getSize()));
                        break;

                    case DownloadInfo.STATUS_DOWNLOADING:
                    case DownloadInfo.STATUS_PREPARE_DOWNLOAD:
                        ivDownloadStart.setImageResource(R.mipmap.download_stop);
                        try {
                            pbDownload.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tvDownloadTotal.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                                .formatFileSize(downloadInfo.getSize()));
                        tvDownloadStatus.setText("正在下载");
                        break;
                    case STATUS_COMPLETED:
                        ivDownloadStart.setImageResource(R.mipmap.selected);
                        try {
                            pbDownload.setProgress((int) (downloadInfo.getProgress() * 100.0 / downloadInfo.getSize()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tvDownloadTotal.setText(FileUtil.formatFileSize(downloadInfo.getProgress()) + "/" + FileUtil
                                .formatFileSize(downloadInfo.getSize()));
                        tvDownloadStatus.setText("下载完成");
                        break;
                    case STATUS_REMOVED:
                        tvDownloadTotal.setText("");
                        pbDownload.setProgress(0);
                        ivDownloadStart.setImageResource(R.mipmap.download_start);
                        tvDownloadStatus.setText("暂无下载信息");
                    case STATUS_WAIT:
                        tvDownloadTotal.setText("");
                        pbDownload.setProgress(0);
                        ivDownloadStart.setImageResource(R.mipmap.download_stop);
                        tvDownloadStatus.setText("等待下载");
                        break;
                }

            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
