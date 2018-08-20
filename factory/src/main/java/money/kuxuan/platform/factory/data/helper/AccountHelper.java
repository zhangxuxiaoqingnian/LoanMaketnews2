package money.kuxuan.platform.factory.data.helper;

import money.kuxuan.platform.common.app.Application;
import money.kuxuan.platform.common.factory.data.DataSource;
import money.kuxuan.platform.common.widget.SelfDialog;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.R;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.account.AccountRspModel;
import money.kuxuan.platform.factory.model.api.account.CodeModel;
import money.kuxuan.platform.factory.model.api.account.CodeRspModel;
import money.kuxuan.platform.factory.model.api.account.ContentModel;
import money.kuxuan.platform.factory.model.api.account.ForgetModel;
import money.kuxuan.platform.factory.model.api.account.LoginByCodeModel;
import money.kuxuan.platform.factory.model.api.account.LoginModel;
import money.kuxuan.platform.factory.model.api.account.RegisterModel;
import money.kuxuan.platform.factory.model.api.account.UpdateModel;
import money.kuxuan.platform.factory.model.api.creditcard.CreditModel;
import money.kuxuan.platform.factory.model.db.User;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.factory.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class AccountHelper {

    /**
     * 注册的接口
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回调
     */
    public static void register(RegisterModel model, DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }


    /**
     * 登录的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void login(final LoginModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountLogin(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }


    /**
     * 通过验证码登录
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void loginToCode(final LoginByCodeModel model, final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountByCode(model);
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));
    }



    /**
     * 退出
     * @param callback 成功与失败的接口回送
     */
    public static void exist( final DataSource.Callback<RspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel> call = service.exist();
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }

    /**
     * 退出
     * @param callback 成功与失败的接口回送
     */
    public static void update(UpdateModel updateModel,final DataSource.Callback<RspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel> call = service.update(updateModel);
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                } else {

                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }


    /**
     * 提交意见
     *
     * @param callback 成功与失败的接口回送
     */
    public static void SendMessage(ContentModel contentModel, final DataSource.Callback<RspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel> call = service.sendMessage(contentModel);
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }


    /**
     * 获取登录状态
     *
     * @param callback 成功与失败的接口回送
     */
    public static void loginState(final DataSource.Callback<User> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.loginState();
        // 异步的请求
        call.enqueue(new AccountRspCallback(callback));


    }

    /**
     * 获取登录状态
     *
     * @param callback 成功与失败的接口回送
     */
    public static void update(ForgetModel forgetModel,final DataSource.Callback<RspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel> call = service.update2(forgetModel);
        // 异步的请求
        call.enqueue(new Callback<RspModel>() {
            @Override
            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
                RspModel rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel);
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel> call, Throwable t) {

            }
        });
    }


    public static void getAdd(final int product_id,final  DataSource.Callback<RspModel> callback){

        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();

        Call<RspModel> call = service.getCanAdd(new CreditModel(product_id));

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

    /**
     * 获取验证码的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void loginByCode(final CodeModel model, final DataSource.Callback<CodeRspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<CodeRspModel>> call = service.loginByCode(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<CodeRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<CodeRspModel>> call, Response<RspModel<CodeRspModel>> response) {
                RspModel<CodeRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    // 拿到实体
                    CodeRspModel codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<CodeRspModel>> call, Throwable t) {

            }
        });
    }

    /**
     * 获取验证码的调用
     *
     * @param model    登录的Model
     * @param callback 成功与失败的接口回送
     */
    public static void forgetByCode(final CodeModel model, final DataSource.Callback<CodeRspModel> callback) {
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<CodeRspModel>> call = service.forgetByCode(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<CodeRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<CodeRspModel>> call, Response<RspModel<CodeRspModel>> response) {
                RspModel<CodeRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    // 拿到实体
                    CodeRspModel codeModel = rspModel.getRst();

                    callback.onDataLoaded(codeModel);

                } else {
                    // 错误解析
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<CodeRspModel>> call, Throwable t) {

            }
        });
    }

    /**
     * 请求的回调部分封装
     */
    private static class AccountRspCallback implements Callback<RspModel<AccountRspModel>> {

        final DataSource.Callback<User> callback;

        AccountRspCallback(DataSource.Callback<User> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(Call<RspModel<AccountRspModel>> call,
                               Response<RspModel<AccountRspModel>> response) {
            // 请求成功返回
            // 从返回中得到我们的全局Model，内部是使用的Gson进行解析
            RspModel<AccountRspModel> rspModel = response.body();
            if (rspModel.success()) {
                // 拿到实体
                AccountRspModel accountRspModel = rspModel.getRst();
                User user = accountRspModel.getUser();
                callback.onDataLoaded(user);

            } else {
                // 错误解析
                Factory.decodeRspCode(rspModel, callback);
            }
        }

        @Override
        public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
            // 网络请求失败
            if (callback != null)
                callback.onDataNotAvailable(R.string.data_network_error);
        }
    }





}
