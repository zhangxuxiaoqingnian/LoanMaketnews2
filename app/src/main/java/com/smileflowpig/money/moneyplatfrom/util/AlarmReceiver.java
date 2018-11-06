package com.smileflowpig.money.moneyplatfrom.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import com.smileflowpig.money.moneyplatfrom.activities.MainActivity;

import com.smileflowpig.money.R;


/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
//注意：receiver记得在manifest.xml注册
public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_pig);
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");
        long ids = intent.getLongExtra("id", 11);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setSmallIcon(R.mipmap.icon_logo);
//        RemoteViews contentView = new RemoteViews(context.getPackageName(),R.layout.notification);
//        contentView.setTextViewText(R.id.notification_cotnent,content);
//        contentView.setTextViewText(R.id.notification_title,name);
//        builder.setContent(contentView);
//        builder.setAutoCancel(true);
//        // 需要VIBRATE权限
//        builder.setDefaults(Notification.DEFAULT_VIBRATE);
//        builder.setPriority(Notification.PRIORITY_DEFAULT);
//        NotificationManager notificationManager = (NotificationManager) context
//                .getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent1 = new Intent(context, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        int requestCode = (int) SystemClock.uptimeMillis();
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//        contentView.setOnClickPendingIntent(R.id.notification, pendingIntent);
//        notificationManager.notify(111, builder.build());
//        //建立一个RemoteView的布局，并通过RemoteView加载这个布局
//
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);
//
//        //为remoteView设置图片和文本
//
//        remoteViews.setTextViewText(R.id.notification_title, name);
//
//        remoteViews.setTextViewText(R.id.notification_cotnent, content);
//
//        //设置PendingIntent
//
//
//        //为id为openActivity的view设置单击事件
//
//
//        //将RemoteView作为Notification的布局
//        builder.build().contentView = remoteViews;
        builder.setAutoCancel(true);
        builder.setContentTitle(name);
        builder.setContentText(content);
        // 需要VIBRATE权限
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.notification, pendingIntent);
        notificationManager.notify((int) ids, builder.build());
    }
}
