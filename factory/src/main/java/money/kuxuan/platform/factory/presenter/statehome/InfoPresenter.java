package money.kuxuan.platform.factory.presenter.statehome;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.ExaimeHelper;
import money.kuxuan.platform.factory.model.api.examine.IdModel;
import money.kuxuan.platform.factory.model.api.examine.InfoModel;

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
