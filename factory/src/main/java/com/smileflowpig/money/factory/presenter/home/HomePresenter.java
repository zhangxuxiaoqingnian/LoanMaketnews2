package com.smileflowpig.money.factory.presenter.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.StringRes;
import android.util.Log;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.common.factory.presenter.BasePresenter;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.R;
import com.smileflowpig.money.factory.data.helper.LauncherHelper;
import com.smileflowpig.money.factory.data.helper.MessageHelper;
import com.smileflowpig.money.factory.data.helper.ProductHelper;
import com.smileflowpig.money.factory.model.api.launcher.LaunchRspModel;
import com.smileflowpig.money.factory.model.api.launcher.NoticeRspModel;
import com.smileflowpig.money.factory.model.api.message.MessageModel;
import com.smileflowpig.money.factory.model.api.message.MessageRspModel;
import com.smileflowpig.money.factory.model.api.product.BannerRspModel;
import com.smileflowpig.money.factory.model.api.product.CategoryModel;
import com.smileflowpig.money.factory.model.api.product.PlatFormModel;
import com.smileflowpig.money.factory.model.api.product.ProductRspModel;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.persistence.Account;

import static java.lang.Integer.parseInt;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {
    private static final String isHot = "1";
    private int page = 1;
    private  int messgaePage = 1;
    private boolean hasNext = false;
    private static final String TAG = "HomePresenter";
    private static final String KEY_MESSAGE_MAX = "KEY_MESSAGE_MAX";

    int maxId = 0;
    public HomePresenter(HomeContract.View view) {
        super(view);
    }


    @Override
    public void start() {
        super.start();
        page = 1;
        messgaePage = 1;
        hasNext = false;
        maxId = 0;
        PlatFormModel model = new PlatFormModel(isHot, page,null);
        ProductHelper.refreshHome(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(getView()!=null)
                getView().onDone(productRspModel.getData());

                    if(productRspModel.getPageinfo().isHasNext()){
                        HomePresenter.this.hasNext = true;
                        page++;
                    }else{
                        HomePresenter.this.hasNext = false;
                        if(getView()!=null)
                            getView().NoData();

                    }


            }
        });
        ProductHelper.refreshBanner(new DataSource.Callback<BannerRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }

            @Override
            public void onDataLoaded(BannerRspModel bannerRspModel) {
                if(getView()!=null)
                getView().onBanner(bannerRspModel.getHomeact());
            }
        });
        ProductHelper.getNotice(new DataSource.Callback<NoticeRspModel>() {

            @Override
            public void onDataLoaded(NoticeRspModel noticeRspModel) {
                if(getView()!=null)
                getView().getNotice(noticeRspModel.getData());

            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(strRes);

            }


        });

        ProductHelper.refreshLabel(new DataSource.Callback<CategoryModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {

            }

            @Override
            public void onDataLoaded(CategoryModel categoryModel) {
                if(getView()!=null)
                getView().onCategory(categoryModel.getRst());
            }
        });


        LauncherHelper.getChannel(Network.channelId, new DataSource.Callback<LaunchRspModel>() {
            @Override
            public void onDataLoaded(LaunchRspModel launcherModel) {
                   int versionCode = parseInt(getVersionCode(Factory.app()));
                   int netVersionName;
                     try{
                         netVersionName =  Integer.parseInt(launcherModel.getVersion());
                     }catch (Exception e){
                         netVersionName = 0;
                     }


                    if(netVersionName>versionCode&&launcherModel.getAndroid().equals("0")){
                        if(getView()!=null)
                         getView().version(launcherModel.getUrl());
                    }

            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
                getView().showError(R.string.data_network_error);
            }

        });

        MessageModel messageModel = new MessageModel(messgaePage);
        MessageHelper.getMessageData(messageModel, new DataSource.Callback<MessageRspModel>() {
            @Override
            public void onDataLoaded(MessageRspModel rspModel) {
               if(load(Factory.app())<rspModel.getMax_id()){
                   if(getView()!=null)
                   getView().getMessageData(rspModel,true);
                   Log.e(TAG,"TIAOSHU"+rspModel.getMax_id()+"true");
               }else{
                   if(getView()!=null)
                   getView().getMessageData(rspModel,false);
                   Log.e(TAG,"TIAOSHU"+rspModel.getMax_id()+"false");
               }

            }

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                if(getView()!=null)
               getView().showError(strRes);
            }


        });

    }


    @Override
    public void requestData() {
        if(hasNext==false)
            return;
        PlatFormModel model = new PlatFormModel(isHot, page,null);
        ProductHelper.refreshHome(model, new DataSource.Callback<ProductRspModel>() {

            @Override
            public void onDataNotAvailable(@StringRes int strRes) {
                getView().showError(strRes);
            }

            @Override
            public void onDataLoaded(ProductRspModel productRspModel) {
                if(getView()!=null)
                getView().refresh(productRspModel.getData());
                if(productRspModel.getPageinfo().isHasNext()){
                    hasNext =true;
                    page++;
                }else{
                    hasNext=false;
                    if(getView()!=null)
                    getView().NoData();
                }
            }
        });
    }

    public static void save(Context context,int max){
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        sp.edit()
                .putInt(KEY_MESSAGE_MAX,max)
                .commit();
    }

    private int load(Context context){
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        maxId = sp.getInt(KEY_MESSAGE_MAX,0);
        return maxId;
    }

    /**
     * get App versionCode
     * @param context
     * @return
     */
    public static String getVersionCode(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionCode=packageInfo.versionCode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
