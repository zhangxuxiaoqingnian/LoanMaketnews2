package com.smileflowpig.money.factory.presenter.bill;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.bean.BillData;

public interface BillContract {


    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {


        void getLists(String page, String status, boolean isRefresh);


        void changeStatus(String id, String status);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void setData(BillData billData, boolean isRefresh);

        void changeStatus(String id, String status);


        void showNoData();
    }
}
