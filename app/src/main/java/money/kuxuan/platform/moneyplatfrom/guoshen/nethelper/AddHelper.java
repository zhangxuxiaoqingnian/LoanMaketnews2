package money.kuxuan.platform.moneyplatfrom.guoshen.nethelper;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel1;
import money.kuxuan.platform.factory.model.api.guoshen.GetRepaymentModel;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Allence on 2018/5/9 0009.
 */

public class AddHelper {


    public static void getRepayment(int id, final DataSource.Callback<RspModel<GetRepaymentModel>> callback){

        RemoteService service = Network.remote();
        Call<RspModel<GetRepaymentModel>> call = service.getRepayment(id);
        call.enqueue(new Callback<RspModel<GetRepaymentModel>>() {
            @Override
            public void onResponse(Call<RspModel<GetRepaymentModel>> call, Response<RspModel<GetRepaymentModel>> response) {
                RspModel<GetRepaymentModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                }else {
                    Factory.decodeRspCode(rspModel, callback);
                }

            }

            @Override
            public void onFailure(Call<RspModel<GetRepaymentModel>> call, Throwable t) {

            }
        });




    }

    public static void addRepayment(AddModel addModel, final DataSource.Callback callback){

        RemoteService service = Network.remote();
        Call<RspModel<Object>> call = service.getAddRepayment(addModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<Object>>() {
            @Override
            public void onResponse(Call<RspModel<Object>> call, Response<RspModel<Object>> response) {
                RspModel<Object> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Object>> call, Throwable t) {

            }
        });

    }

    public static void addRepayment1(AddModel1 addModel, final DataSource.Callback callback){

        RemoteService service = Network.remote();
        Call<RspModel<Object>> call = service.getAddRepayment1(addModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<Object>>() {
            @Override
            public void onResponse(Call<RspModel<Object>> call, Response<RspModel<Object>> response) {
                RspModel<Object> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Object>> call, Throwable t) {

            }
        });

    }


}
