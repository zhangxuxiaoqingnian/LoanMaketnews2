package money.kuxuan.platform.factory.presenter.state;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.db.User;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class StatePresenter extends BasePresenter<StateContract.View>
        implements StateContract.Presenter {

    public StatePresenter(StateContract.View view) {
        super(view);
    }


    @Override
    public void start() {
        super.start();
        AccountHelper.loginState(new DataSource.Callback<User>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){
                    getView().setNoLogin();
                }
            }

            @Override
            public void onDataLoaded(User user) {
                if(getView()!=null){
                    getView().stateLogin(user);
                }

            }
        });
    }
}
