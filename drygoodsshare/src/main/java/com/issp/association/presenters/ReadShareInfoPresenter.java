package com.issp.association.presenters;

import com.issp.association.base.presenter.BasePersenter;
import com.issp.association.bean.ShareBean;
import com.issp.association.interfaces.IReadShareView;
import com.issp.association.interfaces.IShareListView;
import com.issp.association.listeners.OnReadShareListener;
import com.issp.association.listeners.OnShareListener;
import com.issp.association.model.ReadShareInfoModel;
import com.issp.association.model.ShareInfoModel;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public class ReadShareInfoPresenter extends BasePersenter<IReadShareView> implements OnReadShareListener {
    private ReadShareInfoModel model;

    public ReadShareInfoPresenter() {
        model = new ReadShareInfoModel();
    }

    public void ReadShareInfoPresenter(Map<String, String> formData) {
        model.getReadShareInfo(formData, this);
    }

    public void sharePraiseInfoPresenter(Map<String, String> formData) {
        model.getSharePraiseInfo(formData, this);
    }
    public void collectPresenter(Map<String, String> formData){
        model.getCollect(formData,this);
    }

    @Override
    public void getReadShareInfo(ShareBean data) {
        mView.setReadShareData(data);
    }

    @Override
    public void sharePraiseInfo(String data) {
        mView.sharePraise(data);
    }

    @Override
    public void collectListener(String data) {
        mView.collect(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
