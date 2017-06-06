package com.ike.communityalliance.listener;


import com.ike.communityalliance.bean.ProvinceBean;

import java.util.ArrayList;

/**
 * Created by just on 2017/3/5.
 */

public interface OnPersonalApplayFinishListener {
    void showTextEmpty();
    void showRecommedError(String string);
    void succeedToRecommed(String recommedId);
    void returnParserData(ArrayList<ProvinceBean> provinces);
    void returnHobbys(String hobbys);
    void returnCharacters(String characters);
}
