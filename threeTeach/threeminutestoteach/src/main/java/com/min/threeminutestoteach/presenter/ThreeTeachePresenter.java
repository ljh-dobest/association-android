package com.min.threeminutestoteach.presenter;

import com.min.threeminutestoteach.base.BasePersenter;
import com.min.threeminutestoteach.bean.TeacheBean;
import com.min.threeminutestoteach.interfaces.IThreeTeacheView;
import com.min.threeminutestoteach.listener.OnFinishGetThreeTeachListener;
import com.min.threeminutestoteach.module.ThreeTeachModule;

import java.util.List;

/**
 * Created by Min on 2017/6/12.
 */

public class ThreeTeachePresenter extends BasePersenter<IThreeTeacheView> implements OnFinishGetThreeTeachListener {
    ThreeTeachModule threeTeachModule;
    public  ThreeTeachePresenter(){
        threeTeachModule=new ThreeTeachModule();
    }
public void getThreeTeachData(String userId,String page){
    threeTeachModule.getThreeTeachData(userId,page,this);
}

    @Override
    public void showError(String errorString) {
        if(mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnThreeTeachData(List<TeacheBean> data) {
        if(mView!=null){
            mView.setThreeTeacheData(data);
        }
    }

    @Override
    public void returnMoreTeachData(List<TeacheBean> data) {
        if(mView!=null){
            mView.setMoreData(data);
        }
    }
}
