package com.issp.association.listeners;


import com.issp.association.bean.ImageUrlBean;
import com.issp.association.bean.ShareBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.

 */

public interface OnShareListener {
    void getShareInfo(ArrayList<ShareBean> data);

    void sharePraiseInfo(String data);
    void getImageUrl(List<ImageUrlBean> bean);
    void showError(String errorString);
}
