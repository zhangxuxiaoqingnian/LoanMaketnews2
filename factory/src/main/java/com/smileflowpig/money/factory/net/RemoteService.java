package com.smileflowpig.money.factory.net;


import com.smileflowpig.money.factory.bean.BillData;
import com.smileflowpig.money.factory.bean.BillDetialData;
import com.smileflowpig.money.factory.bean.BillStatusData;
import com.smileflowpig.money.factory.bean.HongbaoBean;
import com.smileflowpig.money.factory.bean.HongbaoStatusJson;
import com.smileflowpig.money.factory.bean.ReceiveHongbaoJson;
import com.smileflowpig.money.factory.bean.TaskBean;
import com.smileflowpig.money.factory.bean.ZhenxinUrlJson;
import com.smileflowpig.money.factory.model.api.hongbao.HongbaoModel;
import com.smileflowpig.money.factory.model.api.RspModel;
import com.smileflowpig.money.factory.model.api.account.AccountRspModel;
import com.smileflowpig.money.factory.model.api.account.CodeModel;
import com.smileflowpig.money.factory.model.api.account.CodeRspModel;
import com.smileflowpig.money.factory.model.api.account.ContentModel;
import com.smileflowpig.money.factory.model.api.account.ForgetModel;
import com.smileflowpig.money.factory.model.api.account.LoginByCodeModel;
import com.smileflowpig.money.factory.model.api.account.LoginModel;
import com.smileflowpig.money.factory.model.api.account.RegisterModel;
import com.smileflowpig.money.factory.model.api.account.UpdateModel;
import com.smileflowpig.money.factory.model.api.active.ActiveModel;
import com.smileflowpig.money.factory.model.api.active.ActiveRspModel;
import com.smileflowpig.money.factory.model.api.application.PageModel;
import com.smileflowpig.money.factory.model.api.bill.AddBillModel;
import com.smileflowpig.money.factory.model.api.bill.BillListsModel;
import com.smileflowpig.money.factory.model.api.bill.BillstatusModel;
import com.smileflowpig.money.factory.model.api.bill.DeleteBillModel;
import com.smileflowpig.money.factory.model.api.bill.DetialModel;
import com.smileflowpig.money.factory.model.api.creditcard.CreditCardPageModel;
import com.smileflowpig.money.factory.model.api.creditcard.CreditModel;
import com.smileflowpig.money.factory.model.api.detail.AmountRspModel;
import com.smileflowpig.money.factory.model.api.detail.DetailModel;
import com.smileflowpig.money.factory.model.api.examine.BannerExprt;
import com.smileflowpig.money.factory.model.api.examine.BannerModel;
import com.smileflowpig.money.factory.model.api.examine.Examine;
import com.smileflowpig.money.factory.model.api.examine.Examine1;
import com.smileflowpig.money.factory.model.api.examine.HaLou;
import com.smileflowpig.money.factory.model.api.examine.HomeBean;
import com.smileflowpig.money.factory.model.api.examine.IdModel;
import com.smileflowpig.money.factory.model.api.examine.InfoModel;
import com.smileflowpig.money.factory.model.api.examine.RspExamHomeModel;
import com.smileflowpig.money.factory.model.api.examine.TabListParamter;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel;
import com.smileflowpig.money.factory.model.api.guoshen.AddModel1;
import com.smileflowpig.money.factory.model.api.guoshen.GetRepaymentModel;
import com.smileflowpig.money.factory.model.api.guoshen.PopModel;
import com.smileflowpig.money.factory.model.api.guoshen.RepaymentListBean;
import com.smileflowpig.money.factory.model.api.hongbao.ReceiveModel;
import com.smileflowpig.money.factory.model.api.launcher.LaunchRspModel;
import com.smileflowpig.money.factory.model.api.launcher.LauncherModel;
import com.smileflowpig.money.factory.model.api.launcher.NoticeRspModel;
import com.smileflowpig.money.factory.model.api.launcher.RspAdModel;
import com.smileflowpig.money.factory.model.api.message.MessageModel;
import com.smileflowpig.money.factory.model.api.message.MessageRspModel;
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
import com.smileflowpig.money.factory.model.api.search.FilterRspModel;
import com.smileflowpig.money.factory.model.api.search.SearchModel;
import com.smileflowpig.money.factory.model.api.web.WebModel;
import com.smileflowpig.money.factory.model.db.AllApp;
import com.smileflowpig.money.factory.model.db.ApplyModel;
import com.smileflowpig.money.factory.model.db.DeleteApp;
import com.smileflowpig.money.factory.model.db.Idfa;
import com.smileflowpig.money.factory.model.db.Tool;
import com.smileflowpig.money.factory.model.db.Version;

import java.util.List;

import okhttp3.RequestBody;
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
    Call<RspModel> update2(@Body ForgetModel forgetModel);

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

    @POST("information/getinformationsbypackage")
    Call<RspModel<HomeBean>> gethome(@Body HaLou haLou);

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

    //详情页
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
    Call<RspModel<CreditCardAppliModel>> getCreditCardAppli(@Body PageModel pageModel);

    @POST("CreditCard/list")
    Call<RspModel<CreditCardModel>> getCrediCard(@Body CreditCardPageModel pageModel);

    @POST("CreditCard/add")
    Call<RspModel> getCanAdd(@Body CreditModel creditModel);

    @POST("user/personalCenter")
    Call<RspModel<AccountRspModel>> getLoginState();

    //添加到我的足迹
    @POST("user/addapply")
    Call<RspModel<ApplyModel>> pushApply(@Body Apply apply);

    //删除记录
    @POST("product/markDelete")
    Call<RspModel<Integer>> deleteApp(@Body RequestBody requestBody);

    //删除信用卡记录
    @POST("creditCard/markDelete")
    Call<RspModel<Integer>> deleteCard(@Body RequestBody requestBody);

    //产品添加到我的申请
    @POST("product/automaticAddApply")
    @FormUrlEncoded
    Call<RspModel<DeleteApp>> addApp(@Field("product_id") String appuuid);

    //信用卡添加到我的申请
    @POST("creditCard/automaticAddApply")
    @FormUrlEncoded
    Call<RspModel<DeleteApp>> addCard(@Field("product_id") String carduuid);

    //返回所有App
    @GET("product/allApplyProduct")
    Call<RspModel<AllApp>> allApp();

    //返回所有信用卡
    @GET("creditCard/allApplyProduct")
    Call<RspModel<AllApp>> allCard();

    //获取活动页计算工具
    @GET("activity/getToolsList")
    Call<RspModel<List<Tool>>> tool();


    /**
     * 账单接口
     */

    //获取账单列表
    @POST("huaJiangHu/billList")
//    Call<RspModel<BillData>> getBillManagerLists(@Query("page") String page, @Query("status") String status);
    Call<RspModel<BillData>> getBillManagerLists(@Body BillListsModel billListsModel);

    //修改已还状态
    @POST("huaJiangHu/statusBill")
//    Call<RspModel<BillStatusData>> changeBillStatus(@Query("id") String id, @Query("status") String status);
    Call<RspModel<BillStatusData>> changeBillStatus(@Body BillstatusModel billstatusModel);

    //账单详情
    @POST("huaJiangHu/billDetail")
//    Call<RspModel<BillDetialData>> getBillDetial(@Query("page") String page, @Query("platform_id")String platform_id);
    Call<RspModel<BillDetialData>> getBillDetial(@Body DetialModel model);

    //删除账单
    @POST("huaJiangHu/delBill")
    Call<RspModel<Object>> deleteBill(@Body DeleteBillModel id);

    //添加账单
    @POST("huaJiangHu/addBill")
    Call<RspModel<Object>> addBill(@Body AddBillModel addBillModel);


    /**
     * 红包接口
     */
    //获取红包开启状态
    @POST("v2/HuaRedPacket/getRedPacketStatus")
    Call<RspModel<HongbaoBean>> getHongbaostatus();

    //获取用户是否领取过红包
    @POST("v2/HuaRedPacket/getUserRedPacketStatus")
    Call<RspModel<HongbaoStatusJson>> getUserHongbaostatus(@Body HongbaoModel del);

    //用户领取红包
    @POST("v2/HuaRedPacket/receiveRedPacket")
    Call<RspModel<ReceiveHongbaoJson>> receiveRedPacket(@Body ReceiveModel hongbaoModel);

    //任务列表
    @POST("v2/xiaoHuaZhu/taskList")
    Call<RspModel<TaskBean>> taskLists();

    //获取个人征信
    @GET("huaJiangHu/makeUrl")
    Call<RspModel<ZhenxinUrlJson>> getZhenxinUrl();


    /**
     * 华为上传token
     * @param token
     * @return
     */
    @POST("user/recordSignToken")
    @FormUrlEncoded
    Call<RspModel<Object>> sendToken(@Field("token") String token);

}
