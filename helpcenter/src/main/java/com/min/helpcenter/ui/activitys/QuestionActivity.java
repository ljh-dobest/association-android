package com.min.helpcenter.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.min.helpcenter.R;
import com.min.helpcenter.adapters.QuestionRvAdapter;
import com.min.helpcenter.base.view.BaseMvpActivity;
import com.min.helpcenter.bean.AnswersBean;
import com.min.helpcenter.bean.Code;
import com.min.helpcenter.bean.HelpBean;
import com.min.helpcenter.interfaces.IQuestionView;
import com.min.helpcenter.network.HttpUtils;
import com.min.helpcenter.presenters.QuestionPresenter;
import com.min.helpcenter.utils.T;
import com.min.helpcenter.views.CircleImageView;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.richeditor.RichEditor;
import okhttp3.Call;

public class QuestionActivity extends BaseMvpActivity<IQuestionView, QuestionPresenter> implements IQuestionView, QuestionRvAdapter.OnItemClickLitener, QuestionRvAdapter.SucceedToLikeListener {
    @BindView(R.id.ll_question_back)
    LinearLayout ll_question_back;
    @BindView(R.id.tv_question_answer)
    TextView tv_question_answer;
    @BindView(R.id.iv_question_share)
    ImageView iv_question_share;
    @BindView(R.id.iv_question_good)
    ImageView iv_question_good;
    @BindView(R.id.rv_question)
    RecyclerView rv_question;
    private HelpBean helpBean;
    private QuestionRvAdapter adapter;
    private List<AnswersBean> answers;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private String userId;
    private AnswersBean answersBean;
    private HeaderView headerView;
   private boolean isHelper=false;
    private boolean isHasGood=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        userId = getIntent().getStringExtra("userId");
        helpBean = (HelpBean) getIntent().getSerializableExtra("helpBean");
        initView();
        getQuestionDetails(userId, helpBean.getId());
    }

    /**
     * 初始化数据
     */
    private void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new QuestionRvAdapter(this, userId);
        initHeader();
        adapter.setOnItemClickLitener(this);
        adapter.setSucceedToLikeListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv_question.setLayoutManager(layoutManager);
        rv_question.setAdapter(mHeaderAndFooterWrapper);
        rv_question.setItemAnimator(new DefaultItemAnimator());
        rv_question.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    private void initHeader() {
        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View view = LayoutInflater.from(this).inflate(R.layout.question_header, null);
        headerView = new HeaderView();
        ButterKnife.bind(headerView, view);
        Picasso.with(this).load(HttpUtils.IMAGE_URL + helpBean.getUserPortraitUrl()).into(headerView.iv_question_icon);
        headerView.tv_question_name.setText(helpBean.getNickname());
        headerView.tv_question_time.setText(helpBean.getTime());
        headerView.tv_question_title.setText(helpBean.getTitle());
        headerView.tv_question_content.setHtml(helpBean.getContent());
        headerView.tv_question_responseNum.setText(helpBean.getHelpNumber() + "");
        headerView.tv_question_goldNum.setText(helpBean.getContributionCoin() + "");
        mHeaderAndFooterWrapper.addHeaderView(view);
    }

    @Override
    public QuestionPresenter initPresenter() {
        return new QuestionPresenter();
    }

    @OnClick({R.id.ll_question_back, R.id.tv_question_answer,R.id.iv_question_good})
    public void onQuestionViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_question_back:
                finish();
                break;
            case R.id.iv_question_good:
                userPraise(userId,helpBean.getId(),"8","1");
                break;
            case R.id.tv_question_answer:
                Intent intent = new Intent(this, EvaluateActivity.class);
                intent.putExtra("seekId", helpBean.getId());
                intent.putExtra("userId", userId);
                startActivityForResult(intent, 0);
                break;

        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("title", helpBean.getTitle());
        intent.putExtra("answer", adapter.getmDatas().get(position - 1));
        intent.putExtra("seekId", helpBean.getId());
        intent.putExtra("isHelper",isHelper);
        intent.putExtra("isHasGood",isHasGood);
        intent.putExtra("userId", userId);
        startActivityForResult(intent,0);
    }

    @Override
    public void getQuestionDetails(String userId, String seekId) {
        presenter.getQuestionDetails(userId, seekId);
    }

    @Override
    public void setQuestionDetails(HelpBean helpBean) {
        headerView.tv_question_responseNum.setText(helpBean.getCommentNumber());
        iv_question_good.setImageResource(helpBean.getLikesStatus().equals("0")?R.drawable.ungood:R.drawable.good);
        if (helpBean.getUserId().equals(userId)){
            isHelper=true;
        }
        answersBean = helpBean.getAdopt();
        if(answersBean!=null){
            isHasGood=true;
            initGoodAnswer();
        }
        adapter.setmDatas(helpBean.getAnswers());
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    private void initGoodAnswer() {
        headerView.llGoodAnswer.setVisibility(View.VISIBLE);
        headerView.llGoodAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                intent.putExtra("title", helpBean.getTitle());
                intent.putExtra("answer", answersBean);
                intent.putExtra("seekId", helpBean.getId());
                intent.putExtra("isHelper",isHelper);
                intent.putExtra("isHasGood",isHasGood);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
        Picasso.with(this).load(HttpUtils.IMAGE_URL+answersBean.getUserPortraitUrl()).into(headerView.ivQuestionGoodItemIcon);
        headerView.tvQuestionGoodItemName.setText(answersBean.getNickname());
        headerView.tvQuestionGoodItemTime.setText(answersBean.getTime());
        headerView.tvQuestionGoodItemContent.setText(answersBean.getContent());
        headerView.ivQuestionGoodItemGood.setImageResource(answersBean.getLikesStatus().equals("0")?R.drawable.ungood:R.drawable.good);
        headerView.ivQuestionGoodItemGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPraise(userId,answersBean.getId(),"4","1");
            }
        });
        headerView.tvQuestionGoodItemGoodNum.setText(answersBean.getLikes());
        headerView.tvQuestionGoodItemDiscuNum.setText(answersBean.getCommentNumber());
    }

    private void userPraise(final String userId, String articleId, String type, String status) {
        HttpUtils.postUserPraise("/userPraise", userId, articleId, type, status, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                T.showShort(QuestionActivity.this,e.toString());
            }

            @Override
            public void onResponse(String response, int id) {
                Gson gson=new Gson();
                Type type = new TypeToken<Code<Object>>() {
                }.getType();
                Code<Object> code = gson.fromJson(response,type);
                switch (code.getCode()) {
                    case 200:
                        presenter.getQuestionDetails(userId,helpBean.getId());
                        T.showShort(QuestionActivity.this,"点赞成功");
                        break;
                    case 100:
                        T.showShort(QuestionActivity.this,"请勿重复点赞");
                        break;
                    default:
                        T.showShort(QuestionActivity.this,"点赞失败");
                        break;
                }

            }
        });
    }

    @Override
    public void showError(String errorString) {
        T.showShort(this, errorString);
    }

    @Override
    public void succeedTolike() {
        presenter.getQuestionDetails(userId, helpBean.getId());
    }

    class HeaderView {
        @BindView(R.id.iv_question_icon)
        ImageView iv_question_icon;
        @BindView(R.id.tv_question_name)
        TextView tv_question_name;
        @BindView(R.id.tv_question_time)
        TextView tv_question_time;
        @BindView(R.id.tv_question_title)
        TextView tv_question_title;
        @BindView(R.id.tv_question_content)
        RichEditor tv_question_content;
        @BindView(R.id.tv_question_responseNum)
        TextView tv_question_responseNum;
        @BindView(R.id.tv_question_goldNum)
        TextView tv_question_goldNum;
        @BindView(R.id.iv_question_good_item_icon)
        CircleImageView ivQuestionGoodItemIcon;
        @BindView(R.id.tv_question_good_item_name)
        TextView tvQuestionGoodItemName;
        @BindView(R.id.tv_question_good_item_time)
        TextView tvQuestionGoodItemTime;
        @BindView(R.id.tv_question_good_item_content)
        TextView tvQuestionGoodItemContent;
        @BindView(R.id.iv_question_good_item_good)
        ImageView ivQuestionGoodItemGood;
        @BindView(R.id.tv_question_good_item_goodNum)
        TextView tvQuestionGoodItemGoodNum;
        @BindView(R.id.iv_question_good_item_discu)
        ImageView ivQuestionGoodItemDiscu;
        @BindView(R.id.tv_question_good_item_discuNum)
        TextView tvQuestionGoodItemDiscuNum;
        @BindView(R.id.ll_good_answer)
        AutoLinearLayout llGoodAnswer;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            getQuestionDetails(userId, helpBean.getId());
        }
    }

}
