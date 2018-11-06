package com.smileflowpig.money.factory.presenter.account;

import com.smileflowpig.money.common.factory.presenter.BaseContract;
import com.smileflowpig.money.factory.model.db.User;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface RegisterContract {
    interface View extends BaseContract.View<Presenter> {
        //注册成功
        void registerSuccess(User user);


        void codeSuccess();

    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * 发起注册
         *
         * @param phone    电话号码
         * @param password 密码
         * @param code     验证码
         * @param source   渠道号
         */
        void register(String phone, String password, String code, String source);

        /**
         * 检查手机号是否正确
         *
         * @param phone 手机号码
         * @return 是否正确
         */
        boolean checkMobile(String phone);

        /**
         * 请求验证码
         *
         * @param phone 手机号
         */
        void requestCode(String phone, String smstype);

    }

}
