package money.kuxuan.platform.moneyplatfrom;


import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.os.StrictMode;
//import com.crashlytics.android.Crashlytics;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.util.List;
//import io.fabric.sdk.android.Fabric;
import money.kuxuan.platform.common.app.Application;
import money.kuxuan.platform.factory.Factory;
import money.kuxuan.platform.factory.net.Network;
import money.kuxuan.platform.moneyplatfrom.sqlite.DatabaseHelper;


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

        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                        ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        this.context=getApplicationContext();
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

        //友盟
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx0ecd96dc278329e3", "69a60470c14742059f9d90194b92655b");

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
