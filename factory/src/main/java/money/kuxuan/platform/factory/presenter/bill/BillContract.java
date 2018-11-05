package money.kuxuan.platform.factory.presenter.bill;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.bean.BillData;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import money.kuxuan.platform.factory.presenter.home.MainContract;

public interface BillContract {


    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {


        void getLists(String page, String status,boolean isRefresh);


        void changeStatus(String id,String status);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<BillContract.Presenter> {
        void setData(BillData billData,boolean isRefresh);

        void changeStatus(String id,String status);


        void showNoData();
    }
}
