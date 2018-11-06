package com.smileflowpig.money.moneyplatfrom.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlarmOpertor {

    public static final int REQUEST_CODE = 100;

    private static AlarmOpertor alarmOpertor;

    private Context context;

    private AlarmOpertor(Context context) {
        this.context = context;
    }

    public static AlarmOpertor getInstance(Context context) {
        if (alarmOpertor == null) {
            alarmOpertor = new AlarmOpertor(context);
        }
        return alarmOpertor;
    }


    public void setAlarm(long time, String title, String content) {
        AlarmManager am = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("alarmaction");
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        PendingIntent sender = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            am.setExact(AlarmManager.RTC_WAKEUP, time, sender);
        } else {
            am.set(AlarmManager.RTC_WAKEUP, time, sender);
        }
        Date currentTime = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = formatter.format(currentTime);

        Log.e("启动闹铃","时间:"+dateString);
    }
}
