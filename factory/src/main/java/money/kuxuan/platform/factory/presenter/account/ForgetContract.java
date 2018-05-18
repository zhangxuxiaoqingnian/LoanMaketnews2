package money.kuxuan.platform.factory.presenter.account;

import money.kuxuan.platform.common.factory.presenter.BaseContract;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ForgetContract {
    interface View extends BaseContract.View<Presenter> {
        //登陆成功
        void updateSuccess();

        //获取验证码成功
        void codeSuccess();

    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起修改
         *
         * @param phone    手机号
         * @param password 密码
         */
        void update(String phone, String password,String code);




        /**
         * 发起登陆
         * @param phone   手机号
         */
        void getCode(String phone,String smstype);

    }
}
