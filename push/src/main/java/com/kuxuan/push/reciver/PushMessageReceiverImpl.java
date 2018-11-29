package com.kuxuan.push.reciver;

import android.content.Context;

import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

public class PushMessageReceiverImpl extends OpenClientPushMessageReceiver {
    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage upsNotificationMessage) {

    }

    @Override
    public void onReceiveRegId(Context context, String s) {

    }
}
