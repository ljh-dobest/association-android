package com.ike.communityalliance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.ike.communityalliance.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.ui.RxGalleryListener;
import cn.finalteam.rxgalleryfinal.ui.base.IMultiImageCheckedListener;

/**
 * Created by Min on 2016/12/1.
 */

public class SendShareFriendsGVAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<String> list=new ArrayList<>();
    private LayoutInflater inflater;

    public SendShareFriendsGVAdapter(Context context ) {
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
        if (position == getCount() - 1) {
            holder.iv_sendshareFriend_img.setImageResource(R.mipmap.jy_drltsz_btn_addperson);
            holder.iv_sendshareFriend_img.setOnClickListener(this);
            holder.iv_sendshareFriend_img.setTag(position);
        } else {
            Picasso.with(context).load(new File(list.get(position))).into(holder.iv_sendshareFriend_img);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if((int)v.getTag()==getCount()-1) {
            //打开图片选择
            RxGalleryFinal
                    .with(context)
                    .image()
                    .multiple()
                    .maxSize(9)
                    .imageLoader(ImageLoaderType.PICASSO)
                    .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {
                        @Override
                        protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                            ArrayList<String> path = new ArrayList<String>();
                            for (MediaBean mediaBean : imageMultipleResultEvent.getResult()) {
                                path.add(mediaBean.getOriginalPath());
                            }
                            setImgList(path);
                        }

                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                            Toast.makeText(context, "OVER", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .openGallery();
            RxGalleryListener.getInstance().setMultiImageCheckedListener(new IMultiImageCheckedListener() {
                @Override
                public void selectedImg(Object t, boolean isChecked) {
                    //这个主要点击或者按到就会触发，所以不建议在这里进行Toast
                      /*  Toast.makeText(getBaseContext(),"->"+isChecked,Toast.LENGTH_SHORT).show();*/
                }

                @Override
                public void selectedImgMax(Object t, boolean isChecked, int maxSize) {
                    Toast.makeText(context, "你最多只能选择" + maxSize + "张图片", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ViewHolder{
      ImageView iv_sendshareFriend_img;
    }
}
