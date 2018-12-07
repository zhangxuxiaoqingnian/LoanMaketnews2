package com.smileflowpig.money.moneyplatfrom.push.reciver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.support.api.push.PushReceiver;
import com.smileflowpig.money.moneyplatfrom.LaunchActivity;
import com.smileflowpig.money.moneyplatfrom.MyLifecycleHandler;
import com.smileflowpig.money.moneyplatfrom.activities.CaseurlActivity;
import com.smileflowpig.money.moneyplatfrom.activities.DetailActivity;
import com.smileflowpig.money.moneyplatfrom.push.JumpJson;
import com.smileflowpig.money.moneyplatfrom.push.PushManager;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Log.e("huawei_push", "onEvent: " + bundle.toString());
        if (event == Event.NOTIFICATION_OPENED) {
            try {
                String pushMsg = bundle.getString(BOUND_KEY.pushMsgKey);
                Map<String, String> extra = new HashMap<>();
                    List<Map<String, String>> listObjectFir = (List<Map<String, String>>) com.alibaba.fastjson.JSONArray.parse(pushMsg);
                    for (Map<String, String> mapList : listObjectFir) {
                        for (Map.Entry entry : mapList.entrySet()) {
                            System.out.println(entry.getKey() + "  " + entry.getValue());
                            extra.put(entry.getKey() + "", entry.getValue() + "");
                        }
                    }
                String is_product = extra.get("is_product");
                if (is_product.equals("0")) {
                    //跳转咨询
                    String url = extra.get("url");
                    Intent intent = new Intent(context, CaseurlActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("push", true);
                    intent.putExtra("pushurl", url);
                    context.startActivity(intent);
                } else if (is_product.equals("1")) {
                    //跳转产品
                    String id = extra.get("id");
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

        }
    }
}

