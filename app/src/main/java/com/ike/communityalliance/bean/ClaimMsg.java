package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/5/17.
 */

public class ClaimMsg {
    private List<MineCliamMsgBean> claimMsg;
    private  List<MineCliamMsgBean> beClaim;


    public ClaimMsg(List<MineCliamMsgBean> claimMsg, List<MineCliamMsgBean> beClaim) {
        this.claimMsg = claimMsg;
        this.beClaim = beClaim;
    }

    public List<MineCliamMsgBean> getClaimMsg() {
        return claimMsg;
    }

    public void setClaimMsg(List<MineCliamMsgBean> claimMsg) {
        this.claimMsg = claimMsg;
    }

    public List<MineCliamMsgBean> getBeClaim() {
        return beClaim;
    }

    public void setBeClaim(List<MineCliamMsgBean> beClaim) {
        this.beClaim = beClaim;
    }
}
