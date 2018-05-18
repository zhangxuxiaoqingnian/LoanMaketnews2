package money.kuxuan.platform.factory.presenter.home;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface MainContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{

    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {

        void setAdModel(String time, RspAdModel rspAdModel);


    }
}
