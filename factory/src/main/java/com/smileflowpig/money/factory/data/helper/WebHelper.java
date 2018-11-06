package com.smileflowpig.money.factory.data.helper;

import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.product.ProductRspModel;
import com.smileflowpig.money.factory.model.api.web.WebModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class WebHelper {
    /**
     * 发现接口
     *
     */
    public static void pushData(WebModel webModel) {
        RemoteService service = Network.remote();
        Call<RspModel> call = service.ApplyState(webModel);
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel<ProductRspModel> rspModel = response.body();

            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }
}
