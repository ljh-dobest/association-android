package com.ike.communityalliance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.MineCliamMsgBean;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.mylibrary.util.T;
import com.makeramen.roundedimageview.RoundedImageView;
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

//public class MineClaimMsgRvAdapter extends RecyclerView.Adapter{
//    public static final int ONE_ITEM = 1;
//    public static final int TWO_ITEM = 2;
//    private List<MineCliamMsgBean> mDatas=new ArrayList<>();
//    private Context mContext;
//    private LayoutInflater inflater;
//    private String userId;
//    public void setUserId(String userId){
//        this.userId=userId;
//    }
//
//    public interface OnItemClickLitener
//    {
//        void onItemClick(View view, int position);
//    }
//    public interface OnfinishComfirLitener
//    {
//        void finishComfir();
//    }
//    private MineClaimMsgRvAdapter.OnItemClickLitener mOnItemClickLitener;
//  private  OnfinishComfirLitener mOnFinishComfirListener;
//    public void setOnItemClickLitener(MineClaimMsgRvAdapter.OnItemClickLitener mOnItemClickLitener)
//    {
//        this.mOnItemClickLitener = mOnItemClickLitener;
//    }
//    public void setOnFinishComfirListener(MineClaimMsgRvAdapter.OnfinishComfirLitener mOnFinishComfirListener)
//    {
//        this.mOnFinishComfirListener = mOnFinishComfirListener;
//    }
//    public MineClaimMsgRvAdapter(Context context){
//        this. mContext=context;
//        inflater= LayoutInflater. from(mContext);
//    }
//    public void setmDatas(List<MineCliamMsgBean> data){
//        mDatas.clear();
//        mDatas=data;
//        notifyDataSetChanged();
//    }
//    public void clearData(){
//        mDatas.clear();
//        notifyDataSetChanged();
//    }
//    @Override
//    public int getItemCount() {
//        return mDatas.size();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder holder = null;
//               if(ONE_ITEM == viewType){
//                   View view = inflater.inflate(R.layout.mine_claim_msg_item1,parent, false);
//                    holder= new MyViewHolder(view);
//               }else{
//                   View view2 = inflater.inflate(R.layout.mine_claim_msg_item2,parent, false);
//                   holder= new MyViewHolder2(view2);
//               }
//               return  holder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//                if(holder instanceof MyViewHolder2){
//                    final MineCliamMsgBean mineCliamMsgBean=mDatas.get(position);
//                    final MyViewHolder2 holder2= (MyViewHolder2) holder;
//                    Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+mineCliamMsgBean.getUserPortraitUrl()).into(holder2.iv_mine_claim_msg_item2_userHeader);
//                    holder2.tv_mine_claim_msg_item2name.setText(mineCliamMsgBean.getNickname());
//                    holder2.tv_mine_claim_msg_item2_content.setText("尝试认领你，是否让"+mineCliamMsgBean.getNickname()+"认领成功?");
//                    holder2.iv_mine_claim_open.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            holder2.ll_mine_claim_msg_comfir.setVisibility(holder2.ll_mine_claim_msg_comfir.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
//                            holder2.iv_mine_claim_open.setImageResource(holder2.ll_mine_claim_msg_comfir.getVisibility()==View.VISIBLE?R.mipmap.mine_claim_msg_up:R.mipmap.mine_cliam_msg_down);
//                        }
//                    });
//                    holder2.btn_mine_claim_msg_yes.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            postClaimComfir(userId,mineCliamMsgBean.getUserId(),"1");
//                        }
//                    });
//                    holder2.btn_mine_claim_msg_no.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            postClaimComfir(userId,mineCliamMsgBean.getUserId(),"0");
//                        }
//                    });
//                }else{
//                    MineCliamMsgBean mineCliamMsgBean1=mDatas.get(position);
//                    MyViewHolder holder1= (MyViewHolder) holder;
//                    Picasso.with(mContext).load(HttpUtils.IMAGE_RUL+mineCliamMsgBean1.getUserPortraitUrl()).into(holder1.iv_mine_claim_msg_userHeader);
//                    holder1.tv_mine_claim_msg_name.setText(mineCliamMsgBean1.getNickname());
//                    holder1.tv_mine_claim_msg_time.setText(mineCliamMsgBean1.getClaimTime());
//                }
//    }
//
//    private void postClaimComfir(String userId, String claimUserId, final String status) {
//         HttpUtils.postClaimMsg("/claimConfirm", userId, claimUserId, status, new StringCallback() {
//             @Override
//             public void onError(Call call, Exception e, int id) {
//                 T.showShort(mContext,e.toString());
//             }
//
//             @Override
//             public void onResponse(String response, int id) {
//                 Gson gson = new Gson();
//                 Type type = new TypeToken<Code<Object>>() {
//                 }.getType();
//                 Code<Object> code = gson.fromJson(response, type);
//                 switch (code.getCode()) {
//                     case  200:
//                         if(status.equals("1")){
//                             T.showShort(mContext,"确认成功");
//                         }else{
//                             T.showShort(mContext,"拒绝认领成功");
//                         }
//                         mOnFinishComfirListener.finishComfir();
//                         break;
//                     case 0:
//                         T.showShort(mContext,"操作失败");
//                         break;
//                     case 100:
//                         T.showShort(mContext,"您已被认领");
//                         break;
//                     case 102:
//                         T.showShort(mContext,"用户未填写认领问题");
//                         break;
//                 }
//             }
//         });
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        MineCliamMsgBean mineCliamMsgBean=mDatas.get(position);
//       if(mineCliamMsgBean.getStatus().equals("0")){
//           return TWO_ITEM;
//       }else{
//           return ONE_ITEM;
//       }
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_mine_claim_msg_userHeader)
//        RoundedImageView iv_mine_claim_msg_userHeader;
//        @BindView(R.id.tv_mine_claim_msg_name)
//        TextView tv_mine_claim_msg_name;
//        @BindView(R.id.tv_mine_claim_msg_time)
//        TextView tv_mine_claim_msg_time;
//        public MyViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this,view);
//        }
//    }
//    class MyViewHolder2 extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_mine_claim_msg_item2_userHeader)
//        RoundedImageView iv_mine_claim_msg_item2_userHeader;
//        @BindView(R.id.tv_mine_claim_msg_item2_name)
//        TextView tv_mine_claim_msg_item2name;
//        @BindView(R.id.tv_mine_claim_msg_item2_content)
//        TextView tv_mine_claim_msg_item2_content;
//        @BindView(R.id.ll_mine_claim_msg_comfir)
//        LinearLayout ll_mine_claim_msg_comfir;
//        @BindView(R.id.btn_mine_claim_msg_yes)
//        Button btn_mine_claim_msg_yes;
//        @BindView(R.id.btn_mine_claim_msg_no)
//        Button btn_mine_claim_msg_no;
//        @BindView(R.id.iv_mine_claim_open)
//        ImageView iv_mine_claim_open;
//
//        public MyViewHolder2(View view) {
//            super(view);
//            ButterKnife.bind(this,view);
//        }
//    }
//}
public class MineClaimMsgRvAdapter extends BaseAdapter{
    public static final int ONE_ITEM = 1;
    public static final int TWO_ITEM = 2;
    private List<MineCliamMsgBean> mData=new ArrayList<>();
    private  Context mContent;
    private String userId;
    public MineClaimMsgRvAdapter(Context context) {
        this.mContent=context;
    }
        public void setUserId(String userId){
        this.userId=userId;
    }
    public void setData(List<MineCliamMsgBean> mData){
        this.mData=mData;
        notifyDataSetChanged();
    }
    public interface OnfinishComfirLitener
    {
        void finishComfir();
    }
  private  OnfinishComfirLitener mOnFinishComfirListener;
    public void setOnFinishComfirListener(MineClaimMsgRvAdapter.OnfinishComfirLitener mOnFinishComfirListener)
    {
        this.mOnFinishComfirListener = mOnFinishComfirListener;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        MyViewHolder2 viewHolder2=null;
        int type=getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case ONE_ITEM:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_claim_msg_item1, parent, false);
                    holder = new MyViewHolder(convertView);
                    convertView.setTag(holder);
                    break;
                case TWO_ITEM:
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mine_claim_msg_item2, parent, false);
                    viewHolder2 = new MyViewHolder2(convertView);
                    convertView.setTag(viewHolder2);
                    break;
            }
        }
        if(type==ONE_ITEM){
            MyViewHolder holder1= (MyViewHolder)convertView.getTag();
            MineCliamMsgBean mineCliamMsgBean=mData.get(position);
            Picasso.with(mContent).load(HttpUtils.IMAGE_RUL+mineCliamMsgBean.getUserPortraitUrl()).into(holder1.iv_mine_claim_msg_userHeader);
            holder1.tv_mine_claim_msg_name.setText(mineCliamMsgBean.getNickname());
            holder1.tv_mine_claim_msg_time.setText(mineCliamMsgBean.getClaimTime());
        }else{
            final MyViewHolder2 holder2= (MyViewHolder2)convertView.getTag();
            final MineCliamMsgBean mineCliamMsgBean=mData.get(position);
            Picasso.with(mContent).load(HttpUtils.IMAGE_RUL+mineCliamMsgBean.getUserPortraitUrl()).into(holder2.iv_mine_claim_msg_item2_userHeader);
            holder2.tv_mine_claim_msg_item2name.setText(mineCliamMsgBean.getNickname());
            holder2.tv_mine_claim_msg_item2_content.setText("尝试认领你，是否让"+mineCliamMsgBean.getNickname()+"认领成功?");
            holder2.iv_mine_claim_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder2.ll_mine_claim_msg_comfir.setVisibility(holder2.ll_mine_claim_msg_comfir.getVisibility()==View.VISIBLE?View.GONE:View.VISIBLE);
                    holder2.iv_mine_claim_open.setImageResource(holder2.ll_mine_claim_msg_comfir.getVisibility()==View.VISIBLE?R.mipmap.mine_claim_msg_up:R.mipmap.mine_cliam_msg_down);
                }
            });
            holder2.btn_mine_claim_msg_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postClaimComfir(userId,mineCliamMsgBean.getUserId(),"1");
                }
            });
            holder2.btn_mine_claim_msg_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postClaimComfir(userId,mineCliamMsgBean.getUserId(),"0");
                }
            });
        }
        return convertView;
    }
        private void postClaimComfir(String userId, String claimUserId, final String status) {
         HttpUtils.postClaimMsg("/claimConfirm", userId, claimUserId, status, new StringCallback() {
             @Override
             public void onError(Call call, Exception e, int id) {
                 T.showShort(mContent,e.toString());
             }

             @Override
             public void onResponse(String response, int id) {
                 Gson gson = new Gson();
                 Type type = new TypeToken<Code<Object>>() {
                 }.getType();
                 Code<Object> code = gson.fromJson(response, type);
                 switch (code.getCode()) {
                     case  200:
                         if(status.equals("1")){
                             T.showShort(mContent,"确认成功");
                         }else{
                             T.showShort(mContent,"拒绝认领成功");
                         }
                         mOnFinishComfirListener.finishComfir();
                         break;
                     case 0:
                         T.showShort(mContent,"操作失败");
                         break;
                     case 100:
                         T.showShort(mContent,"您已被认领");
                         break;
                     case 102:
                         T.showShort(mContent,"用户未填写认领问题");
                         break;
                 }
             }
         });
    }

        class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mine_claim_msg_userHeader)
        RoundedImageView iv_mine_claim_msg_userHeader;
        @BindView(R.id.tv_mine_claim_msg_name)
        TextView tv_mine_claim_msg_name;
        @BindView(R.id.tv_mine_claim_msg_time)
        TextView tv_mine_claim_msg_time;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mine_claim_msg_item2_userHeader)
        RoundedImageView iv_mine_claim_msg_item2_userHeader;
        @BindView(R.id.tv_mine_claim_msg_item2_name)
        TextView tv_mine_claim_msg_item2name;
        @BindView(R.id.tv_mine_claim_msg_item2_content)
        TextView tv_mine_claim_msg_item2_content;
        @BindView(R.id.ll_mine_claim_msg_comfir)
        LinearLayout ll_mine_claim_msg_comfir;
        @BindView(R.id.btn_mine_claim_msg_yes)
        Button btn_mine_claim_msg_yes;
        @BindView(R.id.btn_mine_claim_msg_no)
        Button btn_mine_claim_msg_no;
        @BindView(R.id.iv_mine_claim_open)
        ImageView iv_mine_claim_open;

        public MyViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        MineCliamMsgBean mineCliamMsgBean=mData.get(position);
       if(mineCliamMsgBean.getStatus().equals("0")){
           return TWO_ITEM;
       }else{
           return ONE_ITEM;
       }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}
