package com.smileflowpig.money.factory.presenter.statehome;

import android.support.annotation.StringRes;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.data.helper.ExaimeHelper;
import com.smileflowpig.money.factory.model.api.examine.IdModel;
import com.smileflowpig.money.factory.model.api.examine.InfoModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class InfoPresenter extends BasePresenter<InfoContract.View>
        implements InfoContract.Presenter {
    private final String id;

    public InfoPresenter(InfoContract.View view, String id) {
        super(view);
        this.id = id;
    }

    @Override
    public void start() {
        super.start();
        IdModel idModel = new IdModel(id);
        ExaimeHelper.getInfoDetail(idModel, new DataSource.Callback<InfoModel>() {
            @Override
            public void onDataLoaded(InfoModel infoModel) {
                if(getView()!=null)
                 getView().getData(infoModel);
            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

        });


    }
}
