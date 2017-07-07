package com.min.threeminutestoteach.ui.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ike.mylibrary.util.T;
import com.ike.mylibrary.widget.dialog.LoadDialog;
import com.min.threeminutestoteach.R;
import com.min.threeminutestoteach.adpter.AnswerRvAdapter;
import com.min.threeminutestoteach.base.BaseMvpActivity;
import com.min.threeminutestoteach.bean.ArticleCommentBean;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.interfaces.IAnswerView;
import com.min.threeminutestoteach.presenter.AnswerPresenter;
import com.min.threeminutestoteach.utils.HttpUtils;
import com.min.threeminutestoteach.views.XCRoundRectImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscussionActivity extends BaseMvpActivity<IAnswerView, AnswerPresenter> implements IAnswerView, AnswerRvAdapter.OnItemClickLitener, AnswerRvAdapter.OnLikeClickLitener {

    @BindView(R.id.ll_answer_back)
    LinearLayout llAnswerBack;
    @BindView(R.id.iv_answer_userHeader)
    XCRoundRectImageView ivAnswerUserHeader;
    @BindView(R.id.tv_answer_title)
    TextView tvAnswerTitle;
    @BindView(R.id.rv_Answer)
    RecyclerView rvAnswer;
    @BindView(R.id.et_answer_evaluate)
    EditText etAnswerEvaluate;
    @BindView(R.id.btn_answer_sendEvaluate)
    Button btnAnswerSendEvaluate;
    @BindView(R.id.activity_answer)
    RelativeLayout activityAnswer;
    @BindView(R.id.ll_answer_null)
    LinearLayout llAnswerNull;

    private AnswerRvAdapter adapter;
    private TeacheBean teacheBean;
    private String userId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        userId = getIntent().getStringExtra("userId");
        teacheBean = getIntent().getParcelableExtra("teacheBean");
        initView();
        getArticleCommentData();
    }

    private void initView() {
        if (teacheBean != null) {
            tvAnswerTitle.setText(teacheBean.getTitle());
            Picasso.with(this).load(HttpUtils.IMAGE_URL + teacheBean.getUserPortraitUrl()).into(ivAnswerUserHeader);
        }
        initRv();
    }

    private void initRv() {
        adapter = new AnswerRvAdapter(this, userId);
        adapter.setOnItemClickLitener(this);
        adapter.setOnLikeClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rvAnswer.setLayoutManager(layoutManager);
        rvAnswer.setAdapter(adapter);
        rvAnswer.setItemAnimator(new DefaultItemAnimator());
        rvAnswer.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
    }

    @Override
    public AnswerPresenter initPresenter() {
        return new AnswerPresenter();
    }


    @Override
    public void getArticleCommentData() {
        presenter.getAnswersDetail(teacheBean.getId(), userId, "9");
    }

    @Override
    public void setArticleComment(ArticleCommentBean data) {
        adapter.setmDatas(data.getComments());
        llAnswerNull.setVisibility(data.getComments().size() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showError(String errorString) {
        LoadDialog.dismiss(this);
        T.showShort(this, errorString);
    }

    @Override
    public void showSucceedComment(String msg) {
        LoadDialog.dismiss(this);
        etAnswerEvaluate.setText("");
        getArticleCommentData();
        T.showShort(this, msg);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @OnClick({R.id.ll_answer_back, R.id.btn_answer_sendEvaluate})
    public void onAnswerViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_answer_back:
                finish();
                break;
            case R.id.btn_answer_sendEvaluate:
                LoadDialog.show(this);
                String content = etAnswerEvaluate.getText().toString();
                presenter.commentAnswer(teacheBean.getId(), userId, content, "9");
                break;
        }
    }

    @Override
    public void onLikeClick() {
        getArticleCommentData();
    }
}
