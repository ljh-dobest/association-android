package com.issp.association.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

/**
 * Created by T-BayMax on 2017/7/14.
 */

public class DownloadItemAdapter extends SwipeMenuAdapter<DownloadItemAdapter.ViewHolder> {
    private List<DownloadBean> list = new ArrayList<DownloadBean>(0);

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.view_download_itme, parent, false);
    }

    @Override
    public DownloadItemAdapter.ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DownloadItemAdapter.ViewHolder viewHolder = new ViewHolder(realContentView);

        viewHolder.mOnItemClickListener = mOnItemClickListener;
        DownloadBean bean = list.get(viewType);
        viewHolder.tvSize.setText(ShowLongFileSzie(bean.getSize()));
        viewHolder.tvTime.setText(bean.getTime());
        String[] str = bean.getName().split("\\.");
        String suffix = str[str.length - 1];
        viewHolder.tvTitle.setText(bean.getName());
        if (suffix.equals("ppt") || suffix.equals("PPT")) {
            viewHolder.ivIcon.setImageResource(R.mipmap.ppt);
        }
        if (suffix.equals("wps") || suffix.equals("WPS")) {
            viewHolder.ivIcon.setImageResource(R.mipmap.wps);
        }
        if (suffix.equals("xlsx") || suffix.equals("XLSX") || suffix.equals("xls")
                || suffix.equals("XLS") || suffix.equals("xlsm") || suffix.equals("XLSM")) {
            viewHolder.ivIcon.setImageResource(R.mipmap.xlsl);
        }

        return viewHolder;
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
    @Override
    public void onBindViewHolder(DownloadItemAdapter.ViewHolder holder, int position) {

    }

    public void setData(List<DownloadBean> data) {
        list = data;
        DownloadItemAdapter.this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_size)
        TextView tvSize;
        @BindView(R.id.tv_time)
        TextView tvTime;
        OnItemClickListener mOnItemClickListener;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            AutoUtils.autoSize(view, AutoAttr.BASE_HEIGHT);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    public interface OnItemClickListener {

        void onItemClick(int position);

    }
}
