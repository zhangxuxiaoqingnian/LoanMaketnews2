package com.smileflowpig.money.factory.presenter.user;

import com.smileflowpig.money.common.factory.presenter.BaseContract;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ActionContract {
    interface View extends BaseContract.View<Presenter> {
        //发送成功
        void sendSuccess();



    }

    interface Presenter extends BaseContract.Presenter {


        /**
         *
         * @param content
         */
        void send(String content);



    }

}

