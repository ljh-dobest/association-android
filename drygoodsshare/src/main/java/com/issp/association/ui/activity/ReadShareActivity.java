package com.issp.association.ui.activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.czp.library.ArcProgress;
import com.issp.association.R;
import com.issp.association.base.view.BaseMvpActivity;
import com.issp.association.bean.ShareBean;
import com.issp.association.interfaces.IReadShareView;
import com.issp.association.network.HttpUtils;
import com.issp.association.presenters.ReadShareInfoPresenter;
import com.issp.association.utils.CircleTransform;
import com.issp.association.utils.DisplayUtils;
import com.issp.association.utils.FileUtils;
import com.issp.association.utils.T;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享预览
 * Created by T-BayMax on 2017/3/18.
 */

public class ReadShareActivity extends BaseMvpActivity<IReadShareView, ReadShareInfoPresenter> implements IReadShareView {


    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share_icon)
    ImageView ivShareIcon;
    @BindView(R.id.tv_share_user_Name)
    TextView tvShareUserName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_share_btn)
    ImageView ivShareBtn;
    @BindView(R.id.tv_share_btn)
    TextView tvShareBtn;
    @BindView(R.id.iv_like_btn)
    ImageView ivLikeBtn;
    @BindView(R.id.tv_like_btn)
    TextView tvLikeBtn;
    @BindView(R.id.iv_comment_btn)
    ImageView ivCommentBtn;
    @BindView(R.id.tv_comment_btn)
    TextView tvCommentBtn;
    @BindView(R.id.iv_collect_btn)
    ImageView ivCollectBtn;
    @BindView(R.id.tv_collect_btn)
    TextView tvCollectBtn;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.ll_like)
    LinearLayout llLike;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.ll_collect)
    LinearLayout llCollect;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    /* @BindView(R.id.wv_cynopsis)
     WebView wvCynopsis;*/
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;


    ShareBean bean;
    String userId;
    String activesId;
    int checkVip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_share);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ltMainTitleLeft.setText("返回");
        ltMainTitle.setText("");
        Intent intent = getIntent();

        userId = intent.getStringExtra("userId");
        checkVip=intent.getIntExtra("checkVip",0);
        activesId = intent.getStringExtra("activesId");
        Map<String, String> formData = new HashMap<String, String>(0);
        formData.put("userId", userId);
        formData.put("shareId", activesId);
        presenter.ReadShareInfoPresenter(formData);
    }

    @Override
    public ReadShareInfoPresenter initPresenter() {
        return new ReadShareInfoPresenter();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.lt_main_title_left, R.id.ll_share, R.id.ll_like, R.id.ll_comment, R.id.ll_collect, R.id.ll_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lt_main_title_left:
                ReadShareActivity.this.finish();
                break;
            case R.id.ll_share:
                break;
            case R.id.ll_like:
                Map<String, String> formData = new HashMap<String, String>(0);
                formData.put("userId", userId);
                formData.put("articleId", bean.getId());
                formData.put("type", "3");
                formData.put("status", "1");
                presenter.sharePraiseInfoPresenter(formData);
                break;
            case R.id.ll_comment:
                Intent intent = new Intent(ReadShareActivity.this, FeedForCommentActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
            case R.id.ll_collect:
                Map<String, String> data = new HashMap<String, String>(0);
                data.put("userId", userId);
                data.put("articleId", bean.getId());
                data.put("type", "3");
                data.put("status", "1");
                presenter.sharePraiseInfoPresenter(data);
                break;
            case R.id.ll_download:
                if (null!=bean.getDownload()&&bean.getDownload().equals("")){
                if (bean.getIsDownload() == 0) {
                    downloadWindow();
                } else {
                    if (checkVip == 1) {
                        downloadWindow();
                    } else {
                        showComfirmDialog();
                    }
                }}else {
                    T.showLong(ReadShareActivity.this,"没有可下载的干货");
                }
                break;
        }
    }

    private Dialog dialog;
    ArcProgress taskPb;
    /**
     * 选择下载
     */
    private void downloadWindow() {
        FileUtils fileUtils=new FileUtils(ReadShareActivity.this);

            final File file=fileUtils.creatSDDir("association");

        dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.popupwindow_download, null);
        //初始化控件
        ImageView iv_close = (ImageView) inflate.findViewById(R.id.iv_close);
        TextView tv_download = (TextView) inflate.findViewById(R.id.tv_download);
        taskPb= (ArcProgress) inflate.findViewById(R.id.pb_schedule);
        final TextView tv_file= (TextView) inflate.findViewById(R.id.tv_file);
        TextView tv_download_history = (TextView) inflate.findViewById(R.id.tv_download_history);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String download=bean.getDownload();
                String[] name=download.split("/");
                String f=file.getPath()+name[name.length-1];
                BaseDownloadTask task = FileDownloader.getImpl().create(download)
                        .setPath(f)
                        .setCallbackProgressTimes(100)
                        .setListener(taskDownloadListener);
                tv_file.setText(f);
                task.start();
            }
        });
        tv_download_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showLong(ReadShareActivity.this,"下载管理还在开发中");
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(false);dialog.setCancelable(false);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    AlertDialog comfirmDialog;

    /**
     * 非VIP不能下载
     */
    private void showComfirmDialog() {
        comfirmDialog = new android.app.AlertDialog.Builder(this).create();
        comfirmDialog.show();
        Window window = comfirmDialog.getWindow();
        WindowManager.LayoutParams lp = comfirmDialog.getWindow().getAttributes();
        lp.width = DisplayUtils.dp2px(ReadShareActivity.this, 300);//定义宽度
        lp.height = DisplayUtils.dp2px(ReadShareActivity.this, 200);//定义高度
        comfirmDialog.getWindow().setAttributes(lp);
        window.setContentView(R.layout.comfirm_dialog_layout);
        TextView tv_reminder = (TextView) window.findViewById(R.id.tv_reminder);
        tv_reminder.setText("仅限VIP下载");
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        ImageView iv_comfirm_dialog_cancel = (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comfirmDialog.dismiss();
            }
        });
        iv_comfirm_dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comfirmDialog.dismiss();
            }
        });
    }

    @Override
    public void showError(String errorString) {
        T.showShort(ReadShareActivity.this, errorString);
    }

    @Override
    public void setReadShareData(ShareBean data) {
        tvTitle.setText(data.getTitle());
        tvShareUserName.setText(data.getNickname());
        tvTime.setText(data.getTime());
        if (null != data.getUserPortraitUrl())
            Picasso.with(ReadShareActivity.this).load(HttpUtils.IMAGE_RUL + data.getUserPortraitUrl())
                    .transform(new CircleTransform()).into(ivShareIcon);
        if (null != data.getImage())
            Picasso.with(ReadShareActivity.this).load(HttpUtils.IMAGE_RUL + data.getImage())
                    .into(ivImage);
        //  wvCynopsis.loadData(data.getSynopsis(), "text/html; charset=UTF-8", null);
        wvContent.loadData(data.getContent(), "text/html; charset=UTF-8", null);
        tvLikeBtn.setText(data.getLikes() + "");

        tvCommentBtn.setText("" + data.getCommentNumber());
        switch (data.getLikesStatus()) {
            case 0:
                ivLikeBtn.setImageResource(R.mipmap.img_like_btn);
                break;
            case 1:
                ivLikeBtn.setImageResource(R.mipmap.img_have_thumb_up_btn);
                break;
            case 2:
                ivLikeBtn.setImageResource(R.mipmap.img_like_btn_no);
                break;
            case 3:
                ivLikeBtn.setImageResource(R.mipmap.img_comments_have_thumb_up_btn);
                break;
        }

        data.setUserId(userId);
        data.setId(activesId);
        bean = data;
    }

    @Override
    public void collect(String data) {
        ivCollectBtn.setImageResource(R.mipmap.img_collect_btn_);
    }

    @Override
    public void sharePraise(String data) {
        int likes = Integer.parseInt(tvLikeBtn.getText().toString().trim());
        tvLikeBtn.setText((likes + 1) + "");
        ivLikeBtn.setImageResource(R.mipmap.img_have_thumb_up_btn);
        T.showShort(ReadShareActivity.this, data);
    }

    @OnClick(R.id.ll_download)
    public void onViewClicked() {
    }


    private FileDownloadListener taskDownloadListener = new FileDownloadSampleListener() {



        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.pending(task, soFarBytes, totalBytes);


            updateDownloading(FileDownloadStatus.pending, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void started(BaseDownloadTask task) {
            super.started(task);


        }

        @Override
        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            super.connected(task, etag, isContinue, soFarBytes, totalBytes);

            updateDownloading(FileDownloadStatus.connected, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.progress(task, soFarBytes, totalBytes);


            updateDownloading(FileDownloadStatus.progress, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            super.error(task, e);


            updateNotDownloaded(FileDownloadStatus.error, task.getLargeFileSoFarBytes()
                    , task.getLargeFileTotalBytes());
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.paused(task, soFarBytes, totalBytes);

            updateNotDownloaded(FileDownloadStatus.paused, soFarBytes, totalBytes);

        }

        @Override
        protected void completed(BaseDownloadTask task) {
            super.completed(task);

            updateDownloaded();
        }
    };
    private ImageView iv_download;

    /**
     * 下载停止状态
     *
     * @param status 状态
     * @param sofar  下载总量
     * @param total  应用总量
     */
    public void updateNotDownloaded(final int status, final long sofar, final long total) {
        if (sofar > 0 && total > 0) {
            final float percent = sofar
                    / (float) total;
            taskPb.setMax(100);
            taskPb.setProgress((int) (percent * 100));
        } else {
            taskPb.setMax(1);
            taskPb.setProgress(0);
        }

        switch (status) {
            case FileDownloadStatus.error:

                break;
            case FileDownloadStatus.paused:

                break;
            default:

                break;
        }
    }

    /**
     * 正在下载状态
     *
     * @param status 状态
     * @param sofar  下载总量
     * @param total  应用总量
     */
    public void updateDownloading(final int status, final long sofar, final long total) {
        final float percent = sofar
                / (float) total;
        taskPb.setMax(100);
        taskPb.setProgress((int) (percent * 100));

        switch (status) {
            case FileDownloadStatus.pending:

                break;
            case FileDownloadStatus.started:

                break;
            case FileDownloadStatus.connected:

                break;
            case FileDownloadStatus.progress:

                break;
            default:

                break;
        }


    }

    /**
     * 下载完成状态
     */
    public void updateDownloaded() {
        taskPb.setMax(1);
        taskPb.setProgress(1);
        taskPb.setVisibility(View.GONE);
        T.showLong(ReadShareActivity.this,"下载完成");
    }
}
