package com.issp.association.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.issp.association.R;
import com.issp.association.bean.DownloadBean;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.zhy.autolayout.attr.AutoAttr;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by T-BayMax on 2017/7/14.
 */

public class DownloadingItemAdapter extends SwipeMenuAdapter<DownloadingItemAdapter.ViewHolder> {
    private List<DownloadBean> list = new ArrayList<DownloadBean>(0);

    private OnStartClickListener onStartClickListener;

    /* public DownloadItemAdapter(){

     }*/
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_downloading_itme, parent, false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        final ViewHolder viewHolder = new ViewHolder(realContentView);
        realContentView.setTag(viewHolder);
        final DownloadBean bean = list.get(viewType);
        viewHolder.tvDownloadTitle.setText(bean.getName());
        viewHolder.tvDownloadStatus.setText(bean.getSofar() + "/" + ShowLongFileSzie(bean.getSize()));
        viewHolder.pbDownload.setProgress(Integer.parseInt(bean.getSofar()/bean.getSize()+""));
        viewHolder.ivDownloadStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onStartClickListener.onStartClick(viewHolder,bean);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    public void setData(List<DownloadBean> data) {
        list = data;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
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
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_download_title)
        public TextView tvDownloadTitle;
        @BindView(R.id.tv_download_status)
        public TextView tvDownloadStatus;
        @BindView(R.id.tv_download_curprogess)
        public TextView tvDownloadCurprogess;
        @BindView(R.id.tv_download_total)
        TextView tvDownloadTotal;
        @BindView(R.id.pb_download)
        public ProgressBar pbDownload;
        @BindView(R.id.iv_download_start)
        ImageView ivDownloadStart;
         public int status;//0等待下载，1正在下载，2暂停，3下载完成

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(view, AutoAttr.BASE_HEIGHT);
        }
    }

    public void setOnStartClickListener(OnStartClickListener listener) {
        this.onStartClickListener = listener;
    }

    public interface OnStartClickListener {
        public void onStartClick(ViewHolder viewHolder,DownloadBean bean );
    }
}
