package com.smileflowpig.money.factory.presenter.statehome;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.examine.BannerExprt;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ExpertContract {

    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void refreshData();
        void setPage(int page);
        void hasNet(boolean flag);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void getHomeData(BannerExprt bannerExprt);

        void NoData();

        void getRefreshData(BannerExprt bannerExprt);
    }
}
