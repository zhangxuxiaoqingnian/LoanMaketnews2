package com.smileflowpig.money.factory.data.helper;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.R;
import com.smileflowpig.money.factory.bean.BillData;
import com.smileflowpig.money.factory.bean.BillDetialData;
import com.smileflowpig.money.factory.bean.BillStatusData;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.bill.AddBillModel;
import com.smileflowpig.money.factory.model.api.bill.BillListsModel;
import com.smileflowpig.money.factory.model.api.bill.BillstatusModel;
import com.smileflowpig.money.factory.model.api.bill.DeleteBillModel;
import com.smileflowpig.money.factory.model.api.bill.DetialModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillHelper {


    /**
     * 获取列表
     *
     * @param page
     * @param status
     * @param callback
     */
    public static void getLists(String page, String status, final DataSource.Callback<BillData> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<BillData>> call = service.getBillManagerLists(new BillListsModel(page, status));
        // 异步的请求
        call.enqueue(new Callback<RspModel<BillData>>() {
            @Override
            public void onResponse(Call<RspModel<BillData>> call, Response<RspModel<BillData>> response) {
                RspModel<BillData> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
                if (rspModel.success()) {
                    // 拿到实体
                    BillData codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    callback.onDataNotAvailable(R.string.no_data);
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BillData>> call, Throwable t) {

            }
        });
    }


    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @param callback
     */
    public static void changeStatus(String id, String status, final DataSource.Callback<BillStatusData> callback) {
        RemoteService service = Network.remote();

        Call<RspModel<BillStatusData>> call = service.changeBillStatus(new BillstatusModel(id, status));
        call.enqueue(new Callback<RspModel<BillStatusData>>() {
            @Override
            public void onResponse(Call<RspModel<BillStatusData>> call, Response<RspModel<BillStatusData>> response) {
                RspModel<BillStatusData> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
                if (rspModel.success()) {
                    // 拿到实体
                    BillStatusData codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BillStatusData>> call, Throwable t) {

            }
        });
    }


    /**
     * 获取详情
     *
     * @param page
     * @param id
     * @param callback
     */
    public static void getDetial(String page, String id, final DataSource.Callback<BillDetialData> callback) {

        RemoteService service = Network.remote();
        DetialModel detialModel = new DetialModel(page, id);
        Call<RspModel<BillDetialData>> call = service.getBillDetial(detialModel);
        call.enqueue(new Callback<RspModel<BillDetialData>>() {
            @Override
            public void onResponse(Call<RspModel<BillDetialData>> call, Response<RspModel<BillDetialData>> response) {
                RspModel<BillDetialData> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
                if (rspModel.success()) {
                    // 拿到实体
                    BillDetialData codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BillDetialData>> call, Throwable t) {

            }
        });
    }


    /**
     * 删除账单
     *
     * @param id
     * @param callback
     */
    public static void deleteBill(String id, final DataSource.Callback<Object> callback) {

        RemoteService service = Network.remote();
        Call<RspModel<Object>> call = service.deleteBill(new DeleteBillModel(id));
        call.enqueue(new Callback<RspModel<Object>>() {
            @Override
            public void onResponse(Call<RspModel<Object>> call, Response<RspModel<Object>> response) {
                RspModel<Object> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
                if (rspModel.success()) {
                    // 拿到实体
                    Object codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Object>> call, Throwable t) {

            }
        });
    }


    /**
     * 添加账单
     *
     * @param platform_id
     * @param amount
     * @param due_data
     * @param remind_time
     * @param callback
     */
    public static void addBill(String platform_id, String amount, String due_data, String remind_time, final DataSource.Callback<Object> callback) {

        RemoteService service = Network.remote();
        Call<RspModel<Object>> call = service.addBill(new AddBillModel(platform_id, amount, due_data, remind_time));
        call.enqueue(new Callback<RspModel<Object>>() {
            @Override
            public void onResponse(Call<RspModel<Object>> call, Response<RspModel<Object>> response) {
                RspModel<Object> rspModel = response.body();
                if (rspModel == null) {
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
                if (rspModel.success()) {
                    // 拿到实体
                    Object codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                    callback.onDataNotAvailable(R.string.no_data);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Object>> call, Throwable t) {

            }
        });
    }
}
