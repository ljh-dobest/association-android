package com.ike.sq.commonwealactives.interfaces;



import com.ike.sq.commonwealactives.base.view.BaseView;
import com.ike.sq.commonwealactives.bean.MessageBean;

import java.util.ArrayList;

/**
 * 消息
 *Created by T-BayMax on 2017/3/20.
 */

public interface IMessageListView extends BaseView {
    void setCommentMessageListData(ArrayList<MessageBean> data);
}
