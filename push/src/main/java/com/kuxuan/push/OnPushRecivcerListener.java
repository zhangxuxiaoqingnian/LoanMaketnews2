package com.kuxuan.push;

import android.content.Context;
import android.os.Bundle;

import com.huawei.hms.support.api.push.PushReceiver;
import com.vivo.push.model.UPSNotificationMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;

public interface OnPushRecivcerListener {


    void onNotificationMessageClicked(Context context, JumpJson jumpJson);


}
