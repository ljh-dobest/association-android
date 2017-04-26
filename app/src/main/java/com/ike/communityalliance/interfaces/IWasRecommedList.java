package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.RecommendInfoBean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/9.
 */

public interface IWasRecommedList extends BaseView {
    void setRecommedListData(ArrayList<RecommendInfoBean> data);
}
