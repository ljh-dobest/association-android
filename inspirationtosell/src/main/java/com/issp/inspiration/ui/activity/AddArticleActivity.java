package com.issp.inspiration.ui.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.issp.inspiration.App;
import com.issp.inspiration.R;
import com.issp.inspiration.base.view.BaseMvpActivity;
import com.issp.inspiration.bean.DealBuyBean;
import com.issp.inspiration.interfaces.IAddArticleView;
import com.issp.inspiration.network.HttpUtils;
import com.issp.inspiration.presenters.AddArticlePresenter;
import com.issp.inspiration.utils.DisplayUtils;
import com.issp.inspiration.utils.T;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;
import jp.wasabeef.richeditor.RichEditor;

/**
 * 添加灵感贩卖
 * Created by T-BayMax on 2017/4/19.
 */

public class AddArticleActivity extends BaseMvpActivity<IAddArticleView, AddArticlePresenter> implements IAddArticleView, View.OnLayoutChangeListener {


    @BindView(R.id.iv_ask_back)
    ImageView ivAskBack;
    @BindView(R.id.ll_ask_back)
    AutoLinearLayout llAskBack;
    @BindView(R.id.tv_ask_release)
    TextView tvAskRelease;
    @BindView(R.id.tv_preview)
    TextView tvPreview;
    @BindView(R.id.tv_image)
    TextView tvImage;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    RichEditor etContent;
    @BindView(R.id.et_deal_content)
    RichEditor etDealContent;
    @BindView(R.id.iv_ask_camera)
    ImageView ivAskCamera;
    @BindView(R.id.iv_ask_picture)
    ImageView ivAskPicture;
    @BindView(R.id.iv_ask_gold)
    ImageView ivAskGold;
    @BindView(R.id.ll_ask_insert)
    AutoLinearLayout llAskInsert;
    @BindView(R.id.activity_ask)
    AutoRelativeLayout activityAsk;
    @BindView(R.id.ll_open)
    LinearLayout llOpen;
    @BindView(R.id.tv_open)
    TextView tvOpen;
    //屏幕高度
    private EditText et_ask_pop_insertgold;
    private int screenHeight = 0;
    private int screenWidth = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private View goldPopupWindow;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private int goldNum = 0;

    private String path;
    File file;
    android.app.AlertDialog comfirmDialog;
    private ProgressDialog pd;
    private android.app.AlertDialog mPopupWindow;

    private String userId;
    private int status = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);

        App.activityMap.put("AddArticleActivity", AddArticleActivity.this);
        ButterKnife.bind(this);
        ModelUtils.setDebugModel(true);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight / 4;
        initView();
    }


    private void initView() {
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        etDealContent.setEditorHeight(300);
        activityAsk.addOnLayoutChangeListener(this);

        etContent.setPlaceholder("请输入简介（40字~140字以内）");
        etDealContent.setPlaceholder("输入正文");
        initDialog();
    }

    private void initDialog() {
        builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        goldPopupWindow = inflater.inflate(R.layout.popupwindow_insert_gold, null);
        et_ask_pop_insertgold = (EditText) goldPopupWindow.findViewById(R.id.et_ask_pop_insertgold);
        builder.setView(goldPopupWindow);
        alertDialog = builder.create();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorString) {

        if (null != pd)
            pd.dismiss();
        T.showShort(AddArticleActivity.this, errorString);
    }

    @Override
    public void uploadPicturesView(String data) {
        String imgUrl = HttpUtils.IMAGE_RUL + data;
        etDealContent.insertImage(imgUrl, "dachshund");
        T.showShort(AddArticleActivity.this, "上传成功");
    }

    void initPopupWindow() {
        mPopupWindow = new android.app.AlertDialog.Builder(this).create();
        mPopupWindow.show();
        Window window = mPopupWindow.getWindow();
        WindowManager.LayoutParams lp = mPopupWindow.getWindow().getAttributes();
        lp.width = DisplayUtils.dp2px(AddArticleActivity.this, 300);//定义宽度
        lp.height = DisplayUtils.dp2px(AddArticleActivity.this, 200);//定义高度
        mPopupWindow.getWindow().setAttributes(lp);
        window.setContentView(R.layout.popwindow_more);
        TextView tv_information = (TextView) mPopupWindow.findViewById(R.id.tv_information);
        TextView tv_my_share = (TextView) mPopupWindow.findViewById(R.id.tv_my_share);
        tv_information.setText("公开");
        tv_my_share.setText("VIP可见");
        tv_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                tvOpen.setText("公开");
                mPopupWindow.dismiss();
            }
        });
        tv_my_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 1;
                tvOpen.setText("VIP可见");
                mPopupWindow.dismiss();
            }
        });
    }
    @Override
    public void publishAnArticleView(String data) {
        if (null != pd) {
            pd.dismiss();
        }
        T.showShort(AddArticleActivity.this, "灵感贩卖发表成功！");
        AddArticleActivity.this.finish();
    }

    @Override
    public AddArticlePresenter initPresenter() {
        return new AddArticlePresenter();
    }

    @OnClick({R.id.ll_ask_back, R.id.tv_image, R.id.et_title, R.id.et_content, R.id.et_deal_content,R.id.ll_open, R.id.iv_ask_camera,
            R.id.iv_ask_picture, R.id.iv_ask_gold, R.id.tv_preview, R.id.tv_ask_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ask_back:
                AddArticleActivity.this.finish();
                break;
            case R.id.tv_image:
                if (etDealContent.requestFocus()) {
                    RxGalleryFinal
                            .with(AddArticleActivity.this)
                            .image()
                            .radio()
                            .crop()
                            .imageLoader(ImageLoaderType.PICASSO)
                            .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                                @Override
                                protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                    path = imageRadioResultEvent.getResult().getOriginalPath();
                                    file = new File(path);
                                    Picasso.with(getBaseContext()).load(file).into(new Target() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            tvImage.setBackground(new BitmapDrawable(getResources(), bitmap));
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {

                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    });
                                }
                            }).openGallery();
                }
                break;
            case R.id.et_title:
                break;
            case R.id.et_content:
                break;
            case R.id.et_deal_content:
                break;
            case R.id.iv_ask_camera:

                break;
            case R.id.ll_open:
                initPopupWindow();
                break;
            case R.id.iv_ask_picture:
                RxGalleryFinal
                        .with(AddArticleActivity.this)
                        .image()
                        .radio()
                        .crop()
                        .imageLoader(ImageLoaderType.PICASSO)
                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                                String path = imageRadioResultEvent.getResult().getOriginalPath();
                                File file = new File(path);
                                Map<String, String> params = new HashMap<String, String>(0);
                                params.put("userId", userId);
                                presenter.uploadPicturesPresenter(params, file, "file");
                            }
                        }).openGallery();
                break;
            case R.id.iv_ask_gold:
                alertDialog.show();
                break;
            case R.id.tv_preview://预览
                releaseArticle(false);
                break;
            case R.id.tv_ask_release://发布
                showComfirmDialog();
                break;
        }
    }

    public void showComfirmDialog() {
        comfirmDialog = new android.app.AlertDialog.Builder(this).create();
        comfirmDialog.show();
        Window window = comfirmDialog.getWindow();
        WindowManager.LayoutParams lp = comfirmDialog.getWindow().getAttributes();
        lp.width = DisplayUtils.dp2px(AddArticleActivity.this, 300);//定义宽度
        lp.height = DisplayUtils.dp2px(AddArticleActivity.this, 200);//定义高度
        comfirmDialog.getWindow().setAttributes(lp);
        window.setContentView(R.layout.comfirm_dialog_layout);
        TextView tv_reminder = (TextView) window.findViewById(R.id.tv_reminder);
        tv_reminder.setText("确定发布内容");
        Button btn_comfirm_dialog_comfirm = (Button) window.findViewById(R.id.btn_comfirm_dialog_comfirm);
        ImageView iv_comfirm_dialog_cancel = (ImageView) window.findViewById(R.id.iv_comfirm_dialog_cancel);
        btn_comfirm_dialog_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                releaseArticle(true);
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

    private void releaseArticle(boolean isRelease) {
        String title = etTitle.getText().toString();                //标题
        String content = etContent.getHtml();             //非交易内容
        String dealContent = etDealContent.getHtml();         //交易内容
        String dealContribution = et_ask_pop_insertgold.getText().toString().trim();     //交易币
        if (title.equals("")) {
            T.showLong(AddArticleActivity.this, "标题不能为空！");
            return;
        }
        if (null != content && content.equals("")) {
            T.showLong(AddArticleActivity.this, "非交易内容不能为空！");
            return;
        }
        if (dealContent.equals("")) {
            T.showLong(AddArticleActivity.this, "交易内容为空！");
            return;
        }
        if (null == file) {
            T.showLong(AddArticleActivity.this, "请选择背景图片！");
            return;
        }
        if (isRelease) {
            pd = new ProgressDialog(AddArticleActivity.this);
            pd.setTitle("提示");
            pd.setMessage("正在添加灵感贩卖、、、");
            pd.show();
            Map<String, String> formData = new HashMap<String, String>(0);
            formData.put("userId", userId);
            formData.put("title", title);
            formData.put("status",status+"");
            formData.put("content", content);
            formData.put("dealContent", dealContent);
            formData.put("dealContribution", dealContribution);
            presenter.publishAnArticlePresenter(formData, file, "file");
        } else {
            DealBuyBean bean = new DealBuyBean();
            bean.setUserId(userId);
            bean.setTitle(title);
            bean.setContent(content);
            bean.setDealContent(dealContent);
            bean.setImage(path);
            bean.setStatus(status);
            if (!dealContribution.equals("")) {
                bean.setDealContribution(Integer.parseInt(dealContribution));
            } else {
                bean.setDealContribution(0);
            }
            Intent intent = new Intent(AddArticleActivity.this, PreviewActivity.class);
            intent.putExtra("bean", bean);
            startActivity(intent);
        }
    }


    /**
     * 布局底部移动超过屏幕高度的四分之一则判断软键盘弹出或关闭
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
            llAskInsert.setVisibility(View.VISIBLE);
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
            llAskInsert.setVisibility(View.INVISIBLE);
        }
    }

    public static void setLayoutY(View view, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin, y, margin.rightMargin, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    public int getViewHeight(final View messureView) {
        return messureView.getMeasuredHeight();
    }

}
