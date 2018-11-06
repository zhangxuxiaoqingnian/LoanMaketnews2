package com.smileflowpig.money.factory.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.smileflowpig.money.common.factory.data.DataSource;
import com.smileflowpig.money.factory.Constant;
import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.data.helper.AccountHelper;
import com.smileflowpig.money.factory.model.api.account.LoginByCodeModel;
import com.smileflowpig.money.factory.model.api.account.LoginModel;
import com.smileflowpig.money.factory.model.db.User;
import com.smileflowpig.money.factory.net.Network;
import com.smileflowpig.money.factory.net.RemoteService;


/**
 * 自动登录工具类
 * Created by xieshengqi on 2017/9/29.
 */

public class LoginUtil {

    private static LoginUtil mInstance;
    private Context context;

    private LoginUtil(Context context) {
        this.context = context;
    }

    public static LoginUtil getInstance() {
        if (mInstance == null)
            mInstance = new LoginUtil(Factory.app());
        return mInstance;
    }


    public void reLoadLogin(final OnLoadListener onLoadListener) {
        RemoteService service = Network.remote();
        // 得到一个Call
        String name = (String) SPUtil.get(context, Constant.UserInfo.USERNAME, "");
        String pwd = (String) SPUtil.get(context, Constant.UserInfo.PASSWORD, "");
        String codeToken = (String) SPUtil.get(context, Constant.UserInfo.CODETOKEN, "");
        if (TextUtils.isEmpty(pwd)) {
            //验证码登录
            AccountHelper.loginToCode(new LoginByCodeModel(name, codeToken), new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {

                    onLoadListener.onLoadFail(strRes);
                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null){
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                        SPUtil.putAndApply(Factory.app(),Constant.UserInfo.CODETOKEN,user.getUser_token());

                    }
                    onLoadListener.onLoadSuccess( user);
                }
            });
        } else {
            //手机登录
            LoginModel loginModel = new LoginModel(name, pwd);
            AccountHelper.login(loginModel, new DataSource.Callback<User>() {
                @Override
                public void onDataNotAvailable(@StringRes int strRes) {
                    onLoadListener.onLoadFail(strRes);

                }

                @Override
                public void onDataLoaded(User user) {
                    if(user!=null)
                        SPUtil.putAndApply(Factory.app(), Constant.UserInfo.SESSIONID, user.getPHPSESSID());
                    onLoadListener.onLoadSuccess(user);
                }
            });
        }
    }


    public interface OnLoadListener {
        void onLoadSuccess(User user);

        void onLoadFail(int errormsg);
    }

}
