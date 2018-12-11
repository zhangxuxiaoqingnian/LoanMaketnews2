package com.smileflowpig.money.factory.util;

import android.text.TextUtils;
import android.util.Log;

import com.smileflowpig.money.factory.Factory;
import com.smileflowpig.money.factory.model.db.User;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.smileflowpig.money.factory.net.Network.channelId;

/**
 * 自定义拦截器
 * Created by xieshengqi on 2017/10/9.
 */

public class
LoginInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 拿到我们的请求
        Request original = chain.request();
        // 重新进行build
        Request.Builder builder = original.newBuilder();
//                        if (!TextUtils.isEmpty(Account.getToken())) {
//                            // 注入一个token
//                            builder.addHeader("token", Account.getToken());
//                        }
        long time = System.currentTimeMillis() / 1000;
        try {
//            String token = (String) SPUtil.get(Factory.app(),"deviceToken","");
            builder.addHeader("Content-Type", "application/json");
            builder.addHeader("User-Agent", "version=" + Factory.getVersionName(Factory.app()) +
                    "phone=" + android.os.Build.VERSION.RELEASE +
                    android.os.Build.MODEL + "channel=" + channelId);
            String a = "ttl=" + time + "&version=2.0&cid=" + channelId + "&device=Android&sign_key=91f8edf4792c8f63593266f75493a5f5";
            builder.addHeader("ttl", time + "");
            builder.addHeader("version", "2.0");
            builder.addHeader("cid", channelId);
            builder.addHeader("sign", md5(a));
            builder.addHeader("device", "Android");
//            if(!TextUtils.isEmpty(token))
//            builder.addHeader("token", token);


        } catch (Exception e) {
            e.printStackTrace();
        }
        Request newRequest = builder.build();
        Log.e("header",newRequest.headers().toString());
        Response proceed = chain.proceed(newRequest);
        // 返回
        return proceed;
    }

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 90001 || response.code() == 90002) {
            return true;
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return
     */
    private void reLoadLogin() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        LoginUtil loginUtil = LoginUtil.getInstance();
        loginUtil.reLoadLogin(new LoginUtil.OnLoadListener() {
            @Override
            public void onLoadSuccess(User user) {

            }

            @Override
            public void onLoadFail(int strRes) {

            }
        });
    }
}
