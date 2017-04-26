package com.ike.communityalliance.interfaces;


import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.UserInfo;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/9.
 */

public interface IMineFragmentView extends BaseView {
    void setData(ArrayList<UserInfo> data);
}
