package com.smileflowpig.money.moneyplatfrom.push.reciver;

import android.content.Context;

import com.smileflowpig.money.moneyplatfrom.push.JumpJson;
import com.smileflowpig.money.moneyplatfrom.push.PushManager;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

public class PushMessageReceiverImpl extends OpenClientPushMessageReceiver {
    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage upsNotificationMessage) {
        if (PushManager.mlis != null)
            PushManager.mlis.onNotificationMessageClicked(context, new JumpJson());
    }

    @Override
    public void onReceiveRegId(Context context, String s) {

    }
}
