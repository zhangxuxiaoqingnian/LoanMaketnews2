package com.smileflowpig.money.moneyplatfrom.util;


import android.text.TextUtils;

import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.util.SPUtil;
import com.smileflowpig.money.moneyplatfrom.App;


/**
 * 登录状态判断
 */
public class LoginStatusUtil {


    /**
     * 判断是否登陆
     *
     * @return
     */
    public static boolean isLogin() {
        boolean isExit = (boolean) SPUtil.get(App.context, Constant.UserInfo.ISEXITE, true);
        return isExit;
    }


}
