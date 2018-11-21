package com.smileflowpig.money.factory.util;


import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;


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
        boolean isExit = (boolean) SPUtil.get(Factory.app(), Constant.UserInfo.ISEXITE, true);
        return !isExit;
    }


}
