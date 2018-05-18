package money.kuxuan.platform.factory.presenter.addplatform;

import money.kuxuan.platform.common.factory.presenter.BaseContract;

/**
 * Created by Allence on 2018/5/17 0017.
 */

public interface AddPlatfromContract {


    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {


    }


    // 都在基类完成了
    interface View extends BaseContract.View<AddPlatfromContract.Presenter> {


    }
}
