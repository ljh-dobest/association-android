package com.ike.communityalliance.listener;


import com.ike.communityalliance.bean.ProvinceBean;

import java.util.ArrayList;

/**
 * Created by just on 2017/3/5.
 */

public interface OnClaimFinishListener {
    void showTextEmpty();
    void succeedToClaim();
    void failedToClaim(String string);
    void returnParserData(ArrayList<ProvinceBean> provinces);
    void retturnHobbys(String hobbys);
}
