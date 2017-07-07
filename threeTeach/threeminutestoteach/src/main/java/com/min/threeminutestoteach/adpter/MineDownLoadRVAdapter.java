/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.min.threeminutestoteach.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.bean.MineDownLoadBean;
import com.min.threeminutestoteach.listener.OnItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 *
 *
 */
public class MineDownLoadRVAdapter extends SwipeMenuAdapter<MineDownLoadRVAdapter.DefaultViewHolder> {
    private List<MineDownLoadBean> mineDownLoadBeanList;

    private OnItemClickListener mOnItemClickListener;

    public MineDownLoadRVAdapter(List<MineDownLoadBean> mineDownLoadBeanList) {
        this.mineDownLoadBeanList = mineDownLoadBeanList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.setData(mineDownLoadBeanList.get(position));
    }

    @Override
    public int getItemCount() {
        return mineDownLoadBeanList == null ? 0 : mineDownLoadBeanList.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_download_rv_item, parent, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        DefaultViewHolder viewHolder = new DefaultViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        return viewHolder;
    }


    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle,tvSize;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_mine_download_title);
            tvSize = (TextView) itemView.findViewById(R.id.tv_mine_download_size);
        }

        public void setData(MineDownLoadBean mineDownLoadBean) {
            tvTitle.setText(mineDownLoadBean.getFileName());
            tvSize.setText(mineDownLoadBean.getFileSize());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
