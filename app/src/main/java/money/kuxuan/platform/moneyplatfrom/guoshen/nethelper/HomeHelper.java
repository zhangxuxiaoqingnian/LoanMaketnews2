package money.kuxuan.platform.moneyplatfrom.guoshen.nethelper;

import java.util.List;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.guoshen.PopModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Allence on 2018/5/9 0009.
 */

public class HomeHelper {


    public static void putStatus(String putStats, final DataSource.Callback<RspModel> callback){

        RemoteService service = Network.remote();

        Call<RspModel> call = service.putStatus(putStats);

        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {

                RspModel rspModel = response.body();
                if(rspModel.success()){
                    callback.onDataLoaded(rspModel);
                }else {
                    Factory.decodeRspCode(rspModel,callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {


            }
        });

    }


    public static void getPopDetailData(int id, final DataSource.Callback<RspModel<List<PopModel>>> callback){

        RemoteService service = Network.remote();

        Call<RspModel<List<PopModel>>> call = service.getPopDetail(id);
        call.enqueue(new Callback<RspModel<List<PopModel>>>() {
            @Override
            public void onResponse(Call<RspModel<List<PopModel>>> call, Response<RspModel<List<PopModel>>> response) {
                 RspModel rspModel = response.body();
                 if(rspModel.success()){
                     callback.onDataLoaded(rspModel);
                 }else {
                     Factory.decodeRspCode(rspModel,callback);
                 }
            }

            @Override
            public void onFailure(Call<RspModel<List<PopModel>>> call, Throwable t) {

            }
        });


    }


    public static void getRepaymentList(final DataSource.Callback<RspModel<List<RepaymentListBean>>> callback){

        RemoteService service = Network.remote();
        Call<RspModel<List<RepaymentListBean>>> call = service.getRepaymentList();
        call.enqueue(new Callback<RspModel<List<RepaymentListBean>>>() {
            @Override
            public void onResponse(Call<RspModel<List<RepaymentListBean>>> call, Response<RspModel<List<RepaymentListBean>>> response) {
                RspModel rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }

            }

            @Override
            public void onFailure(Call<RspModel<List<RepaymentListBean>>> call, Throwable t) {



            }
        });


    }



}
