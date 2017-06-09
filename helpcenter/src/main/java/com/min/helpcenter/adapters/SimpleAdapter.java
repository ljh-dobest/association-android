package com.min.helpcenter.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.min.helpcenter.R;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.views.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.richeditor.RichEditor;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> {
    private List<HelpBean> list;
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

    public SimpleAdapter(List<HelpBean> list, Context context) {
        this.list = list;
        this.context=context;
    }
  public List<HelpBean> getList(){
      return list;
  }
    public void addMoreData(List<HelpBean> data){
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
        HelpBean helpBean=list.get(position);
        Picasso.with(context).load(HttpUtils.IMAGE_URL+helpBean.getUserPortraitUrl()).into(holder.iv_helpCenter_item_icon);
        holder.tv_helpCenter_item_name.setText(helpBean.getNickname());
        holder.tv_helpCenter_item_time.setText(helpBean.getTime());
        holder.tv_helpCenter_item_title.setText(helpBean.getTitle());
        holder.tv_helpCenter_item_content.setHtml(helpBean.getContent());
        holder.tv_helpCenter_responseNum.setText(helpBean.getHelpNumber()+"");
        holder.tv_helpCenter_item_goldNum.setText(helpBean.getContributionCoin()+"");
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

    public void setData(List<HelpBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.help_center_rv_item, parent, false);
        SimpleAdapterViewHolder vh = new SimpleAdapterViewHolder(v, true);
        return vh;
    }

    public void insert(HelpBean person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }



    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_helpCenter_item_icon)
        CircleImageView iv_helpCenter_item_icon;
        @BindView(R.id.tv_helpCenter_item_name)
        TextView tv_helpCenter_item_name;
        @BindView(R.id.tv_helpCenter_item_title)
        TextView tv_helpCenter_item_title;
        @BindView(R.id.tv_helpCenter_item_content)
        RichEditor tv_helpCenter_item_content;
        @BindView(R.id.tv_helpCenter_responseNum)
        TextView tv_helpCenter_responseNum;
        @BindView(R.id.tv_helpCenter_item_goldNum)
        TextView tv_helpCenter_item_goldNum;
        @BindView(R.id.tv_helpCenter_item_time)
        TextView tv_helpCenter_item_time;
        public SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                ButterKnife.bind(this,itemView);
            }
        }
    }


}