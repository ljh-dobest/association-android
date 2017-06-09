package com.ike.communityalliance.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.PersonalVipBean;
import com.ike.communityalliance.bean.ProvinceBean;
import com.ike.communityalliance.bean.VerifyRecommedInfo;
import com.ike.communityalliance.interfaces.IVerifyRecommedInfoView;
import com.ike.communityalliance.listener.OnVerifyRecommedInfoFinishListener;
import com.ike.communityalliance.module.VerifyRecommedInfoMoudle;

import java.util.ArrayList;

/**
 * Created by just on 2017/3/5.
 */

public class VerifyRecommedInfoPresenter extends BasePersenter<IVerifyRecommedInfoView> implements OnVerifyRecommedInfoFinishListener {
    private VerifyRecommedInfoMoudle verifyRecommedInfoMoudle;
    public VerifyRecommedInfoPresenter() {
        verifyRecommedInfoMoudle=new VerifyRecommedInfoMoudle();
    }
    public  void getVerifyRecommendInfo(String userId,String recommendId){
           verifyRecommedInfoMoudle.getVerifyRecommedInfo(userId,recommendId,this);
    }
    public void postVerifyRecommedInfo(PersonalVipBean verifyRecommedInfoBean){
              verifyRecommedInfoMoudle.verifyRecommedInfo(verifyRecommedInfoBean,this);
    }
    public void parserData(Context mcontext, String fileName){
             verifyRecommedInfoMoudle.getParserData(mcontext,fileName,this);
    }
    public void getHobby(ViewGroup group){
        verifyRecommedInfoMoudle.getHobby(group,this);
    }
    public void getCharacters(ViewGroup group) {
        verifyRecommedInfoMoudle.getCharacters(group,this);
    }
    @Override
    public void showTextEmpty() {
        if (mView!=null){
            mView.showTextEmpty();
        }
    }

    @Override
    public void succeedToVerifyInfo() {
        if (mView!=null){
            mView.succeedVerifyInfo();
        }
    }

    @Override
    public void failedToVerifyInfo(String string) {
        if (mView!=null){
            mView.showVerifyInfoError(string);
        }
    }

    @Override
    public void returnParserData(ArrayList<ProvinceBean> data) {
        if (mView!=null){
            mView.setProvinceData(data);
        }
    }

    @Override
    public void returnHobby(String hobbys) {
        if (mView!=null){
            mView.setHobby(hobbys);
        }
    }

    @Override
    public void returnVerifyInfo(VerifyRecommedInfo verifyRecommedInfoBean) {
        if (mView!=null){
            mView.setVerifyInfo(verifyRecommedInfoBean);
        }
    }

    @Override
    public void returnCharacters(String characters) {
        if(mView!=null){
            mView.setCharacters(characters);
        }
    }
}
