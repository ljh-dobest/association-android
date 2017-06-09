package com.ike.communityalliance.interfaces;

import android.view.ViewGroup;

import com.ike.communityalliance.base.BaseView;
import com.ike.communityalliance.bean.ProvinceBean;

import java.util.ArrayList;

/**
 * Created by Min on 2017/3/10.
 */

public interface IClaimInfoView extends BaseView {
    void showTextEmpty();
    void showSucceedClaim();
    void showFailClaim();
    void showWaitClaim();
    void getHobbys(ViewGroup group);
    void setHobbys(String hobbys);
    void setprovinceData(ArrayList<ProvinceBean> data);
    void getCharacters(ViewGroup group);
    void setCharacters(String characters);
}
