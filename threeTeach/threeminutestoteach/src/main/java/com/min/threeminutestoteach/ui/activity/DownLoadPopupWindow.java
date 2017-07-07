package com.min.threeminutestoteach.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.base.BasePopup;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.db.DBController;
import com.min.threeminutestoteach.domain.MyBusinessInfLocal;
import com.min.threeminutestoteach.utils.DataUtils;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.woblog.android.downloader.DownloadService;
import cn.woblog.android.downloader.callback.DownloadManager;
import cn.woblog.android.downloader.domain.DownloadInfo;
import okhttp3.Call;


/**
 * Created by Min on 2016/11/24.
 * 下载框
 */

public class DownLoadPopupWindow extends BasePopup {
    @BindView(R.id.iv_pop_close)
    ImageView ivPopClose;
    @BindView(R.id.tv_pop_fileSize)
    TextView tvPopFileSize;
    @BindView(R.id.tv_pop_title)
    TextView tvPopTitle;
    @BindView(R.id.iv_pop_download)
    ImageView ivPopDownload;
    @BindView(R.id.iv_pop_completed)
    ImageView ivPopCompleted;
    @BindView(R.id.tv_pop_downloadControl)
    TextView tvPopDownloadControl;
    private String userId;
    private TeacheBean teacheBean;
    private DBController dbController;
    private final DownloadManager downloadManager;
    private DownloadInfo downloadInfo;

    public DownLoadPopupWindow(Activity activity,int Width,int height) {
       super(activity,Width,height);
        try {
            dbController= DBController.getInstance(mActivity.getApplicationContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        downloadManager = DownloadService.getDownloadManager(mActivity.getApplicationContext());
    }

 public void setTeacheBean(TeacheBean teacheBean){
        this.teacheBean=teacheBean;
     tvPopTitle.setText(teacheBean.getTitle());
    int num= dbController.findAllDownload().size();
     DataUtils.DOWNLOAD_COMMPLETE_LIST=downloadManager.findAllDownloaded();
     DataUtils.DOWNLOAD_FILE_SIZE=num;
     tvPopDownloadControl.setText("管理下载("+num+")");
 }
    @Override
    public void setTitleText() {
    }

    @Override
    public View getView() {
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_download, null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public Animation setAnima() {
        return getTransAnimaFromBottom();
    }

    @Override
    public View getCancelButton() {
        return null;
    }

    @Override
    public View getCompleteButton() {
        return null;
    }

    @OnClick({R.id.iv_pop_close, R.id.iv_pop_download, R.id.tv_pop_downloadControl,R.id.tv_pop_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pop_close:
                dismiss();
                break;
            case R.id.iv_pop_download:
                break;
            case R.id.tv_pop_downloadControl:
                Intent intent=new Intent(mActivity,DownLoadControlActivity.class);
                mActivity.startActivity(intent);
                dismiss();
                break;
            case R.id.tv_pop_title:
                if(checkWasDownLoad()&&isFileExists()){
                    ivPopDownload.setVisibility(View.GONE);
                    ivPopCompleted.setVisibility(View.VISIBLE);
                    T.showShort(mActivity,"该视频已下载");
                    return;
                }else{
                    ivPopDownload.setVisibility(View.VISIBLE);
                    ivPopCompleted.setVisibility(View.GONE);
                }
                MyBusinessInfLocal myBusinessInfo=new MyBusinessInfLocal(0,teacheBean.getTitle(),"", HttpUtils.IMAGE_URL+teacheBean.getVideoUrl());
                if(DataUtils.canAddDownloadList(myBusinessInfo)){
                    addDownload(myBusinessInfo);
                }else{
                    T.showShort(mActivity,"已添加到下载列表中");
                }
                break;
        }
    }

    /**
     * 文件是否存在
     * @return
     */
    private boolean isFileExists() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/d/"+teacheBean.getTitle()+".mp4");
        return file.exists();
    }

    /**
     * 添加下载
     * @param myBusinessInfo
     */
    private void addDownload(MyBusinessInfLocal myBusinessInfo) {
        DataUtils.DOWNLOAD_FILE_SIZE+=1;
        tvPopDownloadControl.setText("管理下载("+DataUtils.DOWNLOAD_FILE_SIZE+")");
        DataUtils.DOWNLOAD_LIST.add(myBusinessInfo);
        File d = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "d");
        if (!d.exists()) {
            d.mkdirs();
        }
        String path = d.getAbsolutePath().concat("/").concat(teacheBean.getTitle()+".mp4");
        downloadInfo = new DownloadInfo.Builder().setUrl(myBusinessInfo.getUrl())
                .setPath(path)
                .build();
        downloadManager.download(downloadInfo);
        //save extra info to my database.
        MyBusinessInfLocal myBusinessInfLocal = new MyBusinessInfLocal(
               myBusinessInfo.getUrl().hashCode(), myBusinessInfo.getName(),"",myBusinessInfo.getUrl());
        try {
            dbController.createOrUpdateMyDownloadInfo(myBusinessInfLocal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        postDownLoadTimes();
    }

    private void postDownLoadTimes() {
        HttpUtils.postDownloadOrShareTimes("/updateInfo", teacheBean.getId(), "1", new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

        }
        });
    }


    /**
     * 检查是否已经下载
     * @return
     */
    private boolean checkWasDownLoad() {
        for (DownloadInfo downloadInfo : DataUtils.DOWNLOAD_COMMPLETE_LIST) {
                  if((HttpUtils.IMAGE_URL+teacheBean.getVideoUrl()).equals(downloadInfo.getUri())){
                        return true;
                  }
        }
        return false;
    }
}
