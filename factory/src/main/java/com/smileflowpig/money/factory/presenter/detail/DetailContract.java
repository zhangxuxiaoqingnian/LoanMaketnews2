package com.smileflowpig.money.factory.presenter.detail;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.api.product.ProductDetail;
import com.smileflowpig.money.factory.model.db.Amount;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface DetailContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void codePush(String phone, String smstype);
        void login(String phone, String password);
        void loginP(String phone, String password);
        void loginState();
        void SoundCode(String phone);

        void apply(String product_id, String periods, String money);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {

        void onDone(ProductDetail product);

        void onHorData(List<Amount> amountList);


        void codeSuccess();

        void loginSuccess();

        void state();

        void stateError();

        void getApplyId(String id);

    }
}
