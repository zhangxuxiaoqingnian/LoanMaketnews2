package money.kuxuan.platform.factory.presenter.account;

import android.content.Context;

import money.kuxuan.platform.common.factory.presenter.BaseContract;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface UpdateContract {
    interface View extends BaseContract.View<Presenter> {
        //登陆成功
        void updateSuccess();



    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起修改
         *
         */
        void update(String newpassword, String oldpassword, Context context);



    }
}
