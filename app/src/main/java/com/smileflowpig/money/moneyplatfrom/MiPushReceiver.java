package com.smileflowpig.money.moneyplatfrom;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import java.util.List;

import com.smileflowpig.money.factory.service.MyService;

/**
 * 小米的广播接收器
 */
public class MiPushReceiver extends PushMessageReceiver {

    @Override public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
    }

    @Override public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        Log.e("push", "mipush message: " + message.toString());

        if(_isApplicationRunning(context)){
            if(message.getExtra().containsKey("url")) {
                Intent intent = new Intent(context, DetailActivity.class);
                String url = message.getExtra().get("url");
                Log.e("mipushurl", url);
                int a = url.indexOf("id");

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String b = url.substring(a + 3, url.length());
                intent.putExtra("PRODUCT_ID", b);
                Log.e("id", b);
                context.startActivity(intent);
            }
        }else{
            Log.e("aaa","不在运行");
            Intent launchIntent = context.getPackageManager().
                    getLaunchIntentForPackage("com.smileflowpig.money");
            launchIntent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            String url = message.getExtra().get("url");
            Log.e("mipushurl", url);
            int a = url.indexOf("id");
            String b = url.substring(a + 3, url.length());
            Bundle args = new Bundle();
            args.putString("PRODUCT_ID", b);
            launchIntent.putExtra("push", args);
            context.startActivity(launchIntent);

        }

    }

    @Override public void onNotificationMessageArrived(Context context, MiPushMessage message) {
    }

    @Override public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                String regid = MiPushClient.getRegId(context);
                Log.e("ppppppp", "mipush regid: " + MiPushClient.getRegId(context));
                Intent intent = new Intent(context, MyService.class);
                intent.putExtra("regid",regid);
                context.startService(intent);
            }
        }
    }

    @Override public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
    }

    private boolean _isApplicationRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.processName.equals(context.getApplicationContext().getPackageName())) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String d: processInfo.pkgList) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
