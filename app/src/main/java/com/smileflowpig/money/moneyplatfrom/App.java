package com.smileflowpig.money.moneyplatfrom;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;

import com.smileflowpig.money.BuildConfig;
import com.smileflowpig.money.common.app.Application;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.net.Network;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.utils.UMUtils;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiaomi.mipush.sdk.MiPushClient;

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

        UMConfigure.init(this, "5bda5fdaf1f5564485000223"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        //友盟
        UMShareAPI.get(this);
        //友盟渠道配置
        UMUtils.setChannel(this,BuildConfig.CHANNLE);
        PlatformConfig.setWeixin("wx0ecd96dc278329e3", "69a60470c14742059f9d90194b92655b");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        this.context = getApplicationContext();
        //初始化
        Factory.setup();
        MobclickAgent.setDebugMode(true);
//        MobclickAgent.startWithConfigure(
//                new MobclickAgent.UMAnalyticsConfig(this, UMENG_APP_KEY, BASE_CHANNEL));
        //推送进行初始化
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }


        Network.channelId = BuildConfig.CHANNLE;
//        Fabric.with(this, new Crashlytics());


    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
