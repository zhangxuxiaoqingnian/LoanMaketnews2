package money.kuxuan.platform.factory.data.helper;

import android.text.TextUtils;
import android.util.Log;

import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.launcher.LaunchRspModel;
import money.kuxuan.platform.factory.model.api.launcher.LauncherModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import money.kuxuan.platform.factory.model.db.Idfa;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class LauncherHelper {

    /**
     * 广告的接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getAdPicture(final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        LauncherModel launcherModel = new LauncherModel("750x1334");
        Call<RspModel<RspAdModel>> call = service.getAdPicture(launcherModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<RspAdModel>>() {
            @Override
            public void onResponse(Call<RspModel<RspAdModel>> call, Response<RspModel<RspAdModel>> response) {
                RspModel<RspAdModel> rspModel = response.body();

                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel);

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<RspAdModel>> call, Throwable t) {

            }
        });
    }


    /**
     * 广告的接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getshowDialog(final DataSource.Callback callback) {
        RemoteService service = Network.remote();

        Call<RspModel<RspAdModel>> call = service.getShowPicture();


        // 异步的请求
        call.enqueue(new Callback<RspModel<RspAdModel>>() {
            @Override
            public void onResponse(Call<RspModel<RspAdModel>> call, Response<RspModel<RspAdModel>> response) {
                RspModel<RspAdModel> rspModel = response.body();

                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel);

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<RspAdModel>> call, Throwable t) {
                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }


    /**
     * 获取审核状态接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getChannel(String channel,final DataSource.Callback callback) {

        RemoteService service = Network.remote();
        Call<RspModel<LaunchRspModel>> call = service.getChannel(channel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<LaunchRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<LaunchRspModel>> call, Response<RspModel<LaunchRspModel>> response) {
                RspModel<LaunchRspModel> rspModel = response.body();

                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<LaunchRspModel>> call, Throwable t) {

            }
        });
    }

    /**
     * 获取审核状态接口
     *
     */
    public static void sendImei(String imei) {
        RemoteService service = Network.remote();
        Idfa idfa = new Idfa("Android",Network.channelId,imei);
        Call<RspModel> call = service.sendIdfa(idfa);
        Log.e(TAG,"进入方法");
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel<LaunchRspModel> rspModel = response.body();

            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }
}
