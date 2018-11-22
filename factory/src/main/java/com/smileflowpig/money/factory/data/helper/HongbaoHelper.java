package com.smileflowpig.money.factory.data.helper;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.R;
import com.smileflowpig.money.factory.bean.BillData;
import com.smileflowpig.money.factory.bean.HongbaoBean;
import com.smileflowpig.money.factory.bean.HongbaoStatusJson;
import com.smileflowpig.money.factory.bean.ReceiveHongbaoJson;
import com.smileflowpig.money.factory.bean.TaskBean;
import com.smileflowpig.money.factory.bean.ZhenxinUrlJson;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.bill.BillListsModel;
import com.smileflowpig.money.factory.model.api.hongbao.HongbaoModel;
import com.smileflowpig.money.factory.model.api.hongbao.ReceiveModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HongbaoHelper {


    /**
     * 获取红包开关
     *
     * @param callback
     */
    public static void getHongbaoStatus(final DataSource.Callback<HongbaoBean> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<HongbaoBean>> call = service.getHongbaostatus();
        // 异步的请求
        call.enqueue(new Callback<RspModel<HongbaoBean>>() {
            @Override
            public void onResponse(Call<RspModel<HongbaoBean>> call, Response<RspModel<HongbaoBean>> response) {
                RspModel<HongbaoBean> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                    return;
                }
                if (rspModel.success()) {
                    // 拿到实体
                    HongbaoBean codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<HongbaoBean>> call, Throwable t) {

            }
        });
    }

    /**
     * 获取用户红包领取状态
     *
     * @param type
     * @param callback
     */
    public static void getUserHongbaoStatus(int type, final DataSource.Callback<HongbaoStatusJson> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<HongbaoStatusJson>> call = service.getUserHongbaostatus(new HongbaoModel(type));
        // 异步的请求
        call.enqueue(new Callback<RspModel<HongbaoStatusJson>>() {
            @Override
            public void onResponse(Call<RspModel<HongbaoStatusJson>> call, Response<RspModel<HongbaoStatusJson>> response) {
                RspModel<HongbaoStatusJson> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                    return;
                }
                if (rspModel.success()) {
                    // 拿到实体
                    HongbaoStatusJson codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<HongbaoStatusJson>> call, Throwable t) {

            }
        });
    }

    /**
     * 领取红包
     *
     * @param id
     * @param callback
     */
    public static void getReceiveHongbao(String id, final DataSource.Callback<ReceiveHongbaoJson> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ReceiveHongbaoJson>> call = service.receiveRedPacket(new ReceiveModel(id));
        // 异步的请求
        call.enqueue(new Callback<RspModel<ReceiveHongbaoJson>>() {
            @Override
            public void onResponse(Call<RspModel<ReceiveHongbaoJson>> call, Response<RspModel<ReceiveHongbaoJson>> response) {
                RspModel<ReceiveHongbaoJson> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                    return;
                }
                if (rspModel.success()) {
                    // 拿到实体
                    ReceiveHongbaoJson codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ReceiveHongbaoJson>> call, Throwable t) {

            }
        });
    }

    /**
     * 红包任务
     *
     * @param callback
     */
    public static void taskLists(final DataSource.Callback<TaskBean> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<TaskBean>> call = service.taskLists();
        // 异步的请求
        call.enqueue(new Callback<RspModel<TaskBean>>() {
            @Override
            public void onResponse(Call<RspModel<TaskBean>> call, Response<RspModel<TaskBean>> response) {
                RspModel<TaskBean> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                    return;
                }
                if (rspModel.success()) {
                    // 拿到实体
                    TaskBean codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<TaskBean>> call, Throwable t) {

            }
        });
    }

    /**
     * 获取个人征信
     *
     * @param callback
     */
    public static void getZhenxinUrl(final DataSource.Callback<ZhenxinUrlJson> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ZhenxinUrlJson>> call = service.getZhenxinUrl();
        // 异步的请求
        call.enqueue(new Callback<RspModel<ZhenxinUrlJson>>() {
            @Override
            public void onResponse(Call<RspModel<ZhenxinUrlJson>> call, Response<RspModel<ZhenxinUrlJson>> response) {
                RspModel<ZhenxinUrlJson> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                    return;
                }
                if (rspModel.success()) {
                    // 拿到实体
                    ZhenxinUrlJson codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ZhenxinUrlJson>> call, Throwable t) {

            }
        });
    }
}
