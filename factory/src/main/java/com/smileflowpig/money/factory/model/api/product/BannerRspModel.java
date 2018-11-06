package com.smileflowpig.money.factory.model.api.product;

import com.smileflowpig.money.factory.model.db.BannerData;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class BannerRspModel {
    private List<BannerData> homeact;

    public List<BannerData> getHomeact() {
        return homeact;
    }

    public void setHomeact(List<BannerData> homeact) {
        this.homeact = homeact;
    }
}
