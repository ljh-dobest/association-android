package com.issp.association.presenters;

import com.issp.association.base.presenter.BasePersenter;
import com.issp.association.bean.ImageUrlBean;
import com.issp.association.bean.ShareBean;
import com.issp.association.interfaces.IShareListView;
import com.issp.association.listeners.OnShareListener;
import com.issp.association.model.ShareInfoModel;

import java.util.ArrayList;
import java.util.Map;

/**
 *Created by T-BayMax on 2017/3/13.
 */

public class ShareInfoPresenter extends BasePersenter<IShareListView> implements OnShareListener {
    private ShareInfoModel model;

    public ShareInfoPresenter() {
        model = new ShareInfoModel();
    }

    public void ShareInfoPresenter(Map<String, String> formData) {
        model.getShareInfo(formData, this);
    }

    public void getImage(Map<String ,String> formData){
        model.getImageUrl(formData,this);
    }

    public void sharePraiseInfoPresenter(Map<String, String> formData) {
        model.getSharePraiseInfo(formData, this);
    }

    @Override
    public void getShareInfo(ArrayList<ShareBean> data) {
        mView.setShareListData(data);
    }
    @Override
    public void getImageUrl(ImageUrlBean bean) {
        mView.getImageUrlView(bean);
    }
    @Override
    public void sharePraiseInfo(String data) {
        mView.sharePraise(data);
    }

    @Override
    public void showError(String errorString) {
        mView.showError(errorString);
    }
}
