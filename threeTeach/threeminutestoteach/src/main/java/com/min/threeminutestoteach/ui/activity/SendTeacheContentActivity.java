package com.min.threeminutestoteach.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BaseMvpActivity;
import com.min.threeminutestoteach.interfaces.ISendTeacheContentView;
import com.min.threeminutestoteach.presenter.SendTeacheContentPresenter;
import com.min.threeminutestoteach.utils.DateUtils;
import com.min.threeminutestoteach.utils.PermissionsUtil;
import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import mabeijianxi.camera.LocalMediaCompress;
import mabeijianxi.camera.VCamera;
import mabeijianxi.camera.model.AutoVBRMode;
import mabeijianxi.camera.model.BaseMediaBitrateConfig;
import mabeijianxi.camera.model.LocalMediaConfig;
import mabeijianxi.camera.model.OnlyCompressOverBean;
import mabeijianxi.camera.util.DeviceUtils;

public class SendTeacheContentActivity extends BaseMvpActivity<ISendTeacheContentView, SendTeacheContentPresenter> implements ISendTeacheContentView {
    private static final int CHOOSE_CODE = 1001;
    @BindView(R.id.ll_sendTeache_back)
    LinearLayout llSendTeacheBack;
    @BindView(R.id.tv_sendTeache_public)
    TextView tvSendTeachePublic;
    @BindView(R.id.tv_sendTeache_bg)
    TextView tvSendTeacheBg;
    @BindView(R.id.iv_sendTeache_bg)
    ImageView ivSendTeacheBg;
    @BindView(R.id.et_sendTeache_title)
    EditText etSendTeacheTitle;
    @BindView(R.id.et_sendTeache_content)
    EditText etSendTeacheContent;
    @BindView(R.id.tv_senTeache_see)
    TextView tvSenTeacheSee;
    @BindView(R.id.ll_sendTeache_insert)
    LinearLayout llSendTeacheInsert;
    @BindView(R.id.view_loading)
    AVLoadingIndicatorView viewLoading;
    @BindView(R.id.rl_sendTeache_video)
    RelativeLayout rlSendTeacheVideo;
    @BindView(R.id.tv_sendTeache_whoSee)
    TextView tvSendTeacheWhoSee;
    @BindView(R.id.tv_sendTeache_icon)
    TextView tvSendTeacheIcon;
    @BindView(R.id.iv_sendTeache_icon)
    ImageView ivSendTeacheIcon;
    private AVLoadingIndicatorView pb_upload;
    private String imgFilePath, title, content, playTime, userId;
    private File vedioFile;
    private File imgFile;
    private File sendVedioFile;
    private AlertDialog comfirmDialog;
    private String whoSee = "0";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_teache_content);
        ButterKnife.bind(this);
        initSmallVideo(this);
        PermissionsUtil.initPermissions(this, Manifest.permission.RECORD_AUDIO);
        //imgFilePath = getCacheDir().getAbsolutePath() + "/videoImg.png";
        userId = getIntent().getStringExtra("userId");
    }

    @Override
    public SendTeacheContentPresenter initPresenter() {
        return new SendTeacheContentPresenter();
    }

    @OnClick({R.id.ll_sendTeache_back,R.id.tv_sendTeache_icon, R.id.tv_sendTeache_public, R.id.tv_sendTeache_bg, R.id.tv_senTeache_see, R.id.rl_sendTeache_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sendTeache_back:
                finish();
                break;
            case R.id.tv_sendTeache_public:
                sendTeach();
                break;
            case R.id.tv_sendTeache_icon:
                selectImgIcon();
                break;
            case R.id.tv_sendTeache_bg:
                selectVedio();
                break;
            case R.id.rl_sendTeache_video:
                Intent intent = new Intent(this, VedioActivity.class);
                intent.putExtra("filePath", vedioFile.getAbsolutePath());
                startActivity(intent);
                break;
            case R.id.tv_senTeache_see:
                Intent intent1 = new Intent(this, WhoCanSeeActivity.class);
                intent1.putExtra("whoSee", whoSee);
                startActivityForResult(intent1, 11);
                break;
        }
    }

    private void selectImgIcon() {
        //自定义方法的单选
        RxGalleryFinal
                .with(this)
                .image()
                .radio()
                .crop()
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        //图片选择结果
                     imgFilePath=imageRadioResultEvent.getResult().getOriginalPath();
                        Picasso.with(SendTeacheContentActivity.this).load(new File(imgFilePath)).into(ivSendTeacheIcon);
                    }
                })
                .openGallery();
    }


    private void selectVedio() {
        PermissionsUtil.initPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        RxGalleryFinalApi.getInstance(this)
                .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                .setVDRadioResultEvent(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        String path = imageRadioResultEvent.getResult().getOriginalPath();
                        vedioFile = new File(path);
                        setVedioBitmap(path);
                    }
                })
                .open();
    }

    private void setVedioBitmap(final String path) {
        tvSendTeacheBg.setVisibility(View.GONE);
        viewLoading.setVisibility(View.VISIBLE);
        Observable<Bitmap> observable = (Observable<Bitmap>) Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(path);
                Bitmap bitmap = mmr.getFrameAtTime();
                e.onNext(bitmap);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        //saveBitmap(bitmap);
                        localMedioCompress(path);
                        ivSendTeacheBg.setImageBitmap(bitmap);
                        viewLoading.setVisibility(View.GONE);
                        rlSendTeacheVideo.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void saveBitmap(Bitmap bitmap) {
        File f = new File(imgFilePath);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 选择本地视频压缩
    private void localMedioCompress(String path) {
        BaseMediaBitrateConfig compressMode = null;
        compressMode = new AutoVBRMode();
        compressMode.setVelocity("ultrafast");//设置压缩速度为超快，转码速度控制,速度越快体积将变大，质量也稍差一点点。
        LocalMediaConfig.Buidler buidler = new LocalMediaConfig.Buidler();
        final LocalMediaConfig config = buidler
                .setVideoPath(path)
                .captureThumbnailsTime(1)
                .doH264Compress(compressMode)
                .setFramerate(0)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showUpLoadDialog("compress");
                    }
                });
                OnlyCompressOverBean onlyCompressOverBean = new LocalMediaCompress(config).startCompress();
                sendVedioFile = new File(onlyCompressOverBean.getVideoPath());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        comfirmDialog.dismiss();
                    }
                });
            }
        }).start();
    }

    private void sendTeach() {
        playTime = getMedioPlayTime().replace(":", "'");
        imgFile=new File(imgFilePath);
        title = etSendTeacheTitle.getText().toString();
        content = etSendTeacheContent.getText().toString();
        if (imgFile == null) {
            T.showShort(this, "请选择视频封面图");
            return;
        }
        if (sendVedioFile == null) {
            T.showShort(this, "请选择视频文件");
            return;
        }
        if (title.equals("") || content.equals("")) {
            T.showShort(this, "标题和内容不能为空");
            return;
        }
        try {
            Long fileSize = getFileSize(sendVedioFile);
            if (fileSize > 40L) {
                T.showShort(this, "视频文件已超出40M");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        showUpLoadDialog("public");
        presenter.sendTeachContent(userId, title, content, playTime, whoSee, imgFile, sendVedioFile);
    }

    private String getMedioPlayTime() {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(sendVedioFile.getAbsolutePath());
        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        return DateUtils.getDateTimeFromMillisecond(Long.valueOf(duration));
    }

    private void showUpLoadDialog(String type) {
        comfirmDialog = new AlertDialog.Builder(this).create();
        comfirmDialog.setCanceledOnTouchOutside(false);
        comfirmDialog.show();
        Window window = comfirmDialog.getWindow();
        window.setContentView(R.layout.loading_popupwindow);
        pb_upload = (AVLoadingIndicatorView) window.findViewById(R.id.pb_uploading);
        TextView tv_text = (TextView) window.findViewById(R.id.tv_pop_text);
        tv_text.setText(type.equals("compress") ? "正在压缩..." : "正在发送...");
    }

    /**
     * 获取指定文件大小
     *
     * @return
     * @throws Exception
     */
    private long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available() / 1024L / 1024L;
        }
        return size;
    }

    @Override
    public void postTeachContent() {
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this, errorString);
        comfirmDialog.dismiss();
    }

    @Override
    public void succeedToPublic() {
        comfirmDialog.dismiss();
        sendVedioFile.delete();
        T.showShort(this, "发布成功");
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void currentProgress(float progress) {

    }

    public static void initSmallVideo(Context context) {
        // 设置拍摄视频缓存路径
        File dcim = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        if (DeviceUtils.isZte()) {
            if (dcim.exists()) {
                VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
            } else {
                VCamera.setVideoCachePath(dcim.getPath().replace("/sdcard/",
                        "/sdcard-ext/")
                        + "/mabeijianxi/");
            }
        } else {
            VCamera.setVideoCachePath(dcim + "/mabeijianxi/");
        }
        // 开启log输出,ffmpeg输出到logcat
        VCamera.setDebugMode(true);
        // 初始化拍摄SDK，必须
        VCamera.initialize(context);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 11 && resultCode == RESULT_OK) {
            whoSee = data.getStringExtra("whoSee");
            if (whoSee.equals("0")) {
                tvSendTeacheWhoSee.setText("全平台可见");
                tvSenTeacheSee.setText("公开");
            } else {
                tvSendTeacheWhoSee.setText("仅VIP可见");
                tvSenTeacheSee.setText("非公开");
            }
        }
    }


}
