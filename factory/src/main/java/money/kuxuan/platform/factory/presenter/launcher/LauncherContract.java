package money.kuxuan.platform.factory.presenter.launcher;

import money.kuxuan.platform.common.factory.presenter.BaseContract;
import money.kuxuan.platform.factory.model.db.AdModel;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public interface LauncherContract {

    interface Presenter extends BaseContract.Presenter{
          void getAdPicture();
          void getshowDialog();
          void sendImei(String imei);
    }

    // 都在基类完成了
    interface View extends BaseContract.View<Presenter> {
        void setImage(String url);
        void setStartImage(AdModel adModel);

    }
}
