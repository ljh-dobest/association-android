package com.ike.sq.commonwealactives.presenters;


import com.ike.sq.commonwealactives.base.presenter.BasePersenter;
import com.ike.sq.commonwealactives.bean.MessageBean;
import com.ike.sq.commonwealactives.interfaces.IMessageListView;
import com.ike.sq.commonwealactives.listeners.OnMessageListListener;
import com.ike.sq.commonwealactives.model.MessageModel;

import java.util.ArrayList;

/**
 *Created by T-BayMax on 2017/3/20.
 */

public class MessagePresenter extends BasePersenter<IMessageListView> implements OnMessageListListener {
    private MessageModel recommendInfoMoudle;

    public MessagePresenter() {
        recommendInfoMoudle = new MessageModel();
    }

    public void ShareInfoPresenter(String userId) {
        recommendInfoMoudle.getCommentMessageInfo(userId, this);
    }

    @Override
    public void getCommentMessageInfo(ArrayList<MessageBean> data) {
        mView.setCommentMessageListData(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
