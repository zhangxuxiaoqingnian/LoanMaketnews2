package com.smileflowpig.money.factory.data.helper;

import android.util.Log;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.R;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.application.PageModel;
import com.smileflowpig.money.factory.model.api.creditcard.CreditCardPageModel;
import com.smileflowpig.money.factory.model.api.creditcard.PageModelAll;
import com.smileflowpig.money.factory.model.api.detail.AmountRspModel;
import com.smileflowpig.money.factory.model.api.detail.DetailModel;
import com.smileflowpig.money.factory.model.api.launcher.NoticeRspModel;
import com.smileflowpig.money.factory.model.api.product.Apply;
import com.smileflowpig.money.factory.model.api.product.ApplyProductModel;
import com.smileflowpig.money.factory.model.api.product.BannerRspModel;
import com.smileflowpig.money.factory.model.api.product.CategoryModel;
import com.smileflowpig.money.factory.model.api.product.ChildModel;
import com.smileflowpig.money.factory.model.api.product.CreditCardAppliModel;
import com.smileflowpig.money.factory.model.api.product.CreditCardModel;
import com.smileflowpig.money.factory.model.api.product.PlatFormModel;
import com.smileflowpig.money.factory.model.api.product.ProductDetail;
import com.smileflowpig.money.factory.model.api.product.ProductRspModel;
import com.smileflowpig.money.factory.model.db.ApplyModel;
import com.smileflowpig.money.factory.model.db.Version;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class ProductHelper {

    /**
     * 首页接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void refreshHome(PlatFormModel model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ProductRspModel>> call = service.getHomeData(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<ProductRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<ProductRspModel>> call, Response<RspModel<ProductRspModel>> response) {
                RspModel<ProductRspModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {

                    callback.onDataLoaded(rspModel.getRst());


                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ProductRspModel>> call, Throwable t) {


                if (callback != null)
                    callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }



    /**
     * 子分类接口
     *
     * @param model
     * @param callback 成功与失败的接口回调
     */
    public static void refreshChild(ChildModel model, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ProductRspModel>> call = service.getChild(model);

        // 异步的请求
        call.enqueue(new Callback<RspModel<ProductRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<ProductRspModel>> call, Response<RspModel<ProductRspModel>> response) {
                RspModel<ProductRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ProductRspModel>> call, Throwable t) {

            }
        });
    }

    /**
     * banner接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void refreshBanner(final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Version version = new Version(2);
        Call<RspModel<BannerRspModel>> call = service.getBannerData(version);

        // 异步的请求
        call.enqueue(new Callback<RspModel<BannerRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<BannerRspModel>> call, Response<RspModel<BannerRspModel>> response) {
                RspModel<BannerRspModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<BannerRspModel>> call, Throwable t) {

            }
        });
    }


    /**
     * 分类接口
     *获取label_name字段
     * @param callback 成功与失败的接口回调
     */
    public static void refreshLabel(final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<CategoryModel> call = service.getCategoryData();

        // 异步的请求
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                CategoryModel rspModel = response.body();
                if (rspModel!=null&&rspModel.getErrno().equals("0")) {

                    callback.onDataLoaded(rspModel);

                } else {

                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {

            }
        });
    }

    /**
     * 详情页接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getDetailData(DetailModel detailModel,final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ProductDetail>> call = service.getDetailData(detailModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<ProductDetail>>() {
            @Override
            public void onResponse(Call<RspModel<ProductDetail>> call, Response<RspModel<ProductDetail>> response) {
                RspModel<ProductDetail> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ProductDetail>> call, Throwable t) {

            }
        });
    }


    /**
     * 横向view接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getHorData(DetailModel detailModel,final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<AmountRspModel>> call = service.getHorData(detailModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<AmountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AmountRspModel>> call, Response<RspModel<AmountRspModel>> response) {
                RspModel<AmountRspModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                    Log.e("aaaaa", rspModel.success() + "");
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AmountRspModel>> call, Throwable t) {
                Log.e("aaaaa", t.getMessage());
            }
        });
    }

    /**
     * 发送申请数据
     *
     * @param callback 成功与失败的接口回调
     */
    public static void pushData(Apply apply, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<ApplyModel>> call = service.pushApply(apply);

        // 异步的请求
        call.enqueue(new Callback<RspModel<ApplyModel>>() {
            @Override
            public void onResponse(Call<RspModel<ApplyModel>> call, Response<RspModel<ApplyModel>> response) {
                RspModel<ApplyModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());

                    Log.e("aaaaa", rspModel.success() + "");
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<ApplyModel>> call, Throwable t) {
                Log.e("aaaaa", t.getMessage());
            }
        });
    }


    //    /**
    //     * 发送申请数据
    //     *
    //     * @param callback 成功与失败的接口回调
    //     */
    //    public static void pushappy(Apply apply, final DataSource.Callback callback) {
    //        RemoteService service = Network.remote();
    //        Call<RspModel> call = service.pushApply(apply);
    //
    //        // 异步的请求
    //        call.enqueue(new Callback<RspModel>() {
    //            @Override
    //            public void onResponse(Call<RspModel> call, Response<RspModel> response) {
    //                RspModel<AmountRspModel> rspModel = response.body();
    //                if (rspModel.success()) {
    //                    callback.onDataLoaded(rspModel.getRst());
    //                    Log.e("aaaaa", rspModel.success() + "");
    //                } else {
    //                    Factory.decodeRspCode(rspModel, callback);
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure(Call<RspModel> call, Throwable t) {
    //                Log.e("aaaaa", t.getMessage());
    //            }
    //        });
    //    }




    /**
     * 获取公告接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getNotice(final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<NoticeRspModel>> call = service.getNotice();

        // 异步的请求
        call.enqueue(new Callback<RspModel<NoticeRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<NoticeRspModel>> call, Response<RspModel<NoticeRspModel>> response) {
                RspModel<NoticeRspModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                    Log.e("aaaaa", rspModel.success() + "");
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<NoticeRspModel>> call, Throwable t) {
                Log.e("aaaaa", t.getMessage());
            }
        });
    }



    /**
     * 获取申请接口
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getApplication(boolean isClick, PageModelAll pageModel, final DataSource.Callback callback) {
        RemoteService service;

        if(isClick) {
            service = Network.remote();
            Call<RspModel<ApplyProductModel>> call = service.getApplication((PageModel) pageModel);
            // 异步的请求
            call.enqueue(new Callback<RspModel<ApplyProductModel>>() {
                @Override
                public void onResponse(Call<RspModel<ApplyProductModel>> call, Response<RspModel<ApplyProductModel>> response) {
                    RspModel<ApplyProductModel> rspModel = response.body();
                    if (rspModel!=null&&rspModel.success()) {
                        callback.onDataLoaded(rspModel.getRst());
                        Log.e("aaaaa", rspModel.success() + "");
                    } else {
                        Factory.decodeRspCode(rspModel, callback);
                    }
                }

                @Override
                public void onFailure(Call<RspModel<ApplyProductModel>> call, Throwable t) {
                    Log.e("aaaaa", t.getMessage());

                    callback.onDataNotAvailable(R.string.data_network_error);

                }
            });

        }else {


            service = Network.remote();
            Call<RspModel<CreditCardAppliModel>> call = service.getCreditCardAppli((PageModel) pageModel);

            call.enqueue(new Callback<RspModel<CreditCardAppliModel>>() {
                @Override
                public void onResponse(Call<RspModel<CreditCardAppliModel>> call, Response<RspModel<CreditCardAppliModel>> response) {

                    RspModel<CreditCardAppliModel> rspModel = response.body();

                    if (rspModel != null && rspModel.success()) {
                        callback.onDataLoaded(rspModel.getRst());

                    } else {
                        Factory.decodeRspCode(rspModel, callback);

                    }

                }
                @Override
                public void onFailure(Call<RspModel<CreditCardAppliModel>> call, Throwable t) {
                    callback.onDataNotAvailable(R.string.data_network_error);

                }
            });


        }







    }



    /**
     * 获取信用卡
     *
     * @param callback 成功与失败的接口回调
     */
    public static void getCrediCard(CreditCardPageModel pageModel, final DataSource.Callback callback) {
        RemoteService service = Network.remote();
        Call<RspModel<CreditCardModel>> call;

        call = service.getCrediCard(pageModel);

        // 异步的请求
        call.enqueue(new Callback<RspModel<CreditCardModel>>() {
            @Override
            public void onResponse(Call<RspModel<CreditCardModel>> call, Response<RspModel<CreditCardModel>> response) {
                RspModel<CreditCardModel> rspModel = response.body();
                if (rspModel!=null&&rspModel.success()) {
                    callback.onDataLoaded(rspModel.getRst());
                    Log.e("aaaaa", rspModel.success() + "");
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<CreditCardModel>> call, Throwable t) {
                Log.e("aaaaa", t.getMessage());
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }


}
