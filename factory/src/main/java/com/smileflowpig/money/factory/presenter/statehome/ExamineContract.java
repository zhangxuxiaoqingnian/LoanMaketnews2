package com.smileflowpig.money.factory.presenter.statehome;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.examine.Banner;
import com.smileflowpig.money.factory.model.api.examine.RspExamHomeModel;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public interface ExamineContract {

    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void refreshData();

        void getInfoList(int index);

        void setPage(int page);

        void hasNet(boolean flag);

    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void getHomeData(RspExamHomeModel rspExamHomeModel);

        void tabList(Banner banner, List<String> strings);

        void NoData();

        void getRefreshData(RspExamHomeModel rspExamHomeModel);
    }
}
