package com.ike.communityalliance.interfaces;

import com.ike.communityalliance.bean.RelationshipBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/3/14.
 */

public interface IInterpersonalConnectionsView {
    /**
     * 请求失败
     * @param string
     */
    void onConnectionsError(String string);

    /**
     * 请求成功
     * @param data
     */
    void onConnectionsSucceed(List<RelationshipBean> data);
    void addFriends(String data);
}
