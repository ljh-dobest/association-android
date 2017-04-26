package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.communityalliance.R;
import com.ike.communityalliance.ui.activity.CommentPopuWindow;
import com.ike.communityalliance.bean.CommentBean;
import com.ike.communityalliance.bean.ReplyUserBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.image.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Min on 2017/3/9.
 */

public class ShareFriendsContentRvAdapter extends RecyclerView.Adapter<ShareFriendsContentRvAdapter.MyViewHolder> implements View.OnClickListener, ShareFriendsContentItemRvAdapter.OnItemClickLitener {

    private List<CommentBean> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private CommentPopuWindow commentPopuWindow;
    /**
     * 一级评论
     * @param v
     */
    @Override
    public void onClick(View v) {
        CommentBean comment=mDatas.get((Integer) v.getTag());
       String commentId=comment.getId()+"";
       commentPopuWindow.setCommentId(commentId);
        commentPopuWindow.setCommentArticle(false);
        commentPopuWindow.setHint(comment.getNickname());
        commentPopuWindow.showPopupWindow();
    }

    /**
     * 二级评论
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position, ReplyUserBean replyUserBean) {
        commentPopuWindow.setCommentId(replyUserBean.getId());
        commentPopuWindow.setCommentArticle(false);
        commentPopuWindow.setHint(replyUserBean.getNickname());
        commentPopuWindow.showPopupWindow();
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private ShareFriendsContentRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(ShareFriendsContentRvAdapter .OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public ShareFriendsContentRvAdapter(Context context,CommentPopuWindow commentPopuWindow){
        this. mContext=context;
        this.commentPopuWindow=commentPopuWindow;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<CommentBean> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(CommentBean comment){
        mDatas.add(0,comment);
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
        CommentBean commentBean=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+commentBean.getUserPortraitUrl()).error(R.drawable.rc_image_error).into(holder.iv_shareFriendContent_item_icon);
        holder.tv_shareFriendContent_item_name.setText(commentBean.getNickname());
        holder.tv_shareFriendContent_item_time.setText(commentBean.getCommentTime());
        holder.tv_shareFriendContent_item_content.setText(commentBean.getContent());
        holder.iv_shareFriendContent_item_todicu.setOnClickListener(this);
        holder.iv_shareFriendContent_item_todicu.setTag(position);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        holder.rv_shareFriendContent_item_replayUser.setLayoutManager(layoutManager);
        if(commentBean.getReplyUsers()!=null){
            ShareFriendsContentItemRvAdapter itemRvAdapter = new ShareFriendsContentItemRvAdapter(mContext,commentBean.getReplyUsers());
            itemRvAdapter.setOnItemClickLitener(this);
            holder.rv_shareFriendContent_item_replayUser.setAdapter(itemRvAdapter);
        }
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.share_friends_content_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shareFriendContent_item_icon)
        CircleImageView iv_shareFriendContent_item_icon;
        @BindView(R.id.tv_shareFriendContent_item_name)
        TextView tv_shareFriendContent_item_name;
        @BindView(R.id.tv_shareFriendContent_item_time)
        TextView tv_shareFriendContent_item_time;
        @BindView(R.id.tv_shareFriendContent_item_content)
        TextView tv_shareFriendContent_item_content;
         @BindView(R.id.rv_shareFriendContent_item_replayUser)
         RecyclerView rv_shareFriendContent_item_replayUser;
        @BindView(R.id.iv_shareFriendContent_item_todicu)
        ImageView iv_shareFriendContent_item_todicu;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
