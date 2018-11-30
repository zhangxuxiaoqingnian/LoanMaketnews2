package com.kuxuan.push;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.huawei.android.hms.agent.HMSAgent;
import com.huawei.android.hms.agent.common.handler.ConnectHandler;
import com.huawei.android.hms.agent.push.handler.GetPushStateHandler;
import com.huawei.android.hms.agent.push.handler.GetTokenHandler;
import com.huawei.hms.support.api.push.TokenResult;
import com.vivo.push.PushClient;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * 推送管理类
 * 功能：负责注册推送，各个平台注册推送
 */
public class PushManager {

    public static final String TAG = "pushManager";


    /**
     * 注册
     *
     * @param context
     */
    public static void register(Context context) {
        int phoneModel = PhoneUtil.getPhoneModel(context);
        registerHuaweiPush(context);
//        switch (phoneModel) {
//            case PhoneUtil.XIAOMI:
//                registerMiPush(context);
//                break;
//            case PhoneUtil.VIVO:
//                registerVivoPush(context);
//                break;
//            case PhoneUtil.HUAWEI:
//                registerHuaweiPush(context);
//                break;
//            default:
//                registerMiPush(context);
//                break;
//        }


    }

    private static boolean shouldInit(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注册小米推送
     *
     * @param context
     */
    private static void registerMiPush(Context context) {
        if (shouldInit(context)) {
            MiPushClient.registerPush(context, PushContants.XIAOMI_APP_ID, PushContants.XIAOMI_APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(context, newLogger);
    }

    /**
     * 注册vivo推送
     *
     * @param context
     */
    private static void registerVivoPush(Context context) {
        PushClient.getInstance(context).initialize();

    }


    /**
     * 注册oppo推送
     *
     * @param context
     */
    private static void registerOppoPush(Context context) {


    }

    /**
     * 注册华为推送
     *
     * @param context
     */
    private static void registerHuaweiPush(Context context) {
        Application app = (Application) context.getApplicationContext();
        HMSAgent.init(app);


    }


    /**
     * 华为获取token
     *
     * @param activity
     */
    private static void getHuaweiToken(Activity activity) {
//        int phoneModel = PhoneUtil.getPhoneModel(activity);
//        if (phoneModel == PhoneUtil.HUAWEI) {
            HMSAgent.Push.getToken(new GetTokenHandler() {
                @Override
                public void onResult(int rst) {

                }


            });
//        }
    }

    /**
     * 华为链接
     *
     * @param activity
     */
    public static void huaweiConnect(final Activity activity) {
//        int phoneModel = PhoneUtil.getPhoneModel(activity);
//        if (phoneModel == PhoneUtil.HUAWEI) {
            HMSAgent.connect(activity, new ConnectHandler() {
                @Override
                public void onConnect(int rst) {
                    Log.e("huaweipush_connect", rst + "");
                    getHuaweiToken(activity);
                    HMSAgent.Push.getPushState(new GetPushStateHandler() {
                        @Override
                        public void onResult(int rst) {

                        }
                    });
                }
            });
//        }
    }

    /**
     * 填充push别名或者token
     *
     * @param alias
     */
    public static void setPushAlais(String alias) {

    }
}
