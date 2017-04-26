package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.ViewPagerActivity;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> implements View.OnClickListener {
    private List<TalkTalkBean> list;
    private Context context;
    private String userId;
    private SharedPreferences sp;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public SimpleAdapter(List<TalkTalkBean> list, Context context) {
        this.list = list;
        this.context=context;
        sp=context.getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
    }

    public void addMoreData(List<TalkTalkBean> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(final SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        final TalkTalkBean talkTalkBean = list.get(position);

        holder.rv_shareFriend_item_images.setLayoutManager(new GridLayoutManager(context,3));
        holder.rv_shareFriend_item_images.setAdapter(new CommonAdapter<String>(context, R.layout.share_friends_rv_images_item, talkTalkBean.getImages())
        {
            @Override
            public void convert(ViewHolder rvholder, String s, final int pos) {
             ImageView view=rvholder.getView(R.id.iv_shareFriend_item_images);
                Picasso.with(context).load(HttpUtils.IMAGE_RUL+s).error(R.drawable.rc_image_error).into(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, ViewPagerActivity.class);
                        intent.putExtra("position",pos);
                        intent.putStringArrayListExtra("imgList", (ArrayList<String>) talkTalkBean.getImages());
                        context.startActivity(intent);
                    }
                });
            }
        });
        Picasso.with(context).load(HttpUtils.IMAGE_RUL+list.get(position).getUserPortraitUrl()).error(R.drawable.rc_image_error).into(holder.iv_shareFriend_item_icon);
        holder.tv_shareFriend_item_name.setText(list.get(position).getNickname());
        holder.tv_shareFriend_item_time.setText(list.get(position).getReleaseTime());
        holder.tv_shareFriend_item_title.setText(list.get(position).getContent());
        holder.tv_shareFriends_goodNum.setText(list.get(position).getLikedNumber()+"");
        holder.tv_shareFriends_discuNum.setText(list.get(position).getCommentNumber()+"");
        if(list.get(position).getLikeStatus()==1){
          Picasso.with(context).load(R.mipmap.good).into(holder.tv_shareFriends_good);
        }else{
            Picasso.with(context).load(R.mipmap.ungood).into(holder.tv_shareFriends_good);
        }
        holder.tv_shareFriends_good.setOnClickListener(this);
        holder.tv_shareFriends_good.setTag(position);
    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        return list.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(List<TalkTalkBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.share_friends_rv_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    public void insert(TalkTalkBean person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    @Override
    public void onClick(View v) {
        int positon= (int) v.getTag();
        final TalkTalkBean talkTalkBean= list.get(positon);
       if(talkTalkBean.getLikeStatus()==1){
           return;
       }
        HttpUtils.postTalkTalkLike("/userPraise", talkTalkBean.getId(), userId, "2","1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(context,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                if (code.getCode()==200){
                    int likeNum=talkTalkBean.getLikedNumber();
                    talkTalkBean.setLikedNumber(likeNum+1);
                    talkTalkBean.setLikeStatus(1);
                    notifyDataSetChanged();
                }else{
                   T.showShort(context,"已点赞");
                }
            }
        });
    }



    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_shareFriend_item_icon)
        XCRoundRectImageView iv_shareFriend_item_icon;
        @BindView(R.id.tv_shareFriend_item_name)
        TextView tv_shareFriend_item_name;
        @BindView(R.id.tv_shareFriend_item_time)
        TextView tv_shareFriend_item_time;
        @BindView(R.id.tv_shareFriend_item_title)
        TextView tv_shareFriend_item_title;
        @BindView(R.id.rv_shareFriend_item_images)
        RecyclerView rv_shareFriend_item_images;
        @BindView(R.id.tv_shareFriends_good)
        ImageView tv_shareFriends_good;
        @BindView(R.id.tv_shareFriends_goodNum)
        TextView tv_shareFriends_goodNum;
        @BindView(R.id.tv_shareFriends_discu)
        ImageView tv_shareFriends_discu;
        @BindView(R.id.tv_shareFriends_discuNum)
        TextView tv_shareFriends_discuNum;

        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                ButterKnife.bind(this,itemView);
            }
        }
    }

    public List<TalkTalkBean> getList() {
        return list;
    }

    public TalkTalkBean getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}