package com.issp.association.interfaces;


import com.issp.association.base.view.BaseView;
import com.issp.association.bean.ImageUrlBean;
import com.issp.association.bean.ShareBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface IShareListView extends BaseView {
    void setShareListData(ArrayList<ShareBean> data);

    void getImageUrlView(List<ImageUrlBean> bean);

    void sharePraise(String data);
}
