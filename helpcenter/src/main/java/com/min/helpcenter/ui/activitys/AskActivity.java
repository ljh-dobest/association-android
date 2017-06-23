package com.min.helpcenter.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.R;
import com.min.helpcenter.base.view.BaseActivity;
import com.min.helpcenter.bean.BaseBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.utils.PreferenceService;
import com.min.helpcenter.utils.T;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;
import jp.wasabeef.richeditor.RichEditor;
import okhttp3.Call;

public class AskActivity extends BaseActivity implements View.OnLayoutChangeListener{
    @BindView(R.id.activity_ask)
    RelativeLayout activity_ask;
    @BindView(R.id.ll_ask_back)
    LinearLayout ll_ask_back;
    @BindView(R.id.ll_ask_insert)
    LinearLayout ll_ask_insert;
    @BindView(R.id.tv_ask_release)
    TextView tv_ask_release;
    @BindView(R.id.et_ask_content)
    EditText et_ask_content;
    @BindView(R.id.et_ask_explain)
    RichEditor et_ask_explain;
    @BindView(R.id.iv_ask_gold)
    ImageView iv_ask_gold;
    @BindView(R.id.iv_ask_picture)
    ImageView iv_ask_picture;
    //屏幕高度
    private EditText et_ask_pop_insertgold;
    private int screenHeight = 0;
    private  int screenWidth=0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private View goldPopupWindow;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private int goldNum=0;
    private PreferenceService preferenceService;
    private  String userId;
    private String contributionCoin="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);
        userId=getIntent().getStringExtra("userId");
        ModelUtils.setDebugModel(true);
        //获取屏幕高度
        screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
        screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/4;
        initView();
    }

    private void initView() {
        et_ask_explain.setEditorHeight(300);
        activity_ask.addOnLayoutChangeListener(this);
        initDialog();
    }

private void initDialog(){
    builder = new AlertDialog.Builder(this);
    LayoutInflater inflater= (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    goldPopupWindow=inflater.inflate(R.layout.popupwindow_insert_gold,null);
    et_ask_pop_insertgold= (EditText) goldPopupWindow.findViewById(R.id.et_ask_pop_insertgold);
    Button btn= (Button) goldPopupWindow.findViewById(R.id.btn_ask_pop_insertgold);
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            contributionCoin=et_ask_pop_insertgold.getText().toString();
            alertDialog.dismiss();
        }
    });
    builder.setView(goldPopupWindow);
    alertDialog=builder.create();
}

    @OnClick({R.id.ll_ask_back,R.id.iv_ask_gold,R.id.iv_ask_picture,R.id.tv_ask_release})
    public void onAskViewClick(View view){
        switch (view.getId()) {
            case R.id.tv_ask_release:
                 String title=et_ask_content.getText().toString();
                String explain= et_ask_explain.getHtml();
                if(explain==null){
                    explain="";
                }
                if(title.equals("")||explain.equals("")){
                    T.showShort(this,"标题或问题描述不能为空");
                    return;
                }
                HttpUtils.postSeekHelp("/addSeekHelp", userId,title, explain,contributionCoin, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        T.showShort(AskActivity.this,"内容或问题描述不能为空");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson=new Gson();
                        Type type = new TypeToken<Code<BaseBean>>() {
                        }.getType();
                        Code<BaseBean> code = gson.fromJson(response,type);
                        if(code.getCode()==200){
                            T.showShort(AskActivity.this,"发布成功");
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            T.showShort(AskActivity.this,"发布失败");
                        }
                    }
                });
                break;
            case R.id.ll_ask_back:
                finish();
                break;
            case R.id.iv_ask_gold:
                alertDialog.show();
                break;
            case R.id.iv_ask_picture:
                RxGalleryFinal
                        .with(AskActivity.this)
                        .image()
                        .radio()
                        .crop()
                        .imageLoader(ImageLoaderType.PICASSO)
                        .subscribe(new RxBusResultSubscriber<ImageRadioResultEvent>() {
                            @Override
                            protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                               String path=imageRadioResultEvent.getResult().getOriginalPath();
                                File file=new File(path);
                                HttpUtils.postImage("/files", userId, file, new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        T.showShort(AskActivity.this,"上传失败");
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Gson gson=new Gson();
                                        Type type = new TypeToken<Code<BaseBean>>() {
                                        }.getType();
                                        Code<BaseBean> code = gson.fromJson(response,type);
                                        switch (code.getCode()) {
                                            case 200:
                                                String imgUrl=HttpUtils.IMAGE_URL+code.getData().getImage();
                                                et_ask_explain.insertImage(imgUrl, "dachshund");
                                                T.showShort(AskActivity.this,"上传成功");
                                                break;
                                            case 0:
                                                T.showShort(AskActivity.this,"上传失败");
                                                break;
                                        }
                                    }
                                });
                            }
                        })
                        .openGallery();
                break;
        }
    }


    /**
     *布局底部移动超过屏幕高度的四分之一则判断软键盘弹出或关闭
     */
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){
            ll_ask_insert.setVisibility(View.VISIBLE);
        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            ll_ask_insert.setVisibility(View.INVISIBLE);
        }
    }
    public static void setLayoutY(View view,int y)
    {
        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin,y, margin.rightMargin, y+margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
    public int getViewHeight(final View messureView) {
        return messureView.getMeasuredHeight();
    }

}
