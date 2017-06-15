package com.ike.communityalliance.presenter;


import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.UserInfo;
import com.ike.communityalliance.interfaces.IMineFragmentView;
import com.ike.communityalliance.listener.OnGetMineUserInfoFinishListener;
import com.ike.communityalliance.module.MineFragmentModule;

/**
 * Created by Min on 2017/3/9.
 */

public class MineFragmentPresenter extends BasePersenter<IMineFragmentView> implements OnGetMineUserInfoFinishListener {
    private MineFragmentModule mineFragmentModule;
    public MineFragmentPresenter() {
        mineFragmentModule=new MineFragmentModule();
    }
    public void getMineUserInfoData(String useId){
        mineFragmentModule.getMineUserInfo(useId,this);
    }

    @Override
    public void showError(String errorString) {
        if (mView!=null){
            mView.showError(errorString);
        }
    }

    @Override
    public void returnMineUserInfo(UserInfo data) {
        if (mView!=null){
            mView.setData(data);
        }
    }
}
