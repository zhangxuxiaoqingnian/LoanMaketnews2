package com.smileflowpig.money.factory.presenter.addplatform;

import com.smileflowpig.money.common.factory.presenter.BaseContract;

/**
 * Created by Allence on 2018/5/17 0017.
 */

public interface AddPlatfromContract {


    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {


    }


    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {


    }
}
