package com.ike.communityalliance.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.PossiblePeopleBean;
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

public class SearchPossiblePeopleRvAdapter extends RecyclerView.Adapter<SearchPossiblePeopleRvAdapter.MyViewHolder> {

    private List<PossiblePeopleBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private String useId;
    private String nickName;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public SearchPossiblePeopleRvAdapter(Context context, String useId, String nickName) {
        this.mContext = context;
        this.useId = useId;
        this.nickName = nickName;
        inflater = LayoutInflater.from(mContext);
    }

    public void setmDatas(List<PossiblePeopleBean> data) {
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
        final PossiblePeopleBean possiblePeopleBean = mDatas.get(position);
        Picasso.with(mContext).load(HttpUtils.IMAGE_RUL + possiblePeopleBean.getUserPortraitUrl()).into(holder.ivPossibleUnderstandItem2Header);
        holder.tvPossibleUnderstandItem2Name.setText(possiblePeopleBean.getNickname());
        holder.tvPossibleUnderstandItem2Number.setText(possiblePeopleBean.getNumberId());
        holder.tvPossibleUnderstandItem2SameNumber.setText(possiblePeopleBean.getCount() + "位共同好友");
        if(possiblePeopleBean.getRelationship()==null){
            holder.tvPossibleUnderstandItem2Relation.setVisibility(View.GONE);
        }else{
            holder.tvPossibleUnderstandItem2Relation.setText(getRelationship(possiblePeopleBean.getRelationship()));
        }
        holder.btnPossibleUnderstandItem2Join.setOnClickListener(new View.OnClickListener() {
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
                                sendFriendRequest("0", useId, nickName, possiblePeopleBean.getUserId(), provingMessage);
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
        View view = inflater.inflate(R.layout.possible_understand_item2, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_possible_understand_item2_header)
        CircleImageView ivPossibleUnderstandItem2Header;
        @BindView(R.id.tv_possible_understand_item2_name)
        TextView tvPossibleUnderstandItem2Name;
        @BindView(R.id.tv_possible_understand_item2_number)
        TextView tvPossibleUnderstandItem2Number;
        @BindView(R.id.tv_possible_understand_item2_sameNumber)
        TextView tvPossibleUnderstandItem2SameNumber;
        @BindView(R.id.btn_possible_understand_item2_join)
        Button btnPossibleUnderstandItem2Join;
        @BindView(R.id.tv_possible_understand_item2_relation)
        TextView tvPossibleUnderstandItem2Relation;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
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
    private void sendFriendRequest(String status, String userId, String nickname, String friendId, String message) {
        HttpUtils.sendPostRequest("/addfriendRequest", status, userId, nickname, friendId, message, new StringCallback() {
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
                        T.showShort(mContext, "请求成功，请耐心等待对方审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你们已是好友");
                        break;
                    case 0:
                        T.showShort(mContext, "好友添加失败");
                        break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }

}
