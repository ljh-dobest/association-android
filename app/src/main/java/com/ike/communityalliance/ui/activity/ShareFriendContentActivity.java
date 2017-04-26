package com.ike.communityalliance.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.DividerItemDecoration;
import com.ike.communityalliance.adapter.ShareFriendsContentRvAdapter;
import com.ike.communityalliance.base.BaseMvpActivity;
import com.ike.communityalliance.bean.CommentBean;
import com.ike.communityalliance.bean.TalkTalkBean;
import com.ike.communityalliance.constant.Const;
import com.ike.communityalliance.interfaces.IShareFriendContentView;
import com.ike.communityalliance.listener.OnFinishCommentListener;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.presenter.ShareFriendContentPresenter;
import com.ike.communityalliance.wedget.XCRoundRectImageView;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class ShareFriendContentActivity extends BaseMvpActivity<IShareFriendContentView,ShareFriendContentPresenter> implements IShareFriendContentView,OnFinishCommentListener{
    @BindView(R.id.ll_sendshareFriendContent_back)
    LinearLayout ll_sendshareFriendContent_back;
    @BindView(R.id.iv_shareFriendContent_icon)
    XCRoundRectImageView iv_shareFriendContent_icon;
    @BindView(R.id.tv_shareFriendContent_name)
    TextView tv_shareFriendContent_name;
    @BindView(R.id.tv_shareFriendContent_time)
    TextView tv_shareFriendContent_time;
    @BindView(R.id.tv_shareFriendContent_title)
    TextView tv_shareFriendContent_title;
    @BindView(R.id.rv_shareFriendContent_images)
    RecyclerView rv_shareFriendContent_images;
    @BindView(R.id.tv_shareFriendContent_goodNum)
    TextView tv_shareFriendContent_goodNum;
    @BindView(R.id.tv_shareFriendContent_discuNum)
    TextView tv_shareFriendContent_discuNum;
    @BindView(R.id.iv_shareFriendContent_good)
    ImageView iv_shareFriendContent_good;
    @BindView(R.id.iv_shareFriendContent_discu)
    TextView iv_shareFriendContent_discu;
    @BindView(R.id.iv_shareFriendContent_todicu)
    ImageView iv_shareFriendContent_todicu;
    @BindView(R.id.rv_shareFriendContent)
    RecyclerView rv_shareFriendContent;
    @BindView(R.id.rl_shareFriendContent_main)
    RelativeLayout rl_shareFriendContent_main;
   private TalkTalkBean talkTalk;
    private String nickName;
  private ShareFriendsContentRvAdapter adapter;
    private SharedPreferences sp;
    private String userId;
    private String userPortraitUrl;
    private String provingMessage;
    public static String articleId;
    private CommentPopuWindow commentPopuWindow;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_friend_content);
        ButterKnife.bind(this);
        sp=getSharedPreferences("config", MODE_PRIVATE);
        userId = sp.getString(Const.LOGIN_ID, "");
        talkTalk= (TalkTalkBean) getIntent().getSerializableExtra("shareFriendContent");
        nickName=sp.getString(Const.LOGIN_NICKNAME,"");
        userPortraitUrl=sp.getString(Const.userPortraitUrl,"");
        if(talkTalk!=null){
            getCommentData(userId,talkTalk.getId());
        }
        commentPopuWindow=new CommentPopuWindow(this,rl_shareFriendContent_main,this);
        commentPopuWindow.setArticleId(talkTalk.getId());
        commentPopuWindow.setUseId(userId);
        initData();
        initRV();
    }

    @Override
    public ShareFriendContentPresenter initPresenter() {
        return new ShareFriendContentPresenter();
    }

    private void initRV() {
        adapter=new ShareFriendsContentRvAdapter(this,commentPopuWindow);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_shareFriendContent.setLayoutManager(layoutManager);
        rv_shareFriendContent.setAdapter(adapter);
        rv_shareFriendContent.setItemAnimator(new DefaultItemAnimator());
        rv_shareFriendContent.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
}

    private void initData() {
        Picasso.with(this).load(HttpUtils.IMAGE_RUL+talkTalk.getUserPortraitUrl()).error(R.drawable.rc_image_error).into(iv_shareFriendContent_icon);
        if(talkTalk.getImages().size()!=0){
            rv_shareFriendContent_images.setLayoutManager(new GridLayoutManager(this,3));
            rv_shareFriendContent_images.setAdapter(new CommonAdapter<String>(this, R.layout.share_friends_rv_images_item, talkTalk.getImages())
            {
                @Override
                public void convert(ViewHolder rvholder, String s, int pos) {
                    Picasso.with(ShareFriendContentActivity.this).load(HttpUtils.IMAGE_RUL+s).error(R.drawable.rc_image_error).into((ImageView) rvholder.getView(R.id.iv_shareFriend_item_images));
                }
            });
        }else {
            rv_shareFriendContent_images.setVisibility(View.GONE);
        }
        tv_shareFriendContent_name.setText(talkTalk.getNickname());
        tv_shareFriendContent_time.setText(talkTalk.getReleaseTime());
        tv_shareFriendContent_title.setText(talkTalk.getContent());
        tv_shareFriendContent_goodNum.setText(talkTalk.getLikedNumber()+"");
        tv_shareFriendContent_discuNum.setText(talkTalk.getCommentNumber()+"");
        if(talkTalk.getLikeStatus()==1){
            Picasso.with(this).load(R.mipmap.good).into(iv_shareFriendContent_good);
        }else{
            Picasso.with(this).load(R.mipmap.ungood).into(iv_shareFriendContent_good);
        }
    }


    @Override
    public void getCommentData(String userId, String talkTalkId) {
        presenter.getCommentData(userId,talkTalkId);
    }

    @Override
    public void setCommentData(List<CommentBean> data) {
        tv_shareFriendContent_discuNum.setText(data.size()+"");
        adapter.setmDatas(data);
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this,errorString);
    }

    @Override
    public void succeedToComment(String string) {
        getCommentData(userId,talkTalk.getId());
    }

    @OnClick({R.id.ll_sendshareFriendContent_back,R.id.iv_shareFriendContent_todicu})
    public void ShareFriendsContentViewClcik(View v){
        switch (v.getId()) {
            case  R.id.ll_sendshareFriendContent_back:
                finish();
                break;
            case R.id.iv_shareFriendContent_todicu:
                commentPopuWindow.setCommentArticle(true);
                commentPopuWindow.setHint(talkTalk.getNickname());
                 commentPopuWindow.showPopupWindow();
                break;
        }
    }

    @Override
    public void finishComment() {
        getCommentData(userId,talkTalk.getId());
    }
}
