package com.min.threeminutestoteach.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/3.
 */

public class MainLVAdapter extends BaseAdapter {
    private List<TeacheBean> teacheBeanList = new ArrayList<>();
    private Context mContent;

    public MainLVAdapter(Context context) {
        this.mContent = context;
    }

    public void setData(List<TeacheBean> teacheBeanList) {
        this.teacheBeanList = teacheBeanList;
        notifyDataSetChanged();
    }

    public void addData(List<TeacheBean> teacheBeanList) {
        this.teacheBeanList.addAll(teacheBeanList);
        notifyDataSetChanged();
    }
   public List<TeacheBean> getTeacheList(){
        return teacheBeanList;
   }
    @Override
    public int getCount() {
        return teacheBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lv_item1, parent, false);
            holder = new ViewHolder(convertView);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        TeacheBean teacheBean=teacheBeanList.get(position);
        Picasso.with(mContent).load(HttpUtils.IMAGE_URL+teacheBean.getImageUrl()).into(holder.ivItem1Image);
        holder.tvItem1Title.setText(teacheBean.getTitle());
        holder.tvItem1UserName.setText(teacheBean.getNickname());
        holder.tvItem1Time.setText("/ "+ teacheBean.getPlayTime());
        return convertView;
    }


//    @Override
//    public int getItemViewType(int position) {
//        if (position >= 3) {
//            return 1;//第二种布局
//        }
//        return 0;//第一种
//    }


    class ViewHolder {
        @BindView(R.id.iv_item1_image)
        ImageView ivItem1Image;
        @BindView(R.id.tv_item1_title)
        TextView tvItem1Title;
        @BindView(R.id.tv_item1_userName)
        TextView tvItem1UserName;
        @BindView(R.id.tv_item1_time)
        TextView tvItem1Time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ViewHolder2 {
        @BindView(R.id.iv_item2_icon)
        XCRoundRectImageView ivItem2Icon;
        @BindView(R.id.tv_item2_name)
        TextView tvItem2Name;
        @BindView(R.id.tv_item2_title)
        TextView tvItem2Title;
        @BindView(R.id.tv_item2_details)
        TextView tvItem2Details;
        @BindView(R.id.iv_itme2_image)
        ImageView ivItme2Image;
        @BindView(R.id.tv_item2_shareNum)
        TextView tvItem2ShareNum;
        @BindView(R.id.tv_item2_goodNum)
        TextView tvItem2GoodNum;
        @BindView(R.id.tv_item2_discussNum)
        TextView tvItem2DiscussNum;

        ViewHolder2(View view) {
            ButterKnife.bind(this, view);
        }
    }
}


