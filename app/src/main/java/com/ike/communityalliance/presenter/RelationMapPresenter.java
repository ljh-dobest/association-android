package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.RelationBean;
import com.ike.communityalliance.interfaces.IRelationMapPresenter;
import com.ike.communityalliance.interfaces.IRelationMapView;
import com.ike.communityalliance.listener.RelationMapListener;
import com.ike.communityalliance.module.RelationMapModel;

import java.util.Map;


/**
 * Created by T-BayMax on 2017/3/11.
 */

public class RelationMapPresenter extends BasePersenter<IRelationMapView> implements IRelationMapPresenter, RelationMapListener {
    private RelationMapModel moudle;

    public RelationMapPresenter() {
        this.moudle = new RelationMapModel();
    }

    @Override
    public void onRelationError(String string) {
        mView.onRelationFailed(string);
    }

    @Override
    public void onRelationSucceed(RelationBean relation) {
        mView.onRelationSucceed(relation);
    }

    @Override
    public void postRelationMapData(Map<String, String> formData) {
        moudle.postRelationMap(formData, this);
    }
}
