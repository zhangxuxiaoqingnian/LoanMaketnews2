package com.smileflowpig.money.factory.presenter.creditcard;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.CreditCardProduct;

import java.util.List;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface CreditcardContract {

    interface Presenter extends BaseContract.Presenter{
            void refreshData();

        void codePush(String phone, String smstype);
        void login(String phone, String password);
        void loginP(String phone, String password);
        void SoundCode(String phone);
        void loginState(int product_id);

    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {

        void requestData(List<CreditCardProduct> products);

        //上拉加载
        void refresh(List<CreditCardProduct> products);

        //加载完成
        void NoData();


        void stateError();

        void state();

        void codeSuccess();

        void loginSuccess();


        void loginFailure(@StringRes int strRes);

    }

}
