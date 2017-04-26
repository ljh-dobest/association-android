package com.ike.communityalliance.presenter;

import com.ike.communityalliance.base.BasePersenter;
import com.ike.communityalliance.bean.InterestGroupBean;
import com.ike.communityalliance.interfaces.IChessAndCardsView;
import com.ike.communityalliance.listener.OnGetInterestGroupDataFinishListener;
import com.ike.communityalliance.module.ChessAndCardsModule;

import java.util.List;

/**
 * Created by Min on 2017/3/20.
 */

public class ChessAndCardsPresnter extends BasePersenter<IChessAndCardsView> implements OnGetInterestGroupDataFinishListener {
    private ChessAndCardsModule chessAndCardsModule;
    public ChessAndCardsPresnter() {
              chessAndCardsModule=new ChessAndCardsModule();
    }
    public void getInterestGroupData(String useId,String hobbyId){
        chessAndCardsModule.getInterestGroupData(useId,hobbyId,this);
    }

    @Override
    public void returnInterestGroupData(List<InterestGroupBean> gropsData) {
        if (mView!=null){
            mView.setListData(gropsData);
        }
    }

    @Override
    public void showErrorString(String errorString) {
        if(mView!=null){
            mView.showErrorString(errorString);
        }
    }
}
