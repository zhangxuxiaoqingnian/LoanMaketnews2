package com.smileflowpig.money.moneyplatfrom;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;

import com.kuxuan.push.PushManager;
import com.smileflowpig.money.BuildConfig;
import com.smileflowpig.money.common.app.Application;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.net.Network;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;


import java.util.List;


//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class App extends Application {

    public static final String APP_ID = "2882303761517556732";
    public static final String APP_KEY = "5821755628732";
    private static final String TAG = "App";
    public static final String UMENG_APP_KEY = "59b64bfa677baa34a00017ca";

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();


        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        UMConfigure.setLogEnabled(true);
//        UMConfigure.init(this,"5b1ddad3f43e485f4d00003e","umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.init(this, "5bda5fdaf1f5564485000223"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMUtils.setChannel(this, BuildConfig.CHANNLE);
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx0ecd96dc278329e3", "69a60470c14742059f9d90194b92655b");
        PlatformConfig.setQQZone("101524904","5427cfe5dbe05f72e6fc44acafbb93c3");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        context = getApplicationContext();
        //初始化
        Factory.setup();
//        MobclickAgent.setDebugMode(true);
//        MobclickAgent.startWithConfigure(
//                new MobclickAgent.UMAnalyticsConfig(this, UMENG_APP_KEY, BASE_CHANNEL));
        //推送进行初始化

        PushManager.register(this);

        Network.channelId = BuildConfig.CHANNLE;
//        Fabric.with(this, new Crashlytics());


    }

}
