package com.issp.association.crowdfunding.interfaces;



import com.issp.association.crowdfunding.base.view.BaseView;
import com.issp.association.crowdfunding.bean.CommentsBean;
import com.issp.association.crowdfunding.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息
 *Created by T-BayMax on 2017/3/20.
 */

public interface IMessageListView extends BaseView {
    void setCommentMessageListData(List<CommentsBean> data);
}
