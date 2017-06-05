package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.UserOrGroupBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.CircleImageView;
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
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

public class SearchFriendRvAdapter extends RecyclerView.Adapter<SearchFriendRvAdapter.MyViewHolder> {

    private List<UserOrGroupBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private String useId;
    private String nickName;
    private String keyword;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**设置当前查找输入的关键字
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchFriendRvAdapter(Context context, String useId, String nickName) {
        this.mContext = context;
        this.useId=useId;
        this.nickName=nickName;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<UserOrGroupBean> data) {
        mDatas.clear();
        mDatas = data;
        notifyDataSetChanged();
    }

    public void clearData() {
        mDatas.clear();
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
        final UserOrGroupBean userOrGroupBean = mDatas.get(position);
        if(userOrGroupBean.getStatus().equals("0")){
            //个人
            holder.ivPossibleUnderstandItemSex.setVisibility(View.VISIBLE);
            holder.tvPossibleUnderstandItemSameNumber.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+userOrGroupBean.getUserPortraitUrl()).into(holder.ivPossibleUnderstandItemHeader);
        holder.ivPossibleUnderstandItemSex.setImageResource(userOrGroupBean.getSex().equals("1")?R.drawable.mine_man:R.drawable.mine_women);
        holder.tvPossibleUnderstandItemName.setText(getKeywordColor(keyword,userOrGroupBean.getNickname(),mContext));
        holder.tvPossibleUnderstandItemNumber.setText(userOrGroupBean.getNumberId());
        holder.tvPossibleUnderstandItemSameNumber.setText(userOrGroupBean.getCount()+"位共同好友");
            holder.btnPossibleUnderstandJoin.setText("+ 好友");
            if(userOrGroupBean.getRelationship()==null){
                holder.tvPossibleUnderstandItemRelation.setVisibility(View.GONE);
            }else{
                holder.tvPossibleUnderstandItemRelation.setVisibility(View.VISIBLE);
                holder.tvPossibleUnderstandItemRelation.setText(getRelationship(userOrGroupBean.getRelationship()));
            }
        }else{
            //群组
            Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+userOrGroupBean.getGroupPortraitUrl()).into(holder.ivPossibleUnderstandItemHeader);
            holder.tvPossibleUnderstandItemName.setText(getKeywordColor(keyword,userOrGroupBean.getGroupName(),mContext));
            holder.ivPossibleUnderstandItemSex.setVisibility(View.GONE);
            holder.tvPossibleUnderstandItemRelation.setVisibility(View.GONE);
            holder.tvPossibleUnderstandItemSameNumber.setVisibility(View.GONE);
            holder.tvPossibleUnderstandItemNumber.setText("群人数:"+userOrGroupBean.getGroupUserNumber()+"人");
            holder.btnPossibleUnderstandJoin.setText("申请加入");
        }
       holder.btnPossibleUnderstandJoin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final EditText editText = new EditText(mContext);
                            new AlertDialog.Builder(mContext)
                                    .setTitle("验证信息")
                                    .setView(editText)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            String provingMessage = editText.getText().toString();
                                            LoadDialog.show(mContext);
                                            if(userOrGroupBean.getStatus().equals("0")){
                                                sendFriendRequest(userOrGroupBean.getStatus(),useId,nickName,userOrGroupBean.getUserId(),provingMessage);
                                            }else{
                                                sendGroupRequest(userOrGroupBean.getStatus(),useId,nickName,userOrGroupBean.getGroupId(),provingMessage);
                                            }

                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
           }
       });
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.possible_understand_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_possible_understand_item_header)
        CircleImageView ivPossibleUnderstandItemHeader;
        @BindView(R.id.tv_possible_understand_item_name)
        TextView tvPossibleUnderstandItemName;
        @BindView(R.id.iv_possible_understand_item_sex)
        ImageView ivPossibleUnderstandItemSex;
        @BindView(R.id.tv_possible_understand_item_relation)
        TextView tvPossibleUnderstandItemRelation;
        @BindView(R.id.tv_possible_understand_item_number)
        TextView tvPossibleUnderstandItemNumber;
        @BindView(R.id.tv_possible_understand_item_sameNumber)
        TextView tvPossibleUnderstandItemSameNumber;
        @BindView(R.id.btn_possible_understand_join)
        Button btnPossibleUnderstandJoin;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    private void sendFriendRequest(String status,String userId,String nickname,String friendId,String message) {
        HttpUtils.sendPostRequest("/addfriendRequest",status, userId, nickname, friendId, message, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/addfriendRequest-----"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> baseBean = gson.fromJson(response, type);
                int code = baseBean.getCode();
                switch (code) {
                    case 200:
                        T.showShort(mContext, "请求成功，请耐心等待对方审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你们已是好友");
                        break;
                    case 0:
                        T.showShort(mContext,"好友添加失败");
                        break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }
    private void sendGroupRequest(String status,String userId,String nickname,String groupId,String message) {
        HttpUtils.sendPostRequest("/addfriendRequest", status, userId, groupId, message, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext, "/addfriendRequest-----" + e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson = new Gson();
                Type type = new TypeToken<Code<BaseBean>>() {
                }.getType();
                Code<BaseBean> baseBean = gson.fromJson(response, type);
                int code = baseBean.getCode();
                switch (code) {
                    case 200:
                        T.showShort(mContext, "请求成功，请耐心等待审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你已加入该群组");
                        break;
                    case 0:
                        T.showShort(mContext, "群组添加失败");
                        break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }
    /**
     * 关键字变色
     */
    public SpannableStringBuilder getKeywordColor(String keyword, String strtext, Context context) {
        String docInfo = strtext;
        int keywordIndex = strtext.indexOf(keyword);
        SpannableStringBuilder style = new SpannableStringBuilder(docInfo);
        while (keywordIndex != -1) {
            /**
             * 关键字颜色改变
             */
            style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.red)), keywordIndex, keywordIndex + keyword.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            int tempkeywordTempIndex = keywordIndex + keyword.length();
            strtext = docInfo.substring(tempkeywordTempIndex, docInfo.length());
            keywordIndex = strtext.indexOf(keyword);
            if (keywordIndex != -1) {
                keywordIndex = keywordIndex + tempkeywordTempIndex;
            }
        }
        return style;
    }
    private String getRelationship(String relationship) {
        String relation="";
        switch (relationship) {
            case "1":
                relation="亲人";
                break;
            case "2":
                relation="同事";
                break;
            case "3":
                relation="校友";
                break;
            case "4":
                relation="同乡";
                break;
        }
        return relation;
    }
}
