package money.kuxuan.platform.factory.presenter.active;

import android.support.annotation.StringRes;

import java.util.List;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.db.Active;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface ActiveContract {
    //什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{
        void codePush(String phone,String smstype);
        void login(String phone,String password);
        void loginP(String phone,String password);
        void SoundCode(String phone);
        void requestData();
        void loginState();
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void requestData(List<Active> activeList);

        //上拉加载
        void refresh(List<Active> ActivityRefreshList);

        //加载完成
        void NoData();

        void stateError();

        void state();

        void codeSuccess();

        void loginSuccess();


        void loginFailure(@StringRes int strRes);
    }
}
