package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.ClaimPeopleBean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/9.
 */

public interface IClaimCenterView extends BaseView {
    void setPeoplesData(ArrayList<ClaimPeopleBean> data);
}
