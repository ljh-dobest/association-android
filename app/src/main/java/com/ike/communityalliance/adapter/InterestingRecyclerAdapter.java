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
import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.InterestGroupBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.wedget.image.SelectableRoundedImageView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.rong.imageloader.core.ImageLoader;
import okhttp3.Call;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Min on 2017/3/9.
 */

public class InterestingRecyclerAdapter extends RecyclerView.Adapter<InterestingRecyclerAdapter.MyViewHolder> implements View.OnClickListener {

    private List<InterestGroupBean> mDatas=new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    private int CurPosition=0;

    public InterestingRecyclerAdapter(Context context){
        this. mContext=context;
        inflater= LayoutInflater. from(mContext);
    }
    public void setmDatas(List<InterestGroupBean> data){
        mDatas.clear();
        mDatas=data;
        notifyDataSetChanged();
    }
    public void clearData(){
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        InterestGroupBean bean = mDatas.get(position);
        if(bean.getGroupPortraitUrl()==null){
            holder.iv_inteset_rv_item_header.setImageResource(R.mipmap.ic_launcher);
        }else{
            ImageLoader.getInstance().displayImage(HttpUtils.IMAGE_RUL+bean.getGroupPortraitUrl(),holder.iv_inteset_rv_item_header);
           // Picasso.with(mContext).load(bean.getGroupPortraitUri()).into(holder.iv_inteset_rv_item_header);
        }
        holder.iv_inteset_rv_item_groupName.setText(bean.getGroupName());
        holder.iv_inteset_rv_item_groupNum.setText("群人数:"+bean.getGroupUserNumber());
        holder.btn_inteset_join.setTag(position);
        holder.btn_inteset_join.setOnClickListener(this);
    }

    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.intesest_rv_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }
/**
 * 申请加入兴趣组
* */
    @Override
    public void onClick(View v) {
        final String groupId=mDatas.get((Integer) v.getTag()).getGroupId();
        final EditText editText = new EditText(mContext);
        new AlertDialog.Builder(mContext)
                .setTitle("验证信息")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String provingMessage = editText.getText().toString();
                        LoadDialog.show(mContext);
                            sendGroupRequest(provingMessage,groupId);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
    private void sendGroupRequest(String message, String groupId) {
        String myUserId=mContext.getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
        HttpUtils.sendPostRequest("/addfriendRequest","1", myUserId,groupId, message, new StringCallback() {
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
                        T.showShort(mContext, "请求成功，请耐心等待审核");
                        break;
                    case 11:
                        T.showShort(mContext, "你已加入该群组");
                        break;
                    case 0:
                        T.showShort(mContext,"群组添加失败");
                        break;
                    default:
                        T.showShort(mContext, "error");
                        break;
                }
                LoadDialog.dismiss(mContext);
            }
        });
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
         SelectableRoundedImageView iv_inteset_rv_item_header;
        TextView iv_inteset_rv_item_groupName;
        TextView iv_inteset_rv_item_groupNum;
        Button btn_inteset_join;
        public MyViewHolder(View view) {
            super(view);
            iv_inteset_rv_item_header= (SelectableRoundedImageView) view.findViewById(R.id.iv_inteset_rv_item_header);
            iv_inteset_rv_item_groupName=(TextView) view.findViewById(R.id.iv_inteset_rv_item_groupName);
            iv_inteset_rv_item_groupNum=(TextView) view.findViewById(R.id.iv_inteset_rv_item_groupNum);
            btn_inteset_join= (Button) view.findViewById(R.id.btn_inteset_join);
        }
    }
}
