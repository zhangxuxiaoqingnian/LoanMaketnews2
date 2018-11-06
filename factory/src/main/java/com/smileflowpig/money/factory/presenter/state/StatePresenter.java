package com.smileflowpig.money.factory.presenter.state;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.db.User;

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
