package money.kuxuan.platform.factory.data.helper;

import java.sql.Array;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.db.AllApp;
import money.kuxuan.platform.factory.model.db.AllCard;
import money.kuxuan.platform.factory.model.db.DeleteApp;
import money.kuxuan.platform.factory.model.db.DeleteTwo;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 小狼 on 2018/5/30.
 */

public class MineHelper {

    //我的界面一些借口请求

    //我的足迹里的删除App条目
    public static void deletetapp(FormBody uuid, final DataSource.Callback<Integer> callback){


        RemoteService remote = Network.remote();
        Call<RspModel<Integer>> rspModelCall = remote.deleteApp(uuid);
        rspModelCall.enqueue(new Callback<RspModel<Integer>>() {
            @Override
            public void onResponse(Call<RspModel<Integer>> call, Response<RspModel<Integer>> response) {

                RspModel<Integer> body = response.body();
                if(body.success()){
                    Integer rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Integer>> call, Throwable t) {

            }
        });
    }

    //我的足迹里的删除信用卡条目
    public static void deletetcard(FormBody uuid, final DataSource.Callback<Integer> callback){

        RemoteService remote = Network.remote();
        Call<RspModel<Integer>> rspModelCall = remote.deleteCard(uuid);
        rspModelCall.enqueue(new Callback<RspModel<Integer>>() {
            @Override
            public void onResponse(Call<RspModel<Integer>> call, Response<RspModel<Integer>> response) {


                RspModel<Integer> body = response.body();
                if(body.success()){
                    Integer rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<Integer>> call, Throwable t) {


            }
        });
    }
    //把App添加到已申请列表
    public static void addapp(String uuid, final DataSource.Callback<DeleteApp> callback){

        RemoteService remote = Network.remote();
        Call<RspModel<DeleteApp>> rspModelCall = remote.addApp(uuid);
        rspModelCall.enqueue(new Callback<RspModel<DeleteApp>>() {
            @Override
            public void onResponse(Call<RspModel<DeleteApp>> call, Response<RspModel<DeleteApp>> response) {

                RspModel<DeleteApp> body = response.body();
                if(body.success()){
                    DeleteApp rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<DeleteApp>> call, Throwable t) {

            }
        });
    }
    //把信用卡添加到已申请列表
    public static void addcard(String uuid, final DataSource.Callback<DeleteApp> callback){

        RemoteService remote = Network.remote();
        Call<RspModel<DeleteApp>> rspModelCall = remote.addCard(uuid);
        rspModelCall.enqueue(new Callback<RspModel<DeleteApp>>() {
            @Override
            public void onResponse(Call<RspModel<DeleteApp>> call, Response<RspModel<DeleteApp>> response) {

                RspModel<DeleteApp> body = response.body();
                if(body.success()){
                    DeleteApp rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<DeleteApp>> call, Throwable t) {

            }
        });
    }

    //返回所有商品
    public static void allShap(final DataSource.Callback<AllApp> callback){

        RemoteService remote = Network.remote();
        Call<RspModel<AllApp>> rspModelCall = remote.allApp();
        rspModelCall.enqueue(new Callback<RspModel<AllApp>>() {
            @Override
            public void onResponse(Call<RspModel<AllApp>> call, Response<RspModel<AllApp>> response) {

                RspModel<AllApp> body = response.body();

                if(body.success()){
                    AllApp rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AllApp>> call, Throwable t) {

            }
        });
    }

    //返回所有信用卡
    public static void allCard(final DataSource.Callback<AllApp> callback){

        RemoteService remote = Network.remote();
        Call<RspModel<AllApp>> rspModelCall = remote.allCard();
        rspModelCall.enqueue(new Callback<RspModel<AllApp>>() {
            @Override
            public void onResponse(Call<RspModel<AllApp>> call, Response<RspModel<AllApp>> response) {

                RspModel<AllApp> body = response.body();
                if(body.success()){
                    AllApp rst = body.getRst();
                    callback.onDataLoaded(rst);
                }else {
                    // 错误解析
                    Factory.decodeRspCode(body, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AllApp>> call, Throwable t) {

            }
        });
    }
}
