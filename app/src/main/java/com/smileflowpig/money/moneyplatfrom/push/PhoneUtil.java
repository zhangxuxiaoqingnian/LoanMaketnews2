package com.smileflowpig.money.moneyplatfrom.push;

import android.content.Context;
import android.os.Build;

/**
 * 手机机型判断
 */
public class PhoneUtil {
    public static final int XIAOMI = 1;
    public static final int VIVO = 2;
    public static final int OPPO = 3;
    public static final int HUAWEI = 4;
    public static final int SANXIN = 5;
    public static final int MEIZU = 6;
    public static final int OTHER = 7;

    private static String getPhoneMessage() {
        String brand = Build.BRAND;
        return brand;

    }

    /**
     * 获取手机型号
     *
     * @param context
     * @return
     */
    public static int getPhoneModel(Context context) {
        String phoneMessage = getPhoneMessage();
        if (phoneMessage.equals("HUAWEI")||phoneMessage.equals("HONOR")) {
            return HUAWEI;
        } else if (phoneMessage.equals("XIAOMI")) {
            return XIAOMI;
        } else if (phoneMessage.equals("VIVO")) {
            return VIVO;
        } else if (phoneMessage.equals("OPPO")) {
            return OPPO;
        } else {
            return OTHER;
        }

    }


}
