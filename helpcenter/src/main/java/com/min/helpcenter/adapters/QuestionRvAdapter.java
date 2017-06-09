package com.min.helpcenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.R;
import com.min.helpcenter.bean.AnswersBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.utils.T;
import com.min.helpcenter.views.CircleImageView;
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

public class QuestionRvAdapter extends RecyclerView.Adapter<QuestionRvAdapter.MyViewHolder> implements View.OnClickListener {

    private List<AnswersBean> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private String userId;
private SucceedToLikeListener listener;

    public void setSucceedToLikeListener(SucceedToLikeListener listener) {
        this.listener = listener;
    }

    public interface  SucceedToLikeListener{
        void succeedTolike();
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }

    private QuestionRvAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(QuestionRvAdapter .OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public QuestionRvAdapter(Context context,String userId){
        this. mContext=context;
        this.userId=userId;
        inflater= LayoutInflater.from(mContext);
    }
    public void setmDatas(List<AnswersBean> data){
        mDatas=data;
        notifyDataSetChanged();
    }

    public List<AnswersBean> getmDatas() {
        return mDatas;
    }

    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }
    public void insertData(AnswersBean answer){
        mDatas.add(0,answer);
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
         AnswersBean answersBean=mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_URL+answersBean.getUserPortraitUrl()).into(holder.iv_question_item_icon);
       holder.tv_question_item_name.setText(answersBean.getNickname());
       holder.tv_question_item_time.setText(answersBean.getTime());
       holder.tv_question_item_content.setText(answersBean.getContent());
        holder.iv_question_item_good.setImageResource(answersBean.getLikesStatus().equals("0")?R.drawable.ungood:R.drawable.good);
        holder.iv_question_item_good.setOnClickListener(this);
        holder.iv_question_item_good.setTag(position);
        holder.tv_question_item_goodNum.setText(answersBean.getLikes());
        holder.tv_question_item_discuNum.setText(answersBean.getCommentNumber());
    }
    @Override
    public void onClick(View v) {
        final int positon= (int) v.getTag();
        final AnswersBean answersBean= mDatas.get(positon);
        if(answersBean.getLikesStatus().equals("1")){
            return;
        }
        HttpUtils.postUserPraise("/userPraise", userId, answersBean.getId(), "4", "1", new StringCallback() {
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
                if(code.getCode()==200){
                   listener.succeedTolike();
                }else{
                    T.showShort(mContext,"点赞失败");
                }
            }
        });
    }


    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.question_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_question_item_icon)
        CircleImageView iv_question_item_icon;
        @BindView(R.id.tv_question_item_name)
        TextView tv_question_item_name;
        @BindView(R.id.tv_question_item_time)
        TextView tv_question_item_time;
        @BindView(R.id.tv_question_item_content)
        TextView tv_question_item_content;
        @BindView(R.id.iv_question_item_good)
        ImageView iv_question_item_good;
        @BindView(R.id.tv_question_item_goodNum)
        TextView tv_question_item_goodNum;
        @BindView(R.id.iv_question_item_discu)
        ImageView iv_question_item_discu;
        @BindView(R.id.tv_question_item_discuNum)
        TextView tv_question_item_discuNum;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
