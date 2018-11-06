package com.smileflowpig.money.factory.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.smileflowpig.money.factory.data.helper.LauncherHelper;

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
            if(intent.getStringExtra("regid")!=null){
                String regid =intent.getStringExtra("regid");
                Log.e(TAG,"我是服务，我收到的regid是"+regid);
                LauncherHelper.sendImei(regid);
            }


        }


        return super.onStartCommand(intent, flags, startId);
    }
}
