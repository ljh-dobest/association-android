package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.RelationshipBean;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsPresenter;
import com.ike.communityalliance.interfaces.IInterpersonalConnectionsView;
import com.ike.communityalliance.listener.InterpersonalConnectionsListener;
import com.ike.communityalliance.module.InterpersonalConnectionsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by T-BayMax on 2017/3/14.
 */

public class InterpersonalConnectionsPresenter extends BasePersenter<IInterpersonalConnectionsView> implements IInterpersonalConnectionsPresenter,InterpersonalConnectionsListener {
    private InterpersonalConnectionsModel model;
    public InterpersonalConnectionsPresenter(){
        this.model=new InterpersonalConnectionsModel();
    }
    @Override
    public void onConnectionsError(String string) {
        if (mView!=null){
            mView.onConnectionsError(string);
        }
    }

    @Override
    public void onConnectionsSucceed(List<RelationshipBean> data) {
        if (mView!=null){
            mView.onConnectionsSucceed(data);
        }
    }

    @Override
    public void getConnectionsData(Map<String,String> uid) {

            model.getConnectionData(uid,this);

    }
}
