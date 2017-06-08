package com.issp.association.crowdfunding.presenters;


import com.issp.association.crowdfunding.base.presenter.BasePersenter;
import com.issp.association.crowdfunding.bean.CommentsBean;
import com.issp.association.crowdfunding.bean.MessageBean;
import com.issp.association.crowdfunding.interfaces.IMessageListView;
import com.issp.association.crowdfunding.listeners.OnMessageListListener;
import com.issp.association.crowdfunding.model.MessageModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class MessagePresenter extends BasePersenter<IMessageListView> implements OnMessageListListener {
    private MessageModel recommendInfoMoudle;

    public MessagePresenter() {
        recommendInfoMoudle = new MessageModel();
    }

    public void ShareInfoPresenter(Map<String, String> formData) {
        recommendInfoMoudle.getCommentMessageInfo(formData, this);
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
