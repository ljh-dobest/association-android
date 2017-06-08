package com.ike.sq.commonwealactives.presenters;


import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.CommentsBean;
import com.ike.sq.commonwealactives.bean.MessageBean;
import com.ike.sq.commonwealactives.interfaces.IMessageListView;
import com.ike.sq.commonwealactives.listeners.OnMessageListListener;
import com.ike.sq.commonwealactives.model.MessageModel;

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
