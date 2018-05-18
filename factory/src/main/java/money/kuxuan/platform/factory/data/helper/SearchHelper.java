package money.kuxuan.platform.factory.data.helper;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.product.ProductRspModel;
import money.kuxuan.platform.factory.model.api.search.FilterRspModel;
import money.kuxuan.platform.factory.model.api.search.SearchModel;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class SearchHelper {

    /**
     * 发现接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void getSearchData(SearchModel model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ProductRspModel>> call = service.getSearchData(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<ProductRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<ProductRspModel>> call, Response<RspModel<ProductRspModel>> response) {
                RspModel<ProductRspModel> rspModel = response.body();
                if (rspModel.success()||rspModel.getErrno()==40044) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ProductRspModel>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }


    /**
     * 获取地址接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getDialogList(final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<FilterRspModel>> call = service.getDialogList();
        // 异步的请求
        call.enqueue(new Callback<RspModel<FilterRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<FilterRspModel>> call, Response<RspModel<FilterRspModel>> response) {
                RspModel<FilterRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<FilterRspModel>> call, Throwable t) {

            }
        });
    }
}
