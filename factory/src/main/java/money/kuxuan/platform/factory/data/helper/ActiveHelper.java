package money.kuxuan.platform.factory.data.helper;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.active.ActiveModel;
import money.kuxuan.platform.factory.model.api.active.ActiveRspModel;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ActiveHelper {

    /**
     * 活动界面
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getActiveData(ActiveModel model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ActiveRspModel>> call = service.getActiveData(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<ActiveRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<ActiveRspModel>> call, Response<RspModel<ActiveRspModel>> response) {
                RspModel<ActiveRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ActiveRspModel>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

}
