package com.ike.communityalliance.interfaces;

import android.content.Context;
import android.view.ViewGroup;

import com.ike.communityalliance.bean.PersonalVipBean;


/**
 * Created by just on 2017/3/5.
 */

public interface IRecommedPresenter {
    void verifyRecommedInfo(PersonalVipBean recommendBean);
   void getParserData(Context context, String fileName);
    void getHobby(ViewGroup group);
    void getCharacters(ViewGroup group);
}
