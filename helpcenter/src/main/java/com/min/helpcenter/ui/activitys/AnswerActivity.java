package com.min.helpcenter.ui.activitys;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
<<<<<<< HEAD
import com.ike.mylibrary.widget.dialog.LoadDialog;
=======
>>>>>>> ljh
import com.min.helpcenter.R;
import com.min.helpcenter.adapters.AnswerRvAdapter;
import com.min.helpcenter.base.view.BaseMvpActivity;
import com.min.helpcenter.bean.AnswersBean;
import com.min.helpcenter.bean.ArticleCommentBean;
import com.min.helpcenter.bean.BaseBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.interfaces.IAnswerView;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.presenters.AnswerPresenter;
import com.min.helpcenter.utils.T;
import com.min.helpcenter.views.CircleImageView;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AnswerActivity extends BaseMvpActivity<IAnswerView,AnswerPresenter> implements IAnswerView, AnswerRvAdapter.OnItemClickLitener {
    @BindView(R.id.tv_answer_title)
    TextView tv_answer_title;
    @BindView(R.id.tv_answer_accept)
    TextView tv_answer_accept;
   @BindView(R.id.ll_answer_back)
    LinearLayout ll_answer_back;
    @BindView(R.id.iv_answer_icon)
    CircleImageView iv_answer_icon;
    @BindView(R.id.tv_answer_name)
    TextView tv_answer_name;
    @BindView(R.id.tv_answer_time)
    TextView tv_answer_time;
    @BindView(R.id.tv_answer_content)
    TextView tv_answer_content;
    @BindView(R.id.tv_answer_goodNum)
    TextView tv_answer_goodNum;
    @BindView(R.id.rv_Answer)
    RecyclerView rv_Answer;
    @BindView(R.id.et_answer_evaluate)
    EditText et_answer_evaluate;
    @BindView(R.id.btn_answer_sendEvaluate)
    Button btn_answer_sendEvaluate;
    private AnswerRvAdapter adapter;
    private String title;
   private  boolean isHelper=false;
   private  boolean isHasGood=false;
    private ArticleCommentBean articleComment;
    private AnswersBean answersBean;
    private String seekId;
    private  String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        userId=getIntent().getStringExtra("userId");
        isHelper=getIntent().getBooleanExtra("isHelper",false);
        isHasGood=getIntent().getBooleanExtra("isHasGood",false);
        title=getIntent().getStringExtra("title");
        answersBean=getIntent().getParcelableExtra("answer");
        seekId=getIntent().getStringExtra("seekId");
        initView();
        getArticleCommentData();
    }

    private void initView() {
       if(isHelper&&!isHasGood){
           tv_answer_accept.setVisibility(View.VISIBLE);
       }
        if(answersBean!=null){
            tv_answer_title.setText(title);
            Picasso.with(this).load(HttpUtils.IMAGE_URL+answersBean.getUserPortraitUrl()).into(iv_answer_icon);
            tv_answer_name.setText(answersBean.getNickname());
            tv_answer_time.setText(answersBean.getTime());
            tv_answer_content.setText(answersBean.getContent());
            tv_answer_goodNum.setText(answersBean.getLikes());
        }
        initRv();
    }

    private void initRv() {
        adapter=new AnswerRvAdapter(this);
        adapter.setOnItemClickLitener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_Answer.setLayoutManager(layoutManager);
        rv_Answer.setAdapter(adapter);
        rv_Answer.setItemAnimator(new DefaultItemAnimator());
        rv_Answer.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }

    @Override
    public AnswerPresenter initPresenter() {
        return new AnswerPresenter();
    }


    @Override
    public void getArticleCommentData() {
        presenter.getAnswersDetail(answersBean.getId(),userId,"4");
    }

    @Override
    public void setArticleComment(ArticleCommentBean data) {
        this.articleComment=data;
        adapter.setmDatas(data.getComments());
    }

    @Override
    public void showError(String errorString) {
<<<<<<< HEAD
        LoadDialog.dismiss(this);
=======
>>>>>>> ljh
        T.showShort(this,errorString);
    }

    @Override
    public void showSucceedComment(String msg) {
<<<<<<< HEAD
        LoadDialog.dismiss(this);
=======
>>>>>>> ljh
        et_answer_evaluate.setText("");
        getArticleCommentData();
        T.showShort(this,msg);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
    @OnClick({R.id.ll_answer_back,R.id.btn_answer_sendEvaluate,R.id.tv_answer_accept})
    public void onAnswerViewClick(View view){
        switch (view.getId()) {
                case R.id.ll_answer_back:
                    finish();
                    break;
            case R.id.tv_answer_accept:

                 HttpUtils.answerSeekAdopt("/answerSeekAdopt", userId,seekId,answersBean.getId(),new StringCallback() {
                     @Override
                     public void onError(Call call, Exception e, int id) {
                         T.showShort(AnswerActivity.this,e.toString());
                     }

                     @Override
                     public void onResponse(String response, int id) {
                         Gson gson=new Gson();
                         Type type = new TypeToken<Code<BaseBean>>() {
                         }.getType();
                         Code<BaseBean> code = gson.fromJson(response,type);
                         switch (code.getCode()) {
                             case 200:
                                 T.showShort(AnswerActivity.this,"采纳成功");
                                 setResult(RESULT_OK);
                                 finish();
                                 break;
                             case 0:
                                 T.showShort(AnswerActivity.this,"已采纳");
                                 break;
                             case 102:
                                 T.showShort(AnswerActivity.this,"非法操作");
                                 break;
                         }
                     }
                 });
                break;
            case R.id.btn_answer_sendEvaluate:
<<<<<<< HEAD
                LoadDialog.show(this);
=======
>>>>>>> ljh
               String content=et_answer_evaluate.getText().toString();
                presenter.commentAnswer(answersBean.getId(),userId,content,"4");
                break;
        }
    }
}
