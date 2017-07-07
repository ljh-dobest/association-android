package com.min.threeminutestoteach.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.bean.Code;
import com.min.threeminutestoteach.bean.CommentBean;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.min.threeminutestoteach.views.CircleImageView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Min on 2017/3/9.
 */

public class AnswerRvAdapter extends RecyclerView.Adapter<AnswerRvAdapter.MyViewHolder> {

    private List<CommentBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private String userId;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }
    public interface OnLikeClickLitener {
        void onLikeClick();
    }

    private OnItemClickLitener mOnItemClickLitener;
    private OnLikeClickLitener mOnLikeClickListener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public void setOnLikeClickListener(OnLikeClickLitener mOnLikeClickListener) {
        this.mOnLikeClickListener = mOnLikeClickListener;
    }
    public AnswerRvAdapter(Context context,String userId) {
        this.mContext = context;
        this.userId=userId;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<CommentBean> data) {
        mDatas.clear();
        mDatas = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void insertData(CommentBean commentBean) {
        mDatas.add(0, commentBean);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        final CommentBean commentBean = mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_URL + commentBean.getUserPortraitUrl()).into(holder.ivAnswerItemIcon);
        holder.tvAnswerItemName.setText(commentBean.getNickname());
        holder.tvAnswerItemTime.setText(commentBean.getCommentTime());
        holder.tvAnswerItemContent.setText(commentBean.getContent());
        holder.ivAnswerItemGood.setImageResource(commentBean.getLikesStatus().equals("0")?R.drawable.unlike:R.drawable.like);
        holder.tvAnswerItemGoodNum.setText(commentBean.getLikes());
        holder.ivAnswerItemGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   HttpUtils.clickToComentLike("/commentLikes", userId, commentBean.getId() + "", (commentBean.getLikesStatus().equals("0") ? "1" : "0"), new StringCallback() {
                       @Override
                       public void onError(Call call, Exception e, int id) {
                           T.showShort(mContext,e.toString());
                       }

                       @Override
                       public void onResponse(String response, int id) {
                           Gson gson=new Gson();
                           Type type = new TypeToken<Code<Object>>() {
                           }.getType();
                           Code<Object> code = gson.fromJson(response,type);
                           switch (code.getCode()) {
                               case 200:
                                   mOnLikeClickListener.onLikeClick();
                                   break;
                               case 0:
                                   T.showShort(mContext,"点赞失败");
                                   break;
                           }
                       }
                   });
                       }
                   });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.answer_rv_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }




   class MyViewHolder  extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_answer_item_icon)
        CircleImageView ivAnswerItemIcon;
        @BindView(R.id.tv_answer_item_name)
        TextView tvAnswerItemName;
        @BindView(R.id.iv_answer_item_good)
        ImageView ivAnswerItemGood;
        @BindView(R.id.tv_answer_item_goodNum)
        TextView tvAnswerItemGoodNum;
        @BindView(R.id.tv_answer_item_content)
        TextView tvAnswerItemContent;
        @BindView(R.id.tv_answer_item_time)
        TextView tvAnswerItemTime;

         MyViewHolder(View view) {
             super(view);
            ButterKnife.bind(this, view);
        }
    }
}
