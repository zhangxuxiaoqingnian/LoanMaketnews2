package money.kuxuan.platform.factory.netword;



import android.renderscript.ScriptIntrinsicYuvToRGB;

import java.util.List;

import io.reactivex.Observable;
import money.kuxuan.platform.factory.bean.AllMyBean;
import money.kuxuan.platform.factory.bean.Allbanner;
import money.kuxuan.platform.factory.bean.CancelMyBean;
import money.kuxuan.platform.factory.bean.CancleCollectBean;
import money.kuxuan.platform.factory.bean.CollectBean;
import money.kuxuan.platform.factory.bean.CollectListBean;
import money.kuxuan.platform.factory.bean.DaiBanner;
import money.kuxuan.platform.factory.bean.DetailBean;
import money.kuxuan.platform.factory.bean.HomedataBean;
import money.kuxuan.platform.factory.bean.HomeurlBean;
import money.kuxuan.platform.factory.bean.LapviewBean;
import money.kuxuan.platform.factory.bean.LikeBean;
import money.kuxuan.platform.factory.bean.LoseBean;
import money.kuxuan.platform.factory.bean.MemoBean;
import money.kuxuan.platform.factory.bean.MemoShowBean;
import money.kuxuan.platform.factory.bean.MyDetailBean;
import money.kuxuan.platform.factory.bean.MyHomeFragmentBean;
import money.kuxuan.platform.factory.bean.MyHomeListBean;
import money.kuxuan.platform.factory.bean.MyiconBean;
import money.kuxuan.platform.factory.bean.MynameBean;
import money.kuxuan.platform.factory.bean.NewDeatilsBean;
import money.kuxuan.platform.factory.bean.NewListBean;
import money.kuxuan.platform.factory.bean.QiangBean;
import money.kuxuan.platform.factory.model.api.Bean.HomeBase;
import money.kuxuan.platform.factory.model.api.RspModel;
import money.kuxuan.platform.factory.model.api.guoshen.RepaymentListBean;
import money.kuxuan.platform.factory.model.api.launcher.LauncherModel;
import money.kuxuan.platform.factory.model.api.launcher.RspAdModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by 小狼 on 2017/11/28.
 */

public interface Baseretrofit {


    @POST("advert/getStartAdvertRand")
    @FormUrlEncoded
    Observable<HomeBase> getAdPicture(@Field("resolution") String resolution);


    @GET("/user/repayment/repaymentList")
    Observable<NewListBean> getnewlist();

    @POST("/user/repayment/getRepaymentDetail")
    @FormUrlEncoded
    Observable<NewDeatilsBean> getnewdetail(@Field("id") int id);

    //
    @FormUrlEncoded
    @POST("/user/repayment/updateRepaymentDetail")
    Observable<Object> putStatus(@Field("data") String putStats);


    //获取首页banner
    @POST("/user/information/getClassificationAndBannerList")
    @FormUrlEncoded
    Observable<MyHomeFragmentBean> gethomebanner(@Field("package_id") String id);

    //获取首页列表
    @POST("/user/snatchOrder/orderList")
    @FormUrlEncoded
    Observable<MyHomeListBean> gethomelist(@Field("type") int id,@Field("page") int pager);

    //获取详情
    @POST("/user/snatchOrder/getOrderInfoById")
    @FormUrlEncoded
    Observable<MyDetailBean> getdetails(@Field("order_id") int id);

    //抢单
    @POST("/user/snatchOrder/snatchOrder")
    @FormUrlEncoded
    Observable<QiangBean> getqiang(@Field("order_id") int id);

    //取消抢单
    @POST("/user/snatchOrder/cancelSnatchOrder")
    @FormUrlEncoded
    Observable<QiangBean> getcancle(@Field("order_id") int id);

    //发布订单

    @POST("/user/snatchOrder/publishOrder")
    @FormUrlEncoded
    Observable<Object> setlist(@Field("type") int id,@Field("place") String address,@Field("remark") String remark
    ,@Field("remuneration") float price,@Field("time") String time,@Field("contacts") String man,@Field("contact_number") String phone,@Field("title") String title
    );

    //取消发布订单
    @POST("/user/snatchOrder/cancelPublishOrder")
    @FormUrlEncoded
    Observable<CancelMyBean> getmycancle(@Field("order_id") int id);

    //我的订单
    @POST("/user/snatchOrder/myOrder")
    @FormUrlEncoded
    Observable<AllMyBean> getmylist(@Field("type") int id,@Field("page") int page);


    //获取首页列表
    @POST("/user/snatchOrder/orderList")
    @FormUrlEncoded
    Observable<MyHomeListBean> gettypelist(@Field("type") int id,@Field("page") int page,@Field("ranking_list") String rank);

    //修改头像
    @Multipart
    @POST("/user/snatchOrder/updateUserInformation")
    Observable<MyiconBean> geticon(@Part MultipartBody.Part file);

    //修改昵称
    @POST("/user/snatchOrder/updateUserInformation")
    @FormUrlEncoded
    Observable<MynameBean> getname(@Field("nick") String id);


    //确认订单
    @POST("/user/snatchOrder/confirmOrder")
    @FormUrlEncoded
    Observable<Object> getsuccess(@Field("order_id") int id);

    //线上
    //获取banner
    @POST("product/homeact")
    @FormUrlEncoded
    Observable<Allbanner> getallbanner(@Field("version") int id);

    //获取公告接口
    @POST("homeact/getNoticeList")
    Observable<LapviewBean> getgao();

    //请求产品详情
    @POST("product/detail")
    @FormUrlEncoded
    Observable<Object> getdetal(@Field("id") String id,@Field("enter_source") String enter);

    //获取首页3个模块的数据
    @POST("/user/product/getProductListByCatId")
    @FormUrlEncoded
    Observable<HomedataBean> gethomedata(@Field("id") int id, @Field("source") int source,@Field("num") int num,@Field("page") int page);

    //获取忽略模块
    @POST("/user/product/getRecommend")
    Observable<LoseBean> gethomelose();

    //获取详情页
    @POST("product/detail")
    @FormUrlEncoded
    Observable<DetailBean> getdetail(@Field("enter_source") String source, @Field("id") String id);

    //获取产品列表
    @POST("/user/product/searchSecond")
    @FormUrlEncoded
    Observable<DaiBanner> getlistdata(@Field("search") String search, @Field("term") String term, @Field("amount") String amount, @Field("user_title") String user,@Field("num") int num,@Field("page") int page);


    //猜你喜欢
    @POST("/user/product/getSameAmountByProductId")
    @FormUrlEncoded
    Observable<LikeBean> getlikedata(@Field("id") int id);

    //进行收藏
    @POST("/user/product/addCollection")
    @FormUrlEncoded
    Observable<CollectBean> getcollect(@Field("product_id") String id);
    //取消收藏
    @POST("/user/product/cancelCollection")
    @FormUrlEncoded
    Observable<CancleCollectBean> getnocollect(@Field("product_ids[]") List<String> str);

    //获取收藏列表
    @POST("/user/product/collectionList")
    @FormUrlEncoded
    Observable<CollectListBean> getcollectlist(@Field("page") int page,@Field("num") int num);

    //获取备忘录列表
    @POST("/user/huaJiangHu/memoList")
    @FormUrlEncoded
    Observable<MemoBean> getmemolist(@Field("page") String page);

    //添加备忘录
    @POST("/user/huaJiangHu/addMemo")
    @FormUrlEncoded
    Observable<Object> getmemoadd(@Field("platform_id") String platid,@Field("how_to_use") String use,
                                    @Field("how_much") String much,@Field("how_long") String longer,
                                    @Field("due_date") String data,@Field("gross_interest") String interest);
    //删除备忘录
    @POST("/user/huaJiangHu/delMemo")
    @FormUrlEncoded
    Observable<Object> getmemodetele(@Field("id") String id);

    //获取编辑页信息
    @POST("/user/huaJiangHu/showMemo")
    @FormUrlEncoded
    Observable<MemoShowBean> getmemoshow(@Field("id") String id);

    //保存修改信息
    @POST("/user/huaJiangHu/editMemo")
    @FormUrlEncoded
    Observable<Object> getmemoupdata(@Field("id") String id,@Field("platform_id") String platid,@Field("how_to_use") String use,
                                           @Field("how_much") String much,@Field("how_long") String longer,
                                           @Field("due_date") String data,@Field("gross_interest") String interest);

    //修改个人信息
    @POST("/user/snatchOrder/updateUserInformation")
    @Multipart
    Observable<Object> getmylisticon(@Part MultipartBody.Part nick,@Part MultipartBody.Part file,@Part MultipartBody.Part gender,@Part MultipartBody.Part identity);

    //首页上边5个类型
    @POST("/user/product/getH5List")
    @FormUrlEncoded
    Observable<HomeurlBean> geturllist(@Field("source") String source);

    //资讯类别
    @POST("/user/huaJiangHu/newsCategory")
    Observable<Object> gettexttype();

    //资讯列表
    @POST("/user/huaJiangHu/newsList")
    @FormUrlEncoded
    Observable<Object> gettextlist(@Field("type") String type,@Field("category") String category,@Field("page") String page);

    //资讯详情
    @POST("/user/huaJiangHu/newsDetail")
    @FormUrlEncoded
    Observable<Object> gettextdetail(@Field("id") String id,@Field("view_num") String view_num);



}
