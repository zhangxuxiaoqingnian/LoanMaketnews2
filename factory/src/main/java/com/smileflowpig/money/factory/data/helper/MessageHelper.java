package com.smileflowpig.money.factory.data.helper;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.message.MessageModel;
import com.smileflowpig.money.factory.model.api.message.MessageRspModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class MessageHelper {


    /**
     * 获取message接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getMessageData(MessageModel messageModel, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<MessageRspModel>> call = service.geMessage(messageModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<MessageRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<MessageRspModel>> call, Response<RspModel<MessageRspModel>> response) {
                RspModel<MessageRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<MessageRspModel>> call, Throwable t) {

            }
        });
    }
}
