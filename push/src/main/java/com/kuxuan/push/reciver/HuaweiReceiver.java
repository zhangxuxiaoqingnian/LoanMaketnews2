package com.kuxuan.push.reciver;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;
import com.kuxuan.push.JumpJson;
import com.kuxuan.push.PushManager;

public class HuaweiReceiver extends PushReceiver {


    @Override
    public void onToken(Context context, String s, Bundle bundle) {
        super.onToken(context, s, bundle);
        Log.e("huawei_push", "onToken: " + bundle.toString());
    }


    @Override
    public void onPushMsg(Context context, byte[] bytes, String s) {
        super.onPushMsg(context, bytes, s);
        Log.e("huawei_push", "onPushMsg: " + s);
    }


    @Override
    public void onPushState(Context context, boolean b) {
        super.onPushState(context, b);
        Log.e("huawei_push", "onPushState: " + b);
    }


    @Override
    public void onEvent(Context context, Event event, Bundle bundle) {
//        super.onEvent(context, event, bundle);
        if (event == Event.NOTIFICATION_OPENED) {
            Log.e("huawei_push", "onEvent: " + bundle.toString());
            if (PushManager.mlis != null){
                PushManager.mlis.onNotificationMessageClicked(context, new JumpJson());
            }


        }
    }
}
