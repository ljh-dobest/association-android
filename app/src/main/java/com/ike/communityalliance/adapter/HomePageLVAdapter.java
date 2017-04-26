package com.ike.communityalliance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseAdapters;
import com.ike.communityalliance.wedget.image.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/3.
 */

public class HomePageLVAdapter extends BaseAdapters {
    public HomePageLVAdapter(Context context, List data) {
        super(context, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolder2 viewHolder2=null;
        int type=getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case 0:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_lv_item3, parent, false);
                    holder = new ViewHolder();
                  ButterKnife.bind(holder, convertView);
                    convertView.setTag(holder);
                    break;
                case 1:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_lv_item2, parent, false);
                    viewHolder2 = new ViewHolder2();
                    ButterKnife.bind(viewHolder2, convertView);
                    convertView.setTag(viewHolder2);
                    break;
            }
        }
     if(type==1){
         viewHolder2= (ViewHolder2) convertView.getTag();

     }
        return convertView;
    }

//    class ViewHolder{
//        @BindView(R.id.iv_icon)
//        XCRoundRectImageView iv_con;
//        @BindView(R.id.tv_name)
//        TextView tv_name;
//        @BindView(R.id.tv_style)
//        TextView tv_style;
//        @BindView(R.id.iv_image)
//        XCRoundRectImageView iv_image;
//        @BindView(R.id.tv_title)
//        TextView tv_title;
//        @BindView(R.id.tv_details)
//        TextView tv_details;
//
//    }
    class ViewHolder{
        @BindView(R.id.iv_home_item_userIcon)
        CircleImageView iv_home_item_userIcon;
        @BindView(R.id.tv_home_item_name)
        TextView tv_home_item_name;
        @BindView(R.id.tv_home_item_userNum)
        TextView tv_home_item_userNum;
        @BindView(R.id.tv_home_item_recommender)
        TextView tv_home_item_recommender;
    }
    class ViewHolder2 {
        @BindView(R.id.iv_fa_lv_item_image)
        ImageView iv_fa_lv_item_image;
        @BindView(R.id.tv_fa_item_status)
        TextView tv_fa_item_status;
    }

    @Override
    public int getItemViewType(int position) {
        if(position>2){
            return 1;
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
