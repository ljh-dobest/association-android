package com.ike.communityalliance.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.base.BaseRecyclerAdapter;
import com.ike.communityalliance.base.BaseRecyclerHolder;
import com.ike.communityalliance.bean.BaseBean;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.GroupFlexible;
import com.ike.communityalliance.bean.JoinUserBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.network.HttpUtils;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 群活动的详细信息
 */
public class FlexibleDetailActivity extends BaseActivity {
    @BindView(R.id.ll_activity_detail_back)
    LinearLayout ll_activity_detail_back;
    @BindView(R.id.iv_activity_detail_head)
    ImageView iv_activity_detail_head;
    @BindView(R.id.tv_activity_detail_title)
    TextView tv_activity_detail_title;
    @BindView(R.id.tv_activity_detail_time)
    TextView tv_activity_detail_time;
    @BindView(R.id.tv_activity_detail_address)
    TextView tv_activity_detail_address;
    @BindView(R.id.tv_activity_detail_publisher)
    TextView tv_activity_detail_publisher;
    @BindView(R.id.tv_activity_detail_content)
    TextView tv_activity_detail_content;
    @BindView(R.id.tv_activity_detail_moreJoinUsers)
    TextView tv_activity_detail_moreJoinUsers;
    @BindView(R.id.tv_activity_detail_applyFlexible)
    TextView tv_activity_detail_applyFlexible;
    @BindView(R.id.tv_activity_detail_wasapply)
    TextView tv_activity_detail_wasapply;
    @BindView(R.id.ll_activity_detail_up)
    LinearLayout ll_activity_detail_up;
    @BindView(R.id.ll_activity_detail_down)
    LinearLayout ll_activity_detail_down;
    @BindView(R.id.ll_activity_detail_contentimages)
    LinearLayout ll_activity_detail_contentimages;
    @BindView(R.id.rv_activity_user)
    RecyclerView rvActivityUser;

    private GroupFlexible groupFlexible;
    private String userId;
    private String flexibleId;
    private List<GroupFlexible> list;
    private BaseRecyclerAdapter<JoinUserBean> adapter;
    private List<JoinUserBean> flexibleJoinUsers;
    private GroupFlexible flexible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible_detail);
        ButterKnife.bind(this);
        list=new ArrayList<>();
        flexibleJoinUsers=new ArrayList<>();
        userId=getSharedPreferences("config",MODE_PRIVATE).getString(Const.LOGIN_ID,"");
        Intent intent=getIntent();
        groupFlexible=intent.getParcelableExtra("flexible");
        flexibleId=groupFlexible.getActivesId();
         getFlexibleDetail();
    }

    /**
     * 获取群活动详细信息
     */
    private void getFlexibleDetail() {
      HttpUtils.getGroupFlexibleDetail("/infoActives", flexibleId, new StringCallback() {
          @Override
          public void onError(Call call, Exception e, int id) {
              T.showShort(FlexibleDetailActivity.this,e.toString());
          }

          @Override
          public void onResponse(String response, int id) {
              Gson gson=new Gson();
              Type type=new TypeToken<Code<GroupFlexible>>(){}.getType();
              Code<GroupFlexible> code= gson.fromJson(response,type);
              if(code.getCode()==200){
                  flexible = code.getData();
                  initViewData();
                  flexibleJoinUsers=flexible.getJoinUsers();
                  initAdapter();
              }else{
                  T.showShort(FlexibleDetailActivity.this,"获取失败");
              }

          }
      });
    }

    private void initViewData() {
        if(flexible!=null){
            Picasso.with(this).load(HttpUtils.IMAGE_RUL+flexible.getActivesImage()).into(iv_activity_detail_head);
            tv_activity_detail_title.setText(flexible.getActivesTitle());
            tv_activity_detail_time.setText(flexible.getActivesStart());
            tv_activity_detail_address.setText(flexible.getActivesAddress());
            tv_activity_detail_content.setText(flexible.getActivesContent());
              tv_activity_detail_publisher.setText(flexible.getPublisher());
            tv_activity_detail_wasapply.setText("已报名("+flexible.getJoinUsers().size()+")");
            if(checkJoin()){
             tv_activity_detail_applyFlexible.setText("已报名");
           tv_activity_detail_applyFlexible.setBackgroundResource(R.mipmap.applyed_bg);
                tv_activity_detail_applyFlexible.setClickable(false);
            }
            initContentImages();
        }
    }
   //根据图片数量显示图片
    private void initContentImages() {
        for (int i = 0; i < flexible.getActivesImages().size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(this).load(HttpUtils.IMAGE_RUL+flexible.getActivesImages().get(i)).error(R.drawable.rc_image_error).into(imageView);
            ll_activity_detail_contentimages.addView(imageView);
        }
    }

    //是否已参加
    private boolean checkJoin() {
        for (JoinUserBean joinUserBean : flexible.getJoinUsers()) {
            if(joinUserBean.getUserId().equals(userId)){
                return true;
            }
        }
        return false;
    }


    private void initAdapter() {
        adapter=new BaseRecyclerAdapter<JoinUserBean>(mContext,flexibleJoinUsers,R.layout.item_vote_or_flexible_joinuser) {
            @Override
            public void convert(BaseRecyclerHolder holder, JoinUserBean item, int position, boolean isScrolling) {
                holder.setImageByUrl(R.id.iv_joinUser_icon,HttpUtils.IMAGE_RUL+item.getAvatarImage());
            }
        };
        rvActivityUser.setAdapter(adapter);
        LinearLayoutManager lm=new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        rvActivityUser.setLayoutManager(lm);
    }


    @OnClick({R.id.ll_activity_detail_back, R.id.tv_activity_detail_applyFlexible,
            R.id.ll_activity_detail_up,R.id.ll_activity_detail_down,R.id.tv_activity_detail_moreJoinUsers})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_activity_detail_back:
                finish();
                break;
            case R.id.tv_activity_detail_applyFlexible:
                joinActivity();
                break;
            case R.id.ll_activity_detail_up://收起
                ll_activity_detail_contentimages.setVisibility(View.GONE);
                tv_activity_detail_content.setMaxLines(3);
                ll_activity_detail_down.setVisibility(View.VISIBLE);
                ll_activity_detail_up.setVisibility(View.GONE);
                break;
            case R.id.ll_activity_detail_down://展开
                ll_activity_detail_contentimages.setVisibility(View.VISIBLE);
                tv_activity_detail_content.setMaxLines(8);
                ll_activity_detail_down.setVisibility(View.GONE);
                ll_activity_detail_up.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_activity_detail_moreJoinUsers://查看所有参加人员

                break;
        }
    }

    //参加活动
    private void joinActivity() {
        HttpUtils.postAddFlexible("/joinActives", userId, flexibleId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(mContext,"/joinActives------"+e);
                return;
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type=new TypeToken<Code<BaseBean>>(){}.getType();
                Code<BaseBean> code = gson.fromJson(response,type);
                switch (code.getCode()){
                    case 200:
                        T.showShort(mContext,"加入成功");
                        break;
                    case 0:
                        T.showShort(mContext,"加入失败");
                        break;
                    case 100:
                        T.showShort(mContext,"非群内人员无法加入该活动");
                        break;
                    case 101:
                        T.showShort(mContext,"活动还未开始");
                        break;
                    case 102:
                        T.showShort(mContext,"活动结束");
                        break;
                    case 103:
                        T.showShort(mContext,"活动人数已满");
                        break;
                    case 104:
                        T.showShort(mContext,"活动已加入");
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
