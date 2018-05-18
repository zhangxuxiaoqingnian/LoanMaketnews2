package money.kuxuan.platform.factory.net;


import java.util.List;

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
import money.kuxuan.platform.factory.model.api.active.ActiveModel;
import money.kuxuan.platform.factory.model.api.active.ActiveRspModel;
import money.kuxuan.platform.factory.model.api.application.PageModel;
import money.kuxuan.platform.factory.model.api.creditcard.CreditCardPageModel;
import money.kuxuan.platform.factory.model.api.creditcard.CreditModel;
import money.kuxuan.platform.factory.model.api.detail.AmountRspModel;
import money.kuxuan.platform.factory.model.api.detail.DetailModel;
import money.kuxuan.platform.factory.model.api.examine.BannerExprt;
import money.kuxuan.platform.factory.model.api.examine.BannerModel;
import money.kuxuan.platform.factory.model.api.examine.Examine;
import money.kuxuan.platform.factory.model.api.examine.Examine1;
import money.kuxuan.platform.factory.model.api.examine.IdModel;
import money.kuxuan.platform.factory.model.api.examine.InfoModel;
import money.kuxuan.platform.factory.model.api.examine.RspExamHomeModel;
import money.kuxuan.platform.factory.model.api.examine.TabListParamter;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel;
import money.kuxuan.platform.factory.model.api.guoshen.AddModel1;
import money.kuxuan.platform.factory.model.api.guoshen.GetRepaymentModel;
import money.kuxuan.platform.factory.model.api.guoshen.PopModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.model.api.launcher.LaunchRspModel;
import money.kuxuan.platform.factory.model.api.launcher.LauncherModel;
import money.kuxuan.platform.factory.model.api.launcher.NoticeRspModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import money.kuxuan.platform.factory.model.api.message.MessageModel;
import money.kuxuan.platform.factory.model.api.message.MessageRspModel;
import money.kuxuan.platform.factory.model.api.product.Apply;
import money.kuxuan.platform.factory.model.api.product.ApplyProductModel;
import money.kuxuan.platform.factory.model.api.product.BannerRspModel;
import money.kuxuan.platform.factory.model.api.product.CategoryModel;
import money.kuxuan.platform.factory.model.api.product.ChildModel;
import money.kuxuan.platform.factory.model.api.product.CreditCardAppliModel;
import money.kuxuan.platform.factory.model.api.product.CreditCardModel;
import money.kuxuan.platform.factory.model.api.product.PlatFormModel;
import money.kuxuan.platform.factory.model.api.product.ProductDetail;
import money.kuxuan.platform.factory.model.api.product.ProductRspModel;
import money.kuxuan.platform.factory.model.api.search.FilterRspModel;
import money.kuxuan.platform.factory.model.api.search.SearchModel;
import money.kuxuan.platform.factory.model.api.web.WebModel;
import money.kuxuan.platform.factory.model.db.ApplyModel;
import money.kuxuan.platform.factory.model.db.Idfa;
import money.kuxuan.platform.factory.model.db.Version;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 网络请求的所有的接口
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public interface RemoteService {

    /**
     * 注册接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("user/confirmRegister")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    @POST("user/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);

    @POST("user/loginCode")
    Call<RspModel<AccountRspModel>> accountByCode(@Body LoginByCodeModel model);

    @POST("user/logout")
    Call<RspModel> exist();

    @GET("/user/repayment/repaymentList")
    Call<RspModel<List<RepaymentListBean>>> getRepaymentList();


    @FormUrlEncoded
    @POST("/user/repayment/getRepaymentDetail")
    Call<RspModel<List<PopModel>>> getPopDetail(@Field("id") int id);

    @FormUrlEncoded
    @POST("/user/repayment/updateRepaymentDetail")
    Call<RspModel> putStatus(@Field("data") String putStats);

    @POST("user/updatePassword")
    Call<RspModel> update(@Body UpdateModel updateModel);

    @POST("user/personalCenter")
    Call<RspModel<AccountRspModel>> loginState();


    @POST("user/confirmForgetPasswordCode")
    Call<RspModel> update(@Body ForgetModel forgetModel);

    @POST("user/addMessage")
    Call<RspModel> sendMessage(@Body ContentModel contentModel);



    @POST("user/register")
    Call<RspModel<CodeRspModel>> loginByCode(@Body CodeModel model);

    @POST("user/forgetPassword")
    Call<RspModel<CodeRspModel>> forgetByCode(@Body CodeModel model);


    //获取banner图
    @POST("product/homeact")
    Call<RspModel<BannerRspModel>> getBannerData(@Body Version version);

    @POST("product/searchSecond")
    Call<RspModel<ProductRspModel>> getHomeData(@Body PlatFormModel model);

    @POST("information/getinformationsbypackage")
    Call<RspModel<RspExamHomeModel>> getExamHomeData(@Body Examine1 model);

    @POST("information/getclassification")
    Call<RspModel<List<String>>> getTabList(@Body TabListParamter tabListParamter);

    @GET("information/banner")
    Call<RspModel<BannerModel>> getBanner();


    @POST("information/getinformationsbypackage")
    Call<RspModel<BannerExprt>> getExpertHomeData(@Body Examine model);

    @POST("information/getinformationcontentbyid")
    Call<RspModel<InfoModel>> getInfoData(@Body IdModel model);

    @POST("category/list")
    Call<CategoryModel> getCategoryData();

    @POST("activity/getActivityList")
    Call<RspModel<ActiveRspModel>> getActiveData(@Body ActiveModel model);

    @POST("product/searchSecond")
    Call<RspModel<ProductRspModel>> getSearchData(@Body SearchModel searchModel);


    @POST("user/updateApplyStatus")
    Call<RspModel> ApplyState(@Body WebModel webModel);



    @POST("product/detail")
    Call<RspModel<ProductDetail>> getDetailData(@Body DetailModel detailModel);

    @POST("/user/repayment/addRepayment")
    Call<RspModel<Object>> getAddRepayment(@Body AddModel addModel);

    @POST("/user/repayment/updateRepayment")
    Call<RspModel<Object>> getAddRepayment1(@Body AddModel1 addModel);



    @FormUrlEncoded
    @POST("/user/repayment/getRepayment")
    Call<RspModel<GetRepaymentModel>> getRepayment(@Field("id") int id);


    @POST("product/getSameAmountByProductId")
    Call<RspModel<AmountRspModel>> getHorData(@Body DetailModel detailModel);

    @POST("product/getInfoByCat")
    Call<RspModel<ProductRspModel>> getChild(@Body ChildModel childModel);

    @POST("product/filter")
    Call<RspModel<FilterRspModel>> getDialogList();

    @POST("advert/getStartAdvertRand")
    Call<RspModel<RspAdModel>> getAdPicture(@Body LauncherModel launcherModel);

    @POST("advert/getAlertAdvertRand")
    Call<RspModel<RspAdModel>> getShowPicture();

    @POST("allChannel/getDevByChannelId")
    Call<RspModel<LaunchRspModel>> getChannel(@Query("channel_id") String channelId);

    @POST("user/idfa")
    Call<RspModel> sendIdfa(@Body Idfa idfa);

    @POST("news/getNewsList")
    Call<RspModel<MessageRspModel>> geMessage(@Body MessageModel messageModel);

    @POST("homeact/getNoticeList")
    Call<RspModel<NoticeRspModel>> getNotice();

    @POST("user/myapply")
    Call<RspModel<ApplyProductModel>> getApplication(@Body PageModel pageModel);


    @POST("CreditCard/myapply")
    Call<RspModel<CreditCardAppliModel>> getCreditCardAppli(@Body CreditCardPageModel pageModel);


    @POST("CreditCard/list")
    Call<RspModel<CreditCardModel>> getCrediCard(@Body CreditCardPageModel pageModel);

    @POST("CreditCard/add")
    Call<RspModel> getCanAdd(@Body CreditModel creditModel);



    @POST("user/personalCenter")
    Call<RspModel<AccountRspModel>> getLoginState();


    @POST("user/addapply")
    Call<RspModel<ApplyModel>> pushApply(@Body Apply apply);
}
