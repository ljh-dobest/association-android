package com.ike.sq.commonwealactives.interfaces;



import com.ike.sq.commonwealactives.base.view.BaseView;
import com.ike.sq.commonwealactives.bean.UserBean;

import java.util.List;

/**
 * Created by T-BayMax on 2017/3/13.
 */

public interface IBenefitRegisteredListView extends BaseView {
    void setPlatformRegisteredListData(List<UserBean> data);

   // void dealBuyPraise(String data);
}
