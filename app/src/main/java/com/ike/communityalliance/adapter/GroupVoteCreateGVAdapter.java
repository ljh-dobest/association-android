package com.ike.communityalliance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ike.communityalliance.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

/**
 * Created by Min on 2016/12/1.
 */

public class GroupVoteCreateGVAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<String> list=new ArrayList<>();
    private LayoutInflater inflater;
    private boolean isLocalAdd=false;
    public GroupVoteCreateGVAdapter(Context context) {
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
            View view = inflater.inflate(R.layout.send_friends_gv_item, null);
            holder = new ViewHolder();
            holder.iv_sendshareFriend_img = (ImageView) view.findViewById(R.id.iv_sendshareFriend_img);
//            convertView.setTag(holder);
//            holder = (ViewHolder) convertView.getTag();
        //添加图片
        if (list.size()==0) {
            holder.iv_sendshareFriend_img.setImageResource(R.mipmap.jy_drltsz_btn_addperson);
            holder.iv_sendshareFriend_img.setOnClickListener(this);
        } else {
            if(position<1){
                Picasso.with(context).load(new File(list.get(position))).into(holder.iv_sendshareFriend_img);
                holder.iv_sendshareFriend_img.setOnClickListener(this);
            }
        }
        return view;
    }

    @Override
    public void onClick(View v) {
            //自定义方法的单选
            RxGalleryFinal
                    .with(context)
                    .image()
                    .radio()
                    .crop()
                    .imageLoader(ImageLoaderType.PICASSO)
                    .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                        @Override
                        protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                            ArrayList<String> path = new ArrayList<String>();
                           path.add(imageRadioResultEvent.getResult().getOriginalPath());
                            setImgList(path);

                        }
                    })
                    .openGallery();
    }

    class ViewHolder{
      ImageView iv_sendshareFriend_img;
    }
}
