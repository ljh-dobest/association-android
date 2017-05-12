package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.ActivesBean;
import com.ike.communityalliance.bean.ClaimPeopleBean;
import com.ike.communityalliance.bean.ClaimUsers;
import com.ike.communityalliance.bean.HomePageBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.ui.activity.ClaimInfoActivity;
import com.ike.communityalliance.wedget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/3.
 */

public class HomePageLVAdapter extends BaseAdapter {
    private List<ClaimUsers> claimUsersList=new ArrayList<>();
    private List<ActivesBean> activesBeanList=new ArrayList<>();
     private  Context mContent;
    public HomePageLVAdapter(Context context) {
        this.mContent=context;
    }
  public void setData(HomePageBean homePageBean){
      claimUsersList=homePageBean.getClaimUsers();
      activesBeanList=homePageBean.getActives();
      notifyDataSetChanged();
  }

    @Override
    public int getCount() {
        return claimUsersList.size()+activesBeanList.size();
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
         ActivesBean activesBean=activesBeanList.get(position-claimUsersList.size());
         Picasso.with(mContent).load(HttpUtils.IMAGE_RUL+activesBean.getActivesImage()).into(viewHolder2.iv_main_platform_img);
        viewHolder2.tv_main_platform_title.setText(activesBean.getTitle());
        viewHolder2.tv_main_platform_address.setText(activesBean.getAddress());
     }else{
         holder= (ViewHolder) convertView.getTag();
         final ClaimUsers claimUsers=claimUsersList.get(position);
         Picasso.with(mContent).load(HttpUtils.IMAGE_RUL+claimUsers.getUserPortraitUrl()).into(holder.iv_home_item_userIcon);
         holder.tv_home_item_name.setText(claimUsers.getNickname());
         holder.tv_home_item_userNum.setText(claimUsers.getFullName());
         holder.tv_home_item_recommenderId.setText(claimUsers.getRecommendId());
         holder.tv_home_item_recommenderName.setText(claimUsers.getClaimUsersName());
         holder.tv_home_item_toClaim.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(mContent, ClaimInfoActivity.class);
                 ClaimPeopleBean claimPeopleBean=new ClaimPeopleBean(claimUsers.getId(),claimUsers.getRecommendId(),claimUsers.getNickname(),claimUsers.getUserPortraitUrl());
                 intent.putExtra("claimPeopleBean",claimPeopleBean);
                 mContent.startActivity(intent);
             }
         });
     }
        return convertView;
    }

    class ViewHolder{
        @BindView(R.id.iv_home_item_userIcon)
        CircleImageView iv_home_item_userIcon;
        @BindView(R.id.tv_home_item_name)
        TextView tv_home_item_name;
        @BindView(R.id.tv_home_item_userNum)
        TextView tv_home_item_userNum;
        @BindView(R.id.tv_home_item_recommenderId)
        TextView tv_home_item_recommenderId;
        @BindView(R.id.tv_home_item_recommenderName)
        TextView tv_home_item_recommenderName;
        @BindView(R.id.tv_home_item_toClaim)
        TextView tv_home_item_toClaim;
    }
    class ViewHolder2 {
        @BindView(R.id.iv_main_platform_img)
        ImageView iv_main_platform_img;
        @BindView(R.id.tv_main_platform_status)
        TextView tv_main_platform_status;
        @BindView(R.id.tv_main_platform_title)
        TextView tv_main_platform_title;
        @BindView(R.id.tv_main_platform_address)
        TextView tv_main_platform_address;
    }

    @Override
    public int getItemViewType(int position) {
        if(position>=claimUsersList.size()){
            return 1;//1为平台活动
        }
        return 0;//0为推荐的用户认领
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
