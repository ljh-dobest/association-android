package com.ike.coalition.platform.presenters;


import com.ike.coalition.platform.base.presenter.BasePersenter;
import com.ike.coalition.platform.bean.CommentsBean;
import com.ike.coalition.platform.interfaces.ICommentMessageListView;
import com.ike.coalition.platform.listeners.OnCommentMessageListListener;
import com.ike.coalition.platform.model.CommentMessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class CommentMessagePresenter extends BasePersenter<ICommentMessageListView> implements OnCommentMessageListListener {
    private CommentMessageModel model;

    public CommentMessagePresenter() {
        model = new CommentMessageModel();
    }

    public void ShareInfoPresenter(Map<String,String> formData) {
        model.getCommentMessageInfo(formData, this);
    }

    @Override
    public void getCommentMessageInfo(List<CommentsBean> data) {
        mView.setCommentMessageListData(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
