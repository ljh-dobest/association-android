package com.ike.communityalliance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseAdapters;
import com.ike.communityalliance.bean.ApkItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/3.
 */

public class HomePageGVAdapter extends BaseAdapters {
   private int[] icons={R.mipmap.lingyang};
    public HomePageGVAdapter(Context context, List data) {
        super(context, data);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_gridview_item, parent, false);
            holder = new ViewHolder();
            ButterKnife.bind(holder,convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        ApkItem apkItem= (ApkItem) dataSet.get(position);
        if (null!=apkItem.icon) {
            holder.iv_icon.setImageDrawable(apkItem.icon);
        }else {
            holder.iv_icon.setImageResource(icons[0]);
        }
        holder.tv_item.setText(apkItem.title);
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.homepage_gv_img)
        ImageView iv_icon;
        @BindView(R.id.homepage_gv_tv)
        TextView tv_item;
    }
}
