package com.smileflowpig.money.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.smileflowpig.money.factory.Factory;

/**
 * @author HFRX hfrx1314@qq.com
 * @version 1.0.0
 */
public class Account {
    private static final String KEY_PUSH_ID = "KEY_PUSH_ID";

    //设备的推送ID
    public static String pushId;

    /**
     * 存储数据到XML文件，持久化
     */
    private static void save(Context context){
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        sp.edit()
                .putString(KEY_PUSH_ID,pushId)
                .apply();
    }

    /**
     * 进行数据加载
     */
    public static void load(Context context){
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID,"");
    }
    /**
     * 设置并存储设备的ID
     *
     * @param pushId 设备的推送ID
     */
    public static void setPushId(String  pushId){
        Account.pushId = pushId;
        Account.save(Factory.app());
    }

    /**
     * 获取推送ID
     * @return  设备的推送ID
     */
    public static String getPushId(){
        return pushId;
    }

    /**
     * 返回当前账户是否是登录状态
     * @return True 已登录
     */
    public static boolean isLogin(){
        return true;
    }
}
