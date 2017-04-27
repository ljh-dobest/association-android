package com.ike.communityalliance.bean;

import java.util.List;

/**
 * Created by Min on 2017/4/26.
 */

public class HomePageBean {
    private List<AdvsBean> advs;
    private List<ClaimUsers> claimUsers;
    private List<ActivesBean> actives;

    public HomePageBean(List<AdvsBean> advs, List<ClaimUsers> claimUsers, List<ActivesBean> actives) {
        this.advs = advs;
        this.claimUsers = claimUsers;
        this.actives = actives;
    }

    public List<AdvsBean> getAdvs() {
        return advs;
    }

    public void setAdvs(List<AdvsBean> advs) {
        this.advs = advs;
    }

    public List<ClaimUsers> getClaimUsers() {
        return claimUsers;
    }

    public void setClaimUsers(List<ClaimUsers> claimUsers) {
        this.claimUsers = claimUsers;
    }

    public List<ActivesBean> getActives() {
        return actives;
    }

    public void setActives(List<ActivesBean> actives) {
        this.actives = actives;
    }
}
