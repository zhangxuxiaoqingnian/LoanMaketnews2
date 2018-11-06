package com.smileflowpig.money.factory.presenter.account;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.RspModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ExistPresenter extends BasePresenter<ExistContract.View>
        implements ExistContract.Presenter{
    public ExistPresenter(ExistContract.View view) {
        super(view);
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
