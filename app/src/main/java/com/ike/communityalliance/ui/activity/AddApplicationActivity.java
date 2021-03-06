package com.ike.communityalliance.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.czp.library.ArcProgress;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ike.communityalliance.R;
import com.ike.communityalliance.adapter.AddApplicationAdapter;
import com.ike.communityalliance.base.BaseActivity;
import com.ike.communityalliance.bean.ApkItem;
import com.ike.communityalliance.bean.ApplyListItem;
import com.ike.communityalliance.bean.Code;
import com.ike.communityalliance.bean.Version;
import com.ike.communityalliance.network.HttpUtils;
import com.ike.communityalliance.utils.ApkOperator;
import com.ike.communityalliance.utils.file.FileUtils;
import com.ike.mylibrary.util.T;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.morgoo.droidplugin.pm.PluginManager;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.Call;


/**
 * 添加应用
 * Created by T-BayMax on 2017/3/27.
 */

public class AddApplicationActivity extends BaseActivity {

    @BindView(R.id.lt_main_title_left)
    TextView ltMainTitleLeft;
    @BindView(R.id.lt_main_title)
    TextView ltMainTitle;
    @BindView(R.id.lt_main_title_right)
    TextView ltMainTitleRight;
    @BindView(R.id.gv_application)
    GridView gvApplication;

    private List<ApplyListItem> list;
    private AddApplicationAdapter adapter;

    private ApkOperator mApkOperator; // Apk操作
    private List<String> listApk;
    FileUtils fileUtils;
    File f;
    ArrayList<ApkItem> apkFromInstall;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);
        ButterKnife.bind(this);
        fileUtils = new FileUtils(AddApplicationActivity.this);

        f = fileUtils.redFile("");

        initData();

        //writeToSDFromInput();
        apkTask();

    }

    @OnClick(R.id.lt_main_title_left)
    void leftClick() {
        AddApplicationActivity.this.finish();
    }


    private void initData() {
        list = new ArrayList<ApplyListItem>(0);
        list.add(new ApplyListItem("shareApp", "干货分享", R.mipmap.ganhuo, 1, "com.issp.association",
                f.getPath() + "/drygoodsshare.apk", "http://7xlet1.com1.z0.glb.clouddn.com/drygoodsshare.apk", 0));
        list.add(new ApplyListItem("inspirationApp", "灵感贩卖", R.mipmap.linggan, 1, "com.issp.inspiration",
                f.getPath() + "/inspirationtosell.apk", "http://7xlet1.com1.z0.glb.clouddn.com/inspirationtosell.apk", 0));
       // list.add(new ApplyListItem("claimApp", "认领中心", R.mipmap.lingyang, 1, "", "", "", 0));
        list.add(new ApplyListItem("streamingApp", "直播中心", R.mipmap.zhibo, 1, "", "", "", 0));
        list.add(new ApplyListItem("takingTaxiApp", "联盟打车", R.mipmap.dache, 1, "com.ike.sq.taxi",
                f.getPath() + "/taxi.apk", "http://7xlet1.com1.z0.glb.clouddn.com/taxi.apk", 0));
        list.add(new ApplyListItem("navigationApp", "导航", R.mipmap.daohang, 1, "", "", "", 0));
        list.add(new ApplyListItem("crowdApp", "众筹", R.mipmap.zhongchou, 1, "com.issp.association.crowdfunding",
                f.getPath() + "/crowdfunding.apk", "http://7xlet1.com1.z0.glb.clouddn.com/crowdfunding.apk", 0));

        list.add(new ApplyListItem("threeMinutesApp", "三分钟教学", R.mipmap.sanfenzhong, 1, "com.min.threeminutestoteach",
                f.getPath() + "/threeminutestoteach.apk", "http://7xlet1.com1.z0.glb.clouddn.com/threeminutestoteach.apk", 0));
      //  list.add(new ApplyListItem("surveyResearchApp", "调研中心", R.mipmap.diaoyan, 1, "", "", "", 0));
        list.add(new ApplyListItem("seekHelpApp", "求助中心", R.mipmap.qiuzhu, 1, "com.min.helpcenter",
                f.getPath() + "/helpcenter.apk", "http://7xlet1.com1.z0.glb.clouddn.com/helpcenter.apk", 0));
        list.add(new ApplyListItem("platformApp", "平台活动", R.mipmap.pingtai, 1, "com.ike.coalition.platform",
                f.getPath() + "/platform.apk", "http://7xlet1.com1.z0.glb.clouddn.com/platform.apk", 0));
        list.add(new ApplyListItem("commonwealApp", "公益活动", R.mipmap.gongyi, 1, "com.ike.sq.commonwealactives",
                f.getPath() + "/commonwealactives.apk", "http://7xlet1.com1.z0.glb.clouddn.com/commonwealactives.apk", 0));
        list.add(new ApplyListItem("driverApp", "联盟司机", R.mipmap.siji, 1,  "com.ike.sq.taxi",
                f.getPath() + "/taxi.apk", "http://7xlet1.com1.z0.glb.clouddn.com/taxi.apk", 0));
        list.add(new ApplyListItem("lookForApp", "后台找人", R.mipmap.zhaoren, 1, "", "", "", 0));
        adapter = new AddApplicationAdapter(list, AddApplicationActivity.this);
        gvApplication.setAdapter(adapter);
    }

    @OnItemClick(R.id.gv_application)
    void itemClick(View view, int position) {
        apkFromInstall = getApkFromInstall();
        boolean boo = true;
        for (int i = 0; i < apkFromInstall.size(); i++) {
            if (apkFromInstall.get(i).packageInfo.packageName.equals(list.get(position).getPackageName())) {
                // mApkOperator.openApk(apkFromInstall.get(i));
                T.showLong(AddApplicationActivity.this, "已经添加");
                getLastVersion(apkFromInstall.get(i), view, position);
                boo = false;
                return;
            } else {
                boo = true;
            }

        }
        if (boo) {
            updateApk(view, position);
        }


    }

    private void updateApk(View view, int position) {
        T.showLong(AddApplicationActivity.this, "亲还没有添加哦");
        taskPb = (ArcProgress) view.findViewById(R.id.pb_schedule);
        ivDownload= (ImageView) view.findViewById(R.id.iv_download);
        taskPb.setVisibility(View.VISIBLE);
        ivDownload.setVisibility(View.VISIBLE);
        ApplyListItem model = list.get(position);
        BaseDownloadTask task = FileDownloader.getImpl().create(model.getUrl())
                .setPath(model.getPath())
                .setCallbackProgressTimes(100)
                .setListener(taskDownloadListener);
        ((AddApplicationAdapter.ViewHolder) view.getTag()).addTaskForViewHolder(task);
        task.start();
    }


    // 从下载文件夹获取Apk
    private ArrayList<ApkItem> getApkFromDownload() {
        // for (int i = 0; i <listApk.size() ; i++) {
        // File files = fileUtils.redFile(pageName);
        // }
        PackageManager pm = getApplicationContext().getPackageManager();
        ArrayList<ApkItem> apkItems = new ArrayList<>(0);

        try {
            if (null != f.listFiles()) {
                for (File file : f.listFiles()) {
                    if (file.exists() && file.getPath().toLowerCase().endsWith(".apk")) {
                        final PackageInfo info = pm.getPackageArchiveInfo(file.getPath(), 0);
                        apkItems.add(new ApkItem(pm, info, file.getPath()));
                    }
                }
            }
        } catch (Exception e) {
            Log.e("eeeee", e.toString());
        }

        return apkItems;
    }

    // 在安装中获取Apk

    public ArrayList<ApkItem> getApkFromInstall() {
        ArrayList<ApkItem> apkItems = new ArrayList<>(0);
        try {
            final List<PackageInfo> infos = PluginManager.getInstance().getInstalledPackages(0);
            if (infos == null) {
                return apkItems;
            }
            final PackageManager pm = getPackageManager();
            // noinspection all
            for (final PackageInfo info : infos) {
                apkItems.add(new ApkItem(pm, info, info.applicationInfo.publicSourceDir));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return apkItems;
    }

    public int apkTask() {
        int size = 0;
        mApkOperator = new ApkOperator(this);
        ArrayList<ApkItem> apkFromDownload = getApkFromDownload();
        if (null != apkFromDownload && apkFromDownload.size() > 0) {
            size = apkFromDownload.size();
            for (int i = 0; i < size; i++) {

                new InstallApkTask(apkFromDownload.get(i)).execute();
            }
            // Toast.makeText(this, "正在初始化中、、、请稍后", Toast.LENGTH_LONG).show();
        }

        return size;
    }

    // 安装Apk的线程, Rx无法使用.
    private class InstallApkTask extends AsyncTask<Void, Void, String> {

        private ApkItem mApkItem; // Apk项

        public InstallApkTask(ApkItem mApkItem) {
            this.mApkItem = mApkItem;
        }

        @Override
        protected void onPostExecute(String v) {
            // Toast.makeText(com.t.baymax.navi.activity, v, Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(Void... params) {
            return mApkOperator.installApk(mApkItem);
        }

    }

    private void getLastVersion(final ApkItem apkItem, final View view, final int position) {
        Map<String, String> formData = new HashMap<>(0);
        formData.put("name", apkItem.packageInfo.packageName);
        HttpUtils.sendFormBodyPostRequest("/selectApp", formData, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("getLastVersion", e.toString());
                mApkOperator.openApk(apkItem);
            }

            @Override
            public void onResponse(String response, int id) {

                try {
                    Type jsonType = new TypeToken<Code<Version>>() {
                    }.getType();
                    Code<Version> rowsTable = new Gson().fromJson(response, jsonType);
                    switch (rowsTable.getCode()) {
                        case 200:
                            Version version = rowsTable.getData();
                            if (null != version) {
                                int ersionCode = Integer.parseInt(mApkOperator.versionCode(apkItem).replace(".", ""));
                                int v = Integer.parseInt(version.getVersion().replace(".", ""));
                                if (v > ersionCode) {
                                    if (apkTask() < 1) {
                                        updateApk(view, position);
                                    }

                                }
                            }
                            break;
                        default:
                            mApkOperator.openApk(apkItem);
                            break;
                    }

                } catch (Exception e) {
                    Log.e("AffairsFragment-handler", e.toString());
                } finally {

                }
            }
        });
    }

    private FileDownloadListener taskDownloadListener = new FileDownloadSampleListener() {

        private AddApplicationAdapter.ViewHolder checkCurrentHolder(final BaseDownloadTask task) {
            final AddApplicationAdapter.ViewHolder tag = (AddApplicationAdapter.ViewHolder) task.getTag();
            if (tag.id != task.getId()) {
                return null;
            }

            return tag;
        }

        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.pending(task, soFarBytes, totalBytes);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

            updateDownloading(FileDownloadStatus.pending, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void started(BaseDownloadTask task) {
            super.started(task);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

        }

        @Override
        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            super.connected(task, etag, isContinue, soFarBytes, totalBytes);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

            updateDownloading(FileDownloadStatus.connected, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.progress(task, soFarBytes, totalBytes);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

            updateDownloading(FileDownloadStatus.progress, soFarBytes
                    , totalBytes);
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
            super.error(task, e);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

            updateNotDownloaded(FileDownloadStatus.error, task.getLargeFileSoFarBytes()
                    , task.getLargeFileTotalBytes());
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            super.paused(task, soFarBytes, totalBytes);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }

            updateNotDownloaded(FileDownloadStatus.paused, soFarBytes, totalBytes);

        }

        @Override
        protected void completed(BaseDownloadTask task) {
            super.completed(task);
            final AddApplicationAdapter.ViewHolder tag = checkCurrentHolder(task);
            if (tag == null) {
                return;
            }
            updateDownloaded();
        }
    };
    private ArcProgress taskPb;
    ImageView ivDownload;
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
        apkTask();
        taskPb.setVisibility(View.GONE);
        ivDownload.setVisibility(View.GONE);
    }
}
