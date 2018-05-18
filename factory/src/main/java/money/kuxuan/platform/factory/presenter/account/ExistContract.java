package money.kuxuan.platform.factory.presenter.account;

import money.kuxuan.platform.common.factory.presenter.BaseContract;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ExistContract {

    interface View extends BaseContract.View<Presenter> {
        //登陆成功
        void ExistSuccess();



    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起退出
         */
        void exist();




    }
}
