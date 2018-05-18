package money.kuxuan.platform.moneyplatfrom;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
//import com.crashlytics.android.Crashlytics;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;
//import io.fabric.sdk.android.Fabric;
import money.kuxuan.platform.common.app.Application;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.net.Network;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class App extends Application {

    public static final String APP_ID = "2882303761517556732";
    public static final String APP_KEY = "5821755628732";
    private static final String TAG = "App";
    public static final String UMENG_APP_KEY = "59b64bfa677baa34a00017ca";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Factory.setup();
        MobclickAgent.setDebugMode(true);
//        MobclickAgent.startWithConfigure(
//                new MobclickAgent.UMAnalyticsConfig(this, UMENG_APP_KEY, BASE_CHANNEL));
        //推送进行初始化
        if (shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        Network.channelId = money.kuxuan.platform.moneyplatfrom.BuildConfig.CHANNLE;
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
