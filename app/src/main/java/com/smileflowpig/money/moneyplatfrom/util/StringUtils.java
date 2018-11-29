package com.smileflowpig.money.moneyplatfrom.util;

/**
 * Created by Allence on 2018/5/9 0009.
 */

public class StringUtils {

    public static boolean isEmpty(String str){
        return str == null || str.equals("");
    }



    public static String formatFloatNumber(float value) {
        if(value != 0.00){
            java.text.DecimalFormat df = new java.text.DecimalFormat("########.00");
            return df.format(value);
        }else{
            return "0.00";
        }

    }


}
