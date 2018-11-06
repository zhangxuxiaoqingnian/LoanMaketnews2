package com.smileflowpig.money.factory.presenter.application;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.ApplyProduct;
import com.smileflowpig.money.factory.model.db.CreditCardAppliProduct;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ApplicationContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void refreshData();
    }
    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void requestData(List<ApplyProduct> products);

        void requestData1(List<CreditCardAppliProduct> products);

        //上拉加载
        void refresh(List<ApplyProduct> products);

        void refresh1(List<CreditCardAppliProduct> products);

        //加载完成
        void NoData();


        void clickChange(boolean click);

    }
}
