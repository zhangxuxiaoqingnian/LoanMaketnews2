package com.smileflowpig.money.factory.presenter.account;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.User;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface LoginContract {
    interface View extends BaseContract.View<Presenter> {
        //登陆成功
        void loginSuccess(User user);

        //获取验证码成功
        void codeSuccess();

    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起登陆
         *
         * @param phone    手机号
         * @param password 密码
         */
        void login(String phone, String password);

        /**
         * 发起登陆
         *
         * @param phone    手机号
         * @param password 密码
         */
        void loginByCode(String phone, String password);


        /**
         * 发起登陆
         * @param phone   手机号
         */
        void getCode(String phone, String qrcode, String smstype);

    }

}
