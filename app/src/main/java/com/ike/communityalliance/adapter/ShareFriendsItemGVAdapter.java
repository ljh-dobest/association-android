package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.ViewPagerActivity;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Min on 2016/12/1.
 */

public class ShareFriendsItemGVAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<String> list=new ArrayList<>();
    private LayoutInflater inflater;

    public ShareFriendsItemGVAdapter(Context context ) {
        this.context = context;
        this.inflater=inflater.from(context);
    }
public void setImgList(List<String> list){
    this.list.clear();
    this.list.addAll(list);
    notifyDataSetChanged();
}
public List<String> getImaList(){
    return list;
}

    @Override
    public int getCount() {
            return list.size() + 1;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
            View view = inflater.inflate(R.layout.share_friends_rv_images_item, null);
            holder = new ViewHolder();
            holder.iv_shareFriend_item_images = (ImageView) view.findViewById(R.id.iv_shareFriend_item_images);
        //添加图片
            Picasso.with(context).load(HttpUtils.IMAGE_RUL+list.get(position)).into(holder.iv_shareFriend_item_images);
        holder.iv_shareFriend_item_images.setOnClickListener(this);
        holder.iv_shareFriend_item_images.setTag(position);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(context, ViewPagerActivity.class);
        intent.putExtra("position",(int)v.getTag());
        intent.putStringArrayListExtra("imgList", (ArrayList<String>) list);
        context.startActivity(intent);
    }

    class ViewHolder{
      ImageView iv_shareFriend_item_images;
    }
}
