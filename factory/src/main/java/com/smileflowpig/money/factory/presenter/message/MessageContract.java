package com.smileflowpig.money.factory.presenter.message;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.Message;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface MessageContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void relodeMessage();
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void setMessage(List<Message> messgae);
        void refresh(List<Message> messgae);

        void noData();


    }
}
