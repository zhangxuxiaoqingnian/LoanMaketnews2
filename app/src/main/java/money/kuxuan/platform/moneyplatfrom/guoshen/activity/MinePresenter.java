package money.kuxuan.platform.moneyplatfrom.guoshen.activity;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.AccountHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.db.User;

/**
 * Created by Allence on 2018/5/8 0008.
 */

public class MinePresenter extends BasePresenter<MineConreact.View>
        implements MineConreact.Presenter {


    public MinePresenter(MineConreact.View view) {
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


    @Override
    public void exist() {

        AccountHelper.exist(new DataSource.Callback<RspModel>() {
            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null){
                    getView().showError(strRes);
                }
            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                if(getView()!=null){
                    getView().ExistSuccess();
                }
            }
        });

    }
}