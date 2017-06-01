package com.ike.communityalliance.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.interfaces.IPersonalApplayView;
import com.ike.communityalliance.listener.OnPersonalApplayFinishListener;
import com.ike.communityalliance.module.PersonalApplayMoudle;

import java.util.ArrayList;

/**
 * Created by just on 2017/3/5.
 */

public class PersonalApplayPresenter extends BasePersenter<IPersonalApplayView> implements OnPersonalApplayFinishListener {
    private PersonalApplayMoudle personalApplayMoudle;
    public PersonalApplayPresenter() {
        this.personalApplayMoudle = new PersonalApplayMoudle();
    }



    public void postVipInfo(PersonalVipBean personalVipBean) {
        personalApplayMoudle.personalApplay(personalVipBean,this);
    }


    public void getParserData(Context context, String fileName) {
        personalApplayMoudle.getParserData(context,fileName,this);
    }


    public void getHobby(ViewGroup group) {
        personalApplayMoudle.getHobby(group,this);
    }


    public void getCharacters(ViewGroup group) {
        personalApplayMoudle.getCharacters(group,this);
    }

    @Override
    public void showTextEmpty() {
        if (mView!=null){
            mView.showTextEmpty();
        }
    }

    @Override
    public void showRecommedError(String string) {
        if (mView!=null){
            mView.showRecommedError(string);
        }
    }

    @Override
    public void succeedToRecommed(String recommedId) {
        if (mView!=null){
            mView.succeedToRecommed(recommedId);
        }
    }

    @Override
    public void returnParserData(ArrayList<ProvinceBean> provinces) {
        if(mView!=null){
            mView.getparserData(provinces);
        }
    }

    @Override
    public void returnHobbys(String hobbys) {
        if(mView!=null){
            mView.setHobbys(hobbys);
        }
    }

    @Override
    public void returnCharacters(String characters) {
        if(mView!=null){
            mView.setCharacters(characters);
        }
    }


}
