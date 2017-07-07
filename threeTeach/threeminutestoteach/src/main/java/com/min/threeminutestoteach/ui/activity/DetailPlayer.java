package com.min.threeminutestoteach.ui.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BaseMvpActivity;
import com.min.threeminutestoteach.base.BasePopup;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.interfaces.ITeacheContentView;
import com.min.threeminutestoteach.listener.SampleListener;
import com.min.threeminutestoteach.presenter.TeacheContentPresenter;
import com.min.threeminutestoteach.utils.DataUtils;
import com.min.threeminutestoteach.utils.DisplayUtils;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.min.threeminutestoteach.views.LandLayoutVideo;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailPlayer extends BaseMvpActivity<ITeacheContentView, TeacheContentPresenter> implements ITeacheContentView {
    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;

    //推荐使用StandardGSYVideoPlayer，功能一致
    //CustomGSYVideoPlayer部分功能处于试验阶段
    @BindView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;
    @BindView(R.id.tv_teachContent_title)
    TextView tvTeachContentTitle;
    @BindView(R.id.iv_teachContent_userHeader)
    XCRoundRectImageView ivTeachContentUserHeader;
    @BindView(R.id.tv_teachContent_userName)
    TextView tvTeachContentUserName;
    @BindView(R.id.tv_teachContent_time)
    TextView tvTeachContentTime;
    @BindView(R.id.tv_teachContent_details)
    TextView tvTeachContentDetails;
    @BindView(R.id.iv_teachContent_share)
    ImageView ivTeachContentShare;
    @BindView(R.id.tv_teachContent_shareNum)
    TextView tvTeachContentShareNum;
    @BindView(R.id.iv_teachContent_good)
    ImageView ivTeachContentGood;
    @BindView(R.id.tv_teachContent_goodNum)
    TextView tvTeachContentGoodNum;
    @BindView(R.id.iv_teachContent_discuss)
    ImageView ivTeachContentDiscuss;
    @BindView(R.id.tv_teachContent_discussNum)
    TextView tvTeachContentDiscussNum;
    @BindView(R.id.iv_teachContent_collect)
    ImageView ivTeachContentCollect;
    @BindView(R.id.tv_teachContent_collectNum)
    TextView tvTeachContentCollectNum;
    @BindView(R.id.iv_teachContent_download)
    ImageView ivTeachContentDownload;
    @BindView(R.id.gray_layout)
    View grayLayout;
    @BindView(R.id.detail_player)
    LandLayoutVideo detailPlayer;
    @BindView(R.id.tv_teachContent_downloadNum)
    TextView tvTeachContentDownloadNum;


    private boolean isPlay;
    private boolean isPause;
    private TeacheBean teacheBean;
    private OrientationUtils orientationUtils;
    private boolean isTransition;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);
        ButterKnife.bind(this);
        teacheBean = getIntent().getParcelableExtra("teacheBean");
        userId = getIntent().getStringExtra("userId");
        initView(teacheBean);
        initPlayer();
    }

    private void initView(TeacheBean teacheBean) {
        tvTeachContentTitle.setText(teacheBean.getTitle());
        tvTeachContentDetails.setText(teacheBean.getContent());
        Picasso.with(this).load(HttpUtils.IMAGE_URL + teacheBean.getUserPortraitUrl()).into(ivTeachContentUserHeader);
        tvTeachContentUserName.setText(teacheBean.getNickname());
        tvTeachContentGoodNum.setText(teacheBean.getLikes());
        ivTeachContentGood.setImageResource(teacheBean.getLikesStatus().equals("0") ? R.mipmap.ungood : R.mipmap.good);
        tvTeachContentDiscussNum.setText(teacheBean.getCommentNumber());
        tvTeachContentShareNum.setText(teacheBean.getShareNumber());
        tvTeachContentCollectNum.setText(teacheBean.getCollectionNumber());
        ivTeachContentCollect.setImageResource(teacheBean.getCheckCollect().equals("0") ? R.mipmap.collect : R.mipmap.collected);
        tvTeachContentDownloadNum.setText(teacheBean.getDownloadNumber());
        if(DataUtils.checkVip.equals("0")) {
            ivTeachContentDownload.setVisibility(View.GONE);
            tvTeachContentDownloadNum.setVisibility(View.GONE);
        }
    }

    private void initPlayer() {
        detailPlayer.setUp(HttpUtils.IMAGE_URL + teacheBean.getVideoUrl(), true, null, teacheBean.getTitle());
        resolveNormalVideoUI();
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        detailPlayer.setIsTouchWiget(true);
        //detailPlayer.setIsTouchWigetFull(false);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);
        //detailPlayer.setOpenPreView(false);
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(DetailPlayer.this, true, true);
            }
        });

        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public TeacheContentPresenter initPresenter() {
        return new TeacheContentPresenter();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!detailPlayer.isIfCurrentIsFullscreen()) {
                    detailPlayer.startWindowFullscreen(DetailPlayer.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (detailPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.GONE);
        detailPlayer.getTitleTextView().setText(teacheBean.getTitle());
        detailPlayer.getBackButton().setImageResource(R.mipmap.btn_back);
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBack();
            }
        });
    }

    @OnClick({R.id.iv_teachContent_share, R.id.iv_teachContent_good, R.id.iv_teachContent_discuss, R.id.iv_teachContent_collect, R.id.iv_teachContent_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_teachContent_share:
                break;
            case R.id.iv_teachContent_good:
                postClikeToLike();
                break;
            case R.id.iv_teachContent_discuss:
                Intent intent = new Intent(this, DiscussionActivity.class);
                intent.putExtra("teacheBean", teacheBean);
                intent.putExtra("userId", userId);
                startActivity(intent);
                break;
            case R.id.iv_teachContent_collect:

                presenter.clickToCollection(userId, teacheBean.getId(),teacheBean.getCheckCollect().equals("0")?"1":"0");
                break;
            case R.id.iv_teachContent_download:
                // 设置背景颜色变暗
                grayLayout.setVisibility(View.VISIBLE);
                int WidthPixels = DisplayUtils.getScreenWidthPixels(this);
                int height = DisplayUtils.getScreenHeightPixels(this);
                DownLoadPopupWindow downLoadPopupWindow = new DownLoadPopupWindow(this, WidthPixels, height / 3);
                downLoadPopupWindow.setOutsideTouchable(false);
                downLoadPopupWindow.setTeacheBean(teacheBean);
                downLoadPopupWindow.setOnDismissListener(new BasePopup.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        grayLayout.setVisibility(View.GONE);
                    }
                });
                downLoadPopupWindow.showPopupWindowAtButton(R.id.activity_detail_player);
                break;
        }
    }

    private void toBack() {
        finish();

    }

    @Override
    public void postClikeToLike() {
        presenter.clickToLike(userId, teacheBean.getId(), "1");
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this, errorString);
    }

    @Override
    public void setTeachContent(TeacheBean teacheBean) {
        initView(teacheBean);
    }

    @Override
    public void succeedToLike() {
        T.showShort(this, "点赞成功");
        ivTeachContentGood.setImageResource(R.mipmap.good);
        tvTeachContentGoodNum.setText(Integer.valueOf(teacheBean.getLikes()) + 1 + "");
    }

    @Override
    public void succeedToCollection(String status) {
        ivTeachContentCollect.setImageResource(status.equals("0")?R.mipmap.collect:R.mipmap.collected);
        tvTeachContentCollectNum.setText(status.equals("0")?Integer.valueOf(teacheBean.getCollectionNumber()) - 1 + "":Integer.valueOf(teacheBean.getCollectionNumber()) + 1 + "");
    }
}
