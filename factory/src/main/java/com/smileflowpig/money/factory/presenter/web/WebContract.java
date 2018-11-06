package com.smileflowpig.money.factory.presenter.web;

import com.smileflowpig.money.common.factory.presenter.BaseContract;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface WebContract   {
    interface Presenter extends BaseContract.Presenter{
        void pushData(String product_apply_id, String status, String skip_type, String product_id);

    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {

    }
}
