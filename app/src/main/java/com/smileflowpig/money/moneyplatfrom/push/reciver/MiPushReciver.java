package com.smileflowpig.money.moneyplatfrom.push.reciver;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.JsonObject;
import com.smileflowpig.money.factory.service.MyService;
import com.smileflowpig.money.moneyplatfrom.LaunchActivity;
import com.smileflowpig.money.moneyplatfrom.MyLifecycleHandler;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.push.JumpJson;
import com.smileflowpig.money.moneyplatfrom.push.PushManager;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

import org.json.JSONObject;

import java.util.List;

public class MiPushReciver extends PushMessageReceiver {

    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
    }

    @Override
    public void onNotificationMessageClicked(Context context, MiPushMessage message) {
        Log.e("push", "mipush message: " + message.toString());
        String content = message.getContent();
        try {
            JSONObject jsonObject = new JSONObject(content);
            int is_product = jsonObject.getInt("is_product");
            if (is_product == 0) {
                //跳转咨询
                String url = jsonObject.getString("url");
                Intent intent = new Intent(context, CaseurlActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("push", true);
                intent.putExtra("pushurl", url);
                context.startActivity(intent);
            } else if (is_product == 1) {
                //跳转产品
                int id = jsonObject.getInt("id");
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("PRODUCT_ID", id + "");
                intent.putExtra("channel_type", "push");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Lang", 1);
                intent.putExtra("pricessid", 13);
                intent.putExtra("isPush", true);
                context.startActivity(intent);
            } else {
                //进入首页（如果）
                if (!MyLifecycleHandler.isApplicationInForeground()) {
                    Intent intent = new Intent(context, LaunchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        } catch (Exception e) {

        }
//        if (PushManager.mlis != null)
//            PushManager.mlis.onNotificationMessageClicked(context, new JumpJson());
//        if(_isApplicationRunning(context)){
//            if(message.getExtra().containsKey("url")) {
//                //获取信息跳转产品详情
////                Intent intent = new Intent(context, DetailActivity.class);
////                String url = message.getExtra().get("url");
////                Log.e("mipushurl", url);
////                int a = url.indexOf("id");
////
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                String b = url.substring(a + 3, url.length());
////                intent.putExtra("PRODUCT_ID", b);
////                Log.e("id", b);
////                context.startActivity(intent);
//            }
//        }else{
//            Log.e("aaa","不在运行");
//            Intent launchIntent = context.getPackageManager().
//                    getLaunchIntentForPackage("com.smileflowpig.money");
//            launchIntent.setFlags(
//                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            String url = message.getExtra().get("url");
//            Log.e("mipushurl", url);
//            int a = url.indexOf("id");
//            String b = url.substring(a + 3, url.length());
//            Bundle args = new Bundle();
//            args.putString("PRODUCT_ID", b);
//            launchIntent.putExtra("push", args);
//            context.startActivity(launchIntent);
//        }

    }

    @Override
    public void onNotificationMessageArrived(Context context, MiPushMessage message) {
    }

    @Override
    public void onCommandResult(Context context, MiPushCommandMessage message) {
        String command = message.getCommand();
        if (MiPushClient.COMMAND_REGISTER.equals(command)) {
            if (message.getResultCode() == ErrorCode.SUCCESS) {
                //小米注册成功
                String regid = MiPushClient.getRegId(context);
                Log.e("ppppppp", "mipush regid: " + MiPushClient.getRegId(context));
                Intent intent = new Intent(context, MyService.class);
                intent.putExtra("regid", regid);
                context.startService(intent);
            }
        }
    }

    @Override
    public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
    }

    private boolean _isApplicationRunning(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
            if (processInfo.processName.equals(context.getApplicationContext().getPackageName())) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String d : processInfo.pkgList) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}