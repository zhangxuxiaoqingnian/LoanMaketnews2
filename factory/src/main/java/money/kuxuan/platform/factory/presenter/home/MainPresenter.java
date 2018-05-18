package money.kuxuan.platform.factory.presenter.home;

import android.support.annotation.StringRes;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.factory.presenter.BasePresenter;
import money.kuxuan.platform.factory.data.helper.LauncherHelper;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter{

    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void start() {

        // 获取弹框图
        LauncherHelper.getshowDialog(new DataSource.Callback<RspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
               getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(RspModel rspModel) {
                RspAdModel rspAdModel = (RspAdModel) rspModel.getRst();
                if(getView()!=null)
                getView().setAdModel(rspModel.getTimestamp(),rspAdModel);
            }
        });
    }
}
