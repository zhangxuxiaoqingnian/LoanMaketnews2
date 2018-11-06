package com.smileflowpig.money.moneyplatfrom.guoshen.activity;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.User;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public interface MineConreact {

    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{

        void exist();

    }

    // 都在基类完成了
    interface View extends BaseContract.View<MineConreact.Presenter> {
        void stateLogin(User user);
        void setNoLogin();

        void ExistSuccess();

    }

}
