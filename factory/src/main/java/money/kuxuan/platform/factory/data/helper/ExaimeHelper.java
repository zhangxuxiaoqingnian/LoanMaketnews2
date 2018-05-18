package money.kuxuan.platform.factory.data.helper;

import java.util.List;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.examine.BannerExprt;
import money.kuxuan.platform.factory.model.api.examine.BannerModel;
import money.kuxuan.platform.factory.model.api.examine.Examine;
import money.kuxuan.platform.factory.model.api.examine.Examine1;
import money.kuxuan.platform.factory.model.api.examine.IdModel;
import money.kuxuan.platform.factory.model.api.examine.InfoModel;
import money.kuxuan.platform.factory.model.api.examine.RspExamHomeModel;
import money.kuxuan.platform.factory.model.api.examine.TabListParamter;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */

public class ExaimeHelper {


    /**
     * 过审页首页tabList接口
     */

    public static void getTabList(TabListParamter tabListParamter,final DataSource.Callback callback){
        RemoteService service = Network.remote();
        Call<RspModel<List<String>>> call = service.getTabList(tabListParamter);
        call.enqueue(new Callback<RspModel<List<String>>>() {
            @Override
            public void onResponse(Call<RspModel<List<String>>> call, Response<RspModel<List<String>>> response) {
                 RspModel<List<String>> rspModel = response.body();
                 if(rspModel.success()){
                     callback.onDataLoaded(rspModel.getRst());
                 }else {
                     Factory.decodeRspCode(rspModel,callback);
                 }
            }
            @Override
            public void onFailure(Call<RspModel<List<String>>> call, Throwable t) {
                   if(callback!=null){
                       callback.onDataNotAvailable(R.string.data_network_error);
                   }
            }
        });
    }

    /**
     * 过审页首页banner接口
     */
    public static void getBanner(final DataSource.Callback callback){
        RemoteService service = Network.remote();
        Call<RspModel<BannerModel>> call = service.getBanner();
        call.enqueue(new Callback<RspModel<BannerModel>>() {
            @Override
            public void onResponse(Call<RspModel<BannerModel>> call, Response<RspModel<BannerModel>> response) {
                RspModel<BannerModel> rspModel = response.body();
                if(rspModel.success()){
                    callback.onDataLoaded(rspModel.getRst());
                }else{
                    Factory.decodeRspCode(rspModel,callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BannerModel>> call, Throwable t) {
                 if(callback!=null){
                     callback.onDataNotAvailable(R.string.data_network_error);
                 }
            }
        });

    }






    /**
     * 过审页首页接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void refreshExamine(Examine1 model, final DataSource.Callback callback) {

        RemoteService service = Network.remote();
        Call<RspModel<RspExamHomeModel>> call = service.getExamHomeData(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<RspExamHomeModel>>() {
            @Override
            public void onResponse(Call<RspModel<RspExamHomeModel>> call, Response<RspModel<RspExamHomeModel>> response) {
                RspModel<RspExamHomeModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }
            @Override
            public void onFailure(Call<RspModel<RspExamHomeModel>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 过审页首页接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void refreshExpert(Examine model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<BannerExprt>> call = service.getExpertHomeData(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<BannerExprt>>() {
            @Override
            public void onResponse(Call<RspModel<BannerExprt>> call, Response<RspModel<BannerExprt>> response) {
                RspModel<BannerExprt> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BannerExprt>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }


    /**
     * 过审页首页接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void getInfoDetail(IdModel model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<InfoModel>> call = service.getInfoData(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<InfoModel>>() {
            @Override
            public void onResponse(Call<RspModel<InfoModel>> call, Response<RspModel<InfoModel>> response) {
                RspModel<InfoModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<InfoModel>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
